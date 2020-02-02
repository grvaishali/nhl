package com.nhlteam.di.builders.fragment;

import com.nhlteam.di.modules.HomeViewModule;
import com.nhlteam.presentation.navigation.ui.home.HomeFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilderModule {

//    @ContributesAndroidInjector(modules = AbstractNHLFragmentModule.class)
//    @PerFragment
//    abstract AbstractNHLFragment contributesAbstractNHLFragment();

    @ContributesAndroidInjector(modules = HomeViewModule.class)
    @PerFragment
    abstract HomeFragment contributeHomeFragment();


}
