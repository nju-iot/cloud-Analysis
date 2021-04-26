package com.example.cloudbiz1.VO;

/**
 * @author xmx
 * @date 2021/4/19
 **/

public class PieVO {

    private int value;

    private String name;

    public PieVO(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
