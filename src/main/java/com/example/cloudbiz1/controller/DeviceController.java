package com.example.cloudbiz1.controller;

import com.example.cloudbiz1.VO.PieVO;
import com.example.cloudbiz1.VO.SpotVO;
import com.example.cloudbiz1.VO.TempNumVO;
import com.example.cloudbiz1.entity.DateMean;
import com.example.cloudbiz1.entity.Device;
import com.example.cloudbiz1.service.DeviceService;
import com.example.cloudbiz1.service.MQService;
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

    @Autowired
    public MQService mqService;


    @GetMapping()
    public String devicePage() {
        return "statistic";
    }

    /*
        初始化数组，但目前不被使用
     */
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


    /*
        用于生成折线图，统计室内外温度的变化
     */
    @RequestMapping(value = "/meanByPlace", method = RequestMethod.POST)
    @ResponseBody
    public List<DateMean> meanByPlace(@RequestBody(required = false) String place) {
        return deviceService.queryDeviceByPlace(place);
    }

    /*
        用于生成饼图，统计室内外温度的占比
     */
    @RequestMapping(value = "/InAndOut", method = RequestMethod.GET)
    @ResponseBody
    public List<PieVO> inAndOut() {
        return deviceService.queryDataDistribute();
    }

    /*
        用于生成柱状图，统计温度的出现次数
     */
    @RequestMapping(value = "/tempNum", method = RequestMethod.POST)
    @ResponseBody
    public List<TempNumVO> getTempAndNum(@RequestBody(required = false) String place) {
        return deviceService.tempDistributeNum(place);
    }

    /*
        用于生成散点图 和 回归分析图
     */
    @RequestMapping(value = "/tempMeanScatter", method = RequestMethod.GET)
    @ResponseBody
    public List<List<Double>> getTempMeanScatter() {
        return deviceService.tempDistributeByScatter();
    }


    @RequestMapping(value = "/testMQ",method = RequestMethod.GET)
    @ResponseBody
    public void testProduce(){
        mqService.produceMessage();
    }



    /*

        返回当前数据总量
     */
    @RequestMapping(value = "/allDataNum",method = RequestMethod.GET)
    @ResponseBody
    public String getAllDataNum(){
        return null;
    }


    /*
        返回当前最新温度
     */
    @RequestMapping(value = "/newTemper",method = RequestMethod.GET)
    @ResponseBody
    public String getNewTemper(){
        return null;
    }


    /*


     */
    @RequestMapping(value = "/getEmptySpot", method = RequestMethod.GET)
    @ResponseBody
    public List<List<SpotVO>> getEmptySpot(){
        return deviceService.emptySpotEveryTime();
    }




}
