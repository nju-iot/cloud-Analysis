package com.example.cloudbiz1.service.impl;

import com.example.cloudbiz1.dao.DeviceRepository;
import com.example.cloudbiz1.entity.DateMean;
import com.example.cloudbiz1.entity.Device;
import com.example.cloudbiz1.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @author xmx
 * @date 2021/4/8
 **/
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    DeviceRepository deviceRepository;

    public List<Device> queryAll(){
        List<Device> list = deviceRepository.findAll();
        List<Device> res = new ArrayList<>();
        System.out.println(list.size());
        for(int i=0;i<10;i++) res.add(list.get(i));
        return res;
    }

    public List<DateMean> getMeanTempEveryDay(){
        List<Device> list = deviceRepository.findAll();
        Map<String,Integer> dateTotal = new HashMap<>();
        Map<String, Integer> dateNum = new HashMap<>();
        for(Device device : list){
            String date = device.getDate().substring(0,10);
            dateTotal.put(date, dateTotal.get(date)==null ? Integer.parseInt(device.getTemp()) : dateTotal.get(date) + Integer.parseInt(device.getTemp()));
            dateNum.put(date, dateNum.get(date)==null? 0 : dateNum.get(date) + 1);
        }
        List<DateMean> res = new ArrayList<>();
        Iterator iter = dateTotal.entrySet().iterator();
        while(iter.hasNext()){
            DecimalFormat df = new DecimalFormat("0.000");
            Map.Entry<String, Integer> entry = (Map.Entry) iter.next();
            double mean = entry.getValue().doubleValue() / dateNum.get(entry.getKey()).doubleValue();
            DateMean dateMean = new DateMean(entry.getKey(), Double.parseDouble(df.format(mean)));
            res.add(dateMean);
        }
        return res;
    }

}
