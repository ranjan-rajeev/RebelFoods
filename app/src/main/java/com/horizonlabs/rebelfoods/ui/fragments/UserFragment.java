package com.horizonlabs.rebelfoods.ui.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.horizonlabs.rebelfoods.R;
import com.horizonlabs.rebelfoods.data.local.entity.UserEntity;
import com.horizonlabs.rebelfoods.ui.activities.AddressDetailActivity;
import com.horizonlabs.rebelfoods.ui.adapters.UserAdapter;
import com.horizonlabs.rebelfoods.ui.base.BaseFragment;
import com.horizonlabs.rebelfoods.viewmodels.UserViewModel;

import java.util.List;


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
                startActivity(new Intent(getActivity(), AddressDetailActivity.class));
            }

            @Override
            public void onFavouriteClick(UserEntity userEntity) {
                userViewModel.update(userEntity);
            }
        });
        return view;
    }
}
