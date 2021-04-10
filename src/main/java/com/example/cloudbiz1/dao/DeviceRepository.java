package com.example.cloudbiz1.dao;

import com.example.cloudbiz1.entity.Device;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xmx
 * @date 2021/4/8
 **/
@Repository
public interface DeviceRepository extends MongoRepository<Device, String> {
    //<Device, String>第一个为实体类，第二个为主键ID。

}
