package com.horizonlabs.rebelfoods.data.local;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.horizonlabs.rebelfoods.data.local.dao.UserDao;
import com.horizonlabs.rebelfoods.data.local.entity.UserEntity;

/**
 * Created by Rajeev Ranjan -  ABPB on 20-07-2019.
 */
@Database(entities = {UserEntity.class}, version = 1)
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
