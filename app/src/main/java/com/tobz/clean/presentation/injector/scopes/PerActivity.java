package com.tobz.clean.presentation.injector.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * A scope annotation to permit objects whose lifetime should conform
 * to the life of the activity.
 *
 * Created by bbarbero on 7/24/17.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
