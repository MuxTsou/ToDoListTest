package com.oursky.todo_android.app;

import android.app.Application;

import com.oursky.todo_android.content.DatabaseHelper;

/**
 * Created by yuyauchun on 13/3/15.
 */
public class BaseApplication extends Application {
    private DatabaseHelper mDatabaseHelper;

    public DatabaseHelper getDatabaseHelper() {
        if (mDatabaseHelper == null) {
            mDatabaseHelper = new DatabaseHelper(getApplicationContext());
        }
        return mDatabaseHelper;
    }
}
