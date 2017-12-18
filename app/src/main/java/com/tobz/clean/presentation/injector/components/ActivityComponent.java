package com.tobz.clean.presentation.injector.components;

import com.tobz.clean.presentation.injector.scopes.PerActivity;
import com.tobz.clean.presentation.views.activity.LoginActivity;

import dagger.Component;

/**
 * A base component for activity component.
 *
 * Decorated with annotation {@link com.tobz.clean.presentation.injector.scopes.PerActivity}.
 *
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class)
public interface ActivityComponent {
    // Activities.
    void inject(LoginActivity loginActivity);
}
