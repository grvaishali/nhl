package com.nhl.di.modules;

import com.nhl.NHL_Application;

import dagger.Module;
import dagger.Provides;

@Module
public class NHL_ApplicationModule {
    @Provides
    NHL_Application provideEcatalogueApllication(NHL_Application application) {
        return application;
    }
}
