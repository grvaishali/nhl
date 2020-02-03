package com.nhlteam.presentation.activity;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Root activity for all the activities to facilitate dependency injection
 *
 * @param <T>
 */
public abstract class AbstractNHLActivity<T extends ViewModel> extends DaggerAppCompatActivity {

    private T viewModel;

    /**
     * @return view model instance
     */
    public abstract T getViewModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = viewModel == null ? getViewModel() : viewModel;
    }

    /**
     * Hides soft keyboard.
     */
    public void hideSoftKeyboard() {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }


}
