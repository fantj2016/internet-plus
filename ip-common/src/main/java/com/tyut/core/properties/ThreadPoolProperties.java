package com.tyut.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 线程池配置属性类
 * Created by Fant.J.
 * 2018/4/21 13:25
 */

@ConfigurationProperties(prefix = "task.pool")
public class ThreadPoolProperties {

}
