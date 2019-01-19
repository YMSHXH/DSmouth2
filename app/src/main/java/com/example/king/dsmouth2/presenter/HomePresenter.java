package com.example.king.dsmouth2.presenter;

import com.example.king.dsmouth2.contact.HomeContact;
import com.example.king.dsmouth2.model.HomeModel;
import com.example.king.dsmouth2.net.OkHttpUtilsCallBack;

import java.lang.ref.WeakReference;
import java.util.Map;

public class HomePresenter extends HomeContact.IHomePresenter {

    private HomeContact.IHomeView iHomeView;
    private HomeModel homeModel;

    private WeakReference<HomeContact.IHomeView> weakReference;

    public HomePresenter(HomeContact.IHomeView iHomeView) {
        this.weakReference = new WeakReference<>(iHomeView);
        this.iHomeView = weakReference.get();
        this.homeModel = new HomeModel();
    }

    @Override
    public void setData(Map<String, String> params) {
        if (homeModel != null) {
            homeModel.setData(params, new OkHttpUtilsCallBack() {
                @Override
                public void onSuccess(String res) {
                    if (iHomeView != null){
                        iHomeView.onSuccess(res);
                    }
                }

                @Override
                public void onFile(String res) {
                    if (iHomeView != null){
                        iHomeView.onFile(res);
                    }
                }
            });
        }
    }

    /**
     * 清空缓存
     */
    public void dettach(){
        if (weakReference != null) {
            weakReference.clear();
            weakReference = null;
            homeModel =null;
        }
    }
}
