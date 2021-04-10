package com.example.cloudbiz1.service;

import com.example.cloudbiz1.entity.DateMean;
import com.example.cloudbiz1.entity.Device;

import java.util.List;
import java.util.Map;

/**
 * @author xmx
 * @date 2021/4/8
 **/
public interface DeviceService {
    List<Device> queryAll();

    List<DateMean> getMeanTempEveryDay();
}
