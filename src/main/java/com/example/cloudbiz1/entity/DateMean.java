package com.example.cloudbiz1.entity;

/**
 * @author xmx
 * @date 2021/4/9
 **/
public class DateMean {

    private String date;

    private double mean;

    public DateMean(String date, double mean) {
        this.date = date;
        this.mean = mean;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }
}
