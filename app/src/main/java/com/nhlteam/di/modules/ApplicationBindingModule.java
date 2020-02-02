package com.nhlteam.di.modules;

import com.nhlteam.presentation.start.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface ApplicationBindingModule {

    @ContributesAndroidInjector
    MainActivity mainActivity();
}
