package com.labs.tink.tinklabstechnicalcodingtest.ui.product;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.labs.tink.tinklabstechnicalcodingtest.R;
import com.labs.tink.tinklabstechnicalcodingtest.models.Product;
import com.labs.tink.tinklabstechnicalcodingtest.respositories.ProductRepository;
import com.labs.tink.tinklabstechnicalcodingtest.ui.base.BaseFragment;
import com.labs.tink.tinklabstechnicalcodingtest.widget.TopAndBottomScrollListener;

import java.util.ArrayList;
import java.util.List;


public class ProductFragment extends BaseFragment implements ProductContract.View, ProductAdapter.OnProductClickListener {

    /* Data */
    private ProductContract.Presenter presenter;
    private List<Product> productList;

    /* View */
    private TextView noData;
    private ProductAdapter productAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar progressBar;

    public static ProductFragment newInstance() {
        Bundle args = new Bundle();
        ProductFragment fragment = new ProductFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ProductPresenter(this, ProductRepository.getInstance());
        productList = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(productAdapter = new ProductAdapter(getContext(), this));
        recyclerView.addOnScrollListener(new TopAndBottomScrollListener().onScrollBottom(() -> {
            presenter.getProductList(productList.size());
            progressBar.setVisibility(View.VISIBLE);
        }));

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            productList.clear();
            productAdapter.notifyDataSetChanged();
            presenter.getProductList(productList.size());
        });

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        noData = (TextView) view.findViewById(R.id.noData);
        noData.setVisibility(View.GONE);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.getProductList(productList.size());
    }

    @Override
    public void onProductClick(Product product) {
        Toast.makeText(getContext(), String.format("Click: %s", product.getTitle()), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetProductList(List<Product> products, Throwable throwable) {
        noData.setVisibility(throwable != null && productList.isEmpty() ? View.VISIBLE:View.GONE);

        productList.addAll(products);
        productAdapter.setProductList(productList).notifyDataSetChanged();
    }

    @Override
    public void showProgress(boolean show) {
        swipeRefreshLayout.setRefreshing(false);
        progressBar.setVisibility(View.GONE);
    }
}
