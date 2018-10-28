package com.electricsheeps.myreception.utils;

import android.app.Application;
import android.support.annotation.StringRes;

public class ResourceProvider {

    private final Application application;

    public ResourceProvider(Application application) {
        this.application = application;
    }

    public String getString(@StringRes int id) {
        return application.getString(id);
    }
}


