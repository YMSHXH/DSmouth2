package com.example.king.dsmouth2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.king.dsmouth2.R;
import com.example.king.dsmouth2.bean.HomeBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

public class HomeAdapter extends XRecyclerView.Adapter<HomeAdapter.HomeVH> {

    private Context context;
    private HomeBean.DataBean data;

    public HomeAdapter(Context context, HomeBean.DataBean data) {
        this.context = context;
        this.data = data;
    }
    @NonNull
    @Override
    public HomeVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_banner,viewGroup,false);
        HomeVH homeVH = new HomeVH(view);
        return homeVH;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeVH homeVH, int i) {
        final List<HomeBean.DataBean.BannerBean> bannerlist = data.getBanner();
        List<HomeBean.DataBean.FenleiBean> fenlei = data.getFenlei();
        HomeBean.DataBean.MiaoshaBean miaosha = data.getMiaosha();
        HomeBean.DataBean.TuijianBean tuijian = data.getTuijian();

        //banner
        homeVH.xBanner.setData(bannerlist,null);
        homeVH.xBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(banner).load(bannerlist.get(position).getIcon())
                        .into((ImageView) view);
            }
        });
        homeVH.xBanner.setIsClipChildrenMode(true);
        //推荐
        homeVH.xre_fei.setLayoutManager(new GridLayoutManager(context,5));
        FenAdapter fenAdapter = new FenAdapter(context,fenlei);
        homeVH.xre_fei.setAdapter(fenAdapter);

        //秒杀
        homeVH.xre_miao.setLayoutManager(new LinearLayoutManager(context,0,false));
        MiaoAdapter miaoAdapter = new MiaoAdapter(context,miaosha);
        homeVH.xre_miao.setAdapter(miaoAdapter);

        //推荐
        homeVH.xre_tui.setLayoutManager(new LinearLayoutManager(context));
        TuiAdapter tuiAdapter = new TuiAdapter(context,tuijian);
        homeVH.xre_tui.setAdapter(tuiAdapter);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class HomeVH extends RecyclerView.ViewHolder {
        XBanner xBanner;
        XRecyclerView xre_fei;
        XRecyclerView xre_miao;
        XRecyclerView xre_tui;
        public HomeVH(@NonNull View itemView) {
            super(itemView);
            xBanner = itemView.findViewById(R.id.xbanner);
            xre_fei = itemView.findViewById(R.id.xre_fei);
            xre_miao = itemView.findViewById(R.id.xre_miao);
            xre_tui = itemView.findViewById(R.id.xre_tui);
        }
    }
}
