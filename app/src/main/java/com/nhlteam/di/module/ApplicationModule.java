package com.nhlteam.di.module;

import android.content.Context;

import com.nhlteam.NHLTeamsApplication;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    @Provides
    @Singleton
    @Named("NHLTeamApplicationContext")
    Context provideContext(NHLTeamsApplication application) {
        return application;
    }


}
