package com.horizonlabs.rebelfoods.ui.adapters;


import com.horizonlabs.rebelfoods.ui.fragments.FavouriteFragment;
import com.horizonlabs.rebelfoods.ui.fragments.UserFragment;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


/**
 * Created by Rajeev Ranjan -  ABPB on 20-07-2019.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new UserFragment();
            case 1:
                return new FavouriteFragment();
            default:
                return new UserFragment();
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "Users";
            case 1:
                return "Favourites";
            default:
                return "Home";
        }
    }
}
