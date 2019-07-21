package com.horizonlabs.rebelfoods.viewmodels;

import android.app.Application;


import com.horizonlabs.rebelfoods.data.local.entity.UserEntity;
import com.horizonlabs.rebelfoods.data.repository.UserRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


/**
 * Created by Rajeev Ranjan -  ABPB on 20-07-2019.
 */
public class FavouriteViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private LiveData<List<UserEntity>> favouriteUser;

    public FavouriteViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        favouriteUser = userRepository.getFavouriteUser();
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

    public LiveData<List<UserEntity>> getFavouriteUser() {
        return favouriteUser;
    }
}
