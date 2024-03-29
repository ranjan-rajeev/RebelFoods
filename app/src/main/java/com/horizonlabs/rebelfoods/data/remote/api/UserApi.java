package com.horizonlabs.rebelfoods.data.remote.api;



import com.horizonlabs.rebelfoods.data.local.entity.UserEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Rajeev Ranjan -  ABPB on 20-07-2019.
 */
public interface UserApi {

    @GET("/users")
    Call<List<UserEntity>> getUsers();
}
