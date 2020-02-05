package com.nhlteam;

import android.content.Context;

import com.nhlteam.di.component.DaggerApplicationComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import timber.log.Timber;

/**
 * Application support for Dagger
 */
public class NHLTeamsApplication extends DaggerApplication {
    private static NHLTeamsApplication instance;


    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerApplicationComponent.builder().create(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    /**
     * Gets the application as context
     *
     * @return context for application
     */
    public static Context getContext() {
        return instance;
    }

}
