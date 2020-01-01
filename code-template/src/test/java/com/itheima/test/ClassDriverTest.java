package com.itheima.test;


import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class ClassDriverTest {


@Test
    public  void test() throws Exception {
    Class.forName("com.mysql.jdbc.Driver");
    //2. 获得数据库连接
    Connection conn =
            DriverManager.getConnection("jdbc:mysql://120.24.89.4:3306/changgou_user?useSSL=false", "root", "Jinhao168!123");
}


}
