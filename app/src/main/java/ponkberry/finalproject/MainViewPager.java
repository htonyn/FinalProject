package ponkberry.finalproject;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ponkberry.finalproject.adapter.MainViewPagerAdapter;
import ponkberry.finalproject.util.UtilLog;
import ponkberry.finalproject.view.DemoListView;
import ponkberry.finalproject.view.F2PListView;

/**
 * Created by Ponk on 4/6/2017.
 */

public class MainViewPager extends BaseActivity {

    @BindView(R.id.main_viewpager)
    ViewPager viewPager;

    @BindView(R.id.main_tablayout)
    TabLayout tabLayout;

    @BindView(R.id.main_username)
    TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initializeViewPager();
        setPreferences();
    }

    private void setPreferences() {
        SharedPreferences mainPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        mainPreferences.getString("loginPrefs", "");
        Typeface myriadPro = Typeface.createFromAsset(getAssets(), "fonts/Myriad Pro Regular.ttf");
        Typeface myriadProBold = Typeface.createFromAsset(getAssets(), "fonts/MyriadPro-Bold.otf");
        userName.setTypeface(myriadPro);
        //userName.setText(""+mainPreferences.getString("name",""));
    }

    private void initializeViewPager() {
        ArrayList<View> viewList = new ArrayList<View>();
        viewList.add(new F2PListView(this));
        viewList.add(new DemoListView(this));
        viewPager.setAdapter(new MainViewPagerAdapter(viewList));
        tabLayout.setupWithViewPager(viewPager);
    }
}
