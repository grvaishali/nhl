package com.nhlteam.presentation.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhlteam.R;
import com.nhlteam.constant.NHLConstants;
import com.nhlteam.data.roster.TeamRoster;
import com.nhlteam.data.team.Team;
import com.nhlteam.domain.viewmodel.PlayersViewModel;
import com.nhlteam.presentation.adapter.TeamPlayersAdapter;

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
 * Fragment to display players list
 */
public class PlayersFragment extends AbstractNHLFragment<PlayersViewModel> {

    @BindView(R.id.fragment_players_recyclerView_teamPlayers)
    RecyclerView teamPlayersRecyclerView;

    @BindView(R.id.fragment_players_textView_headerName)
    Button headerNameButton;

    @BindView(R.id.fragment_players_button_headerNumber)
    Button number;

    @BindView(R.id.fragment_players_editText_positionSearch)
    EditText searchFromTeamPlayerPosition;

    @BindView(R.id.fragment_players_textView_teamName)
    TextView teamName;

    @BindView(R.id.fragment_player_tableLayout_roster)
    TableLayout tableLayout;

    @BindView(R.id.fragment_players_imageView_default)
    ImageView defaultImageView;

    @BindView(R.id.fragment_players_textView_default)
    TextView defaultTextView;

    @Inject
    @Named("PlayersViewModel")
    ViewModelProvider.Factory factory;

    private LiveData<Team> currentTeamLiveDate;

    @Inject
    public PlayersViewModel playersViewModel;

    private boolean isNameSortOrderAscending;
    private boolean isNumberSortOrderAscending;
    private static TeamPlayersAdapter teamPlayersAdapter;
    private Call<TeamRoster> teamRosterCall;

    @Inject
    public PlayersFragment() {
    }

    public PlayersFragment(LiveData<Team> currentTeamLiveDate) {
        this.currentTeamLiveDate = currentTeamLiveDate;
    }

    @Override
    public PlayersViewModel getViewModel() {
        playersViewModel = ViewModelProviders.of(this, factory).get(PlayersViewModel.class);
        return playersViewModel;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_players, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Default values for sort
        isNameSortOrderAscending = true;
        isNumberSortOrderAscending = true;

        //Show default image and text, and hide table in case there is no team selected
        if (null == currentTeamLiveDate || null == currentTeamLiveDate.getValue()) {
            tableLayout.setVisibility(View.INVISIBLE);
            defaultImageView.setImageDrawable(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), android.R.drawable.ic_dialog_email));
            defaultImageView.setAlpha(0.5f);
            defaultImageView.setVisibility(View.VISIBLE);
            defaultTextView.setVisibility(View.VISIBLE);
        }
        //Hide default image and text, and show table in case there is no team selected
        else {
            teamName.setText(currentTeamLiveDate.getValue().getName());
            defaultImageView.setImageResource(android.R.color.transparent);
            defaultImageView.setVisibility(View.GONE);
            defaultTextView.setVisibility(View.GONE);
            tableLayout.setVisibility(View.VISIBLE);
            fetchTeamPlayers(currentTeamLiveDate.getValue().getId());
            attachNameSortListener();
            attachNumberSortListener();
            searchPosition();


        }
    }


    private void fetchTeamPlayers(int position) {
        teamRosterCall = playersViewModel.teamService.getTeamRoster(position);
        teamRosterCall.enqueue(new Callback<TeamRoster>() {
            @Override
            public void onResponse
                    (@NotNull Call<TeamRoster> call, @NotNull Response<TeamRoster> response) {
                if (response.isSuccessful()) {
                    TeamRoster teamRoster = response.body();
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    teamPlayersRecyclerView.setLayoutManager(layoutManager);
                    teamPlayersRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    teamPlayersAdapter = new TeamPlayersAdapter(Objects.requireNonNull(teamRoster).getRoster(), getActivity());
                    teamPlayersAdapter.sortByName(isNameSortOrderAscending);
                    teamPlayersRecyclerView.setAdapter(teamPlayersAdapter);
                } else {
                    if (response.errorBody() == null) throw new AssertionError();
                    Log.e("Team Players Call", response.errorBody().toString(), null);
                }
            }

            @Override
            public void onFailure(@NotNull Call<TeamRoster> call, @NotNull Throwable t) {
                Log.e("Team Players Call", t.getMessage(), t);
            }

        });
    }

    /**
     * Attach name sort listener on table
     */
    private void attachNameSortListener() {
        headerNameButton.setOnClickListener(v -> {
            teamPlayersAdapter.sortByName(isNameSortOrderAscending);
            isNameSortOrderAscending = !isNameSortOrderAscending;
        });
    }

    /**
     * Attach name number listener on table
     */
    private void attachNumberSortListener() {
        number.setOnClickListener(v -> {
            teamPlayersAdapter.sortByNumber(isNumberSortOrderAscending);
            isNumberSortOrderAscending = !isNumberSortOrderAscending;
        });
    }

    /**
     * Attach search filter on table
     */
    private void searchPosition() {
        searchFromTeamPlayerPosition.setHint(NHLConstants.ENTER_POSITION);
        searchFromTeamPlayerPosition.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                searchFromTeamPlayerPosition.setHint(NHLConstants.ENTER_POSITION);
            } else {
                searchFromTeamPlayerPosition.setHint("");
            }
        });
        searchFromTeamPlayerPosition.addTextChangedListener(new TextWatcher() {
            /**
             * Left empty as no behaviour is intended
             * @param s
             * @param start
             * @param count
             * @param after
             */
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            /**
             * Left empty as no behaviour is intended
             * @param s
             * @param start
             * @param before
             * @param count
             */
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