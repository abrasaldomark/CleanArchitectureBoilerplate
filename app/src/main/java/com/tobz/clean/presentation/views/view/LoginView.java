package com.tobz.clean.presentation.views.view;


public interface LoginView extends View {
    void showLoginProgress();
    void hideLoginProgress();
    void onErrorLogin(String message);
    void onSuccessLogin(Boolean val);
}
