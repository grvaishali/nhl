package com.nhlteam.presentation.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.nhlteam.R;

import com.nhlteam.constant.NHLConstants;
import com.nhlteam.databinding.ActivityPlayerBinding;
import com.nhlteam.domain.viewmodel.TeamViewModel;
import com.nhlteam.data.people.PeopleDetails;
import com.nhlteam.util.ConfigUtil;
import com.nhlteam.util.CountryUtil;
import com.nhlteam.util.ImageUtil;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Player country details activity
 */
public class PlayerActivity extends AbstractNHLActivity<TeamViewModel> {

    @BindView(R.id.activity_player_textView_country)
    TextView playerCountryName;
    @BindView(R.id.activity_player_imageView_flag)
    ImageView playerCountryFlag;
    @Inject
    TeamViewModel teamViewModel;
    @Inject
    @Named("TeamViewModel")
    ViewModelProvider.Factory factory;
    Call<PeopleDetails> people;

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
        fetchPlayerDetails(8471233);
    }


    /**
     * Fetches the details of player and displays country flag
     *
     * @param position
     */
    public void fetchPlayerDetails(int position) {
        people = teamViewModel.teamService.getPeople(position);
        people.enqueue(new Callback<PeopleDetails>() {
            @Override
            public void onResponse
                    (@NotNull Call<PeopleDetails> call, @NotNull Response<PeopleDetails> response) {
                if (response.isSuccessful()) {
                    PeopleDetails peopleDetails;
                    peopleDetails = response.body();
                    if (null != peopleDetails.getPeople() && !peopleDetails.getPeople().isEmpty()) {
                        playerCountryName.setText(peopleDetails.getPeople().get(0).getNationality());
                        ImageUtil.fetchJpg(playerCountryFlag.getContext(), ConfigUtil.getProperty(NHLConstants.FLAG_IMAGE_BASE_URL,playerCountryFlag.getContext()) + CountryUtil.iso3CountryCodeToIso2CountryCode(peopleDetails.getPeople().get(0).getNationality()) + ConfigUtil.getProperty(NHLConstants.FLAG_IMAGE_SUFFIX,playerCountryFlag.getContext()), playerCountryFlag);
                    }
                } else {
                    if (response.errorBody() == null) throw new AssertionError();
                    Log.e("Person Details", response.errorBody().toString(), null);
                }
            }

            @Override
            public void onFailure(@NotNull Call<PeopleDetails> call,@NotNull Throwable t) {
                Log.e("Person Details", "onFailure: ", t);
            }
        });
    }

    private void bind() {
        ActivityPlayerBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_player);
        binding.setViewModel(teamViewModel);
    }

}
