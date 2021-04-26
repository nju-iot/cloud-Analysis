package com.example.cloudbiz1.controller;

import com.example.cloudbiz1.VO.PieVO;
import com.example.cloudbiz1.VO.TempNumVO;
import com.example.cloudbiz1.entity.DateMean;
import com.example.cloudbiz1.entity.Device;
import com.example.cloudbiz1.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String devicePage() {
        return "statistic";
    }

    @RequestMapping(value = "/initList", method = RequestMethod.GET)
    @ResponseBody
    public Integer initList() {
        long startTime = System.currentTimeMillis();
        System.out.println("init List ...");
        int res = deviceService.init_list();
        long endTime = System.currentTimeMillis();
        System.out.println("init 时间： " + (endTime - startTime) + " ms");
        return res;
    }

    @RequestMapping(value = "/meanByPlace", method = RequestMethod.POST)
    @ResponseBody
    public List<DateMean> meanByPlace(@RequestBody(required = false) String place) {
        return deviceService.queryDeviceByPlace(place);
    }


    @RequestMapping(value = "/InAndOut", method = RequestMethod.GET)
    @ResponseBody
    public List<PieVO> inAndOut() {
        return deviceService.queryDataDistribute();
    }

    @RequestMapping(value = "/tempNum", method = RequestMethod.POST)
    @ResponseBody
    public List<TempNumVO> getTempAndNum(@RequestBody(required = false) String place) {
        return deviceService.tempDistributeNum(place);
    }


    @RequestMapping(value = "/tempMeanScatter", method = RequestMethod.GET)
    @ResponseBody
    public List<List<Double>> getTempMeanScatter() {
        return deviceService.tempDistributeByScatter();
    }


}
