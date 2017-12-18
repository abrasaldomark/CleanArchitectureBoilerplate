package com.tobz.clean.presentation.views.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.tobz.clean.R;
import com.tobz.clean.presentation.views.activity.base.ActivityHandler;
import com.tobz.clean.presentation.views.presenter.LoginPresenter;
import com.tobz.clean.presentation.views.view.LoginView;

import java.util.UUID;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends ActivityHandler implements LoginView {

    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.username)
    EditText mUsername;
    @BindView(R.id.login_progress)
    View mProgressView;
    @BindView(R.id.login_form)
    View mLoginFormView;
    @BindView(R.id.login_activity)
    View mRootView;

    @Inject
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        // Initialize injector.
        initializeInjector();
        // Initialize view.
        initializePresenter();
    }

    /**
     * Initialize dagger component.
     */
    private void initializeInjector() {
        this.getActivityComponent().inject(this);
    }

    /**
     * Init view.
     */
    private void initializePresenter() {
        this.loginPresenter.attachView(this);
    }

    @OnClick(R.id.login_button)
    void onLogin() {
        attemptLogin();
    }

    /**
     * Login attempt.
     */
    private void attemptLogin() {
        // Reset errors.
        mUsername.setError(null);
        mPassword.setError(null);

        // Store values at the time of the login attempt.
        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check if password is empty.
        if (TextUtils.isEmpty(password)) {
            mPassword.setError(getString(R.string.error_field_required));
            focusView = mPassword;
            cancel = true;
        }

        // Check if username is empty.
        if (TextUtils.isEmpty(username)) {
            mUsername.setError(getString(R.string.error_field_required));
            focusView = mUsername;
            cancel = true;
        }

        if (cancel)
            focusView.requestFocus();
        else
            loginPresenter.login(username, password);
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    /**
     * Call intent in navigator.
     * @param context
     * @return
     */
    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    @Override
    public void showLoginProgress() {
        showProgress(true);
    }

    @Override
    public void hideLoginProgress() {
        showProgress(false);
    }

    @Override
    public void onErrorLogin(String message) {
        Runnable runnable = () -> Snackbar.make(mRootView, message, Snackbar.LENGTH_LONG).show();
        runOnUiThread(runnable);
    }

    @Override
    public void onSuccessLogin(Boolean val) {
        Runnable runnable = () -> {
            if(val) {
                // Generate dummy token.
                this.prefUtil.setPrefToken(UUID.randomUUID().toString());

                this.navigator.navigateToMain(this);
            }
            else
                Snackbar.make(mRootView, getString(R.string.error_login), Snackbar.LENGTH_LONG).show();
        };
        runOnUiThread(runnable);
    }
}

