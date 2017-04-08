package ponkberry.finalproject;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ponkberry.finalproject.adapter.MainViewPagerAdapter;
import ponkberry.finalproject.view.DemoListView;
import ponkberry.finalproject.view.F2PListView;

/**
 * Created by Ponk on 4/6/2017.
 */

public class MainViewPager extends BaseActivity {

    private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();

    @BindView(R.id.main_viewpager)
    ViewPager viewPager;

    @BindView(R.id.main_tablayout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initializeViewPager();
    }

    private void initializeViewPager() {
        ArrayList<View> viewList = new ArrayList<View>();
        viewList.add(new F2PListView(this));
        viewList.add(new DemoListView(this));
        viewPager.setAdapter(new MainViewPagerAdapter(viewList));
        tabLayout.setupWithViewPager(viewPager);
    }
}
