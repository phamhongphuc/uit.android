package view.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {
    private int[] mLayouts;

    public FragmentAdapter(FragmentManager fragmentManager, int[] layouts) {
        super(fragmentManager);
        mLayouts = layouts;
    }

    @Override
    public int getCount() {
        return mLayouts.length;
    }

    @Override
    public Fragment getItem(int i) {
        return new FragmentPage(mLayouts[i]);
    }
}
