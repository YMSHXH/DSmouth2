package com.example.king.dsmouth2.myview;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.king.dsmouth2.R;

public class SerchView extends ConstraintLayout {

    private ImageView or_cord;
    private EditText ed_find;

    private SearchCallBack searchCallBack;

    public void setSearchCallBack(SearchCallBack searchCallBack) {
        this.searchCallBack = searchCallBack;
    }

    public SerchView(Context context) {
        this(context,null);
    }

    public SerchView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SerchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_serach,this,true);
        or_cord = view.findViewById(R.id.or_cord);
        ed_find = view.findViewById(R.id.ed_find);

        ed_find.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                searchCallBack.setSearchClick();
            }
        });

    }

    public interface SearchCallBack{
        void setSearchClick();
    }
}
