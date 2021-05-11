package com.example.cloudbiz1.VO;

/**
 * @author xmx
 * @date 2021/5/11
 **/
public class SpotVO {

    private Integer line;

    private Integer num;


    public SpotVO(Integer line, Integer num) {
        this.line = line;
        this.num = num;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

}
