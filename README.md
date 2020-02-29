# 竞赛网项目
[![License](https://img.shields.io/badge/License-MIT-green.svg)](https://github.com/fantj2016/java-reader/blob/master/LICENSE)
[![我的微信](https://img.shields.io/badge/%E5%BE%AE%E4%BF%A1%E7%BE%A4-%E4%BA%8C%E7%BB%B4%E7%A0%81-orange.svg)](https://upload-images.jianshu.io/upload_images/5786888-aceaf4a8c7d17891.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
[![微信公众号](https://img.shields.io/badge/%E5%85%AC%E4%BC%97%E5%8F%B7-PlayInJava-red.svg)](https://upload-images.jianshu.io/upload_images/5786888-74bca7fff151cfb8.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/500)


2.29 正式将之前做的一个互联网+ 竞赛网开源.本项目采用分布式微服务架构实现, 本项目只涉及到后台部分, 可以部署到本地打开swagger api页面查看api, 前端Vue部分我将联系朋友协助开源。

适合springboot初学者参考, 有兴趣也可以一起把他完善成开源的竞赛网项目.

#### 概述
1. 使用Redis实现数据库减压，Mysql实现数据持久化。
2. 使用SpringBoot做基础框架，JPA/Mybatis作ORM框架，Spring Security 作权限鉴定框架。
3. 使用Dubbo做微服务框架，Zookeeper做服务注册中心。
4. 使用Nginx做反向代理，Nginx+ FTP实现文件服务器。

#### 具体技术栈
* SpringBoot
* SpringSecurity JWT
* Mybatis
* SpringData JPA
* Druid
* java mail
* Mysql
* Apache Dubbo
* Swagger2
* logback
* 自定义日志注解(借助于AOP实现)
* Druid springboot配置
* Redis springboot配置
* Rest 结果集封装
* FTP 连接工具
* 全局异常处理
* 前后端分离

### API
![API总览](https://github.com/fantj2016/internet-plus/blob/master/img/1.png)
![](https://github.com/fantj2016/internet-plus/blob/master/img/2.png)
![](https://github.com/fantj2016/internet-plus/blob/master/img/3.png)
![](https://github.com/fantj2016/internet-plus/blob/master/img/4.png)
![](https://github.com/fantj2016/internet-plus/blob/master/img/5.png)