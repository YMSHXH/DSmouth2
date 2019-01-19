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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FenAdapter extends XRecyclerView.Adapter<FenAdapter.FenVH> {

    private Context context;
    private List<HomeBean.DataBean.FenleiBean> list;

    public FenAdapter(Context context, List<HomeBean.DataBean.FenleiBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FenVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_fen,viewGroup,false);
        FenVH fenVH = new FenVH(view);
        return fenVH;
    }

    @Override
    public void onBindViewHolder(@NonNull FenVH fenVH, int i) {
        HomeBean.DataBean.FenleiBean fenleiBean = list.get(i); 
        fenVH.text_fen.setText(fenleiBean.getName());

        Glide.with(context).load(fenleiBean.getIcon()).into(fenVH.img_fen);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class FenVH extends RecyclerView.ViewHolder {

        ImageView img_fen;
        TextView text_fen;
        public FenVH(@NonNull View itemView) {
            super(itemView);
            img_fen = itemView.findViewById(R.id.img_fen);
            text_fen = itemView.findViewById(R.id.text_fen);
        }
    }
}
