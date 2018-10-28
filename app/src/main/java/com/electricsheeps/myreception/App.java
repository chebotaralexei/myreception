package com.electricsheeps.myreception;

import android.app.Application;

import com.electricsheeps.myreception.dagger.AppComponent;
import com.electricsheeps.myreception.dagger.DaggerAppComponent;
import com.electricsheeps.myreception.dagger.module.ApplicationModule;


public class App extends Application {
    public static App INSTANCE;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }

    public AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return appComponent;
    }
}
