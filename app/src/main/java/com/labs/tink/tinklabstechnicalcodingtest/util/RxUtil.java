package com.labs.tink.tinklabstechnicalcodingtest.util;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxDialogFragment;
import com.trello.rxlifecycle2.components.support.RxFragment;

import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxUtil {

    public static <T> LifecycleTransformer<T> bindLifecycle(Object view) {
        if (view instanceof RxAppCompatActivity) {
            return ((RxAppCompatActivity) view).bindUntilEvent(ActivityEvent.DESTROY);
        } else if (view instanceof RxDialogFragment) {
            return ((RxDialogFragment) view).bindUntilEvent(FragmentEvent.DESTROY_VIEW);
        } else if (view instanceof RxFragment) {
            return ((RxFragment) view).bindUntilEvent(FragmentEvent.DESTROY_VIEW);
        } else {
            throw new IllegalArgumentException("not support this bind object");
        }
    }

    public static <T> SingleTransformer<T, T> mainAsync() {
        return obs -> obs.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}