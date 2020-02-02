package com.nhl.di.modules;

import androidx.lifecycle.ViewModelProvider;

import com.nhl.presentation.fragment.AbstractNHLFragment;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class AbstractNHLFragmentModule {

    @Provides
    @Named("Fragment")
    ViewModelProvider.Factory provideAbstractCatalogueFragmentProvider() {
        return (ViewModelProvider.Factory) new AbstractNHLFragment();
    }
}
