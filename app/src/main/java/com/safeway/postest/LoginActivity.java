package com.safeway.postest;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.microsoft.identity.client.AuthenticationCallback;
import com.microsoft.identity.client.IAccount;
import com.microsoft.identity.client.IAuthenticationResult;
import com.microsoft.identity.client.IMultipleAccountPublicClientApplication;
import com.microsoft.identity.client.IPublicClientApplication;
import com.microsoft.identity.client.ISingleAccountPublicClientApplication;
import com.microsoft.identity.client.PublicClientApplication;
import com.microsoft.identity.client.exception.MsalClientException;
import com.microsoft.identity.client.exception.MsalException;
import com.microsoft.identity.client.exception.MsalServiceException;
import com.safeway.postest.Data.MSUserResponse;
import com.safeway.postest.Data.NetworkManager;
import com.safeway.postest.Data.remote.Service;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    Button Login;
    ProgressBar mProgressbar;
    private static final String TAG = "AzureLogin";
    private ISingleAccountPublicClientApplication mSingleAccountApp;
    private IAccount mAccount;
//    private IMultipleAccountPublicClientApplication mMultipleAccountApp;
//    private List<IAccount> accountList;

    String SCOPE = "user.read";
    private String userName;


    String action;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        action = getIntent().getStringExtra("action");
        mProgressbar = findViewById(R.id.progressbar);
        Login = findViewById(R.id.cta_login);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
