package com.example.king.dsmouth2.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.king.dsmouth2.R;
import com.example.king.dsmouth2.bean.ClsLeftBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class ClsLeftAdapter extends XRecyclerView.Adapter<ClsLeftAdapter.ClsLeftVH> {

    private Context context;
    private List<ClsLeftBean.DataBean> list;
    private ItemCallBack itemCallBack;

    public void setItemCallBack(ItemCallBack itemCallBack) {
        this.itemCallBack = itemCallBack;
    }

    public ClsLeftAdapter(Context context, List<ClsLeftBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ClsLeftVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cls_left,viewGroup,false);
        ClsLeftVH clsLeftVH = new ClsLeftVH(view);
        return clsLeftVH;
    }

    @Override
    public void onBindViewHolder(@NonNull ClsLeftVH clsLeftVH, int i) {
        final ClsLeftBean.DataBean dataBean = list.get(i);
        clsLeftVH.cls_left.setText(dataBean.getName());
        //设置单击事件
        clsLeftVH.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemCallBack.setOnItemClick(dataBean.getCid());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size() == 0 ? 0 : list.size();
    }

    public class ClsLeftVH extends RecyclerView.ViewHolder {
        TextView cls_left;
        public ClsLeftVH(@NonNull View itemView) {
            super(itemView);
            cls_left= itemView.findViewById(R.id.cls_left);
        }
    }

    /**
     * 点击事件接口回调
     */
    public interface ItemCallBack{
        void setOnItemClick(String cid);
    }
}
