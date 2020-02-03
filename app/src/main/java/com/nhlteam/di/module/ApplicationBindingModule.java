package com.nhlteam.di.module;

import com.nhlteam.presentation.activity.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface ApplicationBindingModule {

    @ContributesAndroidInjector
    MainActivity mainActivity();
}
