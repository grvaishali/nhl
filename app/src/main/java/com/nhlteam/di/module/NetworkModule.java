package com.nhlteam.di.module;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nhlteam.constant.NHLConstants;
import com.nhlteam.rest.NHLRestService;
import com.nhlteam.util.ConfigUtil;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.nhlteam.constant.NHLConstants.REQUEST_TIMEOUT;

@Module
public class NetworkModule {


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
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient, @Named("NHLTeamApplicationContext") Context context) {
        return new Retrofit.Builder()
                .baseUrl(ConfigUtil.getProperty(NHLConstants.NHL_API_BASE_URL, context))
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    final OkHttpClient providesClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();

        return okHttpClient;
    }


    @Singleton
    @Provides
    NHLRestService provideApiService(Retrofit retrofit) {
        return retrofit.create(NHLRestService.class);
    }


}

