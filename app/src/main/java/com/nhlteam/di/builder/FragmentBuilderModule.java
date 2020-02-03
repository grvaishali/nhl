package com.nhlteam.di.builder;

import com.nhlteam.annotation.PerFragment;
import com.nhlteam.di.module.HomeViewModule;
import com.nhlteam.presentation.fragment.PlayersFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilderModule {
    @ContributesAndroidInjector(modules = HomeViewModule.class)
    @PerFragment
    abstract PlayersFragment contributeHomeFragment();


}
