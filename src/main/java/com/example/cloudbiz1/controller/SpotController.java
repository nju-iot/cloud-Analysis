package com.example.cloudbiz1.controller;

import com.example.cloudbiz1.VO.SpotVO;
import com.example.cloudbiz1.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author xmx
 * @date 2021/5/24
 **/

@Controller
@RequestMapping(value = "/spot")
public class SpotController {

    @Autowired
    public DeviceService deviceService;

    @RequestMapping(value = "/EmptySpot", method = RequestMethod.GET)
    @ResponseBody
    public List<List<SpotVO>> getEmptySpot(){
        return deviceService.emptySpotEveryTime();
    }
}