//                Intent intent = new Intent(getBaseContext(),Home.class);
//                startActivity(intent);
            }
        });

        initAzureLib();
    }

    public void signIn() {
        if (mSingleAccountApp == null) {
            return;
        }

        mProgressbar.setVisibility(View.VISIBLE);
        //  mSingleAccountApp.signIn(this, null, new String[]{SCOPE},  getAuthInteractiveCallback());
        mSingleAccountApp.getCurrentAccountAsync(new ISingleAccountPublicClientApplication.CurrentAccountCallback() {
            @Override
            public void onAccountLoaded(@Nullable IAccount activeAccount) {
                if (null == activeAccount) {
                    clearCookies();
                    mSingleAccountApp.signIn(
                            LoginActivity.this,
                            null,
                            new String[]{SCOPE},
                            getAuthInteractiveCallback());
                }
            }

            @Override
            public void onAccountChanged(@Nullable IAccount priorAccount, @Nullable IAccount currentAccount) {
            }

            @Override
            public void onError(@NonNull MsalException exception) {
            }
        });
    }

    public  void signOut(){
        if (mSingleAccountApp == null) {
            return;
        }

        mSingleAccountApp.signOut(new ISingleAccountPublicClientApplication.SignOutCallback() {
            @Override
            public void onSignOut() {
                Log.d(TAG, "onSignOut: Success"+ mAccount);

                mAccount = null;
                Util.deleteCache(LoginActivity.this);
                Util.saveString(LoginActivity.this, Util.USER_NAME, "");
                Login.setVisibility(View.VISIBLE);
                mProgressbar.setVisibility(View.GONE);

            }

            @Override
            public void onError(@NonNull MsalException exception) {
                Log.d(TAG, "onError: logout");
                displayError(exception);
            }
        });
    }

    public void initAzureLib(){
        PublicClientApplication.createSingleAccountPublicClientApplication(this,
                R.raw.auth_config_single_account,
                new IPublicClientApplication.ISingleAccountApplicationCreatedListener() {
                    @Override
                    public void onCreated(ISingleAccountPublicClientApplication application) {

                        mSingleAccountApp = application;

                        if("signout".equals(action)){
                            signOut();
                        }else {
                            loadAccount();
                        }
                    }

                    @Override
                    public void onError(MsalException exception) {
                        displayError(exception);
                    }
                });
    }

    private void loadAccount() {
        if (mSingleAccountApp == null) {
            return;
        }

        mSingleAccountApp.getCurrentAccountAsync(new ISingleAccountPublicClientApplication.CurrentAccountCallback() {
            @Override
            public void onAccountLoaded(@Nullable IAccount activeAccount) {

                mAccount = activeAccount;

//                if(mAccount != null && mAccount.getClaims() != null) {
//                    Log.d(TAG, "onAccountLoaded: login " + mAccount.getClaims().get("name"));
//                    gotoHomePage(mAccount.getClaims().get("name").toString());
//                }

               // String userName = Util.getString(LoginActivity.this, Util.USER_NAME, "");


                if(mAccount != null)
                userName = mAccount.getClaims().get("name").toString();
                if(!TextUtils.isEmpty(userName)) {
                    gotoHomePage(userName);

                }else {
                    Login.setVisibility(View.VISIBLE);
                    mProgressbar.setVisibility(View.GONE);
                }

                // updates the user account if they're already logged in.
               // updateUI();
               // activeAccount.getUsername();
                Log.d(TAG, "onAccountLoaded:  " );
            }

            @Override
            public void onAccountChanged(@Nullable IAccount priorAccount, @Nullable IAccount currentAccount) {
                if (currentAccount == null) {

                    Log.d(TAG, "onAccountChanged: " +currentAccount);

                    // Perform a cleanup task as the signed-in account changed.
                  //  showToastOnSignOut();

                    Util.saveString(getApplicationContext(), Util.USER_NAME, "");

                }
            }

            @Override
            public void onError(@NonNull MsalException exception) {
                displayError(exception);
                Log.d(TAG, "onError: "+ exception);
            }
        });
    }

    private AuthenticationCallback getAuthInteractiveCallback() {
        return new AuthenticationCallback() {

            @Override
            public void onSuccess(IAuthenticationResult authenticationResult) {
                mProgressbar.setVisibility(View.GONE);
                /* Successfully got a token, use it to call a protected resource - MSGraph */
                Log.d(TAG, "Successfully authenticated");
                Log.d(TAG, "ID Token: " + authenticationResult.getAccount().getClaims().get("id_token"));
                Log.d(TAG, "onSuccess: "+authenticationResult);

                /* Update account */
                mAccount = authenticationResult.getAccount();
              //  updateUI();

                mProgressbar.setVisibility(View.GONE);
                String userName =  mAccount.getClaims().get("name").toString();
                Util.saveString(LoginActivity.this, Util.USER_NAME,  userName);
                gotoHomePage(userName);

//                Intent intent = new Intent(getBaseContext(),Home.class);
//                intent.putExtra("userName", mAccount.getAuthority());
//                startActivity(intent);

                /* call graph */
               // callGraphAPI(authenticationResult);
            }

            @Override
            public void onError(MsalException exception) {
                mProgressbar.setVisibility(View.GONE);
                /* Failed to acquireToken */
                Log.d(TAG, "Authentication failed: " + exception.toString());
                displayError(exception);

                if (exception instanceof MsalClientException) {
                    /* Exception inside MSAL, more info inside MsalError.java */
                    Log.d(TAG, "onError: MsalClientException "+ exception);
                } else if (exception instanceof MsalServiceException) {
                    /* Exception when communicating with the STS, likely config issue */
                    Log.d(TAG, "onError: " + exception);
                }
            }

            @Override
            public void onCancel() {
                /* User canceled the authentication */
                Log.d(TAG, "User cancelled login.");
            }
        };
    }

    private void clearCookies() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            CookieManager.getInstance().removeAllCookies(null);
            CookieManager.getInstance().flush();
        } else {
            CookieSyncManager cookieSyncMngr = CookieSyncManager.createInstance(getApplicationContext());
            cookieSyncMngr.startSync();
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.removeAllCookie();
            cookieManager.removeSessionCookie();
            cookieSyncMngr.stopSync();
            cookieSyncMngr.sync();
        }
    }

    private void displayError(@NonNull final Exception exception) {
        Toast.makeText(this, exception.toString(), Toast.LENGTH_SHORT).show();
        Log.d(TAG, "displayError: "+ exception.toString());
    }

    private void callGraphAPI(final IAuthenticationResult authenticationResult) {
        mProgressbar.setVisibility(View.VISIBLE);
        Service apiService = NetworkManager.createRetrofit().create(Service.class);
        apiService.getMsAuthUserProfile( "Bearer "+authenticationResult.getAccessToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MSUserResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(MSUserResponse msUserResponse) {
                        mProgressbar.setVisibility(View.GONE);
                        Util.saveString(LoginActivity.this, Util.USER_NAME, msUserResponse.getGivenName());
                        gotoHomePage(msUserResponse.getGivenName());

                    }

                    @Override
                    public void onError(Throwable e) {
                        mProgressbar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onComplete() {

                    }

                });
    }


    private void gotoHomePage(String userName){
        Intent intent = new Intent(getBaseContext(),Home.class);
        intent.putExtra("userName", userName);
        startActivity(intent);
        finish();

    }
}



