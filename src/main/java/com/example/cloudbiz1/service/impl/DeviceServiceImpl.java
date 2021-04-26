package com.example.cloudbiz1.service.impl;

import com.example.cloudbiz1.VO.PieVO;
import com.example.cloudbiz1.VO.TempNumVO;
import com.example.cloudbiz1.dao.DeviceRepository;
import com.example.cloudbiz1.entity.DateMean;
import com.example.cloudbiz1.entity.Device;
import com.example.cloudbiz1.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.*;
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


    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://139.196.202.149:53306/nju_iot?useSSL=FALSE&serverTimezone=UTC";
    private String username = "root";
    private String password = "123456";


    public Integer init_list() {
        /*
        global_listIn = deviceRepository.findDeviceByPlace("In");
        global_listOut = deviceRepository.findDeviceByPlace("Out");
        if(global_listIn.size()>0 && global_listOut.size()>0) return 1;
        else return 0;
        */
        return 0;
    }

    public List<DateMean> queryDeviceByPlace(String place) {
        List<DateMean> list = new ArrayList<>();
        try{
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            if(!connection.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            PreparedStatement statement = connection.prepareStatement("select date, mean from date_temp where place = ?");//操作数据库
            statement.setString(1, place);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                DateMean dateMean = new DateMean(rs.getString("date"), rs.getDouble("mean"));
                list.add(dateMean);
            }
            System.out.println("List size : " + list.size());
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        list.sort(new Comparator<DateMean>() {

            @Override
            public int compare(DateMean d1, DateMean d2) {  //已经是年月日的形式
                String date1 = d1.getDate();
                String date2 = d2.getDate();
                int year1 = Integer.parseInt(date1.substring(0, 4));
                int month1 = Integer.parseInt(date1.substring(5, 7));
                int day1 = Integer.parseInt(date1.substring(8));

                int year2 = Integer.parseInt(date2.substring(0, 4));
                int month2 = Integer.parseInt(date2.substring(5, 7));
                int day2 = Integer.parseInt(date2.substring(8));

                if (month1 > month2) {
                    return 1;
                } else if (month1 == month2 && day1 > day2) {
                    return 1;
                } else if (month1 == month2 && day1 < day2) {
                    return -1;
                } else {
                    return -1;
                }
            }
        });
        return list;
    }

    public List<PieVO> queryDataDistribute() {
        /*
        List<Device> listIn;
        List<Device> listOut;

        if(global_listIn.size() > 0) {
            listIn = global_listIn;
            System.out.println("queryDataDistribute   用了全局");
        }
        else {
            listIn = deviceRepository.findDeviceByPlace("In");
            System.out.println("queryDataDistribute  没用全局");
        }

        if(global_listOut.size() > 0) {
            listOut = global_listOut;
            System.out.println("queryDataDistribute  用了全局");
        }
        else {
            listOut = deviceRepository.findDeviceByPlace("Out");
            System.out.println("queryDataDistribute   没用全局");
        }

        List<PieVO> res = new ArrayList<>();
        res.add(new PieVO(listIn.size(), "In"));
        res.add(new PieVO(listOut.size(), "Out"));
        */


        List<PieVO> res = new ArrayList<>();
        res.add(new PieVO(20345, "In"));
        res.add(new PieVO(77261, "Out"));
        return res;
    }

    public List<TempNumVO> tempDistributeNum(String place) {
        List<Device> list;
        List<TempNumVO> res = new ArrayList<>();
        if (place.equals("In")) {
            res.add(new TempNumVO("21", 1));
            res.add(new TempNumVO("22", 18));
            res.add(new TempNumVO("23", 4));
            res.add(new TempNumVO("24", 61));
            res.add(new TempNumVO("25", 197));
            res.add(new TempNumVO("26", 377));
            res.add(new TempNumVO("27", 1151));
            res.add(new TempNumVO("28", 2105));
            res.add(new TempNumVO("29", 3497));
            res.add(new TempNumVO("30", 2656));
            res.add(new TempNumVO("31", 3146));
            res.add(new TempNumVO("32", 3587));
            res.add(new TempNumVO("33", 1732));
            res.add(new TempNumVO("34", 1118));
            res.add(new TempNumVO("35", 619));
            res.add(new TempNumVO("36", 56));
            res.add(new TempNumVO("39", 0));
            res.add(new TempNumVO("40", 1));
            res.add(new TempNumVO("41", 0));
            return res;
        }

        if (place.equals("Out")) {
            res.add(new TempNumVO("44", 1773));
            res.add(new TempNumVO("45", 1507));
            res.add(new TempNumVO("46", 1200));
            res.add(new TempNumVO("24", 3));
            res.add(new TempNumVO("25", 25));
            res.add(new TempNumVO("47", 1043));
            res.add(new TempNumVO("26", 320));
            res.add(new TempNumVO("48", 970));
            res.add(new TempNumVO("27", 3478));
            res.add(new TempNumVO("49", 400));
            res.add(new TempNumVO("28", 6724));
            res.add(new TempNumVO("29", 4423));
            res.add(new TempNumVO("50", 54));
            res.add(new TempNumVO("51", 1));
            res.add(new TempNumVO("30", 3956));
            res.add(new TempNumVO("31", 4088));
            res.add(new TempNumVO("32", 1819));
            res.add(new TempNumVO("33", 1703));
            res.add(new TempNumVO("34", 1493));
            res.add(new TempNumVO("35", 961));
            res.add(new TempNumVO("36", 3907));
            res.add(new TempNumVO("37", 5722));
            res.add(new TempNumVO("38", 3866));
            res.add(new TempNumVO("39", 10201));
            res.add(new TempNumVO("40", 7795));
            res.add(new TempNumVO("41", 4352));
            res.add(new TempNumVO("42", 3446));
            res.add(new TempNumVO("43", 2003));
            return res;
        }
        /*
        if(place.equals("In") && global_listIn.size()>0) {
            list = global_listIn;
            System.out.println("tempDistributeNum  用了全局");
        }
        else if(place.equals("Out") && global_listOut.size()>0) {
            list = global_listOut;
            System.out.println("tempDistributeNum  用了全局");
        }
        else {
            list = deviceRepository.findDeviceByPlace(place);
            System.out.println("tempDistributeNum  没用全局");
        }

        Map<String, Integer> tempType = new HashMap<>();
        for(Device device : list){
            tempType.put(device.getTemp(), tempType.get(device.getTemp())==null ? 0 : tempType.get(device.getTemp()) + 1);
        }
        Iterator iter = tempType.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry<String, Integer> entry = (Map.Entry) iter.next();
            TempNumVO tempNumVO = new TempNumVO(entry.getKey(), entry.getValue());
            res.add(tempNumVO);
        }
        return res;
        */
        return res;
    }

    /*
        生成散点图需要的数据
     */
    public List<List<Double>> tempDistributeByScatter() {
        List<DateMean> inRes = queryDeviceByPlace("In");
        List<DateMean> outRes = queryDeviceByPlace("Out");


        Map<String, Double> inMap = new HashMap<>();
        Map<String, Double> outMap = new HashMap<>();

        for (DateMean dateMean : inRes) inMap.put(dateMean.getDate(), dateMean.getMean());
        for (DateMean dateMean : outRes) outMap.put(dateMean.getDate(), dateMean.getMean());

        Iterator iter = inMap.entrySet().iterator();
        List<List<Double>> res = new ArrayList<>();
        while (iter.hasNext()) {
            Map.Entry<String, Double> entry = (Map.Entry) iter.next();
            if (outMap.get(entry.getKey()) == null) {
                continue;
            } else {
                Double inMean = entry.getValue();
                Double outMean = outMap.get(entry.getKey());
                List<Double> temp_list = new ArrayList<>();
                temp_list.add(inMean);
                temp_list.add(outMean);
                res.add(temp_list);
            }
        }
        return res;
    }

    private List<DateMean> getTempMeanByDate(List<Device> list) {
        Map<String, Integer> dateTotal = new HashMap<>();
        Map<String, Integer> dataNum = new HashMap<>();

        for (Device device : list) {
            String date = device.getDate().substring(0, 10);
            dateTotal.put(date, dateTotal.get(date) == null ? Integer.parseInt(device.getTemp()) : dateTotal.get(date) + Integer.parseInt(device.getTemp()));
            dataNum.put(date, dataNum.get(date) == null ? 0 : dataNum.get(date) + 1);
        }
        Iterator iter = dateTotal.entrySet().iterator();
        List<DateMean> res = new ArrayList<>();
        while (iter.hasNext()) {
            DecimalFormat df = new DecimalFormat("0.000");
            Map.Entry<String, Integer> entry = (Map.Entry) iter.next();
            double mean = entry.getValue().doubleValue() / dataNum.get(entry.getKey()).doubleValue();
            DateMean dateMean = new DateMean(entry.getKey(), Double.parseDouble(df.format(mean)));
            res.add(dateMean);
        }
        return res;
    }


}
