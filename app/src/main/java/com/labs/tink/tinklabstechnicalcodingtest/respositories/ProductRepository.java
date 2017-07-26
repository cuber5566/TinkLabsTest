package com.labs.tink.tinklabstechnicalcodingtest.respositories;

import com.labs.tink.tinklabstechnicalcodingtest.Config;
import com.labs.tink.tinklabstechnicalcodingtest.api.NetworkService;
import com.labs.tink.tinklabstechnicalcodingtest.api.ProductResponse;
import com.labs.tink.tinklabstechnicalcodingtest.models.Product;

import java.util.List;

import io.reactivex.Single;

public class ProductRepository {

    private static final ProductRepository INSTANCE = new ProductRepository();

    public static ProductRepository getInstance() {
        return INSTANCE;
    }

    public Single<List<Product>> getProductList(int offset) {
        return NetworkService.getInstance().getProductService().getProductList(Config.PRODUCT_PAGE_SIZE, offset)
                .map(ProductResponse::getResults);
    }
}
