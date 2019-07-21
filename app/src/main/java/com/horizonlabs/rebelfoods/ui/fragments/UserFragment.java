package com.horizonlabs.rebelfoods.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.horizonlabs.rebelfoods.R;
import com.horizonlabs.rebelfoods.data.local.entity.UserEntity;
import com.horizonlabs.rebelfoods.ui.activities.MapsActivity;
import com.horizonlabs.rebelfoods.ui.adapters.UserAdapter;
import com.horizonlabs.rebelfoods.ui.base.BaseFragment;
import com.horizonlabs.rebelfoods.utils.Constants;
import com.horizonlabs.rebelfoods.utils.Utility;
import com.horizonlabs.rebelfoods.viewmodels.UserViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by Rajeev Ranjan -  ABPB on 20-07-2019.
 */
public class UserFragment extends BaseFragment {

    View view;
    RecyclerView rvAllUsers;
    UserViewModel userViewModel;
    UserAdapter userAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user, container, false);

        rvAllUsers = view.findViewById(R.id.rvAllUsers);
        rvAllUsers.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        rvAllUsers.setHasFixedSize(true);
        userAdapter = new UserAdapter(getActivity());
        rvAllUsers.setAdapter(userAdapter);

        userViewModel = ViewModelProviders.of(this.getActivity()).get(UserViewModel.class);
        userViewModel.getAllUser().observe(this.getActivity(), new Observer<List<UserEntity>>() {
            @Override
            public void onChanged(@Nullable List<UserEntity> userEntities) {
                if (userEntities != null) {
                    userAdapter.setUserEntities(userEntities);
                } else {
                    Toast.makeText(getActivity(), "On Changed ", Toast.LENGTH_SHORT).show();
                }

            }
        });

        userAdapter.setOnItemClickListener(new UserAdapter.ItemClick() {
            @Override
            public void onItemClick(UserEntity userEntity) {
                startActivity(new Intent(getActivity(), MapsActivity.class)
                        .putExtra(Constants.LAT, userEntity.getAddress().getGeo().getLat())
                        .putExtra(Constants.LON, userEntity.getAddress().getGeo().getLng())
                        .putExtra(Constants.ADDRESS, Utility.getAddress(userEntity)));
            }

            @Override
            public void onFavouriteClick(UserEntity userEntity) {
                userViewModel.update(userEntity);
            }
        });
        return view;
    }
}
