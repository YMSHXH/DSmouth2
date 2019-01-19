package com.example.king.dsmouth2.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.king.dsmouth2.R;
import com.example.king.dsmouth2.contact.FindContact;
import com.example.king.dsmouth2.presenter.FindPresenter;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements FindContact.IFindView {

    private EditText find;
    private Button btn_find;
    private FindPresenter findPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        findPresenter = new FindPresenter(this);
    }

    private void initView() {
        find = findViewById(R.id.ed_find);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                find.setCursorVisible(true);
            }
        });
        btn_find = findViewById(R.id.btn_find);
        btn_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                find.setCursorVisible(false);
                String keywords = find.getText().toString();
                Map<String, String> param = new HashMap<>();
                param.put("keywords",keywords);
                param.put("page","1");
                param.put("sort","0");
                findPresenter.setData(param);
            }
        });

    }

    @Override
    public void onSuccess(String res) {
        Toast.makeText(MainActivity.this,res,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFile(String res) {
        Toast.makeText(MainActivity.this,res,Toast.LENGTH_SHORT).show();
    }


//    public void jump(View view) {
//        Intent intent = new Intent(MainActivity.this,MaiActivity.class);
//        intent.putExtra("name","123");
//        startActivity(intent);
//        finish();
//    }

}
