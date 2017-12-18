package com.tobz.clean.data.repository;

import com.tobz.clean.data.network.RestApi;
import com.tobz.clean.domain.repository.UserRepository;
import com.tobz.clean.util.PrefUtil;

import javax.inject.Inject;

import io.reactivex.Observable;

public class UserDataRepository implements UserRepository {

    private RestApi restApi;
    private PrefUtil prefUtil;

    @Inject
    public UserDataRepository(RestApi restApi, PrefUtil prefUtil) {
        this.restApi = restApi;
        this.prefUtil = prefUtil;
    }

    @Override
    public Observable<Boolean> userLogin(String username, String password) {
        return Observable.defer(() -> {
            // For testing purpose only.
            boolean result = false;
            String user = "test";
            String pass = "pass";
            if(username.equals(user) && password.equals(pass))
                result = true;
            return Observable.just(result);
        });
    }
}
