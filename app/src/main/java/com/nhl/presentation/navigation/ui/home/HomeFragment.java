package com.nhl.presentation.navigation.ui.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhl.R;
import com.nhl.model.team.roster.TeamRoster;
import com.nhl.presentation.adapter.TeamAdapter;
import com.nhl.presentation.adapter.TeamPlayersAdapter;
import com.nhl.presentation.fragment.AbstractNHLFragment;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends AbstractNHLFragment<HomeViewModel> {

    @BindView(R.id.teamPlayers)
    RecyclerView teamPlayerView;

    @BindView(R.id.teamPlayer_name)
    Button name;

    @BindView(R.id.teamPlayer_number)
    Button number;

    @BindView(R.id.editText_items_search)
    EditText searchPosition;

    @Inject
    @Named("HomeViewModel")
    ViewModelProvider.Factory factory;

    LiveData<Integer> positionLiveData;

    @Inject
    public HomeViewModel homeViewModel;

    HomeFragment homeFragment;
    boolean nameSortOrderAcending;
    boolean numberSortOrderAcending;

    @Inject
    public HomeFragment() {

    }

    public HomeFragment(LiveData<Integer> positionLiveData) {
        this.positionLiveData = positionLiveData;
    }

    @Inject
    TeamAdapter adapter;

    public static TeamPlayersAdapter teamPlayersAdapter;

    @Override
    public HomeViewModel getViewModel() {
        homeViewModel = ViewModelProviders.of(this, factory).get(HomeViewModel.class);
        return homeViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    int id = 1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nameSortOrderAcending=true;
        numberSortOrderAcending =true;
        if (null == positionLiveData || null == positionLiveData.getValue()) {
            return;
        }
        getTeamPlayers(Integer.parseInt(String.valueOf(positionLiveData.getValue())));
        attachNameSortListener();
        attachNumberSortListener();
        searchPosition();
    }


    Call<TeamRoster> teamRosterCall;

    public void getTeamPlayers(int position) {
        teamRosterCall = homeViewModel.teamService.getTeamRoster(position);

        teamRosterCall.enqueue(new Callback<TeamRoster>() {
            @Override
            public void onResponse
                    (Call<TeamRoster> call, Response<TeamRoster> response) {
                if (response.isSuccessful()) {
                    TeamRoster teamRoster = response.body();
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    teamPlayerView.setLayoutManager(layoutManager);
                    teamPlayerView.setItemAnimator(new DefaultItemAnimator());
                    teamPlayersAdapter = new TeamPlayersAdapter(teamRoster.getRoster(), getActivity());
                    teamPlayersAdapter.sortByName(nameSortOrderAcending);
                    teamPlayerView.setAdapter(teamPlayersAdapter);


                } else {
                    Log.e("Team Players Call", response.errorBody().toString(), null);
                }
            }

            @Override
            public void onFailure(Call<TeamRoster> call, Throwable t) {
                Log.e("Team Players Call", t.getMessage(), t);
            }

        });
    }

    public static void refreshAdapter() {
        teamPlayersAdapter.notifyDataSetChanged();
    }

    public void attachNameSortListener() {
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamPlayersAdapter.sortByName(nameSortOrderAcending);
                nameSortOrderAcending = !nameSortOrderAcending;
            }
        });
    }

    public void attachNumberSortListener() {
        number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamPlayersAdapter.sortByNumber(numberSortOrderAcending);
                numberSortOrderAcending = !numberSortOrderAcending;
            }
        });
    }

    public void searchPosition(){
        searchPosition.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                teamPlayersAdapter.filterPlayers(s.toString());
            }
        });
    }
}