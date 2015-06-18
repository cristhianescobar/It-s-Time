package com.cristhianescobar.itstime;

/**
 * Created by cristhian.escobar on 6/16/15.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by cristhian.escobar on 6/12/15.
 */
public class CEPageAdapter extends FragmentPagerAdapter {

    private ArrayList<BlankFragment> fragmentList = new ArrayList<>();

    public CEPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position).fragment;
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentList.get(position).title;
    }

    public void addPage(Fragment fragment, String title) {
        fragmentList.add(new BlankFragment(fragment, title));
    }

    private class BlankFragment {

        Fragment fragment;
        String title;

        public BlankFragment(Fragment fragment, String title) {
            this.fragment = fragment;
            this.title = title;
        }
    }
}