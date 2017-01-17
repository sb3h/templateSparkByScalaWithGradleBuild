package com.hhh.hello;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 16-12-20.
 */
public class FilePathByJava {
    public static void main(String[] args) {
        File  jarDir = new File("/export/servers/spark/jars");
        StringBuffer sb = new StringBuffer();
        List jars = new ArrayList();
//        for (File jarFile:jarDir.listFiles()) {
//            jars.add(jarFile.getName());
////            System.out.println(jarFile.getName());
//        }
        File[] jarFiles = jarDir.listFiles();
        for (int i = 0; i < jarFiles.length; i++) {
            File jarFile = jarFiles[i];
            System.out.println(jarFile.getName());
        }
    }
}
