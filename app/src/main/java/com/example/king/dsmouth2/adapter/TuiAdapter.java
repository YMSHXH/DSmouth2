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

public class TuiAdapter extends XRecyclerView.Adapter<TuiAdapter.TuiVH> {

    private Context context;
    private HomeBean.DataBean.TuijianBean tuijian = new HomeBean.DataBean.TuijianBean();

    public TuiAdapter(Context context, HomeBean.DataBean.TuijianBean tuijian) {
        this.context = context;
        this.tuijian = tuijian;
    }

    @NonNull
    @Override
    public TuiVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_tui,viewGroup,false);
        TuiVH tuiVH = new TuiVH(view);
        return tuiVH;
    }

    @Override
    public void onBindViewHolder(@NonNull TuiVH tuiVH, int i) {
        HomeBean.DataBean.TuijianBean.ListBeanX listBeanX = tuijian.getList().get(i);
        tuiVH.text_fen.setText(listBeanX.getTitle());
        tuiVH.price_fen.setText("￥：" + listBeanX.getPrice());
        String images = listBeanX.getImages();
        String[] img_spilt = images.split("\\|");
        if (img_spilt.length > 0 && img_spilt != null ){
            Glide.with(context).load(img_spilt[0]).into(tuiVH.img_fen);
        } else {
            Glide.with(context).load(images).into(tuiVH.img_fen);
        }
    }

    @Override
    public int getItemCount() {
        return tuijian.getList().size();
    }

    public class TuiVH extends RecyclerView.ViewHolder {
        ImageView img_fen;
        TextView text_fen,price_fen;
        public TuiVH(@NonNull View itemView) {
            super(itemView);
            img_fen = itemView.findViewById(R.id.img_fen);
            text_fen = itemView.findViewById(R.id.text_fen);
            price_fen = itemView.findViewById(R.id.price_fen);
        }
    }
}
