package com.example.cloudbiz1.service;

import com.example.cloudbiz1.VO.PieVO;
import com.example.cloudbiz1.VO.SpotVO;
import com.example.cloudbiz1.VO.TempNumVO;
import com.example.cloudbiz1.VO.TimeDataVO;
import com.example.cloudbiz1.entity.DateMean;
import com.example.cloudbiz1.entity.Device;

import java.util.List;
import java.util.Map;

/**
 * @author xmx
 * @date 2021/4/8
 **/
public interface DeviceService {

    Integer init_list();

    List<DateMean> queryDeviceByPlace(String place);

    List<PieVO> queryDataDistribute();

    List<TempNumVO> tempDistributeNum(String place);

    List<List<Double>> tempDistributeByScatter();

    List<List<SpotVO>> emptySpotEveryTime();

    void update_data(String date,String temp,String name,String lng, String lat);

    int getSizeOfAllData();

    TimeDataVO getNewData();


}
