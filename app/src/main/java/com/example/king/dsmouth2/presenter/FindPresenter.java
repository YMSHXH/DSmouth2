package com.example.king.dsmouth2.presenter;

import com.example.king.dsmouth2.contact.FindContact;
import com.example.king.dsmouth2.model.FindModel;
import com.example.king.dsmouth2.net.OkHttpUtilsCallBack;

import java.lang.ref.WeakReference;
import java.util.Map;

public class FindPresenter extends FindContact.IFindPresenter {

    private FindContact.IFindView iFindView;
    private FindModel findModel;
    private WeakReference<FindContact.IFindView> weakReference;

    public FindPresenter(FindContact.IFindView iFindView) {
        this.weakReference = new WeakReference<>(iFindView);
        this.iFindView = weakReference.get();
        this.findModel = new FindModel();
    }

    @Override
    public void setData(Map<String, String> params) {
        if (findModel != null) {
            findModel.setData(params, new OkHttpUtilsCallBack() {
                @Override
                public void onSuccess(String res) {
                    if (iFindView != null){
                        iFindView.onSuccess(res);
                    }
                }

                @Override
                public void onFile(String res) {
                    if (iFindView != null){
                        iFindView.onFile(res);
                    }
                }
            });
        }
    }

    //解绑
    public void dottach(){
        if (weakReference != null) {
            weakReference.clear();
            weakReference = null;
            iFindView = null;
        }
    }
}
