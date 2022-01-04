package com.safeway.postest;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.safeway.postest.Data.NetworkManager;
import com.safeway.postest.Data.model.BaseResponse;
import com.safeway.postest.Data.model.Cart;
import com.safeway.postest.Data.model.Item;
import com.safeway.postest.Data.model.ItemIdRequest;
import com.safeway.postest.Data.model.ItemRequest;
import com.safeway.postest.Data.model.checkout.Data2;
import com.safeway.postest.Data.model.Item_ids_remove_list;
import com.safeway.postest.Data.model.receipt.Data;
import com.safeway.postest.Data.model.receipt.Receipt;
import com.safeway.postest.Data.remote.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

public class CartActivity extends Activity implements CartRecyclerViewAdapter.OnItemClickListener{

    ImageButton scanButton;
    Button checkoutButton;
    Button scanClubCard;
    RecyclerView cartRecyclerView;
    CartRecyclerViewAdapter cartRecyclerViewAdapter;
    TextView cartTotal;
    EditText etClubcard;
    LinearLayout checkoutLayout;
    ConstraintLayout loadingLayout;
    String getguid;
    String getstoreId;
    String getOrderId;

    Boolean deleteItemPressed = false;
    Boolean itemRemoved = false;
    Boolean needRecalculate= false;


    public Cart cartList;
    public List<Item> itemList;

    String estTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartTotal =findViewById(R.id.tv_cartTotal);
        scanButton = findViewById(R.id.Img_btn_scanBarcodeBtn);
        checkoutButton= findViewById(R.id.btn_checkout);
        checkoutLayout=findViewById(R.id.ll_checkout);
//        cartRecyclerView = findViewById(R.id.rv_cart);
//        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
   //        cartRecyclerViewAdapter = new CartRecyclerViewAdapter(this);
   //     cartRecyclerView.setAdapter(cartRecyclerViewAdapter);
        loadingLayout = findViewById(R.id.loadingLayout);
        scanClubCard = findViewById(R.id.btn_Scan_club_card);
        etClubcard = findViewById(R.id.et_Club_card_input);

        String getViewCart = getIntent().getStringExtra("viewCart");
        String recallNumber = getIntent().getStringExtra("viewCart");
        getguid = getIntent().getStringExtra("guid");
        getstoreId = getIntent().getStringExtra("storeId");
        getOrderId = getIntent().getStringExtra("orderId");
        try {
//            if (getViewCart.equals("true")){
//                viewCart("default","9879");
//            }
          //  viewCart(getguid,getstoreId,getOrderId);

        } catch (Exception e) {
            e.printStackTrace();
        }

//        try {
//            JSONObject  json_object = new JSONObject(getIntent().getStringExtra("receipt"));
//
//            Log.e(TAG, "Example Item: " + json_object.getString("receipt"));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

//        cartRecyclerView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                  //  viewCart("default","9879");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(CartActivity.this);
                integrator.setCaptureActivity(CaptureActivityPortrait.class);
                integrator.setOrientationLocked(false);
                integrator.initiateScan();
            }
        });
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             // getCheckout(getstoreId,getguid,getguid);
               checkoutDemo();
                if(deleteItemPressed || needRecalculate){
           //         getCheckout(getstoreId,getguid,getguid,getOrderId);
                }else{
                    checkoutDemo();
           //         needRecalculate =true;
                 //   getReceipt(getOrderId,getstoreId);
                }

                loadingLayout.setVisibility(View.VISIBLE);
              //  getReceipt(getOrderId);
