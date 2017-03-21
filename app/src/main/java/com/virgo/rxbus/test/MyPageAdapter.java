package com.virgo.rxbus.test;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.virgo.rxbus.utils.imageloader.ImageLoader;

import java.util.List;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by Administrator on 2017/3/21.
 */
public class MyPageAdapter extends PagerAdapter {
    private Context mContext;
    private List<String> mUrls;

    public MyPageAdapter(Context context, List<String> urls) {
        this.mContext = context;
        this.mUrls = urls;
    }

    @Override
    public int getCount() {
        return mUrls.size();
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        PhotoView photoview = new PhotoView(mContext);
        ImageLoader.getInstance().displayImage(mContext,mUrls.get(position),photoview);
        container.addView(photoview, ViewPager.LayoutParams.MATCH_PARENT,
                ViewPager.LayoutParams.MATCH_PARENT);
        return photoview;
    }


}
