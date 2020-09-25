package com.example.collegeapp.intro_activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.college_app_sdk.classes.IntroViewPagerItem;
import com.example.collegeapp.R;

import java.util.List;

public class IntroViewPagerAdapter extends PagerAdapter {

    Context mContext;
    List<IntroViewPagerItem> mViewPagerItemList;
    LayoutInflater mLayoutInflater;

    public IntroViewPagerAdapter(Context mContext, List<IntroViewPagerItem> mViewPagerItemList) {
        this.mContext = mContext;
        this.mViewPagerItemList = mViewPagerItemList;
        mLayoutInflater = LayoutInflater.from(mContext.getApplicationContext());
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //TODO: dont pass null, if possible (works for now)
        View viewPagerItem = mLayoutInflater.inflate(R.layout.view_pager_item, null);

        ImageView pagerItemImage = viewPagerItem.findViewById(R.id.view_pager_item_image);
        TextView pagerItemTitle = viewPagerItem.findViewById(R.id.view_pager_item_title);
        TextView pagerItemDesc = viewPagerItem.findViewById(R.id.view_pager_item_description);
        IntroViewPagerItem pagerItem = mViewPagerItemList.get(position);

        pagerItemImage.setImageResource(pagerItem.getImageId());
        pagerItemTitle.setText(pagerItem.getTitle());
        pagerItemDesc.setText(pagerItem.getDescription());

        container.addView(viewPagerItem);

        return viewPagerItem;
    }

    @Override
    public int getCount() {
        return mViewPagerItemList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View) object);

    }
}
