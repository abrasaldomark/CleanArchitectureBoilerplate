package com.tobz.clean.presentation.injector.modules;

import android.content.Context;
import android.content.SharedPreferences;

import com.tobz.clean.CleanApplication;
import com.tobz.clean.data.network.RestApi;
import com.tobz.clean.data.repository.UserDataRepository;
import com.tobz.clean.domain.repository.UserRepository;
import com.tobz.clean.util.PrefUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Data module that provides object related to data storage.
 *
 */

@Module
public class DataModule {

    private final CleanApplication application;
    private static final String PREF_NAME = "PREFS";

    public DataModule(CleanApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    SharedPreferences providePreferences() {
        return application.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    UserRepository provideUserRepository(RestApi restApi, PrefUtil prefUtil) {
        return new UserDataRepository(restApi, prefUtil);
    }
}
