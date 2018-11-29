package com.example.root.ecodz;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by root on 17/03/18.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {

    private int NUM_ITEMS = 4;
    private String[] titles= new String[]{"Petrole", "Monnaie","News","Information"};

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return  NUM_ITEMS ;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Petrole();
            case 1:
                return new Monnaie();
            case 2:
                return new News();
            case 3:
                return new Information();
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return  titles[position];
    }

}