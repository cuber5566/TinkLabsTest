package com.labs.tink.tinklabstechnicalcodingtest.ui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.labs.tink.tinklabstechnicalcodingtest.R;
import com.labs.tink.tinklabstechnicalcodingtest.ui.product.ProductFragment;
import com.labs.tink.tinklabstechnicalcodingtest.widget.SmartFragmentStatePagerAdapter;


class MainPagerAdapter extends SmartFragmentStatePagerAdapter {

    private String[] title;

    MainPagerAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        title = context.getResources().getStringArray(R.array.category);
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public Fragment getItem(int position) {
        return ProductFragment.newInstance();
    }
}
