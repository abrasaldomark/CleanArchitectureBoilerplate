package com.tobz.clean.presentation.injector.modules;

import android.content.Context;

import com.tobz.clean.CleanApplication;
import com.tobz.clean.domain.executor.PostExecutionThread;
import com.tobz.clean.domain.executor.ThreadExecutor;
import com.tobz.clean.presentation.executor.JobExecutor;
import com.tobz.clean.presentation.executor.UIThread;
import com.tobz.clean.presentation.navigation.Navigator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger Module that provide objects which will leave during application lifecycle.
 *
 */

@Module
public class ApplicationModule {

    private final CleanApplication application;

    public ApplicationModule(CleanApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return this.application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    Navigator provideNavigator() {
        return Navigator.getInstance();
    }
}
