package com.horizonlabs.rebelfoods.data.local.dao;



import com.horizonlabs.rebelfoods.data.local.entity.UserEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

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
