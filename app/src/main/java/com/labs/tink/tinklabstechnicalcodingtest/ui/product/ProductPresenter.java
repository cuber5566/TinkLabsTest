package com.labs.tink.tinklabstechnicalcodingtest.ui.product;

import com.labs.tink.tinklabstechnicalcodingtest.respositories.ProductRepository;
import com.labs.tink.tinklabstechnicalcodingtest.util.RxUtil;

class ProductPresenter implements ProductContract.Presenter {

    private ProductContract.View view;
    private ProductRepository productRepository;
    private boolean isLoading = false;

    ProductPresenter(ProductContract.View view, ProductRepository productRepository) {
        this.view = view;
        this.productRepository = productRepository;
    }

    @Override
    public void getProductList(int offset) {

        if (isLoading) return;

        view.showProgress(isLoading = true);
        productRepository.getProductList(offset)
                .compose(RxUtil.bindLifecycle(view))
                .compose(RxUtil.mainAsync())
                .doFinally(() -> view.showProgress(isLoading = false))
                .subscribe((products, throwable) -> view.onGetProductList(products, throwable));
    }
}
