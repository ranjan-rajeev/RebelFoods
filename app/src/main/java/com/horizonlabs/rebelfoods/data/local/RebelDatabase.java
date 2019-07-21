package com.horizonlabs.rebelfoods.data.local;


import android.content.Context;

import com.horizonlabs.rebelfoods.data.local.dao.UserDao;
import com.horizonlabs.rebelfoods.data.local.entity.UserEntity;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * Created by Rajeev Ranjan -  ABPB on 20-07-2019.
 */
@Database(entities = {UserEntity.class}, version = 1,exportSchema = false)
public abstract class RebelDatabase extends RoomDatabase {

    private static RebelDatabase rebelDatabase;

    public abstract UserDao userDao();

    public static synchronized RebelDatabase getInstance(Context context) {
        if (rebelDatabase == null) {
            rebelDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    RebelDatabase.class, "rebel_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return rebelDatabase;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };

}
