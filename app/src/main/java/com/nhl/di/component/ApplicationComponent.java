package com.nhl.di.component;

import com.nhl.NHL_Application;
import com.nhl.di.builders.BuilderModuler;
import com.nhl.di.builders.fragment.FragmentBuilderModule;
import com.nhl.di.modules.AbstractNHLFragmentModule;
import com.nhl.di.modules.ApplicationBindingModule;
import com.nhl.di.modules.ApplicationModule;
import com.nhl.di.modules.HomeViewModule;
import com.nhl.di.modules.NHL_ApplicationModule;
import com.nhl.di.modules.NetworkModule;
import com.nhl.di.modules.TeamRepositoryModule;
import com.nhl.di.modules.TeamServiceModule;
import com.nhl.di.modules.TeamViewModule;

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
