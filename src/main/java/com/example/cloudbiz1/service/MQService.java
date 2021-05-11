package com.example.cloudbiz1.service;

/**
 * @author xmx
 * @date 2021/5/11
 **/
public interface MQService {

    void produceMessage();

    void consumeMessage();
}
