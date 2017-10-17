package com.codoon.common.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by huangjingqing on 17/5/11.
 */
public class FileUtil {

    StringBuffer file=new StringBuffer("/sdcard/autotest/");

    public void initFile(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(df.format(new Date()));
        file.append(df.format(new Date()));
        File folder=new File(String.valueOf(file));
        if(!folder.exists()){
            folder.mkdirs();
        }
    }

    public  void takeScreenShot(String filename){
        initFile();
        String command = "/system/bin/screencap -p /sdcard/"+filename+".png";
        Process p = null;
        try {
            p = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (p != null) {
                p.waitFor();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static  void saveLog(){

    }

}
