package com.tobz.clean.presentation.executor;

import com.tobz.clean.domain.executor.ThreadExecutor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Decorated {@link ThreadPoolExecutor}
 *
 */

@Singleton
public class JobExecutor implements ThreadExecutor {

    private final ThreadPoolExecutor threadPoolExecutor;

    // Number of thread to keep in the pool.
    private static final int CORE_POOL_SIZE = 3;
    // Maximum number of threads to allow in the pool.
    private static final int MAX_POOL_SIZE = 5;
    // The amount of time an idle thread waits before terminating
    private static final int KEEP_ALIVE_TIME = 10;

    @Inject
    public JobExecutor() {
        this.threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE,
                KEEP_ALIVE_TIME, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new JobThreadFactory());
    }

    @Override
    public void execute(Runnable runnable) {
        this.threadPoolExecutor.execute(runnable);
    }

    private static class JobThreadFactory implements ThreadFactory {
        private static final String THREAD_NAME = "android_";
        private int counter = 0;

        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, THREAD_NAME + counter++);
        }
    }
}
