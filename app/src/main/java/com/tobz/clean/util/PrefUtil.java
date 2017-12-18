package com.tobz.clean.util;

import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * Storing data in {@link android.content.SharedPreferences}
 *
 * Created by bbarbero on 7/24/17.
 */

public class PrefUtil {

    private SharedPreferences preferences;

    public static final String PREF_APP_VERSION = "app_version";
    public static final String PREF_TOKEN = "token";

    @Inject
    public PrefUtil(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public void removeItem(String key) {
        preferences.edit().remove(key).commit();
    }

    public void clearAll() {
        preferences.edit().clear().commit();
    }

    public void setPrefAppVersion(String val) {
        preferences.edit().putString(PREF_APP_VERSION, val).commit();
    }

    public String getPrefAppVersion() {
        return preferences.getString(PREF_APP_VERSION, null);
    }

    public void setPrefToken(String value) {
        preferences.edit().putString(PREF_TOKEN, value).commit();
    }

    public String getPrefToken() {
        return preferences.getString(PREF_TOKEN, null);
    }
}
