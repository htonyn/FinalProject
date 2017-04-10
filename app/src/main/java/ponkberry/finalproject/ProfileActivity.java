package ponkberry.finalproject;

import android.animation.ObjectAnimator;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ponkberry.finalproject.adapter.NavMenuAdapter;
import ponkberry.finalproject.service.BaseAudioOb;
import ponkberry.finalproject.service.MusicController;
import ponkberry.finalproject.util.UtilDensity;
import ponkberry.finalproject.util.UtilLog;

/**
 * Created by htony on 4/9/2017.
 */

public class ProfileActivity extends BaseActivity implements View.OnTouchListener, MusicController.IPlayerStatus, AdapterView.OnItemClickListener {

    private ArrayList<BaseAudioOb> contentList = new ArrayList<BaseAudioOb>();
    private MusicController controller;
    private GestureDetector mGestureDetector;
    private DrawerLayout mNavMenu;
    private ListView mNavList;
    private ArrayList<String> listResult;
    private static final String[] navMenuList = new String[] { "Logout" };

    @OnClick(R.id.prof_nav_button)
    public void navMenu() {
        if (mNavMenu.isDrawerOpen(Gravity.START)) {
            mNavMenu.closeDrawer(Gravity.START);
        } else {
            mNavMenu.openDrawer(Gravity.START);
        }
    }
    @BindView(R.id.prof_username)
    TextView userName;
    @BindView(R.id.prof_nav_view)
    LinearLayout nav_view;
    @BindView(R.id.prof_play_button)
    ImageButton playButton;
    @BindView(R.id.prof_achievements)
    TextView tv_ach;
    @BindView(R.id.prof_comp_perc)
    TextView tv_perc;
    @BindView(R.id.prof_total_comp)
    TextView tv_comp;

    @OnClick(R.id.prof_play_button)
    public void playButton() {
        if (!controller.isPlaying) {
            UtilLog.logD("ProfMusicPlayer", "IsPlaying");
            controller.play();
        } else {
            UtilLog.logD("ProfMusicPlayer", "IsPausing");
            controller.pause();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        AudioOb m1 = new AudioOb();
        m1.setURL("http://puu.sh/uYEg4/c88c536ef6.mp3");
        m1.setName("Profile Song");
        contentList.add(m1);
        mGestureDetector = new GestureDetector(this,new ProfileActivity.simpleGestureListener());
        nav_view.setOnTouchListener(this);
        nav_view.setLongClickable(true);
        setPreferences();
        setMusicController();
        mNavMenu = (DrawerLayout) findViewById(R.id.prof_nav_menu);
        listResult = new ArrayList<String>();
        for (int i = 0; i < navMenuList.length; i++) {
            listResult.add(navMenuList[i]);
        }
        mNavList = (ListView) findViewById(R.id.prof_nav_menu_list);
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
        userName.setText(mainPreferences.getString("name",""));
        //toastShort(""+mainPreferences.getInt("ach",0));
        tv_ach.setText(""+mainPreferences.getInt("ach", 0));
        int comp = mainPreferences.getInt("completed", 0);
        tv_comp.setText(""+comp);
        comp = comp * 100 / 486;
        tv_perc.setText(comp+"%");
    }

    private void setMusicController() {
        controller = MusicController.getInstance(this);
        controller.setPlayList(getContent());
        controller.addListener("ProfileView", this);
    }

    public ArrayList<BaseAudioOb> getContent(){
        return contentList;
    }

    @Override
    protected void onDestroy() {
        MusicController controller = MusicController.getInstance(this);
        controller.destroy();
        super.onDestroy();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    private void prepareStatus() {
        playButton.setImageResource(R.mipmap.play_button_2);
    }

    private void pauseStatus() {
        playButton.setImageResource(R.mipmap.play_button_2);
    }

    private void startStatus() {
        playButton.setImageResource(R.mipmap.pause_button_2);
    }

    @Override
    public void onLoading() {
        prepareStatus();
    }

    @Override
    public void onProgress(int i) {

    }

    @Override
    public void onError(String error) {
        toastShort(error);
    }

    @Override
    public void onPrepared() {

    }

    @Override
    public void onSeekComplete() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onUpdateCache(int i) {

    }

    @Override
    public void onPause() {
        super.onPause();
        pauseStatus();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart(int i) {
        startStatus();
    }

    @Override
    public void onInitComplete() {

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
            } else if (e2.getX() < e1.getX()) {
                //toastShort("Left");
                toActivity(MainViewPager.class);
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
