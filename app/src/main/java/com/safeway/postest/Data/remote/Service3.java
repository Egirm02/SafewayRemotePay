package com.safeway.postest.Data.remote;


import com.safeway.postest.Data.MSUserResponse;
import com.safeway.postest.Data.RecalculateTransaction;
import com.safeway.postest.Data.model.BaseResponse;
import com.safeway.postest.Data.model.COA_Request;
import com.safeway.postest.Data.model.COA_Response;
import com.safeway.postest.Data.model.Cart;
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

public interface Service3 {


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


    @GET("scanandgodev/getReceipt?orderId=")
    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "Ocp-Apim-Subscription-Key: 78ae78149bff429789d33a7b15c32437",
    })
    Observable<ReceiptResponse> getCoinData(@Query("orderid") String orderid);

    @GET("scanandgoemp/itemLookup?")
    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "Ocp-Apim-Subscription-Key: 78ae78149bff429789d33a7b15c32437",
    })
    Observable<BaseResponse<Item>> getItem(@Query("scan_code") String code, @Query("upc_type") String upcType, @Query("storeid") String storeid);

    @PUT("scanandgoemp/addItemToCart")
    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "Ocp-Apim-Subscription-Key: 78ae78149bff429789d33a7b15c32437",
            "GUID:200-160-1531871357979",

    })
    Observable<BaseResponse> addItemToCart(@Query("clientId") String clientId, @Body ItemRequest itemRequest, @Header("storeid") String storeid);

    //cloverPaymentProd/removeItems
    //7024f91451d74393bbf891483210dc28
    //cloverPaymentDev/removeItems
    //2f480da761e145f081a04a1e8ac0f59a
    @PUT("cloverPaymentProd/removeItems")
    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "Ocp-Apim-Subscription-Key: 7024f91451d74393bbf891483210dc28",
    })
    Completable deleteItemFromCart(@Body ItemIdRequest itemIds, @Header("storeid") String storeid, @Header("guid") String guid, @Header("orderId") String orderId, @Header("add") Boolean add);

    /* //    @GET("scanandgoemp/viewCart")
 //    @Headers({"Accept: application/json",
 //            "Content-Type: application/json",
 //            "Ocp-Apim-Subscription-Key: 78ae78149bff429789d33a7b15c32437",
 //            "GUID:200-160-1531871357979",
 //
 //    })
 //    Observable<BaseResponse<Cart>> getCart(@Query("clientId") String clientId, @Header("storeid") String storeid);*/
    //cloverPaymentProd/viewCart
    //7024f91451d74393bbf891483210dc28
    //cloverPaymentDev/viewCart
    //2f480da761e145f081a04a1e8ac0f59a
    @GET("cloverPaymentProd/viewCart")
    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "Ocp-Apim-Subscription-Key: 7024f91451d74393bbf891483210dc28",

    })
    Observable<BaseResponse<Cart>> getCart(@Header("guid") String guid, @Header("storeid") String storeid, @Header("orderId") String orderId);

    //cloverPaymentProd/getReceipt
    //7024f91451d74393bbf891483210dc28
    //cloverPaymentDev/getReceipt
    //2f480da761e145f081a04a1e8ac0f59a
    @GET("cloverPaymentProd/getReceipt")
    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "Ocp-Apim-Subscription-Key: 7024f91451d74393bbf891483210dc28",


    })
    Observable<BaseResponse<Data>> getReceipt(@Query("orderId") String orderId, @Header("storeid") String storeId);

    //cloverBackendProd/cloverCheckOutCart
    //7024f91451d74393bbf891483210dc28
    //cloverBackendDev/cloverCheckOutCart
    //2f480da761e145f081a04a1e8ac0f59a
    @GET("cloverBackendProd/cloverCheckOutCart")
    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "Ocp-Apim-Subscription-Key: 7024f91451d74393bbf891483210dc28",

    })
    Observable<BaseResponse<Data2>> getcloverCheckOut(@Header("storeid") String storeId, @Header("guid") String guid, @Header("clubcard_nbr") String clubcard_nbr, @Header("orderId") String orderId);

    //cloverBackendProd/retrieveOrder
    //7024f91451d74393bbf891483210dc28
    //cloverBackendDev/retrieveOrder
    //2f480da761e145f081a04a1e8ac0f59a
    @GET("cloverBackendProd/retrieveOrder")
    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "Ocp-Apim-Subscription-Key: 7024f91451d74393bbf891483210dc28",

    })
    Observable<BaseResponse<Data3>> getRetrieveOrder(@Header("suspend_barcode") String orderId, @Header("storeid") String storeId);

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
