package com.nhl.di.builders.fragment;

import com.nhl.di.modules.AbstractNHLFragmentModule;
import com.nhl.di.modules.HomeViewModule;
import com.nhl.presentation.fragment.AbstractNHLFragment;
import com.nhl.presentation.navigation.ui.home.HomeFragment;

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
