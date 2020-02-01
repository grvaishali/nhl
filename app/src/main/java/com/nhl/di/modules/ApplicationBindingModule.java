package com.nhl.di.modules;

import com.nhl.presentation.start.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface ApplicationBindingModule {

    @ContributesAndroidInjector
    MainActivity mainActivity();
}
