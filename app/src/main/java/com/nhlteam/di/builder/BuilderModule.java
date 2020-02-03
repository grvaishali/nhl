package com.nhlteam.di.builder;

import com.nhlteam.di.module.TeamViewModule;
import com.nhlteam.presentation.activity.PlayerActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuilderModule {

@ContributesAndroidInjector(modules = TeamViewModule.class)
 abstract PlayerActivity contributeTeamActivity();

}
