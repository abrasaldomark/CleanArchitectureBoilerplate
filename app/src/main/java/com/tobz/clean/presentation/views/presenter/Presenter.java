package com.tobz.clean.presentation.views.presenter;

import android.support.annotation.NonNull;

import com.tobz.clean.presentation.views.view.View;

/**
 * Interface representing a Presenter in a model view presenter (MVP) pattern.
 *
 */

public interface Presenter<T extends View> {
    void onResume();
    void onDestroy();
    void onPause();
    void attachView(@NonNull T view);
}
