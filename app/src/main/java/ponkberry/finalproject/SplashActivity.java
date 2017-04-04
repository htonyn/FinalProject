package ponkberry.finalproject;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ponkberry.finalproject.util.UtilLog;


public class SplashActivity extends BaseActivity {
    private Animation splashAnimation;
    private int mProgressStatus = 0;
    private static final String[] loadStatements = new String[] { "Loading...", "Inheriting Tacos...", "Creating Palindromes...", "Sprinkling Cheese...", "Initializing Spice...", "Unpacking Peppers...", "Encapsulating Beef...", "Masking Chickens...", "Generating Revenue...", "Rerouting Onions...", "Achieving Perfection..."};
    private int counter = 0;
    private Handler mHandler = new Handler();

    @BindView(R.id.splash_title)
    TextView tv;

    @BindView(R.id.splash_text)
    TextView tv2;

    @BindView(R.id.splash_bar)
    ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        UtilLog.logD("Splash", "onCreate");
        ButterKnife.bind(this);
        initialize();

    }

    private void initialize() {
        SharedPreferences splashPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        splashPreferences.getString("loginPrefs", "");
        UtilLog.logD("Splash", "initialize");
        Typeface myriadPro = Typeface.createFromAsset(getAssets(), "fonts/Myriad Pro Regular.ttf");
        Typeface myriadProBold = Typeface.createFromAsset(getAssets(), "fonts/MyriadPro-Bold.otf");
        tv.setTypeface(myriadPro);
        tv2.setTypeface(myriadProBold);
        String name = splashPreferences.getString("name","");
        //toastShort("Toast: "+name);
        tv.setText("Hello, "+splashPreferences.getString("name",""));
    }

//  The animation cannot be placed in onCreate because the animation would not be loaded during
//  the onCreate period and thus cannot start the activation; alternatively, used windowFocus
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        ImageView iv = (ImageView) this.findViewById(R.id.splash_image);
        if(hasFocus){
            splashAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);
            iv.startAnimation(splashAnimation);
        }
        new Thread(new Runnable() {

            public void run() {
                while (mProgressStatus < 100) {
                    try {
                        Thread.sleep(30);

                        mProgressStatus++;
                        // Update the progress bar
                        mHandler.post(new Runnable() {
                            public void run() {
                                mProgress.setProgress(mProgressStatus);
                                tv2.setText(loadStatements[counter % loadStatements.length]);
                            }
                        });
                        if (mProgressStatus%10==0) {
                            counter++;
                        }
                        if (mProgressStatus == 100) {
                            endSplash();
                        }
                    }  catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    protected void endSplash() {
        toActivity(MainActivity.class);
        finish();
    }
}
