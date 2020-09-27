package com.safeway.postest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.safeway.postest.Data.NetworkManager;
import com.safeway.postest.Data.model.BaseResponse;
import com.safeway.postest.Data.model.Cart;
import com.safeway.postest.Data.model.profile.ProfileRequest;
import com.safeway.postest.Data.model.Stores;
import com.safeway.postest.Data.model.profile.DataProfile;
import com.safeway.postest.Data.model.receipt.Data;
import com.safeway.postest.Data.model.receipt.Receipt;
import com.safeway.postest.Data.model.retreaveOrder.Data3;
import com.safeway.postest.Data.remote.Service;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

public class Home extends AppCompatActivity {
    Button startNewTransaction, recallTransaction, showClubCard, scanClubCard;
    FrameLayout clubCardlayout;
    TextView manualEntryTitle;
    TextView welcomeUserName;
    EditText etClubcard;
    EditText etStoreId;
    String retrieveOrder;
    String storeId;
    Button btn_submit_recall;
    ConstraintLayout loadingLayout;
    double longitude;
    double latitude;

    long time;
    long expireTime;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        startNewTransaction = findViewById(R.id.btn_StartNew);
        recallTransaction = findViewById(R.id.btn_RecallTransaction);
        showClubCard = findViewById(R.id.btn_show_club_card);
        scanClubCard = findViewById(R.id.btn_Scan_club_card);
        clubCardlayout = findViewById(R.id.fl_club_card);
        etClubcard = findViewById(R.id.et_retreave_transaction);
        manualEntryTitle = findViewById(R.id.tv_manualEntryTitle);
        loadingLayout = findViewById(R.id.loadingLayoutHome);
        btn_submit_recall = findViewById(R.id.btn_submit_recall);
        welcomeUserName = findViewById(R.id.tv_welcomeTextView);
        etStoreId = findViewById(R.id.et_storeId);

        startNewTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), CartActivity.class);
                startActivity(intent);
            }
        });

        recallTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getBaseContext(), CartActivity.class);
//                startActivity(intent);
                //Real
//                if (etStoreId.getText().toString().equals("")) {
//                    Toast.makeText(Home.this, "Please set store number first", Toast.LENGTH_SHORT).show();
//                } else {
//                    IntentIntegrator integrator = new IntentIntegrator(Home.this);
//                    integrator.setCaptureActivity(CaptureActivityPortrait.class);
//                    integrator.setOrientationLocked(false);
//                    integrator.initiateScan();
//                }
                //Demo
