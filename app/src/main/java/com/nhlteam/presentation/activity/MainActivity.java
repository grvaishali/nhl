package com.nhlteam.presentation.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.nhlteam.R;
import com.nhlteam.data.team.Team;
import com.nhlteam.data.team.Teams;
import com.nhlteam.databinding.ActivityMainBindingImpl;
import com.nhlteam.domain.viewmodel.TeamViewModel;
import com.nhlteam.presentation.adapter.TeamsAdapter;
import com.nhlteam.presentation.fragment.PlayersFragment;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Main activity for the application
 */
public class MainActivity extends AbstractNHLActivity<TeamViewModel> {
    private AppBarConfiguration NHLAppBarConfiguration;
    private LinearLayoutManager navigationDrawerTeamLinerLayoutManager;
    private LiveData<Team> currentTeamLiveData;

    Call<Teams> teams;
    @BindView(R.id.activity_main_navigation_recyclerView_teams)
    RecyclerView navigationDrawerTeamsRecyclerView;
    @Inject
    TeamViewModel teamViewModel;
    @Inject
    @Named("TeamViewModel")
    ViewModelProvider.Factory viewModelFactory;

    @Override
    public TeamViewModel getViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(TeamViewModel.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind();
        teamViewModel = getViewModel();
        ButterKnife.bind(this);

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Navigation Drawer
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.activity_main_navigationView);
        NHLAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.content_main_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, NHLAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationDrawerTeamsRecyclerView.setHasFixedSize(true);

        //Create shared live data and set observer on it to refresh he players list
        currentTeamLiveData = new MutableLiveData<>();
        currentTeamLiveData.observe(this, team -> {
            PlayersFragment playersFragment = new PlayersFragment(currentTeamLiveData);
            FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_home, playersFragment).addToBackStack(null);
            fragmentTransaction.commit();
            drawer.closeDrawers();
        });

        //Load teams into navigation drawer
        loadTeams();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.content_main_fragment);
        return NavigationUI.navigateUp(navController, NHLAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    /**
     * Loads teams list in navigation drawer adapter
     */
    public void loadTeams() {
        teams = teamViewModel.teamService.getTeam();
        teams.enqueue(new Callback<Teams>() {
            @Override
            public void onResponse
                    (@NotNull Call<Teams> call, @NotNull Response<Teams> response) {
                if (response.isSuccessful()) {
                    Teams teamsResponse;
                    teamsResponse = response.body();
                    navigationDrawerTeamLinerLayoutManager = new LinearLayoutManager(MainActivity.this);
                    navigationDrawerTeamsRecyclerView.setLayoutManager(navigationDrawerTeamLinerLayoutManager);
                    navigationDrawerTeamsRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    TeamsAdapter teamsAdapter = new TeamsAdapter(Objects.requireNonNull(teamsResponse).getTeams(), currentTeamLiveData, MainActivity.this);
                    navigationDrawerTeamsRecyclerView.setAdapter(teamsAdapter);
                } else {
                    Log.e("Get Teams", Objects.requireNonNull(response.errorBody()).toString(), null);
                }
            }

            @Override
            public void onFailure(@NotNull Call<Teams> call, @NotNull Throwable t) {
                Log.e("Get Teams", "onFailure: ", t);
            }
        });
    }

    private void bind() {
        ActivityMainBindingImpl binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(teamViewModel);

    }

}
