package com.nhl.di.modules;

import android.content.Context;

import com.nhl.NHL_Application;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    @Provides
    Context provideContext(NHL_Application application) {
        return application;
    }
}
