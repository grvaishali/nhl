package com.nhl.di.builders.fragment;

import com.nhl.di.modules.AbstractNHLFragmentModule;
import com.nhl.presentation.fragment.AbstractNHLFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilderModule {
    @ContributesAndroidInjector(modules = AbstractNHLFragmentModule.class)
    @PerFragment
    abstract AbstractNHLFragment contributesAbstractCatalogueFragment();
}
