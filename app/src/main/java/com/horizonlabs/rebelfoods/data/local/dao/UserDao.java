package com.horizonlabs.rebelfoods.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.horizonlabs.rebelfoods.data.local.entity.UserEntity;

import java.util.List;

/**
 * Created by Rajeev Ranjan -  ABPB on 20-07-2019.
 */
@Dao
public interface UserDao {
    @Query("SELECT * FROM UserEntity")
    LiveData<List<UserEntity>> getAllUsers();

    @Query("SELECT * FROM UserEntity where isBookmarked=1")
    LiveData<List<UserEntity>> getBookMarkedUsers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveUser(UserEntity... userEntities);

    @Update
    void updateUser(UserEntity userEntity);
}
