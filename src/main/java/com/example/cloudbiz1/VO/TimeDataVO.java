package com.example.cloudbiz1.VO;

import java.sql.Timestamp;

/**
 * @author xmx
 * @date 2021/5/17
 **/
public class TimeDataVO {

    private String id;

    private String device_name;

    private int temp;

    private String lng;

    private String lat;

    private Timestamp time;

    public TimeDataVO(){

    }
    public TimeDataVO(String id, String device_name, int temp, String lng, String lat, Timestamp time) {
        this.id = id;
        this.device_name = device_name;
        this.temp = temp;
        this.lng = lng;
        this.lat = lat;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
