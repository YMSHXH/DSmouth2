package com.example.king.dsmouth2.contact;

import com.example.king.dsmouth2.net.OkHttpUtilsCallBack;

import java.util.Map;

public interface HomeContact {

    public abstract class IHomePresenter{
        public abstract void setData(Map<String, String> params);
    }

    interface IHomeModel {
        void setData(Map<String,String> params, OkHttpUtilsCallBack okHttpUtilsCallBack);
    }

    interface IHomeView {
        void onSuccess(String res);
        void onFile(String res);
    }
}
