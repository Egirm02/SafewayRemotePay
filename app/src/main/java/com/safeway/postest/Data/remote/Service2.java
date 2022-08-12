package com.safeway.postest.Data.remote;


import com.safeway.postest.Data.MSUserResponse;
import com.safeway.postest.Data.RecalculateTransaction;
import com.safeway.postest.Data.model.BaseResponse;
import com.safeway.postest.Data.model.COA_Request;
import com.safeway.postest.Data.model.COA_Response;
import com.safeway.postest.Data.model.Cart;
import com.safeway.postest.Data.model.CheckoutResponse;
import com.safeway.postest.Data.model.FinalizeSplitTransaction;
import com.safeway.postest.Data.model.Item;
import com.safeway.postest.Data.model.ItemIdRequest;
import com.safeway.postest.Data.model.ItemRequest;
import com.safeway.postest.Data.model.profile.DataProfile;
import com.safeway.postest.Data.model.profile.ProfileRequest;
import com.safeway.postest.Data.model.ReceiptResponse;
import com.safeway.postest.Data.model.Stores;
import com.safeway.postest.Data.model.checkout.Checkout;
import com.safeway.postest.Data.model.checkout.Data2;
import com.safeway.postest.Data.model.receipt.Data;
import com.safeway.postest.Data.model.receipt.Receipt;
import com.safeway.postest.Data.model.retreaveOrder.Data3;


import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

//import com.journaldev.rxjavaretrofit.pojo.ScannedResponse;
//import com.journaldev.rxjavaretrofit.pojo.UserInStoreResponse;

public interface Service2 {


    // String BASE_URL = "https://api.cryptonator.com/api/full/";
    String BASE_URL = "https://retail-api.azure-api.net/";
    String ENVIRONMENT = "Dev";

//     static String Env(){
//        if(ENVIRONMENT == "scanandgodev/orderLookUp?orderId="){
//            return  "";
//        } else
//            {  return "Dev";}
//    };

//    @GET("{coin}-usd")
//    Observable<Crypto> getCoinData(@Path("coin") String coin);

    //Prod key 703f24b413024248b302a2f16ab5a0fe
    // scanandgoprod
    //Dev key 78ae78149bff429789d33a7b15c32437
    // scanandgodev
    //QA key 8de2cd8ae8b449aaa054f34a024c507b  2adea0f3833f433984136e291fbab38b
    // scanandgoqa

    @GET("scanandgoprod/checkOutCart")
    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "Ocp-Apim-Subscription-Key: 703f24b413024248b302a2f16ab5a0fe",
            "x-swy-client-id: clover"
    })
    Observable<BaseResponse<CheckoutResponse>> checkoutCart(@Header("storeid") String storeid,@Header("guid") String guid,@Header("clubcard_nbr") String clubcard_nbr);


    @GET("scanandgoprod/getReceipt?orderId=")
    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "Ocp-Apim-Subscription-Key: 703f24b413024248b302a2f16ab5a0fe",
    })
    Observable<BaseResponse<ReceiptResponse>> getReceiptSnP(@Query("orderid") String orderid);

    @POST("scanandgoprod/finalizeSplitTransaction")
    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "Ocp-Apim-Subscription-Key: 703f24b413024248b302a2f16ab5a0fe"
    })
    Observable<BaseResponse> finalizeCallSnP(@Body FinalizeSplitTransaction finalizeSplitTransaction, @Header("storeid") String storeId);

    //Prod key 7e0105e413484c059127085e72aed41f
    //scanandgoprodjs
    //Dev key 2adea0f3833f433984136e291fbab38b
    //scanandgodevjs
    //Dev key 78ae78149bff429789d33a7b15c32437
    //scanandgoemp
    // QA key 7024f91451d74393bbf891483210dc28  8de2cd8ae8b449aaa054f34a024c507b
    //  scanandgoQaJS/auditCart

    @GET("scanandgoprodjs/itemLookup?")
    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "Ocp-Apim-Subscription-Key: 7e0105e413484c059127085e72aed41f",
    })
    Observable<BaseResponse<Item>> getItem(@Query("scan_code") String code, @Query("upc_type") String upcType, @Query("storeid") String storeid);

    @PUT("scanandgoprodjs/addItemToCart")
    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "Ocp-Apim-Subscription-Key: 7e0105e413484c059127085e72aed41f",

    })
    Observable<BaseResponse> addItemToCart(@Query("clientId") String clientId, @Body ItemRequest itemRequest, @Header("storeid") String storeid,@Header("guid") String guid);

    @PUT("scanandgoprodjs/removeItems")
    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "Ocp-Apim-Subscription-Key: 7e0105e413484c059127085e72aed41f"})
    Completable deleteItemsFromCart(@Query("clientId") String clientId, @Body ItemIdRequest itemIds, @Header("storeid") String storeid,@Header("guid") String guid);



    @GET("scanandgoprodjs/viewCart")
    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "Ocp-Apim-Subscription-Key: 7e0105e413484c059127085e72aed41f",
            "app: sng",
            "version: 3.1.2",
            "clubcard_nbr: 49236827917"


    })
    Observable<BaseResponse<Cart>> getCart(@Header("guid") String guid, @Header("storeid") String storeid);

    @GET("retailoperations/phone/status")
    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "Ocp-Apim-Subscription-Key: c3306584cdd2499c92e2594e48eba542"
    })
    Observable<BaseResponse> getPhoneStatus(@Header("number")String number);
