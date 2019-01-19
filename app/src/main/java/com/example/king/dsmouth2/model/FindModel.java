package com.example.king.dsmouth2.model;

import com.example.king.dsmouth2.apis.Api;
import com.example.king.dsmouth2.contact.FindContact;
import com.example.king.dsmouth2.net.OkHttpUtils;
import com.example.king.dsmouth2.net.OkHttpUtilsCallBack;

import java.util.Map;


public class FindModel implements FindContact.IFindModel {
    @Override
    public void setData(Map<String, String> params, OkHttpUtilsCallBack okHttpUtilsCallBack) {
        OkHttpUtils.getInstance().toGet(Api.FIND_API,params,okHttpUtilsCallBack);
    }
}
