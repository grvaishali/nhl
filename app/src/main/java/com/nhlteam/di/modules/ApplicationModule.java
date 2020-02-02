package com.nhlteam.di.modules;

import android.content.Context;

import com.nhlteam.NHL_Application;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    @Provides
    Context provideContext(NHL_Application application) {
        return application;
    }
}
