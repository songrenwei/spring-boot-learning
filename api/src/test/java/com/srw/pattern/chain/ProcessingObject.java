package com.srw.pattern.chain;

/**
 * @Description:
 * @Author: renwei.song
 * @Date: 2021/4/12 15:02
 */
public abstract class ProcessingObject<T> {

    protected ProcessingObject<T> successor;

    public ProcessingObject<T> setSuccessor(ProcessingObject<T> successor){
        this.successor = successor;
        return this.successor;
    }

    public T handle(T input){
        T r = handleWork(input);
        if(successor != null){
            return successor.handle(r);
        }
        return r;
    }

    abstract protected T handleWork(T input);

    public void handle2() {
        this.handleWork2();
        if(successor != null){
            successor.handle2();
        }
    }

    abstract protected void handleWork2();

}
