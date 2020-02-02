package com.nhl.presentation.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;

import dagger.android.support.DaggerFragment;

public abstract class AbstractNHLFragment<T extends ViewModel>extends DaggerFragment {
    private T viewModel;
    /**
     * @return view model instance
     */
    public abstract T getViewModel();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = viewModel == null ? getViewModel() : viewModel;
    }



}
