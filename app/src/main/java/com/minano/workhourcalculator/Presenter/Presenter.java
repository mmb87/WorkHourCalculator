package com.minano.workhourcalculator.Presenter;

/**
 * Created by Admin on 31/03/2017.
 */
public interface Presenter<T> {
    void setView(T view);
    void detachView();
}
