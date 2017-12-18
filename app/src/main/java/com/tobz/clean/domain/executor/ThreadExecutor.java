package com.tobz.clean.domain.executor;

import java.util.concurrent.Executor;

/**
 * Executor implementation a technique of asynchronous execution.
 * Execution of {@link com.tobz.clean.domain.interactors.UseCase} will be out of UI thread.
 * But we can use other types of {@link io.reactivex.schedulers.Schedulers} aside from this executor.
 *
 */

public interface ThreadExecutor extends Executor {
}
