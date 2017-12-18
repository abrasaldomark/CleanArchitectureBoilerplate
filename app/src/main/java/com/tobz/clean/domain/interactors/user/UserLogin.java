package com.tobz.clean.domain.interactors.user;

import com.tobz.clean.domain.executor.PostExecutionThread;
import com.tobz.clean.domain.executor.ThreadExecutor;
import com.tobz.clean.domain.interactors.UseCase;
import com.tobz.clean.domain.repository.UserRepository;

import javax.inject.Inject;

import dagger.internal.Preconditions;
import io.reactivex.Observable;

public class UserLogin extends UseCase<Boolean, UserLogin.Params> {

    private UserRepository repository;

    @Inject
    public UserLogin(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, UserRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    public Observable<Boolean> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params);
        return repository.userLogin(params.username, params.password);
    }

    public static final class Params {
        private final String username;
        private final String password;

        private Params(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public static Params setParams(String username, String password) {
            return new Params(username, password);
        }
    }
}
