package com.example.cloudbiz1.entity;

import com.alibaba.fastjson.JSON;
import com.example.cloudbiz1.config.MQConfig;
import com.example.cloudbiz1.service.DeviceService;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;


import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xmx
 * @date 2021/5/11
 **/

@Component
public class Consumer {
    /**
     * 消费者实体对象
     */
    private DefaultMQPushConsumer consumer;
    /**
     * 消费者组
     */
    private static final String CONSUMER_GROUP = "test_consumer";

    @Autowired
    DeviceService deviceService;


    public Consumer() throws MQClientException{
        consumer = new DefaultMQPushConsumer(CONSUMER_GROUP);
        consumer.setNamesrvAddr(MQConfig.NAME_SERVER);
        //消费模式:一个新的订阅组第一次启动从队列的最后位置开始消费 后续再启动接着上次消费的进度开始消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        //订阅主题 和 标签（* 代表所有标签）下的信息
        consumer.subscribe(MQConfig.TOPIC, "*");
        // 注册消费的监听 并在此监听中消费信息，并返回消费的状态信息
        consumer.registerMessageListener(new MessageListenerConcurrently(){

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext){
                try {
                    Message msg = list.get(0);
                    String body = new String(msg.getBody(), "utf-8");
                    String topic = msg.getTopic();
                    show_body(body);
                    System.out.println("Topic: " + topic + " Body: " + body);
                } catch (UnsupportedEncodingException e){
                    e.printStackTrace();
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();
        System.out.println("消费者 启动成功=====");
    }

    private void show_body(String body){
        HashMap map = JSON.parseObject(body, HashMap.class);
        Object time = map.get("time");
        Object temp = map.get("temp");
        Object device_name = map.get("deviceName");
        Object lng = map.get("Lng"); //经度
        Object lat = map.get("Lat"); //纬度
        deviceService.update_data(time.toString() ,temp.toString(), device_name.toString(), lng.toString(), lat.toString());
        System.out.println("时间： " + time.toString() + " 温度： " + temp.toString());
    }
}
