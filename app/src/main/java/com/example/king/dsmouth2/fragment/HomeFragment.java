package com.example.king.dsmouth2.fragment;

import android.content.Intent;
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
import com.example.king.dsmouth2.activity.MainActivity;
import com.example.king.dsmouth2.adapter.HomeAdapter;
import com.example.king.dsmouth2.bean.HomeBean;
import com.example.king.dsmouth2.contact.HomeContact;
import com.example.king.dsmouth2.myview.SerchView;
import com.example.king.dsmouth2.presenter.HomePresenter;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment implements HomeContact.IHomeView {

    private HomePresenter homePresenter;
    private XRecyclerView xre_home;
    private HomeBean.DataBean data;
    private SerchView myView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    private void initView(View view) {
        myView = view.findViewById(R.id.myview_ser);
        xre_home = view.findViewById(R.id.xre_home);
        xre_home.setLayoutManager(new LinearLayoutManager(getActivity()));

        myView.setSearchCallBack(new SerchView.SearchCallBack() {
            @Override
            public void setSearchClick() {
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        data = new HomeBean.DataBean();
        homePresenter = new HomePresenter(this);
        Map<String, String> param = new HashMap<>();
        homePresenter.setData(param);
    }

    @Override
    public void onSuccess(String res) {
        //Toast.makeText(getActivity(),res,Toast.LENGTH_SHORT).show();
        HomeBean homeBean = new Gson().fromJson(res, HomeBean.class);
        if ("0".equals(homeBean.getCode())){
            data = homeBean.getData();
            HomeAdapter homeAdapter = new HomeAdapter(getActivity(),data);
            xre_home.setAdapter(homeAdapter);
        }
    }

    @Override
    public void onFile(String res) {
        Toast.makeText(getActivity(),res,Toast.LENGTH_SHORT).show();
    }
}