//                String retrieve = "100019900017" ;// result.getContents().toString();
//                if(retrieve.length()> 12){
//                    retrieveOrder(retrieve.substring(0,12),etStoreId.getText().toString());
//                }else{retrieveOrder(retrieve,etStoreId.getText().toString());}
//                    loadingLayout.setVisibility(View.VISIBLE);
                //Demo2
                loadingLayout.setVisibility(View.GONE);
               // Toast.makeText(Home.this, "Terminal: " + receiptResponse.getResponse().getTerminalNumber(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(), CartActivity.class);
                intent.putExtra("guid", "00");
                intent.putExtra("storeId", "00");
                intent.putExtra("orderId", "00");
                startActivity(intent);
            }
        });
        showClubCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                manualEntryTitle.setVisibility(View.VISIBLE);
                showClubCard.setVisibility(View.GONE);
                clubCardlayout.setVisibility(View.VISIBLE);
            }
        });

        btn_submit_recall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etStoreId.getText().toString().equals("")) {
                    Toast.makeText(Home.this, "Please set store number first", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        String retreave = etClubcard.getText().toString();
                        if (retreave.length() > 12) {
                            retrieveOrder(retreave.substring(0, 12), storeId);
                        } else {
                            retrieveOrder(retreave, storeId);
                        }
                        loadingLayout.setVisibility(View.VISIBLE);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

   //     userName = getIntent().getStringExtra("userName");
        userName = Util.getString(getApplicationContext(), Util.USER_NAME, "");
        //understand the blank
        storeId = Util.getString(getApplicationContext(), Util.STORE_ID, "");
        if (userName != null) {
            welcomeUserName.setText("Welcome Associate, " + userName);
        }
        if (storeId != null) {
            etStoreId.setText(storeId);
        }

        if(etStoreId.getText().toString().equals("")){
            Toast.makeText(this, "Please enter store number", Toast.LENGTH_SHORT).show();
        }
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        time = System.currentTimeMillis();
        expireTime = System.currentTimeMillis() + 2000000;
//        scanClubCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                IntentIntegrator integrator = new IntentIntegrator(Home.this);
//                integrator.setCaptureActivity(CaptureActivityPortrait.class);
//                integrator.setOrientationLocked(false);
//                integrator.initiateScan();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item1:
                Intent intent = new Intent(this, LoginActivity.class);
                intent.putExtra("action", "signout");
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                //  etClubcard.setText(result.getContents().toString());
//                Intent intent = new Intent(getBaseContext(), CartActivity.class);
//                intent.putExtra("viewCart","true");
//                intent.putExtra("recallapi",result.getContents().toString());
//                startActivity(intent);
                String retrieve = result.getContents().toString();  //"1000099000416"


                    try {
                        if (etStoreId.getText() != null) {

                            storeId = etStoreId.getText().toString();
                            Util.saveString(Home.this, Util.STORE_ID,  storeId);

                        }
                        if (retrieve.length() > 12) {
                            retrieveOrder(retrieve.substring(0, 12), storeId);
                        } else {
                            retrieveOrder(retrieve, storeId);
                        }
                        loadingLayout.setVisibility(View.VISIBLE);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void retrieveOrder(String retrieveOrder, String storeId) {
        Service apiService = NetworkManager.createRetrofit().create(Service.class);
        apiService.getRetrieveOrder(retrieveOrder, storeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse<Data3>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(BaseResponse<Data3> data3BaseResponse) {
                        if (!data3BaseResponse.getAck().equals("1")) {
                            if (data3BaseResponse.getResponse() != null && data3BaseResponse.getResponse().getOrderId() != null) {
                                String orderid = data3BaseResponse.getResponse().getOrderId().toString();
                                getReceipt(orderid, storeId);
                            }
                        } else {
                            loadingLayout.setVisibility(View.GONE);
                            // getReceipt("6904");
                            Toast.makeText(Home.this, data3BaseResponse.getErrorMessage().get(0).getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
//                        LogUtil.printLogMessage("error response", t.getMessage());
                        Log.d(TAG, "handleError: error" + t.toString());
                        loadingLayout.setVisibility(View.GONE);
                        // Toast.makeText(CartActivity.this, "Error add", Toast.LENGTH_SHORT).show();
//                        progressDialog.dismiss();
//                        tvName.setText("error :   " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    public void getReceipt(String orderId, String storeId) {
        Service apiService = NetworkManager.createRetrofit().create(Service.class);
        long time = System.currentTimeMillis();
        long expireTime = System.currentTimeMillis() + 2000000;
        Log.d(TAG, "getReceipt: " + "time " + time + "exptime " + expireTime);
//TODO fix expire time
        apiService.getReceipt(orderId, storeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .repeatWhen(completed -> Observable.interval(2, TimeUnit.SECONDS))
                //check for payment pending > transaction void or retreave failed
                .takeUntil(receiptResponse -> receiptResponse.getAck().equals("0") || receiptResponse.getResponse() != null)
                .subscribe(new Observer<BaseResponse<Data>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }
                    // PP 20 (check tv,error) ->Vc ((check tv or rf) or rf) (return some Error msg)

                    //polling ---     payment pending || transaction void || rf  > ---> 20seconds  (check retrival failed > show generic error)
                    @Override
                    public void onNext(BaseResponse<Data> receiptResponse) {

                        try {
                            //  Log.d(TAG, "onNext: Button press"+data.getData().getItemCount());
//                            itemCount = data.getData().getItemCount();
//                            tv_TotalQuantity.setText(String.valueOf(itemCount));if

                            if (receiptResponse.getResponse() != null) {
                                //      Data receipt = receiptResponse.getResponse();
                               /* Toast.makeText(CartActivity.this, "Terminal: "+receiptResponse.getResponse().getData().getTerminalNumber(), Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getBaseContext(),MainActivity.class);
                                intent.putExtra("checkoutTotal",receiptResponse.getResponse().getData().getReceiptJson().getReceiptTotalResult().getTotal().toString());
                                intent.putExtra("subTotal",receiptResponse.getResponse().getData().getReceiptJson().getReceiptTotalResult().getSubTotal().toString());
                                intent.putExtra("taxTotal",receiptResponse.getResponse().getData().getReceiptJson().getReceiptTotalResult().getTax().toString());
                                intent.putExtra("appliedDiscountsTotal",receiptResponse.getResponse().getData().getReceiptJson().getReceiptTotalResult().getTotalSavings().toString());
                                intent.putExtra("ebtTotal",receiptResponse.getResponse().getData().getReceiptJson().getReceiptTotalResult().getEbt().toString());
                                startActivity(intent);*/

                                if (receiptResponse.getResponse().getTransactionStatus().equals("TRANSACTION VOID")) {
                                    Toast.makeText(Home.this, "TRANSACTION VOID", Toast.LENGTH_SHORT).show();
                                    loadingLayout.setVisibility(View.GONE);
                                } else if (expireTime > System.currentTimeMillis() && receiptResponse.getResponse().getTransactionStatus().equals("RETRIEVE FAILED")) {
                                    Toast.makeText(Home.this, "RETRIEVE FAILED", Toast.LENGTH_SHORT).show();
                                    loadingLayout.setVisibility(View.GONE);
                                } else if (receiptResponse.getResponse().getTransactionStatus().equals("PAYMENT PENDING")) {
                                    loadingLayout.setVisibility(View.GONE);
                                    Toast.makeText(Home.this, "Terminal: " + receiptResponse.getResponse().getTerminalNumber(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getBaseContext(), CartActivity.class);
                                    intent.putExtra("guid", receiptResponse.getResponse().getGuid());
                                    intent.putExtra("storeId", receiptResponse.getResponse().getStoreId());
                                    intent.putExtra("orderId", orderId);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(Home.this, "Generic Error", Toast.LENGTH_SHORT).show();
                                    loadingLayout.setVisibility(View.GONE);
                                    //  intent.putExtra("receipt",receipt);
                                }
                            }
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                            Log.d(TAG, "onNext: countError" + e.getMessage());
                        }
//
                    }

                    @Override
                    public void onError(Throwable t) {
//                        LogUtil.printLogMessage("error response", t.getMessage());
                        Log.d(TAG, "handleError: error" + t.toString());
                        Toast.makeText(Home.this, "Error Receipt " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        loadingLayout.setVisibility(View.GONE);
//                        progressDialog.dismiss();
//                        tvName.setText("error :   " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

//    public void getProfile(ProfileRequest profileRequest) {
//        Service apiService = NetworkManager.createRetrofit().create(Service.class);
//        long time = System.currentTimeMillis();
//        long expireTime = System.currentTimeMillis() + 2000000;
//        Log.d(TAG, "getReceipt: " + "time " + time + "exptime " + expireTime);
//
//        apiService.getProfile(profileRequest)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .repeatWhen(completed -> Observable.interval(2, TimeUnit.SECONDS))
//                //check for payment pending > transaction void or retreave failed
//                .takeUntil(profileResponse -> profileResponse.getAck().equals("0") || profileResponse.getResponse() != null)
//                .subscribe(new Observer<BaseResponse<DataProfile>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        Log.d(TAG, "onSubscribe: ");
//                    }
//                    // PP 20 (check tv,error) ->Vc ((check tv or rf) or rf) (return some Error msg)
//
//                    //polling ---     payment pending || transaction void || rf  > ---> 20seconds  (check retrival failed > show generic error)
//                    @Override
//                    public void onNext(BaseResponse<DataProfile> profileResponse) {
//
//                        try {
//
//                            if (profileResponse.getResponse() != null) {
//
//                                loadingLayout.setVisibility(View.GONE);
//
//                                if (profileResponse.getResponse().getStores() != null) {
//                                    Toast.makeText(Home.this, "Got StoreId", Toast.LENGTH_SHORT).show();
//                                    storeId = profileResponse.getResponse().getStores().getStoreId();
//                                    etStoreId.setText(storeId);
//                                    etStoreId.clearFocus();
//
//                                }
//
//                            }
//                        } catch (NullPointerException e) {
//                            e.printStackTrace();
//                            Log.d(TAG, "onNext: countError" + e.getMessage());
//                        }
////
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
////                        LogUtil.printLogMessage("error response", t.getMessage());
//                        Log.d(TAG, "handleError: error" + t.toString());
//                        Toast.makeText(Home.this, "Error Receipt " + t.getMessage(), Toast.LENGTH_SHORT).show();
//                        loadingLayout.setVisibility(View.GONE);
////                        progressDialog.dismiss();
////                        tvName.setText("error :   " + t.getMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d(TAG, "onComplete: ");
//                    }
//                });
//    }
//
//    public void getLocation() {
//        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//        try {
//            longitude = location.getLongitude();
//            latitude = location.getLatitude();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        final LocationListener locationListener = new LocationListener() {
//            public void onLocationChanged(Location location) {
//                longitude = location.getLongitude();
//                latitude = location.getLatitude();
//            }
//
//            @Override
//            public void onStatusChanged(String s, int i, Bundle bundle) {
//
//            }
//
//            @Override
//            public void onProviderEnabled(String s) {
//
//            }
//
//            @Override
//            public void onProviderDisabled(String s) {
//
//            }
//        };
//
//
//        if(longitude == 0 || latitude == 0){
//             lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListener);
//         }
//
//
//         try {
//            ProfileRequest profileRequest = new ProfileRequest("safeway",-121.815,"scanandgo","android","Eyoha.g@gmail.com",37.706,"test111",  "1.0.0");
//            getProfile(profileRequest);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }






}
