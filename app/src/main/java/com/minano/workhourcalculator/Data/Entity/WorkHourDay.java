package com.minano.workhourcalculator.Data.Entity;

/**
 * Created by Admin on 24/12/2016.
 */
public class WorkHourDay {
    private int id;
    private String numberOfWorkHours;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumberOfWorkHours() {
        return numberOfWorkHours;
    }

    public void setNumberOfWorkHours(String numberOfWorkHours) {
        this.numberOfWorkHours = numberOfWorkHours;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
