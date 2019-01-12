package com.example.king.dspcweek301.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.king.dspcweek301.R;
import com.example.king.dspcweek301.fragment.HomeFragment;
import com.example.king.dspcweek301.fragment.NoneFragment;

public class GoodsActivity extends AppCompatActivity {

    private ViewPager pager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    pager.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_dashboard);
                    pager.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_notifications);
                    pager.setCurrentItem(2);
                    return true;
                case R.id.navigation_shopp:
                    //mTextMessage.setText(R.string.title_notifications);
                    pager.setCurrentItem(3);
                    return true;
                case R.id.navigation_my:
                    //mTextMessage.setText(R.string.title_notifications);
                    pager.setCurrentItem(4);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);

        initView();

    }



    private void initView() {

        pager = findViewById(R.id.pager);
        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                switch (i){
                    case 0:
                        return new HomeFragment();
                    case 1:
                        return new NoneFragment();
                    case 2:
                        return new NoneFragment();
                    case 3:
                        return new NoneFragment();
                    case 4:
                        return new NoneFragment();
                }
                return null;
            }

            @Override
            public int getCount() {
                return 5;
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
                        navigation.setSelectedItemId(R.id.navigation_dashboard);
                        break;
                    case 2:
                        navigation.setSelectedItemId(R.id.navigation_notifications);
                        break;
                    case 3:
                        navigation.setSelectedItemId(R.id.navigation_shopp);
                        break;
                    case 4:
                        navigation.setSelectedItemId(R.id.navigation_my);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

}
