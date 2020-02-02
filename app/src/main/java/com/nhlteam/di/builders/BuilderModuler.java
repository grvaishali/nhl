package com.nhlteam.di.builders;

import com.nhlteam.di.modules.TeamViewModule;
import com.nhlteam.presentation.team.TeamActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuilderModuler {

@ContributesAndroidInjector(modules = TeamViewModule.class)
 abstract TeamActivity contributeTeamActivity();

}
