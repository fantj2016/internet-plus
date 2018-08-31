package com.tyut.core.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Base64;
import java.util.UUID;

/**
 * Created by Fant.J.
 * 2018/8/9 19:16
 */
@Slf4j
public class Base64ToFile implements Serializable {
    /**
     * base64 转换为 File(需要文件名)
     */
    public static File base64ToFile(String base64, String filePath,String fileName) {
        File file = null;
        //创建文件目录
        File  dir=new File(filePath);
        if (!dir.exists() && !dir.isDirectory()) {
            dir.mkdirs();
        }
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        try {
            byte[] bytes = Base64.getDecoder().decode(base64);
            file=new File(filePath,fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        log.info("base64ToFile执行结果：{}",file);
        return file;
    }

    /**
     * 不需要文件名
     * @param base64
     * @param filePath
     * @return
     */
    public static File base64ToFile(String base64, String filePath) {
        File file = null;
        //创建文件目录
        File  dir=new File(filePath);
        if (!dir.exists() && !dir.isDirectory()) {
            dir.mkdirs();
        }
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        try {
            byte[] bytes = Base64.getDecoder().decode(base64);
            file=new File(filePath);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        log.info("base64ToFile执行结果：{}",file);
        return file;
    }
}
