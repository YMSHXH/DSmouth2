package com.example.king.dsmouth2.presenter;

import com.example.king.dsmouth2.contact.ClsContact;
import com.example.king.dsmouth2.model.ClsModel;
import com.example.king.dsmouth2.net.OkHttpUtilsCallBack;

import java.lang.ref.WeakReference;
import java.util.Map;

public class ClsPresenter extends ClsContact.IClsPresenter {

    private ClsContact.IClsView iClsView;
    private ClsModel clsModel;
    //弱引用 优化内存溢出
    private WeakReference<ClsContact.IClsView> weakReference;

    public ClsPresenter(ClsContact.IClsView iClsView) {
        this.weakReference = new WeakReference<>(iClsView);
        this.iClsView = weakReference.get();
        this.clsModel = new ClsModel();
    }

    @Override
    public void setLeftData(Map<String, String> params) {
        if (clsModel != null){
            clsModel.setLeftData(params, new OkHttpUtilsCallBack() {
                @Override
                public void onSuccess(String res) {
                    if (iClsView != null){
                        iClsView.onLeftSuccess(res);
                    }
                }

                @Override
                public void onFile(String res) {
                    if (iClsView != null){
                        iClsView.onLeftFile(res);
                    }
                }
            });
        }
    }

    @Override
    public void setRightData(Map<String, String> params) {
        if (clsModel != null){
            clsModel.setRightData(params, new OkHttpUtilsCallBack() {
                @Override
                public void onSuccess(String res) {
                    if (iClsView != null){
                        iClsView.onRightSuccess(res);
                    }
                }

                @Override
                public void onFile(String res) {
                    if (iClsView != null){
                        iClsView.onRightFile(res);
                    }
                }
            });
        }
    }

    /**
     * 清空缓存
     */
    public void dottach(){
        if (weakReference != null) {
            weakReference.clear();
            weakReference = null;
            iClsView = null;
        }
    }
}
