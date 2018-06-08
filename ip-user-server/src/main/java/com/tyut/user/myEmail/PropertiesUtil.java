package com.tyut.user.myEmail;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * PropertiesUtil是用于读取*.properties配置文件的工具类
 * Created by Fant.J.
 */
public class PropertiesUtil  implements Serializable {
    private final ResourceBundle resource;
    private final String fileName;

    /**
     * 构造函数实例化部分对象，获取文件资源对象
     *
     * @param fileName
     */
    public PropertiesUtil(String fileName)
    {
        this.fileName = fileName;
        this.resource = ResourceBundle.getBundle(this.fileName, Locale.SIMPLIFIED_CHINESE);
    }

    /**
     * 根据传入的key获取对象的值 getValue
     *
     * @param key properties文件对应的key
     * @return String 解析后的对应key的值
     */
    public String getValue(String key)
    {
        String message = this.resource.getString(key);
        return message;
    }

    /**
     * 获取properties文件内的所有key值<br>
     * @return
     */
    public Enumeration<String> getKeys(){
        return resource.getKeys();
    }
}
