package com.tyut.web.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.Writer;
import java.util.Map;

/**
 * Created by Fant.J.
 */
@RestController
public class HelloController {

    @Autowired
    private Configuration configuration;


    @GetMapping("/hello")
    public String demo(Map<String, Object> map) {
        map.put("name", "demo");
        freeMarkerContent(map,"hello");
        return "hello";
    }

    private void freeMarkerContent(Map<String,Object> root,String ftl){
        try {
            Template temp = configuration.getTemplate(ftl+".ftl");
            //以classpath下面的static目录作为静态页面的存储目录，同时命名生成的静态html文件名称
            String path=this.getClass().getResource("/").getPath()+"templates/"+ftl+".html";
            Writer file = new FileWriter(path);
            temp.process(root, file);
            file.flush();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
