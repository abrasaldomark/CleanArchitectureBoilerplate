package com.tobz.clean.presentation.views.activity.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.tobz.clean.BuildConfig;
import com.tobz.clean.CleanApplication;
import com.tobz.clean.presentation.injector.components.ActivityComponent;
import com.tobz.clean.presentation.injector.components.ApplicationComponent;
import com.tobz.clean.presentation.injector.components.DaggerActivityComponent;
import com.tobz.clean.presentation.navigation.Navigator;
import com.tobz.clean.util.PrefUtil;

import javax.inject.Inject;

/**
 * Android base activity.
 *
 */

public abstract class BaseActivity extends AppCompatActivity {

    private ActivityComponent activityComponent;

    @Inject
    public PrefUtil prefUtil;
    @Inject
    public Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
        // Check version.
        checkVersion();
    }

    /**
     * Add fragments.
     * @param containerViewId
     * @param fragment
     * @param tag
     */
    protected void addFragment(int containerViewId, Fragment fragment, String tag, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment, tag);
        if(addToBackStack)
            fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

    /**
     * Get the fragment tag.
     * @param tag
     * @return
     */
    protected Fragment getCurrentFragmentByTag(String tag) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
        return fragment;
    }

    /**
     * Get the Main Application component for dependency injection.
     *
     * @return {@link ApplicationComponent}
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((CleanApplication) getApplication()).getApplicationComponent();
    }

    /**
     * Activity component.
     * @return {@link ActivityComponent}
     */
    protected ActivityComponent getActivityComponent() {
        activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(getApplicationComponent())
                .build();
        return activityComponent;
    }

    /**
     * Check app version.
     */
    private void checkVersion() {
        String version = this.prefUtil.getPrefAppVersion();
        if(version == null || version.isEmpty())
            this.prefUtil.setPrefAppVersion(BuildConfig.VERSION_NAME);
        else {
            String current = BuildConfig.VERSION_NAME;
            if(!current.equals(version)) {
                this.prefUtil.clearAll();
                this.navigator.navigateToLogin(this);
                finish();
            }
        }
    }
}
