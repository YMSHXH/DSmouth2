package com.example.king.dsmouth2.contact;

import com.example.king.dsmouth2.net.OkHttpUtilsCallBack;

import java.util.Map;

public interface FindContact {

    public abstract class IFindPresenter{
        public abstract void setData(Map<String, String> params);
    }

    interface IFindModel {
        void setData(Map<String, String> params, OkHttpUtilsCallBack okHttpUtilsCallBack);
    }

    interface IFindView {
        void onSuccess(String res);
        void onFile(String res);
    }
}
