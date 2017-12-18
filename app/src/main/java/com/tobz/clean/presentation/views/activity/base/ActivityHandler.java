package com.tobz.clean.presentation.views.activity.base;


import com.tobz.clean.data.network.error.RestApiException;
import com.tobz.clean.presentation.views.view.View;

/**
 * Handler class for error.
 */

public abstract class ActivityHandler extends BaseActivity implements View {

    public void redirectToLogin() {
        //this.prefUtil.removeUserCredentials();
        this.navigator.navigateToLogin(this);
        finish();
    }

    @Override
    public void handleError(Throwable e) {
        if(e instanceof RestApiException) {
            switch (((RestApiException) e).getStatusCode()) {
                case RestApiException.UNAUTHORIZED:
                    redirectToLogin();
                    break;
            }
        }
    }
}
