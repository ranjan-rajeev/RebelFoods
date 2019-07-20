package com.horizonlabs.rebelfoods.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.horizonlabs.rebelfoods.R;
import com.horizonlabs.rebelfoods.ui.base.BaseFragment;


/**
 * Created by Rajeev Ranjan -  ABPB on 20-07-2019.
 */
public class FavouriteFragment extends BaseFragment {
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_favourite, container, false);
        return view;

    }
}
