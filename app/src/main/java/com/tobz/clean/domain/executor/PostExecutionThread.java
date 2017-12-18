package com.tobz.clean.domain.executor;


import io.reactivex.Scheduler;

public interface PostExecutionThread {
    Scheduler getScheduler();
}
