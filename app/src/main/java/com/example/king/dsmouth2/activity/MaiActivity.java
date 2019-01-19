package com.example.king.dsmouth2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.king.dsmouth2.R;
import com.example.king.dsmouth2.fragment.CartFragment;
import com.example.king.dsmouth2.fragment.ClsFragment;
import com.example.king.dsmouth2.fragment.HomeFragment;
import com.example.king.dsmouth2.fragment.MineFragment;

public class MaiActivity extends AppCompatActivity {

    private ViewPager pager;
    private BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    pager.setCurrentItem(0);
                    return true;
                case R.id.navigation_cls:
                    pager.setCurrentItem(1);
                    return true;
                case R.id.navigation_cart:
                    pager.setCurrentItem(2);
                    return true;
                case R.id.navigation_mine:
                    pager.setCurrentItem(3);
                    return true;
            }
            return false;
        }
    };
    private HomeFragment homeFragment;
    private ClsFragment clsFragment;
    private CartFragment cartFragment;
    private MineFragment mineFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mai);


        getSupportActionBar().hide();
        initView();
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        Log.e("=====",""+name);
        if ("".equals(name) || null == name){
            pager.setCurrentItem(0);
        } else {
            pager.setCurrentItem(2);
        }
    }

    private void initView() {
        pager = findViewById(R.id.pager);
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //初始化fragment
        homeFragment = new HomeFragment();
        clsFragment = new ClsFragment();
        cartFragment = new CartFragment();
        mineFragment = new MineFragment();

        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                switch (i){
                    case 0:
                        return homeFragment;
                    case 1:
                        return clsFragment;
                    case 2:
                        return cartFragment;
                    case 3:
                        return mineFragment;

                }
                return null;
            }

            @Override
            public int getCount() {
                return 4;
            }
        });

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        navigation.setSelectedItemId(R.id.navigation_home);
                        break;
                    case 1:
                        navigation.setSelectedItemId(R.id.navigation_cls);
                        break;
                    case 2:
                        navigation.setSelectedItemId(R.id.navigation_cart);
                        break;
                    case 3:
                        navigation.setSelectedItemId(R.id.navigation_mine);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

}