//////////////
    @PUT("cloverPaymentDev/removeItems")
    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "Ocp-Apim-Subscription-Key: 2f480da761e145f081a04a1e8ac0f59a",
    })
    Completable deleteItemFromCart(@Body ItemIdRequest itemIds, @Header("storeid") String storeid,@Header("guid") String guid,@Header("orderId") String orderId,@Header("add") Boolean add);

    //cloverPaymentProd/getReceipt
    //7024f91451d74393bbf891483210dc28
    //cloverPaymentDev/getReceipt
    //2f480da761e145f081a04a1e8ac0f59a
    @GET("cloverPaymentProd/getReceipt")
    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "Ocp-Apim-Subscription-Key: 7024f91451d74393bbf891483210dc28",


    })
    Observable<BaseResponse<Data>> getReceipt(@Query("orderId") String orderId,@Header("storeid") String storeId);

    //cloverBackendProd/cloverCheckOutCart
    //7024f91451d74393bbf891483210dc28
    //cloverBackendDev/cloverCheckOutCart
    //2f480da761e145f081a04a1e8ac0f59a
    @GET("cloverBackendProd/cloverCheckOutCart")
    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "Ocp-Apim-Subscription-Key: 7024f91451d74393bbf891483210dc28",

    })
    Observable<BaseResponse<Data2>> getcloverCheckOut(@Header("storeid") String storeId,@Header("guid") String guid,@Header("clubcard_nbr") String clubcard_nbr,@Header("orderId") String orderId);

    //cloverBackendProd/retrieveOrder
    //7024f91451d74393bbf891483210dc28
    //cloverBackendDev/retrieveOrder
    //2f480da761e145f081a04a1e8ac0f59a
    @GET("cloverBackendProd/retrieveOrder")
    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "Ocp-Apim-Subscription-Key: 7024f91451d74393bbf891483210dc28",

    })
    Observable<BaseResponse<Data3>> getRetrieveOrder(@Header("suspend_barcode") String orderId,@Header("storeid") String storeId);

    //cloverBackendProd/recalculateTransaction
    //7024f91451d74393bbf891483210dc28
    //cloverBackendDev/recalculateTransaction
    //2f480da761e145f081a04a1e8ac0f59a
    @POST("cloverBackendProd/recalculateTransaction")
    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "Ocp-Apim-Subscription-Key: 7024f91451d74393bbf891483210dc28"
    })
    Observable<BaseResponse> recalculateCall(@Body RecalculateTransaction recalculateRequest);

    //cloverBackendProd/finalizeSplitTransaction
    //7024f91451d74393bbf891483210dc28
    //cloverBackendDev/finalizeSplitTransaction
    //2f480da761e145f081a04a1e8ac0f59a
    @POST("cloverBackendProd/finalizeSplitTransaction")
    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "Ocp-Apim-Subscription-Key: 7024f91451d74393bbf891483210dc28"
    })
    Observable<BaseResponse> finalizeCall(@Body FinalizeSplitTransaction finalizeSplitTransaction);

    //cloverPaymentProd/refundToCOA
    //7024f91451d74393bbf891483210dc28
    //cloverPaymentDev/refundToCOA
    //2f480da761e145f081a04a1e8ac0f59a
    @POST("cloverPaymentProd/refundToCOA")
    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "Ocp-Apim-Subscription-Key: 7024f91451d74393bbf891483210dc28"
    })
    Observable<BaseResponse<COA_Response>> processCOA(@Body COA_Request coaRequest);

    @GET("v1.0/me")
    @Headers({"Accept: application/json",
            "Content-Type: application/json"
    })
    Observable<MSUserResponse> getMsAuthUserProfile(@Header("Authorization") String authorization);


    @POST("/retailoperationsdev/getProfile")
    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "Ocp-Apim-Subscription-Key: 78ae78149bff429789d33a7b15c32437"
    })
    Observable<BaseResponse<DataProfile>> getProfile(@Body ProfileRequest profileRequest);
//    @GET("scanandgoDevJS/auditCart?")
//    @Headers({"Accept: application/json",
//            "Content-Type: application/json",
//            "Ocp-Apim-Subscription-Key: 2adea0f3833f433984136e291fbab38b"
//
//    })
//    Observable<UserInStoreResponse> getUserInStoreData(@Query("store_id") Integer store_id);
//
//    @GET("scanandgoDevJS/auditCart")
//    @Headers({"Accept: application/json",
//            "Content-Type: application/json",
//            "Ocp-Apim-Subscription-Key: 2adea0f3833f433984136e291fbab38b"
//
//    })
//    Observable<UserInStoreResponse> setOrderId(@Query("store_id") Integer store_id, @Query("order_id") Integer order_id);


//    @GET("scanandgodev/orderLookUp?orderId=")
//    @Headers({"Accept: application/json",
//            "Content-Type: application/json",
//            "Ocp-Apim-Subscription-Key: 78ae78149bff429789d33a7b15c32437",
//    })
//    Single<ScannedResponse> getOrderData(@Query("orderid") String orderid);
}
