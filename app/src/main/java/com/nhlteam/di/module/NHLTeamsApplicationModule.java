package com.nhlteam.di.module;

import com.nhlteam.NHLTeamsApplication;

import dagger.Module;
import dagger.Provides;

@Module
public class NHLTeamsApplicationModule {
    @Provides
    NHLTeamsApplication provideNHLTeamsApplication(NHLTeamsApplication application) {
        return application;
    }
}
