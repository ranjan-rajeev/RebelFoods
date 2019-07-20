package com.horizonlabs.rebelfoods.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.horizonlabs.rebelfoods.data.local.entity.UserEntity;
import com.horizonlabs.rebelfoods.data.repository.UserRepository;

import java.util.List;

/**
 * Created by Rajeev Ranjan -  ABPB on 20-07-2019.
 */
public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;

    private LiveData<List<UserEntity>> allUser;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        allUser = userRepository.getAllUser();
    }


    public void insert(UserEntity... userEntities) {
        userRepository.insert(userEntities);
    }

    public void update(UserEntity userEntity) {
        if (userEntity.getIsBookmarked() == 0) {
            userEntity.setIsBookmarked(1);
        } else {
            userEntity.setIsBookmarked(0);
        }
        userRepository.update(userEntity);
    }

    public LiveData<List<UserEntity>> getAllUser() {
        return allUser;
    }


}
