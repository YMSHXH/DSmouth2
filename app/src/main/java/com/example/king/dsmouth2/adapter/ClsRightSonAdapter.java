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
import com.example.king.dsmouth2.bean.ClsRightBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class ClsRightSonAdapter extends XRecyclerView.Adapter<ClsRightSonAdapter.ClsRightSonVH> {
    private Context context;
    private List<ClsRightBean.DataBean.ListBean> list;

    public ClsRightSonAdapter(Context context, List<ClsRightBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ClsRightSonVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_fen,viewGroup,false);
        ClsRightSonVH clsRightSonVH = new ClsRightSonVH(view);
        return clsRightSonVH;
    }

    @Override
    public void onBindViewHolder(@NonNull ClsRightSonVH clsRightSonVH, int i) {
        ClsRightBean.DataBean.ListBean listBean = list.get(i);
        clsRightSonVH.text_fen.setText(listBean.getName());
        Glide.with(context).load(listBean.getIcon()).into(clsRightSonVH.img_fen);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ClsRightSonVH extends RecyclerView.ViewHolder {
        ImageView img_fen;
        TextView text_fen;
        public ClsRightSonVH(@NonNull View itemView) {
            super(itemView);
            img_fen = itemView.findViewById(R.id.img_fen);
            text_fen = itemView.findViewById(R.id.text_fen);
        }
    }
}
