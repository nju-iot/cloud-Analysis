package com.example.cloudbiz1.entity;

import com.example.cloudbiz1.config.MQConfig;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.stereotype.Component;

/**
 * @author xmx
 * @date 2021/5/11
 **/

@Component
public class Producer {
    /*
        生产者组
     */
    private String producerGroup = "test_producer";

    private DefaultMQProducer producer;

    public Producer(){
        //示例生产者
        producer = new DefaultMQProducer(producerGroup);
        //不开启vip通道 开通口端口会减2
        producer.setVipChannelEnabled(false);
        //绑定Name Server
        producer.setNamesrvAddr(MQConfig.NAME_SERVER);
        start();
    }

    public void start(){
        try{
            this.producer.start();
        } catch (MQClientException e){
            e.getErrorMessage();
        }
    }

    public DefaultMQProducer getProducer(){
        return this.producer;
    }

    /**
     * 一般在应用上下文，使用上下文监听器，进行关闭
     */
    public void shutdown(){
        this.producer.shutdown();
    }
}
