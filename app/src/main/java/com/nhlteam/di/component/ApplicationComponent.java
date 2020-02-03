package com.nhlteam.di.component;

import com.nhlteam.NHLTeamsApplication;
import com.nhlteam.di.builder.BuilderModule;
import com.nhlteam.di.builder.FragmentBuilderModule;
import com.nhlteam.di.module.AbstractNHLFragmentModule;
import com.nhlteam.di.module.ApplicationBindingModule;
import com.nhlteam.di.module.ApplicationModule;
import com.nhlteam.di.module.HomeViewModule;
import com.nhlteam.di.module.NetworkModule;
import com.nhlteam.di.module.TeamRepositoryModule;
import com.nhlteam.di.module.TeamServiceModule;
import com.nhlteam.di.module.TeamViewModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class, NetworkModule.class,
        ApplicationBindingModule.class,
        TeamViewModule.class, TeamRepositoryModule.class, TeamServiceModule.class, HomeViewModule.class,
        BuilderModule.class, ApplicationModule.class, FragmentBuilderModule.class, AbstractNHLFragmentModule.class})
    public interface ApplicationComponent extends AndroidInjector<NHLTeamsApplication> {
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<NHLTeamsApplication> {



    }

}
