package com.labs.tink.tinklabstechnicalcodingtest.widget;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class TopAndBottomScrollListener extends RecyclerView.OnScrollListener {

    private int firstVisibleItemPosition;
    private int lastVisibleItemPosition;

    private OnUpScrollListener onUpScrollListener;
    private OnDownScrollListener onDownScrollListener;
    private OnBottomListener onBottomListener;
    private OnTopListener onTopListener;

    private int upGap = 1, downGap = 1;
    private boolean isScrollDown;
    private boolean isBottom;
    private int itemCount = 0;


    public interface OnUpScrollListener {
        void onUpScroll();
    }

    public interface OnDownScrollListener {
        void onDownScroll();
    }

    public interface OnTopListener {
        void onScrollTop();
    }

    public interface OnBottomListener {
        void onScrollBottom();
    }

    public TopAndBottomScrollListener onUpScroll(int upGap, OnUpScrollListener onUpScrollListener) {
        this.upGap = upGap;
        this.onUpScrollListener = onUpScrollListener;
        return this;
    }

    public TopAndBottomScrollListener onDownScroll(int downGap, OnDownScrollListener onDownScrollListener) {
        this.downGap = downGap;
        this.onDownScrollListener = onDownScrollListener;
        return this;
    }

    public TopAndBottomScrollListener onScrollBottom(OnBottomListener onBottomListener) {
        this.onBottomListener = onBottomListener;
        return this;
    }

    public TopAndBottomScrollListener onScrollBottom(int itemCount, OnBottomListener onBottomListener) {
        this.itemCount = itemCount;
        this.onBottomListener = onBottomListener;
        return this;
    }

    public TopAndBottomScrollListener onScrollTop(OnTopListener onTopListener) {
        this.onTopListener = onTopListener;
        return this;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        isBottom = false;

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager != null) {
            firstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
            lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        }

        if (dy > 0) {
            if (isScrollDown && lastVisibleItemPosition % upGap == 0) {
                isScrollDown = false;
                if (onUpScrollListener != null) onUpScrollListener.onUpScroll();
            }
        } else {
            if (!isScrollDown && firstVisibleItemPosition % downGap == 0) {
                isScrollDown = true;
                if (onDownScrollListener != null) onDownScrollListener.onDownScroll();
            }
        }

        if (recyclerView.getScrollY() == 0) {
            if (onTopListener != null) onTopListener.onScrollTop();
        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();

        if (visibleItemCount > 0 && newState == RecyclerView.SCROLL_STATE_IDLE && (lastVisibleItemPosition) >= totalItemCount - itemCount - 1 && !isBottom) {
            isBottom = true;
            if (onBottomListener != null) onBottomListener.onScrollBottom();
        }
    }
}
