package com.tobz.clean;

import android.app.Application;

import com.tobz.clean.presentation.injector.components.ApplicationComponent;
import com.tobz.clean.presentation.injector.components.DaggerApplicationComponent;
import com.tobz.clean.presentation.injector.modules.ApplicationModule;
import com.tobz.clean.presentation.injector.modules.DataModule;
import com.tobz.clean.presentation.injector.modules.NetworkModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Ez select main application.
 *
 * Edited by tobz inspired by bbarbero 7/24/17.
 */

public class CleanApplication extends Application {

    /**
     * Dagger component.
     */
    private ApplicationComponent applicationComponent;

    /**
     * Realm db name.
     */
    private static final String db = "clean.realm";

    @Override
    public void onCreate() {
        super.onCreate();
        initRealmConfiguration();
        setupInjector();
    }

    /**
     * Initialize injector and create the app graph.
     */
    private void setupInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .dataModule(new DataModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    /**
     * Initialize realm configuration.
     */
    private void initRealmConfiguration() {
        Realm.init(this);
        RealmConfiguration config;
        // Migration config.
        /*config = new RealmConfiguration.Builder()
                .schemaVersion(0)
                .name(db)
                .migration(new Migration())
                .build();*/
        // Auto delete db.
        config = new RealmConfiguration.Builder()
                .name(db)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    /**
     * Application component.
     * @return component
     */
    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
