package com.tyut.web.util;

import com.tyut.core.utils.PrintUtil;

/**
 * Created by Fant.J.
 * 2018/5/3 17:28
 */
public class Test {
    @org.junit.Test
    public void test(){
        System.out.println(CheckFormat.isEmail("844072586@qq.com"));
    }

    @org.junit.Test
    public void test2(){
        System.out.println(CheckFormat.isPhone("14797472524"));
    }

    @org.junit.Test
    public void printTest(){
        String[] name = "Sun Jan 01 2012 00:00:00 GMT+0800 (中国标准时间)".split(" ");
        System.out.println(name[3]);
        PrintUtil.printObj("sdfsdf","prefix");
    }
}
