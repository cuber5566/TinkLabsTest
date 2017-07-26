package com.labs.tink.tinklabstechnicalcodingtest.api;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ProductService {

    @GET("api/home/")
    Single<ProductResponse> getProductList(@Query("limit") int limit, @Query("offset") int offset);
}
