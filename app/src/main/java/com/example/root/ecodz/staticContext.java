package com.example.root.ecodz;

import android.app.Application;
import android.content.Context;

/**
 * Created by root on 19/03/18.
 */

public class staticContext extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        staticContext.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return staticContext.context;
    }
}
