package com.example.cloudbiz1.config;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author xmx
 * @date 2021/5/14
 **/
public class DBConfig {

    private static DBConfig dbConfig = null;

    private Connection con;

    private DBConfig(){

    }

    public static DBConfig getInstance(){
        if(dbConfig == null){
            synchronized (DBConfig.class){
                if (dbConfig == null){
                    dbConfig = new DBConfig();
                }
            }
        }
        return dbConfig;
    }


    public Connection getConnection() throws Exception{
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://139.196.202.149:53306/nju_iot?useSSL=FALSE&serverTimezone=UTC";
        String username = "root";
        String password = "123456";
        if(con==null){
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
        }
        return con;
    }
}
