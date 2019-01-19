package com.example.king.dsmouth2.contact;

import com.example.king.dsmouth2.net.OkHttpUtilsCallBack;

import java.util.Map;

public interface ClsContact {

    public abstract class IClsPresenter{
        public abstract void setLeftData(Map<String, String> params);
        public abstract void setRightData(Map<String, String> params);
    }

    interface IClsModel {
        void setLeftData(Map<String, String> params, OkHttpUtilsCallBack okHttpUtilsCallBack);
        void setRightData(Map<String, String> params, OkHttpUtilsCallBack okHttpUtilsCallBack);
    }

    interface IClsView {
        void onLeftSuccess(String res);
        void onLeftFile(String res);
        void onRightSuccess(String res);
        void onRightFile(String res);
    }
}
