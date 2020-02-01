package com.nhl.di.modules;

import androidx.lifecycle.ViewModelProvider;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nhl.constants.RemoteConstants;
import com.nhl.data.rest.remote.RestApi;
import com.nhl.domain.model.factory.ViewModelProviderFactory;
import com.nhl.domain.repositories.TeamRepository;
import com.nhl.domain.repositories.impl.TeamRepositoryImpl;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private static int REQUEST_TIMEOUT = 10;
    private static OkHttpClient okHttpClient;

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder builder =
                new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return builder.setLenient().create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RemoteConstants.baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    @Singleton
    @Provides
    final OkHttpClient providesClient() {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .build();

        return okHttpClient;
    }


    @Singleton
    @Provides
    RestApi provideApiService(Retrofit retrofit) {
        return retrofit.create(RestApi.class);
    }




}

