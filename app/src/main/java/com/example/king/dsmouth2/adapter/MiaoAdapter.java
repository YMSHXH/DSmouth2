package com.example.king.dsmouth2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.king.dsmouth2.R;
import com.example.king.dsmouth2.bean.HomeBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

public class MiaoAdapter extends XRecyclerView.Adapter<MiaoAdapter.MiaoVH> {

    private Context context;
    private HomeBean.DataBean.MiaoshaBean miaosha = new HomeBean.DataBean.MiaoshaBean();

    public MiaoAdapter(Context context, HomeBean.DataBean.MiaoshaBean miaosha) {
        this.context = context;
        this.miaosha = miaosha;
    }

    @NonNull
    @Override
    public MiaoVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_miao,viewGroup,false);
        MiaoVH miaoVH = new MiaoVH(view);
        return miaoVH;
    }

    @Override
    public void onBindViewHolder(@NonNull MiaoVH miaoVH, int i) {
        HomeBean.DataBean.MiaoshaBean.ListBean listBean = miaosha.getList().get(i);
        miaoVH.text_fen.setText(listBean.getTitle());
        String images = listBean.getImages();
        String[] img_spilt = images.split("\\|");
        if (img_spilt.length > 0 && img_spilt != null ){
            Glide.with(context).load(img_spilt[0]).into(miaoVH.img_fen);
        } else {
            Glide.with(context).load(images).into(miaoVH.img_fen);
        }
    }

    @Override
    public int getItemCount() {
        return miaosha.getList().size();
    }

    public class MiaoVH extends RecyclerView.ViewHolder {
        ImageView img_fen;
        TextView text_fen;
        public MiaoVH(@NonNull View itemView) {
            super(itemView);
            img_fen = itemView.findViewById(R.id.img_fen);
            text_fen = itemView.findViewById(R.id.text_fen);
        }
    }
}
