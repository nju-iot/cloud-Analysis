package com.example.cloudbiz1.service.impl;

import com.example.cloudbiz1.config.MQConfig;
import com.example.cloudbiz1.entity.Consumer;
import com.example.cloudbiz1.entity.Producer;
import com.example.cloudbiz1.service.MQService;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xmx
 * @date 2021/5/11
 **/
@Service
public class MQServiceImpl implements MQService {

    @Autowired
    private Producer producer;


    public void produceMessage(){
        List<String> mesList = new ArrayList<>();
        mesList.add("哈哈");
        mesList.add("爸爸");
        mesList.add("妈妈");
        mesList.add("爷爷");
        mesList.add("奶奶");
        try {
            for(String s : mesList){
                Message message = new Message(MQConfig.TOPIC, "test_tag", ("测试数据: " + s).getBytes());
                //发送
                SendResult sendResult = producer.getProducer().send(message);
                System.out.println(sendResult);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void consumeMessage(){
        try{
            Consumer consumer = new Consumer();
        }catch (Exception e){
            e.printStackTrace();
        }
        /*
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("PushConsumer");
        consumer.setNamesrvAddr("139.196.202.149:9876");
        try{
            //订阅PushTopic下 Tag 为 push的数据
            consumer.subscribe("PushTopic","push");
            //程序第一次启动从消息队列头取得数据
            //这里设置的是一个consumer的消费策略
            //CONSUME_FROM_LAST_OFFSET 默认策略，从该队列最尾开始消费，即跳过历史消息
            //CONSUME_FROM_FIRST_OFFSET 从队列最开始开始消费，即历史消息（还储存在broker的）全部消费一遍
            //CONSUME_FROM_TIMESTAMP 从某个时间点开始消费，和setConsumeTimestamp()配合使用，默认是半个小时以前
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                    Message msg = list.get(0);

                    String topic = msg.getTopic();
                    System.out.println("topic = " + topic);
                    byte[] body = msg.getBody();
                    System.out.println("body: " + new String(body));

                    String keys = msg.getKeys();
                    System.out.println("keys = " + keys);

                    String tags = msg.getTags();
                    System.out.println("tags = " + tags);
                    System.out.println("--------------------------------------");

                    //返回消费状态
                    //CONSUME_SUCCESS 消费成功
                    //RECONSUME_LATER 消费失败，需要稍后重新消费
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            consumer.start();
            System.out.println("Consumer Start.");
        }catch (Exception e){
            e.printStackTrace();
        }
        */


    }
}
