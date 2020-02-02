package com.nhlteam.presentation.team;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.nhlteam.R;
import com.nhlteam.databinding.ActivityTeamBinding;
import com.nhlteam.domain.model.factory.TeamViewModel;
import com.nhlteam.model.team.people.PeopleDetails;
import com.nhlteam.presentation.AbstractNHLActivity;
import com.nhlteam.presentation.CountryUtil;
import com.nhlteam.presentation.ImageUtil;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamActivity extends AbstractNHLActivity<TeamViewModel> {

    @BindView(R.id.country_name)
    TextView countryName;
    @BindView(R.id.flag_image)
    ImageView flag;

    @Inject
    TeamViewModel teamViewModel;

    @Inject
    @Named("TeamViewModel")
    ViewModelProvider.Factory factory;

    @Override
    public TeamViewModel getViewModel() {
        teamViewModel = ViewModelProviders.of(this, factory).get(TeamViewModel.class);
        return teamViewModel;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind();
        ButterKnife.bind(this);
       getViewModel();
       getTeams(8471233);
    }


    Call<PeopleDetails> people;

    public void getTeams(int position) {
        people = teamViewModel.teamService.getPeople(position);
        people.enqueue(new Callback<PeopleDetails>() {
            @Override
            public void onResponse
                    (Call<PeopleDetails> call, Response<PeopleDetails> response) {
                if (response.isSuccessful()) {
                    PeopleDetails people;
                    people = (PeopleDetails) response.body();

                    countryName.setText(people.getPeople().get(0).getNationality());
                    ImageUtil.fetchJpg(flag.getContext(),"https://www.countryflags.io/"+ CountryUtil.iso3CountryCodeToIso2CountryCode(people.getPeople().get(0).getNationality()) +"/flat/64.png",flag);

                } else {
                    // error response, no access to resource?
                }
            }

            @Override
            public void onFailure(Call<PeopleDetails> call, Throwable t) {
                Log.e("Person Details", "onFailure: ", t);
            }
        });
    }

    private void bind() {
     ActivityTeamBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_team);
        //teamViewModel = ViewModelProviders.of(this).get(TeamViewModel.class);
        binding.setViewModel(teamViewModel);

    }

}
