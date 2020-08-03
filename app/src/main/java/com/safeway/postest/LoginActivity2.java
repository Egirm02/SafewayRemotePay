package com.safeway.postest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
import com.safeway.postest.Data.model.BaseResponse;
import com.safeway.postest.Data.model.receipt.Data;
import com.safeway.postest.Data.remote.Service;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

public class LoginActivity2 extends AppCompatActivity {

    Button Login;
    private static final String TAG = "AzureLogin";
    private ISingleAccountPublicClientApplication mSingleAccountApp;
    private IAccount mAccount;
    String SCOPE = "user.read";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initAzureLib();
        Login = findViewById(R.id.cta_login);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  signIn();
                Intent intent = new Intent(getBaseContext(),Home.class);
                startActivity(intent);
            }
        });
    }

    public void signIn(){
        if (mSingleAccountApp == null) {
            return;
        }

        mSingleAccountApp.signIn(this, null, new String[]{SCOPE},  getAuthInteractiveCallback());
    }

    public void initAzureLib(){
        PublicClientApplication.createSingleAccountPublicClientApplication(this,
                R.raw.auth_config_single_account,
                new IPublicClientApplication.ISingleAccountApplicationCreatedListener() {
                    @Override
                    public void onCreated(ISingleAccountPublicClientApplication application) {
                        /**
                         * This test app assumes that the app is only going to support one account.
                         * This requires "account_mode" : "SINGLE" in the config json file.
                         **/
                        mSingleAccountApp = application;
                        loadAccount();
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
                // You can use the account data to update your UI or your app database.
                //TODO MOVE USER TO HOME ACTIVITY
                mAccount = activeAccount;
                Log.d(TAG, "onAccountLoaded: login"+ activeAccount);
                // updates the user account if they're already logged in.
               // updateUI();
            }

            @Override
            public void onAccountChanged(@Nullable IAccount priorAccount, @Nullable IAccount currentAccount) {
                if (currentAccount == null) {

                    Log.d(TAG, "onAccountChanged: " +currentAccount);

                    // Perform a cleanup task as the signed-in account changed.
                  //  showToastOnSignOut();
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
                /* Successfully got a token, use it to call a protected resource - MSGraph */
                Log.d(TAG, "Successfully authenticated");
                Log.d(TAG, "ID Token: " + authenticationResult.getAccount().getClaims().get("id_token"));
                Log.d(TAG, "onSuccess: "+authenticationResult);

                /* Update account */
                mAccount = authenticationResult.getAccount();
              //  updateUI();

//                Intent intent = new Intent(getBaseContext(),Home.class);
//                intent.putExtra("userName", mAccount.getAuthority());
//                startActivity(intent);

                /* call graph */
                callGraphAPI(authenticationResult);
            }

            @Override
            public void onError(MsalException exception) {
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

    private void displayError(@NonNull final Exception exception) {
        Toast.makeText(this, exception.toString(), Toast.LENGTH_SHORT).show();
        Log.d(TAG, "displayError: "+ exception.toString());
    }

    private void callGraphAPI(final IAuthenticationResult authenticationResult) {
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
                            msUserResponse.getGivenName();
                        Intent intent = new Intent(getBaseContext(),Home.class);
                        intent.putExtra("userName", msUserResponse.getGivenName().toString());
                        startActivity(intent);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }

                });
    }
}
