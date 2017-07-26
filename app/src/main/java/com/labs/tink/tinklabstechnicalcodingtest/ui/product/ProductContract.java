package com.labs.tink.tinklabstechnicalcodingtest.ui.product;

import com.labs.tink.tinklabstechnicalcodingtest.models.Product;

import java.util.List;

interface ProductContract {

    interface View {

        void onGetProductList(List<Product> products, Throwable throwable);

        void showProgress(boolean show);
    }

    interface Presenter {

        void getProductList(int page);
    }
}