/*package com.safeway.postest;

        import android.content.Intent;
        import android.os.Bundle;
        import android.text.TextUtils;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ProgressBar;
        import android.widget.Toast;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;

        import com.microsoft.identity.client.AuthenticationCallback;
        import com.microsoft.identity.client.IAccount;
        import com.microsoft.identity.client.IAuthenticationResult;
        import com.microsoft.identity.client.IPublicClientApplication;
        import com.microsoft.identity.client.ISingleAccountPublicClientApplication;
        import com.microsoft.identity.client.PublicClientApplication;
        import com.microsoft.identity.client.exception.MsalClientException;
        import com.microsoft.identity.client.exception.MsalException;
        import com.microsoft.identity.client.exception.MsalServiceException;
        import com.safeway.postest.Data.MSUserResponse;
        import com.safeway.postest.Data.NetworkManager;
        import com.safeway.postest.Data.remote.Service;

        import io.reactivex.Observer;
        import io.reactivex.android.schedulers.AndroidSchedulers;
        import io.reactivex.disposables.Disposable;
        import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    Button Login;
    ProgressBar mProgressbar;
    private static final String TAG = "AzureLogin";
    private ISingleAccountPublicClientApplication mSingleAccountApp;
    private IAccount mAccount;
    String SCOPE = "user.read";
    private String userName;


    String action;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        action = getIntent().getStringExtra("action");
        mProgressbar = findViewById(R.id.progressbar);
        Login = findViewById(R.id.cta_login);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
//                Intent intent = new Intent(getBaseContext(),Home.class);
//                startActivity(intent);
            }
        });

        initAzureLib();
    }

    public void signIn(){
        if (mSingleAccountApp == null) {
            return;
        }

        mProgressbar.setVisibility(View.VISIBLE);
        mSingleAccountApp.signIn(this, null, new String[]{SCOPE},  getAuthInteractiveCallback());
    }

    public  void signOut(){
        if (mSingleAccountApp == null) {
            return;
        }

        mSingleAccountApp.signOut(new ISingleAccountPublicClientApplication.SignOutCallback() {
            @Override
            public void onSignOut() {
                mAccount = null;
                Util.saveString(LoginActivity.this, Util.USER_NAME, "");
                Login.setVisibility(View.VISIBLE);
                mProgressbar.setVisibility(View.GONE);

            }

            @Override
            public void onError(@NonNull MsalException exception) {
                displayError(exception);
            }
        });
    }

    public void initAzureLib(){
        PublicClientApplication.createSingleAccountPublicClientApplication(this,
                R.raw.auth_config_multiple_account,
                new IPublicClientApplication.ISingleAccountApplicationCreatedListener() {
                    @Override
                    public void onCreated(ISingleAccountPublicClientApplication application) {

                        mSingleAccountApp = application;

                        if("signout".equals(action)){
                            signOut();
                        }else {
                            loadAccount();
                        }
                    }

                    @Override
                    public void onError(MsalException exception) {
                        displayError(exception);
                    }
                });
    }

    private void loadAccount() {
        if (mSingleAccountApp == null) {
            return;
        }

        mSingleAccountApp.getCurrentAccountAsync(new ISingleAccountPublicClientApplication.CurrentAccountCallback() {
            @Override
            public void onAccountLoaded(@Nullable IAccount activeAccount) {

                mAccount = activeAccount;

//                if(mAccount != null && mAccount.getClaims() != null) {
//                    Log.d(TAG, "onAccountLoaded: login " + mAccount.getClaims().get("name"));
//                    gotoHomePage(mAccount.getClaims().get("name").toString());
//                }

                // String userName = Util.getString(LoginActivity.this, Util.USER_NAME, "");
                if(mAccount != null)
                    userName = mAccount.getClaims().get("name").toString();
                if(!TextUtils.isEmpty(userName)) {
                    gotoHomePage(userName);
                }else {
                    Login.setVisibility(View.VISIBLE);
                    mProgressbar.setVisibility(View.GONE);
                }

                // updates the user account if they're already logged in.
                // updateUI();
                // activeAccount.getUsername();
                Log.d(TAG, "onAccountLoaded:  " );
            }

            @Override
            public void onAccountChanged(@Nullable IAccount priorAccount, @Nullable IAccount currentAccount) {
                if (currentAccount == null) {

                    Log.d(TAG, "onAccountChanged: " +currentAccount);

                    // Perform a cleanup task as the signed-in account changed.
                    //  showToastOnSignOut();

                    Util.saveString(getApplicationContext(), Util.USER_NAME, "");

                }
            }

            @Override
            public void onError(@NonNull MsalException exception) {
                displayError(exception);
                Log.d(TAG, "onError: "+ exception);
            }
        });
    }

    private AuthenticationCallback getAuthInteractiveCallback() {
        return new AuthenticationCallback() {

            @Override
            public void onSuccess(IAuthenticationResult authenticationResult) {
                mProgressbar.setVisibility(View.GONE);
                *//* Successfully got a token, use it to call a protected resource - MSGraph *//*
                Log.d(TAG, "Successfully authenticated");
                Log.d(TAG, "ID Token: " + authenticationResult.getAccount().getClaims().get("id_token"));
                Log.d(TAG, "onSuccess: "+authenticationResult);

                *//* Update account *//*
                mAccount = authenticationResult.getAccount();
                //  updateUI();

                mProgressbar.setVisibility(View.GONE);
                String userName =  mAccount.getClaims().get("name").toString();
                Util.saveString(LoginActivity.this, Util.USER_NAME,  userName);
                gotoHomePage(userName);
//                Intent intent = new Intent(getBaseContext(),Home.class);
//                intent.putExtra("userName", mAccount.getAuthority());
//                startActivity(intent);

                *//* call graph *//*
                // callGraphAPI(authenticationResult);
            }

            @Override
            public void onError(MsalException exception) {
                mProgressbar.setVisibility(View.GONE);
                *//* Failed to acquireToken *//*
                Log.d(TAG, "Authentication failed: " + exception.toString());
                displayError(exception);

                if (exception instanceof MsalClientException) {
                    *//* Exception inside MSAL, more info inside MsalError.java *//*
                    Log.d(TAG, "onError: MsalClientException "+ exception);
                } else if (exception instanceof MsalServiceException) {
                    *//* Exception when communicating with the STS, likely config issue *//*
                    Log.d(TAG, "onError: " + exception);
                }
            }

            @Override
            public void onCancel() {
                *//* User canceled the authentication *//*
                Log.d(TAG, "User cancelled login.");
            }
        };
    }

    private void displayError(@NonNull final Exception exception) {
        Toast.makeText(this, exception.toString(), Toast.LENGTH_SHORT).show();
        Log.d(TAG, "displayError: "+ exception.toString());
    }

    private void callGraphAPI(final IAuthenticationResult authenticationResult) {
        mProgressbar.setVisibility(View.VISIBLE);
        Service apiService = NetworkManager.createRetrofit().create(Service.class);
        apiService.getMsAuthUserProfile( "Bearer "+authenticationResult.getAccessToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MSUserResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(MSUserResponse msUserResponse) {
                        mProgressbar.setVisibility(View.GONE);
                        Util.saveString(LoginActivity.this, Util.USER_NAME, msUserResponse.getGivenName());
                        gotoHomePage(msUserResponse.getGivenName());

                    }

                    @Override
                    public void onError(Throwable e) {
                        mProgressbar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onComplete() {

                    }

                });
    }


    private void gotoHomePage(String userName){
        Intent intent = new Intent(getBaseContext(),Home.class);
        intent.putExtra("userName", userName);
        startActivity(intent);
        finish();

    }
}*/

