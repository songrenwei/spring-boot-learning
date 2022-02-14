package com.srw.common.component;

public interface Lock {

    void lock();

    boolean tryLock();

    void unlock();



}
