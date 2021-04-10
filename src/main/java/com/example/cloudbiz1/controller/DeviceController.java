package com.example.cloudbiz1.controller;

import com.example.cloudbiz1.entity.DateMean;
import com.example.cloudbiz1.entity.Device;
import com.example.cloudbiz1.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author xmx
 * @date 2021/4/9
 **/

@Controller
@RequestMapping(value = "/device")
public class DeviceController {

    @Autowired
    public DeviceService deviceService;

    @GetMapping()
    public String devicePage(){
        return "statistic";
    }

    @RequestMapping(value = "/meanAndDate", method = RequestMethod.GET)
    @ResponseBody
    public List<DateMean> meanAndDate(){
        return deviceService.getMeanTempEveryDay();
    }


    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public List<Device> getAllDevice(){
        return deviceService.queryAll();
    }


}
