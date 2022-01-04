package com.safeway.postest.Data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class NetworkManager {

    private static OkHttpClient client;  //https://clover-payment.azure-api.net/
                                        //https://retail-api.azure-api.net/
  //  public static String BASE_URL = "https://clover-payment.azure-api.net/"; //ebt
    public static String BASE_URL = "https://retail-api.azure-api.net/"; //pharma
    public static String MSGRAPH_URL ="https://graph.microsoft.com/";

    private NetworkManager() {
    }

    public static OkHttpClient getClient() {
        if (client == null) {
            client = createOkHttpClient();
        }
        return client;
    }

//    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
//
//    Gson gson = new GsonBuilder()
//            .setLenient()
//            .create();
//    retrofit = new Retrofit.Builder()
//            .baseUrl(BASE_URL)
//                .client(client)
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build();

    public static Retrofit createRetrofit() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(createOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }



    private static OkHttpClient createOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                HttpUrl originalUrl = originalRequest.url();

                HttpUrl url = originalUrl.newBuilder()
                        //.addQueryParameter("username", "demo")
                        .build();
                Request.Builder requestBuilder = originalRequest.newBuilder().url(url);

                return chain.proceed(requestBuilder.build());
            }
        })
                .addInterceptor(interceptor);
        return builder.build();
    }

}