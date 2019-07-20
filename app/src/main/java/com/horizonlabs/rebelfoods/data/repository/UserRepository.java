package com.horizonlabs.rebelfoods.data.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.horizonlabs.rebelfoods.data.local.RebelDatabase;
import com.horizonlabs.rebelfoods.data.local.dao.UserDao;
import com.horizonlabs.rebelfoods.data.local.entity.UserEntity;
import com.horizonlabs.rebelfoods.data.remote.RetrofitClent;
import com.horizonlabs.rebelfoods.data.remote.api.UserApi;
import com.horizonlabs.rebelfoods.utils.Logger;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rajeev Ranjan -  ABPB on 20-07-2019.
 */
public class UserRepository {

    private UserApi userApi;
    private UserDao userDao;


    private LiveData<List<UserEntity>> allUser;
    private LiveData<List<UserEntity>> favouriteUser;

    public UserRepository(Application application) {
        userDao = RebelDatabase.getInstance(application).userDao();
        userApi = RetrofitClent.getInstance().create(UserApi.class);
        allUser = userDao.getAllUsers();
        favouriteUser = userDao.getBookMarkedUsers();
    }

    public void insert(UserEntity... userEntities) {
        new InsertUserAsyncTask(userDao).execute(userEntities);
    }

    public void update(UserEntity userEntity) {
        new UpdateUserAsyncTask(userDao).execute(userEntity);
    }

    public LiveData<List<UserEntity>> getAllUser() {

        getUsersFromServer();
        return allUser;
    }

    public LiveData<List<UserEntity>> getFavouriteUser() {

        return favouriteUser;
    }

    private static class InsertUserAsyncTask extends AsyncTask<UserEntity, Void, Void> {

        private UserDao userDao;

        private InsertUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(UserEntity... userEntities) {
            userDao.saveUser(userEntities);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<UserEntity, Void, Void> {

        private UserDao userDao;

        private UpdateUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(UserEntity... userEntities) {
            userDao.updateUser(userEntities[0]);
            return null;
        }
    }

    public LiveData<List<UserEntity>> getUsersFromServer() {

        userApi.getUsers().enqueue(new Callback<List<UserEntity>>() {
            @Override
            public void onResponse(Call<List<UserEntity>> call, Response<List<UserEntity>> response) {
                if (response.isSuccessful() && allUser==null) {
                    Logger.d("list fetch successful");
                    insert(response.body().toArray(new UserEntity[response.body().size()]));
                }
            }

            @Override
            public void onFailure(Call<List<UserEntity>> call, Throwable t) {
                // TODO: 20-07-2019 HAndle failure cases
                Logger.d("" + t.getMessage());
            }
        });

        return allUser;
    }
}
