package com.tobz.clean.presentation.navigation;

import android.content.Context;
import android.content.Intent;

import com.tobz.clean.presentation.views.activity.LoginActivity;
import com.tobz.clean.presentation.views.activity.MainActivity;

/**
 * Class used to navigate through the application.
 *
 */

public class Navigator {

    private static final Navigator instance = new Navigator();

    /**
     * No need to instantiate
     */
    private Navigator() {}

    /**
     * Instantiation of class.
     * @return
     */
    public static Navigator getInstance() {
        return instance;
    }

    /**
     * Navigate to login.
     * @param context
     */
    public static void navigateToLogin(Context context) {
        if(context != null) {
            Intent intentToLaunch = LoginActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    /**
     * Navigate to main activity.
     * @param context
     */
    public static void navigateToMain(Context context) {
        if(context != null) {
            Intent intentToLaunch = MainActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }
}