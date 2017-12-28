package com.hq.uitest.recyclerviewt.headandfootrv;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hq.uitest.R;
import com.hq.uitest.StringAdapter;
import com.hq.uitest.viewpager.FirstPagerTransFormer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heqiang on 17/11/8.
 */

public class MultiPartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int HORIZONTAL_RV = 0x3;
    private final int VIEWPAGER = 0x4;
    private final int NORMAL = 0x5;
    private LayoutInflater mInflater;

    public MultiPartAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case HORIZONTAL_RV:
                view = mInflater.inflate(R.layout.item_rv_horizontal_rv, parent, false);
                return new HorizontalRvViewHolder(view);
            case VIEWPAGER:
                view = mInflater.inflate(R.layout.item_rv_viewpager, parent, false);
                return new BannerViewHolder(view);
            case NORMAL:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_string, parent, false);
                return new ItemViewHolder(view);
            default:
                break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemViewHolder){
            ItemViewHolder holder1 = (ItemViewHolder) holder;
            holder1.tv_content.setText("position" + position);
        }
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 1) {
            return HORIZONTAL_RV;
        } else if (position == 2) {
            return VIEWPAGER;
        }
        return NORMAL;
    }

    class HorizontalRvViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rv_horizontal;

        public HorizontalRvViewHolder(View itemView) {
            super(itemView);
            rv_horizontal = (RecyclerView) itemView.findViewById(R.id.rv_horizontal_rv);
            LinearLayoutManager manager = new LinearLayoutManager(itemView.getContext());
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rv_horizontal.setLayoutManager(manager);
            List<String> mData = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                mData.add("第" + i + "项");
            }
            StringAdapter adapter = new StringAdapter(mData);
            rv_horizontal.setAdapter(adapter);
        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        ViewPager vp;

        public BannerViewHolder(View itemView) {
            super(itemView);
            vp = (ViewPager) itemView.findViewById(R.id.vp_rv_banner);
            LayoutInflater mInflater = LayoutInflater.from(itemView.getContext());

            List<View> mViews;
            mViews = new ArrayList<>();
            View view1 = mInflater.inflate(R.layout.lay_vp, null);
            TextView tv1 = (TextView) view1.findViewById(R.id.tv_vp_content);
            tv1.setText("1");
            mViews.add(view1);

            View view2 = mInflater.inflate(R.layout.lay_vp, null);
            TextView tv2 = (TextView) view2.findViewById(R.id.tv_vp_content);
            tv2.setText("2");
            mViews.add(view2);

            View view3 = mInflater.inflate(R.layout.lay_vp, null);
            TextView tv3 = (TextView) view3.findViewById(R.id.tv_vp_content);
            tv3.setText("3");
            mViews.add(view3);

            View view4 = mInflater.inflate(R.layout.lay_vp, null);
            TextView tv4 = (TextView) view4.findViewById(R.id.tv_vp_content);
            tv4.setText("4");
            mViews.add(view4);

            vp.setPageTransformer(true, new FirstPagerTransFormer());
            vp.setPageMargin(-100);
            vp.setOffscreenPageLimit(3);
            vp.setAdapter(new VpAdapter(mViews));
        }
    }

    class VpAdapter extends PagerAdapter {
        private List<View> mViews;


        public VpAdapter(List<View> views) {
            mViews = views;
        }

        @Override
        public int getCount() {
            return mViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mViews.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = mViews.get(position);
            container.removeView(view);
        }

        @Override
        public float getPageWidth(int position) {
//                return 0.7f;
            return super.getPageWidth(position);
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_content;
//        View view_null;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_content = (TextView) itemView.findViewById(R.id.tv_rv_str);
//            view_null = itemView.findViewById(R.id.view_null);
        }
    }
}
