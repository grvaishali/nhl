package com.nhl.di.builders;

import com.nhl.di.modules.TeamViewModule;
import com.nhl.presentation.team.TeamActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuilderModuler {

@ContributesAndroidInjector(modules = TeamViewModule.class)
 abstract TeamActivity contributeTeamActivity();

}
