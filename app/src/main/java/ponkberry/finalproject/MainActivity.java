package ponkberry.finalproject;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ponkberry.finalproject.adapter.MainViewPagerAdapter;
import ponkberry.finalproject.fragment.DemoItemFragment;
import ponkberry.finalproject.fragment.F2PItemFragment;
import ponkberry.finalproject.gameobject.GameObject;

/**
 * Created by Ponk on 3/5/2017.
 */

public class MainActivity extends BaseActivity implements F2PItemFragment.OnListFragmentInteractionListener, DemoItemFragment.OnListFragmentInteractionListener {

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
        initialize();
    }

    private void initialize() {
        fragmentList.add(new F2PItemFragment());
        fragmentList.add(new DemoItemFragment());
//        fragmentList.add(new UnrestrictedDemoFragment());
//        fragmentList.add(new ActionFragment());
        MainViewPagerAdapter viewPagerAdapter = new MainViewPagerAdapter(this.getSupportFragmentManager());
//         ViewPagerAdapter must accept a Fragment Manager as a parameter
        viewPagerAdapter.setContent(fragmentList);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void F2PFragmentInteraction(GameObject game) {

    }

    @Override
    public void DemoFragmentInteraction(GameObject game) {

    }
}
