### 竞赛网
2.29 正式将之前做的一个互联网+ 竞赛网开源.本项目采用分布式微服务架构实现, 本项目只涉及到后台部分, 可以部署到本地打开swagger api页面查看api, 前端Vue部分我将联系朋友协助开源。


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