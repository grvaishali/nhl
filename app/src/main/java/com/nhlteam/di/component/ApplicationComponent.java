package com.nhlteam.di.component;

import com.nhlteam.NHL_Application;
import com.nhlteam.di.builders.BuilderModuler;
import com.nhlteam.di.builders.fragment.FragmentBuilderModule;
import com.nhlteam.di.modules.AbstractNHLFragmentModule;
import com.nhlteam.di.modules.ApplicationBindingModule;
import com.nhlteam.di.modules.ApplicationModule;
import com.nhlteam.di.modules.HomeViewModule;
import com.nhlteam.di.modules.NHL_ApplicationModule;
import com.nhlteam.di.modules.NetworkModule;
import com.nhlteam.di.modules.TeamRepositoryModule;
import com.nhlteam.di.modules.TeamServiceModule;
import com.nhlteam.di.modules.TeamViewModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class, NetworkModule.class, NHL_ApplicationModule.class,
        ApplicationBindingModule.class,
        TeamViewModule.class, TeamRepositoryModule.class, TeamServiceModule.class, HomeViewModule.class,
        BuilderModuler.class, ApplicationModule.class, FragmentBuilderModule.class, AbstractNHLFragmentModule.class})
    public interface ApplicationComponent extends AndroidInjector<NHL_Application> {
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<NHL_Application> {
    }
}
