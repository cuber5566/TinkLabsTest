package com.labs.tink.tinklabstechnicalcodingtest.ui.product;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.labs.tink.tinklabstechnicalcodingtest.R;
import com.labs.tink.tinklabstechnicalcodingtest.models.Product;

import java.util.List;


class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_FILL = 0;
    private static final int TYPE_IMAGE = 1;

    private Context context;
    private List<Product> productList;
    private OnProductClickListener listener;

    interface OnProductClickListener {
        void onProductClick(Product product);
    }

    ProductAdapter(Context context, OnProductClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    ProductAdapter setProductList(List<Product> productList) {
        this.productList = productList;
        return this;
    }

    @Override
    public int getItemCount() {
        return productList == null ? 0 : productList.size();
    }

    @Override
    public int getItemViewType(int position) {

        switch (productList.get(position).getType()) {

            case Product.TYPE_FILL:
                return TYPE_FILL;

            case Product.TYPE_IMAGE:
                return TYPE_IMAGE;

            default:
                return TYPE_FILL;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        switch (viewType) {

            case TYPE_FILL:
                return new FillHolder(layoutInflater.inflate(R.layout.item_product_fill, parent, false));

            case TYPE_IMAGE:
                return new ImageHolder(layoutInflater.inflate(R.layout.item_product_image, parent, false));

            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Product product = productList.get(position);

        if (holder instanceof FillHolder) {

            ((FillHolder) holder).title.setText(product.getTitle() == null ? "" : product.getTitle());
            ((FillHolder) holder).content.setText(product.getContent() == null ? "" : product.getContent());
            Glide.with(context)
                    .load(product.getImage())
                    .centerCrop()
                    .into(((FillHolder) holder).image);

        } else if (holder instanceof ImageHolder) {

            Glide.with(context)
                    .load(product.getImage())
                    .centerCrop()
                    .into(((ImageHolder) holder).image);
        }

        holder.itemView.setOnClickListener(v -> listener.onProductClick(product));
    }

    private class FillHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title;
        TextView content;

        FillHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.title);
            content = (TextView) itemView.findViewById(R.id.content);
        }
    }

    private class ImageHolder extends RecyclerView.ViewHolder {

        ImageView image;

        ImageHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
