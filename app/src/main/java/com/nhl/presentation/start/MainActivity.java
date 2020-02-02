package com.nhl.presentation.start;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.nhl.R;
import com.nhl.databinding.ActivityMainBindingImpl;
import com.nhl.domain.model.factory.TeamViewModel;
import com.nhl.model.team.Teams;
import com.nhl.presentation.AbstractNHLActivity;
import com.nhl.presentation.adapter.TeamAdapter;
import com.nhl.presentation.navigation.ui.home.HomeFragment;
import com.nhl.presentation.team.TeamActivity;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AbstractNHLActivity<TeamViewModel> {

    private AppBarConfiguration mAppBarConfiguration;
    private LinearLayoutManager layoutManager;

    private LiveData<Integer> positionLiveData;

    @BindView(R.id.teamView)
    RecyclerView teamView;


    @Inject
    TeamViewModel teamViewModel;

    @Inject
    @Named("TeamViewModel")
    ViewModelProvider.Factory factory;

    int position;

    @Override
    public TeamViewModel getViewModel() {
        teamViewModel = ViewModelProviders.of(this, factory).get(TeamViewModel.class);
        return teamViewModel;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind();
        getViewModel();

        ButterKnife.bind(this);

        positionLiveData = new MutableLiveData<Integer>(Integer.valueOf(1));
        positionLiveData.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                HomeFragment homeFragment = new HomeFragment(positionLiveData);
                FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_home, homeFragment);
                fragmentTransaction.commit();

            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TeamActivity.class);
                startActivity(intent);
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        teamView.setHasFixedSize(true);
        getTeams();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    Call<Teams> teams;

    public LiveData<String> getPosition() {
        LiveData<String> stringLiveData = new MutableLiveData<String>(String.valueOf(position));
        return stringLiveData;
    }

    public void getTeams() {


        teams = teamViewModel.teamService.getTeam();
        teams.enqueue(new Callback<Teams>() {
            @Override
            public void onResponse
                    (Call<Teams> call, Response<Teams> response) {
                if (response.isSuccessful()) {

                    Teams teamsResponse;
                    teamsResponse = response.body();
                    layoutManager = new LinearLayoutManager(MainActivity.this);
                    teamView.setLayoutManager(layoutManager);
                    teamView.setItemAnimator(new DefaultItemAnimator());
                    TeamAdapter teamAdapter = new TeamAdapter(teamsResponse.getTeams(), positionLiveData, MainActivity.this);
                    teamView.setAdapter(teamAdapter);


                } else {
                    // error response, no access to resource?
                }
            }

            @Override
            public void onFailure(Call<Teams> call, Throwable t) {

            }
        });
    }


    private void bind() {
        ActivityMainBindingImpl binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(teamViewModel);

    }

}
