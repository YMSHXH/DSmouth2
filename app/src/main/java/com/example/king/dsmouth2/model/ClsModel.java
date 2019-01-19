package com.example.king.dsmouth2.model;

import com.example.king.dsmouth2.apis.Api;
import com.example.king.dsmouth2.contact.ClsContact;
import com.example.king.dsmouth2.net.OkHttpUtils;
import com.example.king.dsmouth2.net.OkHttpUtilsCallBack;

import java.util.Map;

public class ClsModel implements ClsContact.IClsModel {
    @Override
    public void setLeftData(Map<String, String> params, OkHttpUtilsCallBack okHttpUtilsCallBack) {
        OkHttpUtils.getInstance().toGet(Api.CLS_LEFT_API,params,okHttpUtilsCallBack);
    }

    @Override
    public void setRightData(Map<String, String> params, OkHttpUtilsCallBack okHttpUtilsCallBack) {
        OkHttpUtils.getInstance().toGet(Api.CLS_RIGHT_API,params,okHttpUtilsCallBack);
    }
}
