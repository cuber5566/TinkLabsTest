package com.labs.tink.tinklabstechnicalcodingtest.api;

import com.labs.tink.tinklabstechnicalcodingtest.Config;

import java.util.concurrent.TimeUnit;

import lombok.Getter;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Getter
public class NetworkService {

    private static NetworkService INSTANCE;
    private final ProductService productService;

    public static NetworkService getInstance() {
        return INSTANCE != null ? INSTANCE : (INSTANCE = new NetworkService());
    }

    private NetworkService() {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(Config.TIME_OUT_CONNECT, TimeUnit.SECONDS)
                .readTimeout(Config.TIME_OUT_READ, TimeUnit.SECONDS)
                .writeTimeout(Config.TIME_OUT_WRITE, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Config.BASE_URL)
                .client(client)
                .build();

        productService = retrofit.create(ProductService.class);
    }
}
