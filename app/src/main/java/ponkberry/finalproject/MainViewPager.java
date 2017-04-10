package ponkberry.finalproject;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ponkberry.finalproject.adapter.MainViewPagerAdapter;
import ponkberry.finalproject.adapter.NavMenuAdapter;
import ponkberry.finalproject.gameobject.GameObject;
import ponkberry.finalproject.gameobject.GameProgress;
import ponkberry.finalproject.util.UtilDensity;
import ponkberry.finalproject.util.UtilLog;
import ponkberry.finalproject.view.DemoListView;
import ponkberry.finalproject.view.F2PListView;

/**
 * Created by Ponk on 4/6/2017.
 */

public class MainViewPager extends BaseActivity implements View.OnTouchListener, AdapterView.OnItemClickListener {

    private GestureDetector mGestureDetector;
    private DrawerLayout mNavMenu;
    private ListView mNavList;
    private ArrayList<String> listResult;
    private static final String[] navMenuList = new String[] { "Logout" };

    @OnClick(R.id.main_nav_button)
    public void navMenu() {
        if (mNavMenu.isDrawerOpen(Gravity.START)) {
            mNavMenu.closeDrawer(Gravity.START);
        } else {
            mNavMenu.openDrawer(Gravity.START);
        }
    }
    @BindView(R.id.main_nav_view)
    LinearLayout nav_view;
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
        mGestureDetector = new GestureDetector(this,new simpleGestureListener());
        nav_view.setOnTouchListener(this);
        nav_view.setLongClickable(true);
        initializeViewPager();
        setPreferences();
        mNavMenu = (DrawerLayout) findViewById(R.id.main_nav_menu);
        listResult = new ArrayList<String>();
        for (int i = 0; i < navMenuList.length; i++) {
            listResult.add(navMenuList[i]);
        }
        mNavList = (ListView) findViewById(R.id.nav_menu_list);
        NavMenuAdapter listViewAdapter = new NavMenuAdapter(this, listResult);
        mNavList.setAdapter(listViewAdapter);
        mNavList.setOnItemClickListener(this);
    }

    private void setPreferences() {
        SharedPreferences mainPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        mainPreferences.getString("loginPrefs", "");
        Typeface myriadPro = Typeface.createFromAsset(getAssets(), "fonts/Myriad Pro Regular.ttf");
        Typeface myriadProBold = Typeface.createFromAsset(getAssets(), "fonts/MyriadPro-Bold.otf");
        userName.setTypeface(myriadPro);
        userName.setText(""+mainPreferences.getString("name",""));
//        if(mainPreferences.getString("progress","")==null) {
//            emptyShell();
//        }
    }

    private void emptyShell() {
        ArrayList<ArrayList> totalGames = new ArrayList<>();
        ArrayList<GameProgress> freeGP = new ArrayList<>();
        ArrayList<GameProgress> demoGP = new ArrayList<>();
        totalGames.add(freeGP);
        totalGames.add(demoGP);
        freeGP.add(new GameProgress("404Sight", 0, 0));
        freeGP.add(new GameProgress("Altitude", 0, 0));
        freeGP.add(new GameProgress("Antenna", 0, 0));
        freeGP.add(new GameProgress("Blind Trust", 0, 0));
        freeGP.add(new GameProgress("Carpe Diem", 0, 0));
        freeGP.add(new GameProgress("Cloney", 0, 0));
        freeGP.add(new GameProgress("Dev Guy", 0, 0));
        freeGP.add(new GameProgress("Emily is Away", 0, 0));
        freeGP.add(new GameProgress("Free to Play", 0, 0));
        freeGP.add(new GameProgress("Iron Snout", 0, 0));
        freeGP.add(new GameProgress("Mandagon", 0, 0));
        demoGP.add(new GameProgress("Angels that Kill", 1, 0));
        demoGP.add(new GameProgress("Blob From Space", 1, 0));
        demoGP.add(new GameProgress("Blue Rose", 2, 0));
        demoGP.add(new GameProgress("Concursion", 2, 0));
        demoGP.add(new GameProgress("Cursed Sight", 3, 0));
        demoGP.add(new GameProgress("Flat Kingdom", 1, 0));
        demoGP.add(new GameProgress("Mu Complex", 5, 0));
        demoGP.add(new GameProgress("She Remembered Caterpillars", 4, 0));
        demoGP.add(new GameProgress("Stanley Parable", 1, 0));
        demoGP.add(new GameProgress("Teslagrad", 9, 0));
        demoGP.add(new GameProgress("Without Within 2", 2, 0));
    }

    private void initializeViewPager() {
        ArrayList<View> viewList = new ArrayList<View>();
        viewList.add(new F2PListView(this));
        viewList.add(new DemoListView(this));
        viewPager.setAdapter(new MainViewPagerAdapter(viewList));
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SharedPreferences mSharedPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        SharedPreferences.Editor mSPEditor = mSharedPreferences.edit();
        mSPEditor.clear();
        mSPEditor.commit();
        toActivity(LoginActivity.class);
    }

    private class simpleGestureListener extends GestureDetector.SimpleOnGestureListener {
        public boolean onDown(MotionEvent e) {
            UtilLog.logD("MyGesture", "onDown: "+e.toString());
            //toastShort("onDown");
            return false;
        }

        public void onShowPress(MotionEvent e) {
            UtilLog.logD("MyGesture", "onShowPress");
            //toastShort("onShowPress");
        }

        public void onLongPress(MotionEvent e) {
            UtilLog.logD("MyGesture", "onLongPress");
            //toastShort("onLongPress");
        }

        public boolean onSingleTapUp(MotionEvent e) {
            UtilLog.logD("MyGesture", "onSingleTapUp");
            //toastShort("onSingleTapUp");
            return true;
        }

        public boolean onSingleTapConfirmed(MotionEvent e) {
            UtilLog.logD("MyGesture", "onSingleTapConfirmed");
            //toastShort("onSingleTapConfirmed");
            return true;
        }

        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            UtilLog.logD("MyGesture", "onScroll: " + (e2.getX() - e1.getX() + "   " +distanceX));
            //toastShort("onScroll");
            //scroll_menu_v.startAnimation(transAnimation);
            // Must do this programmatically, XML animation is not sufficient

            return true;
        }

        public boolean onFling (MotionEvent e1, MotionEvent e2, float veloxityX, float velocityY) {
            UtilLog.logD("MyGesture", "onFling");

            if ((e1.getX() < e2.getX())) {
                //toastShort("Right");
                toActivity(ProfileActivity.class);
            } else if (e2.getX() < e1.getX()) {
                //toastShort("Left");
            }

            //toastShort("onFling");
            return true;
        }

        public boolean onDoubleTap (MotionEvent e) {
            UtilLog.logD("MyGesture", "onDoubleTap");
            //toastShort("onDoubleTap");
            return true;
        }

        public boolean onDoubleTapEvent(MotionEvent e) {
            UtilLog.logD("MyGesture", "onDoubleTapEvent");
            //toastShort("onDoubleTapEvent");
            return true;
        }
    }
}
