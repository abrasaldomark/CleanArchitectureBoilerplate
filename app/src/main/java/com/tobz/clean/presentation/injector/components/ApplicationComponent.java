package com.tobz.clean.presentation.injector.components;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.tobz.clean.data.network.RestApi;
import com.tobz.clean.data.network.interceptor.HttpInterceptor;
import com.tobz.clean.domain.executor.PostExecutionThread;
import com.tobz.clean.domain.executor.ThreadExecutor;
import com.tobz.clean.domain.repository.UserRepository;
import com.tobz.clean.presentation.injector.modules.ApplicationModule;
import com.tobz.clean.presentation.injector.modules.DataModule;
import com.tobz.clean.presentation.injector.modules.NetworkModule;
import com.tobz.clean.presentation.navigation.Navigator;
import com.tobz.clean.presentation.views.activity.base.BaseActivity;
import com.tobz.clean.util.PrefUtil;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;

/**
 * A dagger component whose lifetime is the duration of application.
 *
 */

@Singleton
@Component(modules = {ApplicationModule.class, DataModule.class, NetworkModule.class})
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);
    Context context();
    PostExecutionThread postExecutionThread();
    ThreadExecutor threadExecutor();
    Navigator navigator();
    SharedPreferences sharedPreferences();
    PrefUtil prefUtil();
    HttpInterceptor httpInterceptor();
    RestApi restApi();
    Gson gson();
    OkHttpClient okHttpClient();
    UserRepository userRepository();
}
