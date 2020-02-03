package com.nhlteam;

import android.content.Context;

import com.nhlteam.di.component.DaggerApplicationComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * Application support for Dagger
 */
public class NHLTeamsApplication extends DaggerApplication {
    private static NHLTeamsApplication instance;


    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerApplicationComponent.builder().create(this);
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
