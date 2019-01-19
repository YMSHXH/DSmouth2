package com.example.king.dsmouth2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.king.dsmouth2.R;
import com.example.king.dsmouth2.bean.ClsRightBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class ClsRightAdapter extends XRecyclerView.Adapter<ClsRightAdapter.ClsRightVH> {

    private Context context;
    private List<ClsRightBean.DataBean> list;

    public ClsRightAdapter(Context context, List<ClsRightBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ClsRightVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cls_right,viewGroup,false);
        ClsRightVH clsRightVH = new ClsRightVH(view);
        return clsRightVH;
    }

    @Override
    public void onBindViewHolder(@NonNull ClsRightVH clsRightVH, int i) {
        ClsRightBean.DataBean dataBean = list.get(i);
        clsRightVH.cls_right.setText(dataBean.getName());
        //设置适配器
        clsRightVH.cls_xre.setLayoutManager(new GridLayoutManager(context,3));
        List<ClsRightBean.DataBean.ListBean> list = dataBean.getList();
        ClsRightSonAdapter clsRightSonAdapter = new ClsRightSonAdapter(context,list);
        clsRightVH.cls_xre.setAdapter(clsRightSonAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ClsRightVH extends RecyclerView.ViewHolder {
        TextView cls_right;
        XRecyclerView cls_xre;
        public ClsRightVH(@NonNull View itemView) {
            super(itemView);
            cls_right= itemView.findViewById(R.id.cls_right);
            cls_xre= itemView.findViewById(R.id.cls_xre);
        }
    }
}