//            Intent intent = new Intent(getBaseContext(),MainActivity.class);
//            intent.putExtra("checkoutTotal",estTotal);
//                startActivity(intent);
            }
        });

        scanClubCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(CartActivity.this);
                integrator.setCaptureActivity(CaptureActivityPortrait.class);
                integrator.setOrientationLocked(false);
                integrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                if(result.getContents().equals("410511246024")){
                    etClubcard.setText(result.getContents().toString());
                }else{
                if(result.getContents().length()>4){
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                getItemLookup2(result.getContents(),"UPCA","9879");
                } else {
                    Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                    getReceipt(result.getContents(),getstoreId);
                }
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void getItemLookup2(String scanCode, String upcType,String storeid) {
        Service apiService = NetworkManager.createRetrofit().create(Service.class);
        apiService.getItem(scanCode,upcType,storeid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse<Item>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(BaseResponse<Item> itemBaseResponse) {

                        try {
                            //  Log.d(TAG, "onNext: Button press"+data.getData().getItemCount());
//                            itemCount = data.getData().getItemCount();
//                            tv_TotalQuantity.setText(String.valueOf(itemCount));if
                            if(itemBaseResponse.getResponse()!=null){

                            Toast.makeText(CartActivity.this, itemBaseResponse.getResponse().getExtDescription(), Toast.LENGTH_SHORT).show();
                           ItemRequest itemRequest = new ItemRequest(itemBaseResponse.getResponse().getItemId(),1,itemBaseResponse.getResponse().getUpcType(),false);
                            addItemToCart("default",itemRequest,"9879");
                        }
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                            Log.d(TAG, "onNext: countError"+ e.getMessage());
                        }
//
                    }

                    @Override
                    public void onError(Throwable t) {
//                        LogUtil.printLogMessage("error response", t.getMessage());
                        Log.d(TAG, "handleError: error"+ t.toString());
                        Toast.makeText(CartActivity.this, "Error", Toast.LENGTH_SHORT).show();
//                        progressDialog.dismiss();
//                        tvName.setText("error :   " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    public void addItemToCart(String scanCode, ItemRequest upcType, String storeid) {
        Service apiService = NetworkManager.createRetrofit().create(Service.class);
        apiService.addItemToCart(scanCode,upcType,storeid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(BaseResponse baseResponse) {

                        try {
                            //  Log.d(TAG, "onNext: Button press"+data.getData().getItemCount());
//                            itemCount = data.getData().getItemCount();
//                            tv_TotalQuantity.setText(String.valueOf(itemCount));
                            Toast.makeText(CartActivity.this, "Added", Toast.LENGTH_SHORT).show();
                            viewCart(getguid,storeid,getOrderId);
                          //  viewCartItem("default",storeid);
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                            Log.d(TAG, "onNext: countError"+ e.getMessage());
                        }
//
                    }

                    @Override
                    public void onError(Throwable t) {
//                        LogUtil.printLogMessage("error response", t.getMessage());
                        Log.d(TAG, "handleError: error"+ t.toString());
                        Toast.makeText(CartActivity.this, "Error add", Toast.LENGTH_SHORT).show();
//                        progressDialog.dismiss();
//                        tvName.setText("error :   " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    public void removeItems(ItemIdRequest itemIdRequest, String storeId,String guid, String orderId, Boolean add){
        Service apiService = NetworkManager.createRetrofit().create(Service.class);
        apiService.deleteItemFromCart(itemIdRequest,storeId,guid,orderId,add)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(CartActivity.this, "Item Removed", Toast.LENGTH_SHORT).show();
                        deleteItemPressed = true;
                        itemRemoved = true;
                        needRecalculate =true;

                        viewCart(getguid,storeId, getOrderId);
                        loadingLayout.setVisibility(View.VISIBLE);
                        // getCheckout2(getstoreId,getguid,getguid);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });

    }

    public void viewCart(String guid, String storeid, String orderId) {
        Service apiService = NetworkManager.createRetrofit().create(Service.class);
        apiService.getCart(guid,storeid,orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse<Cart>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(BaseResponse<Cart> cartBaseResponse) {

                        try {
                            checkoutLayout.setVisibility(View.VISIBLE);
                            if(itemRemoved){
                                cartTotal.setText("Checkout for total");
                            }else{
                                cartTotal.setText("Total: $"+String.format("%.2f",cartBaseResponse.getResponse().getTotalPrice()));
                                cartTotal.setText("Total: $"+"129.00");

                            }

                            estTotal = String.valueOf(cartBaseResponse.getResponse().getTotalPrice());
                            Toast.makeText(CartActivity.this, "ViewCart", Toast.LENGTH_SHORT).show();
                            handleResultsCart(cartBaseResponse.getResponse().getData());
                            loadingLayout.setVisibility(View.GONE);
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                            Log.d(TAG, "onNext: countError"+ e.getMessage());
                        }
//
                    }

                    @Override
                    public void onError(Throwable t) {
//                        LogUtil.printLogMessage("error response", t.getMessage());
                        Log.d(TAG, "handleError: error"+ t.toString());
                        Toast.makeText(CartActivity.this, "Error add", Toast.LENGTH_SHORT).show();
//                        progressDialog.dismiss();
//                        tvName.setText("error :   " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    private void handleResultsCart(List<Item> items) {
        this.itemList = items;

        cartRecyclerViewAdapter.setData(items);
        cartRecyclerViewAdapter.notifyDataSetChanged();
        Toast.makeText(this, "ViewCarts", Toast.LENGTH_SHORT).show();

    }

    public void getCheckout(String storeId, String guid, String clubCard_nbr, String orderId ) {
        Service apiService = NetworkManager.createRetrofit().create(Service.class);
        apiService.getcloverCheckOut(storeId,guid,clubCard_nbr,orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .delay(6000,TimeUnit.MILLISECONDS)
                .subscribe(new Observer<BaseResponse<Data2>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(BaseResponse<Data2> checkoutResponse) {

                        try {
                            //  Log.d(TAG, "onNext: Button press"+data.getData().getItemCount());
//                            itemCount = data.getData().getItemCount();
//                            tv_TotalQuantity.setText(String.valueOf(itemCount));if
                            if(checkoutResponse.getResponse()!=null){
                                itemRemoved = false;
                                getReceipt(checkoutResponse.getResponse().getOrderId(),checkoutResponse.getResponse().getStoreId());


                            }
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                            Log.d(TAG, "onNext: countError"+ e.getMessage());
                        }
//
                    }

                    @Override
                    public void onError(Throwable t) {
//                        LogUtil.printLogMessage("error response", t.getMessage());
                        Log.d(TAG, "handleError: error"+ t.toString());
                        try {
                            Toast.makeText(CartActivity.this, "Error Receipt "+ t.getMessage(), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
//                        progressDialog.dismiss();
//                        tvName.setText("error :   " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    public void getCheckout2(String storeId, String guid, String clubCard_nbr, String orderId ) {
        Service apiService = NetworkManager.createRetrofit().create(Service.class);
        apiService.getcloverCheckOut(storeId,guid,clubCard_nbr, orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .delay(6000,TimeUnit.MILLISECONDS)
                .subscribe(new Observer<BaseResponse<Data2>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(BaseResponse<Data2> checkoutResponse) {

                        try {
//                              Log.d(TAG, "onNext: Button press"+data.getData().getItemCount());
//                            itemCount = data.getData().getItemCount();
//                            tv_TotalQuantity.setText(String.valueOf(itemCount));if
                            if(checkoutResponse.getResponse()!=null){
                                viewCart(getguid,getstoreId,getOrderId);
                            }
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                            Log.d(TAG, "onNext: countError"+ e.getMessage());
                        }
//
                    }

                    @Override
                    public void onError(Throwable t) {
//                        LogUtil.printLogMessage("error response", t.getMessage());
                        Log.d(TAG, "handleError: error"+ t.toString());
                        Toast.makeText(CartActivity.this, "Error Receipt "+ t.getMessage(), Toast.LENGTH_SHORT).show();
//                        progressDialog.dismiss();
//                        tvName.setText("error :   " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    public void checkoutDemo(){
        Toast.makeText(CartActivity.this, "Terminal: "+"123",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getBaseContext(),MainActivity.class);
        intent.putExtra("checkoutTotal","129.00");
        intent.putExtra("subTotal","129.00");
        intent.putExtra("taxTotal","0.00");
        intent.putExtra("appliedDiscountsTotal","00.0");
        intent.putExtra("ebtTotal","00.00");
        intent.putExtra("receipt","\\r\\n                                      \\r\\nTHANKS FOR SHOPPING SAFEWAY U CHECKOUT\\r\\n\\r\\n        PHARMACY         129.00 S\\r\\n        CRV SFTDK SNGL NTX      0.05 S\\r\\n        TAX                     0.00  \\r\\n   **** BALANCE                 129.00  \\r\\n                                      \\r\\n06/05/20 03:06 9879 196 878 8833      \\r\\n                                      \\r\\n--------------------------------------\\r\\nYOUR CASHIER TODAY WAS SCAN AND GO    \\r\\n--------------------------------------\\r\\n--------------------------------------\\r\\n Valued Customer                491211\\r\\n--------------------------------------\\r\\n   HOW WAS YOUR SHOPPING EXPERIENCE?  \\r\\n        WE VALUE YOUR FEEDBACK!       \\r\\n   ENTER TO WIN A $100.00 GIFT CARD   \\r\\nGO TO: www.safeway.com/survey         \\r\\n    ENTER THE SURVEY CODE BELOW:      \\r\\n        987906/0503:06196/878         \\r\\n    Thank you for shopping Safeway    \\r\\nFor just for U or Rewards questions\\r\\n   call 877-276-9637 or Safeway.com   \\r\\n                                      \\r\\n***                               ***\\r\\n\", \"order_id\" : \"6520\", \"suspend_barcode_value\" : \"100019600878\", \"suspendReceiptTrailer\" : \"\\r\\n                                      \\r\\n06/05/20 03:06 9879 196 878 8833      \\r\\n                                      \\r\\n--------------------------------------\\r\\nYOUR CASHIER TODAY WAS SCAN AND GO    \\r\\n--------------------------------------\\r\\n--------------------------------------\\r\\n Valued Customer                491211\\r\\n--------------------------------------\\r\\n   HOW WAS YOUR SHOPPING EXPERIENCE?  \\r\\n        WE VALUE YOUR FEEDBACK!       \\r\\n   ENTER TO WIN A $100.00 GIFT CARD   \\r\\nGO TO: www.safeway.com/survey         \\r\\n    ENTER THE SURVEY CODE BELOW:      \\r\\n        987906/0503:06196/878         \\r\\n    Thank you for shopping Safeway    \\r\\nFor just for U or Rewards questions\\r\\n   call 877-276-9637 or Safeway.com   \\r\\n                                      \\r\\n***                               ***\"");
        startActivity(intent);
    }

    public void getReceipt(String orderId,String storeId) {
        Service apiService = NetworkManager.createRetrofit().create(Service.class);
        apiService.getReceipt(orderId,storeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .repeatWhen(completed -> Observable.interval(2, TimeUnit.SECONDS))
                .takeUntil(receiptResponse -> receiptResponse.getErrorMessage() == null && (receiptResponse.getResponse().getTransactionStatus().equals("PAYMENT PENDING")))
                .subscribe(new Observer<BaseResponse<Data>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(BaseResponse<Data> receiptResponse) {

                        try {
                            //  Log.d(TAG, "onNext: Button press"+data.getData().getItemCount());
//                            itemCount = data.getData().getItemCount();
//                            tv_TotalQuantity.setText(String.valueOf(itemCount));if

                            if(receiptResponse.getResponse()!=null ){
                                if(receiptResponse.getResponse().getTransactionStatus().equals("PAYMENT PENDING")) {

                               /* Toast.makeText(CartActivity.this, "Terminal: "+receiptResponse.getResponse().getData().getTerminalNumber(), Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getBaseContext(),MainActivity.class);
                                intent.putExtra("checkoutTotal",receiptResponse.getResponse().getData().getReceiptJson().getReceiptTotalResult().getTotal().toString());
                                intent.putExtra("subTotal",receiptResponse.getResponse().getData().getReceiptJson().getReceiptTotalResult().getSubTotal().toString());
                                intent.putExtra("taxTotal",receiptResponse.getResponse().getData().getReceiptJson().getReceiptTotalResult().getTax().toString());
                                intent.putExtra("appliedDiscountsTotal",receiptResponse.getResponse().getData().getReceiptJson().getReceiptTotalResult().getTotalSavings().toString());
                                intent.putExtra("ebtTotal",receiptResponse.getResponse().getData().getReceiptJson().getReceiptTotalResult().getEbt().toString());
                                startActivity(intent);*/

                                    Toast.makeText(CartActivity.this, "Terminal: " + receiptResponse.getResponse().getTerminalNumber(), Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(getBaseContext(), MainActivity.class);

                                    intent.putExtra("checkoutTotal", receiptResponse.getResponse().getReceiptJson().getReceiptTotalResult().getTotal().toString());
                                    intent.putExtra("subTotal", receiptResponse.getResponse().getReceiptJson().getReceiptTotalResult().getSubTotal().toString());
                                    intent.putExtra("taxTotal", receiptResponse.getResponse().getReceiptJson().getReceiptTotalResult().getTax().toString());
                                    intent.putExtra("appliedDiscountsTotal", receiptResponse.getResponse().getReceiptJson().getReceiptTotalResult().getTotalSavings().toString());
                                    intent.putExtra("ebtTotal", receiptResponse.getResponse().getReceiptJson().getReceiptTotalResult().getEbt().toString());
                                   // intent.putExtra("recalculation", receiptResponse.getResponse().getIsRecalculationNeeded().toString());
                                    intent.putExtra("orderId", receiptResponse.getResponse().getOrderId().toString());
                                    intent.putExtra("storeId",receiptResponse.getResponse().getStoreId().toString());
                                    intent.putExtra("clubcard",receiptResponse.getResponse().getGuid().toString());
                                    intent.putExtra("receipt", receiptResponse.getResponse().getReceipt());

                                    startActivity(intent);
                                    loadingLayout.setVisibility(View.GONE);
                                }
                            }
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                            Log.d(TAG, "onNext: countError"+ e.getMessage());
                        }
//
                    }

                    @Override
                    public void onError(Throwable t) {
//                        LogUtil.printLogMessage("error response", t.getMessage());
                        Log.d(TAG, "handleError: error"+ t.toString());
                        Toast.makeText(CartActivity.this, "Error Receipt "+ t.getMessage(), Toast.LENGTH_SHORT).show();
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

    public void Checkout (Receipt receipt){

    }
    @Override
    public void onItemClick(int position, Boolean deleteItem) {
        Item cartItem = itemList.get(position);
      //  if (deleteItem){
//        Item_ids_remove_list item_ids_remove_list = new Item_ids_remove_list();
//        item_ids_remove_list.setItem_id(cartItem.getItemId());
//        item_ids_remove_list.setQuantity(cartItem.getSellMultiple().toString());
//        List<Item_ids_remove_list> ids = Collections.singletonList(item_ids_remove_list);
//        ItemIdRequest itemIdRequest = new ItemIdRequest(ids);
         //   List<List> ids = Arrays.asList(cartItem.getItemId(), cartItem.getExtendedPrice());

      //  {"item_ids":[ ["0001600046684","2"], ["000160004","4"] ]}
      //  {"item_ids":[["0002200001475"],[3.79]]}
        try {
            List<List<Object>> listOfMixedTypes = new ArrayList<>();
            List<Object> newList = new ArrayList<>();
            newList.add(cartItem.getItemId());
            newList.add(cartItem.getSellMultiple());
            newList.add(cartItem.getItemNum());
            listOfMixedTypes.add(newList);


            ItemIdRequest itemIdRequest = new ItemIdRequest(listOfMixedTypes);
           removeItems(itemIdRequest, getstoreId, getguid, getOrderId,cartItem.getRemovedItem());

        } catch (Exception e) {
            e.printStackTrace();
        }
        //   }
    }

    @Override
    protected void onResume() {
         //   viewCart(getguid,getstoreId,getOrderId);
        super.onResume();
    }

    //    public void getItemLookup(String scanCode, String upcType,String storeid){
//
//        Service apiService = NetworkManager.createRetrofit().create(Service.class);
//
//        Observable<BaseResponse<Item>> feedbackObservable = apiService.getItem(scanCode,upcType,storeid) ;
//
//        Observable.wrap(feedbackObservable)
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(this::handleResults, this::handleError);
//    }
//
//    private void handleResults(BaseResponse<Item> itemBaseResponse) {
//        try {
//            //  Log.d(TAG, "onNext: Button press"+data.getData().getItemCount());
////                            itemCount = data.getData().getItemCount();
////                            tv_TotalQuantity.setText(String.valueOf(itemCount));
//            Toast.makeText(CartActivity.this, itemBaseResponse.getResponse().getExtDescription(), Toast.LENGTH_SHORT).show();
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//            Log.d(TAG, "onNext: countError"+ e.getMessage());
//        }
//    }
//
//    private void handleError(Throwable t) {
//        Toast.makeText(CartActivity.this, "Error", Toast.LENGTH_SHORT).show();
//    }
}
