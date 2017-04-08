package ponkberry.finalproject.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class MainViewPagerAdapter extends PagerAdapter {

    private static final String[] tabHeaders = new String[] { "Free2Play", "Demos", "Login" };
    private List<View> viewList;

    public MainViewPagerAdapter(List<View> mListViews) {
        this.viewList = mListViews;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabHeaders[position % tabHeaders.length];
//        return fragmentList.get(position).getClass().getName();
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position), 0);
        return viewList.get(position);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }
}