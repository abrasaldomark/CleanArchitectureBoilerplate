package com.tobz.clean.presentation.views.presenter;


import android.support.annotation.NonNull;

import com.tobz.clean.domain.interactors.user.UserLogin;
import com.tobz.clean.presentation.injector.scopes.PerActivity;
import com.tobz.clean.presentation.views.view.LoginView;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

@PerActivity
public class LoginPresenter implements Presenter<LoginView> {

    private LoginView loginView;
    private UserLogin userLogin;

    @Inject
    public LoginPresenter(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {
        userLogin.dispose();
        loginView = null;
    }

    @Override
    public void onPause() {

    }

    @Override
    public void attachView(@NonNull LoginView view) {
        this.loginView = view;
    }

    public void login(@NonNull String username, @NonNull String password) {
        this.userLogin.execute(new LoginObserver(), UserLogin.Params.setParams(username, password));
    }

    private class LoginObserver extends DisposableObserver<Boolean> {

        @Override
        public void onStart() {
            loginView.showLoginProgress();
        }

        @Override
        public void onNext(Boolean val) {
            loginView.onSuccessLogin(val);
        }

        @Override
        public void onError(Throwable e) {
            loginView.hideLoginProgress();
            loginView.onErrorLogin(e.getMessage());
        }

        @Override
        public void onComplete() {
            loginView.hideLoginProgress();
        }
    }
}
