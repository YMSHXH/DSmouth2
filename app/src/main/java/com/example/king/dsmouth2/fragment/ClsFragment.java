package com.example.king.dsmouth2.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.king.dsmouth2.R;
import com.example.king.dsmouth2.adapter.ClsLeftAdapter;
import com.example.king.dsmouth2.adapter.ClsRightAdapter;
import com.example.king.dsmouth2.bean.ClsLeftBean;
import com.example.king.dsmouth2.bean.ClsRightBean;
import com.example.king.dsmouth2.contact.ClsContact;
import com.example.king.dsmouth2.presenter.ClsPresenter;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClsFragment extends Fragment implements ClsContact.IClsView,ClsLeftAdapter.ItemCallBack {

    private XRecyclerView xre_left,xre_right;
    private ClsLeftAdapter clsLeftAdapter;
    private ClsPresenter clsPresenter;
    private Map<String, String> param_left;
    private Map<String, String> param_right;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_cls,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    private void initView(View view) {
        xre_left = view.findViewById(R.id.xre_left);
        xre_left.setLayoutManager(new LinearLayoutManager(getActivity()));
        xre_right = view.findViewById(R.id.xre_right);
        xre_right.setLayoutManager(new LinearLayoutManager(getActivity()));


    }

    private void initData() {
        clsPresenter = new ClsPresenter(this);
        param_left = new HashMap<>();
        clsPresenter.setLeftData(param_left);

        param_right = new HashMap<>();
        param_right.put("cid","1");
        clsPresenter.setRightData(param_right);
    }

    @Override
    public void onLeftSuccess(String res) {
        //Toast.makeText(getActivity(),res,Toast.LENGTH_SHORT).show();
        ClsLeftBean clsLeftBean = new Gson().fromJson(res, ClsLeftBean.class);
        if ("0".equals(clsLeftBean.getCode())){
            List<ClsLeftBean.DataBean> list = clsLeftBean.getData();
            clsLeftAdapter = new ClsLeftAdapter(getActivity(),list);
            clsLeftAdapter.setItemCallBack(this);
            xre_left.setAdapter(clsLeftAdapter);
        }
    }

    @Override
    public void onLeftFile(String res) {
        Toast.makeText(getActivity(),res,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRightSuccess(String res) {
        //Toast.makeText(getActivity(),res,Toast.LENGTH_SHORT).show();
        ClsRightBean clsRightBean = new Gson().fromJson(res, ClsRightBean.class);
        if ("0".equals(clsRightBean.getCode())){
            List<ClsRightBean.DataBean> list = clsRightBean.getData();
            ClsRightAdapter clsRightAdapter = new ClsRightAdapter(getActivity(),list);
            xre_right.setAdapter(clsRightAdapter);
        }
    }

    @Override
    public void onRightFile(String res) {
        Toast.makeText(getActivity(),res,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setOnItemClick(String cid) {
        param_right.put("cid",cid);
        clsPresenter.setRightData(param_right);
    }
}
