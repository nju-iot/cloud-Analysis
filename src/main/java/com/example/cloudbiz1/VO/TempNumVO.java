package com.example.cloudbiz1.VO;

/**
 * @author xmx
 * @date 2021/4/21
 **/
public class TempNumVO {

    private String temp;

    private Integer num;

    public TempNumVO(String temp, Integer num) {
        this.temp = temp;
        this.num = num;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
