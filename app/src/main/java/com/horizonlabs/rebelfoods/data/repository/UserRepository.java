package com.horizonlabs.rebelfoods.data.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.horizonlabs.rebelfoods.data.local.RebelDatabase;
import com.horizonlabs.rebelfoods.data.local.dao.UserDao;
import com.horizonlabs.rebelfoods.data.local.entity.UserEntity;
import com.horizonlabs.rebelfoods.data.remote.RetrofitClent;
import com.horizonlabs.rebelfoods.data.remote.api.UserApi;

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

    public MutableLiveData<List<UserEntity>> getUsersFromServer() {
        final MutableLiveData<List<UserEntity>> userList = new MutableLiveData<>();

        userApi.getUsers().enqueue(new Callback<LiveData<List<UserEntity>>>() {
            @Override
            public void onResponse(Call<LiveData<List<UserEntity>>> call, Response<LiveData<List<UserEntity>>> response) {
                if (response.isSuccessful()) {
                    userList.setValue(response.body().getValue());
                }
            }

            @Override
            public void onFailure(Call<LiveData<List<UserEntity>>> call, Throwable t) {

            }
        });

        return userList;
    }
}
