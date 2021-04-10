package com.example.cloudbiz1.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author xmx
 * @date 2021/4/8
 **/
@Document(collection = "device")
public class Device {


    private String Id;

    private String date;

    private String temp;

    private String place;

    private String year;

    private String month;

    private String day;

    private String season;

    public Device(String Id, String date, String temp, String place, String year, String month, String day, String season) {
        this.Id = Id;
        this.date = date;
        this.temp = temp;
        this.place = place;
        this.year = year;
        this.month = month;
        this.day = day;
        this.season = season;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }
}
