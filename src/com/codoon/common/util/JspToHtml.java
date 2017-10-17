package com.codoon.common.util;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by huangjingqing on 17/5/18.
 */
public class JspToHtml {

    private static String title ="标题测试";
    private static String context ="标题测试";
    private static String editer ="标题测试";

    /**
     * 根据本地模板生成静态页面
//   * @param    jsp路经
     * @param HtmlFile html路经
     * @return
     **/
    public static boolean JspToHtmlFile(String filePath, String HtmlFile) {
        String str = "";
        long beginDate = (new Date()).getTime();
        try {
            String tempStr = "";
            FileInputStream is = new FileInputStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((tempStr = br.readLine()) != null)
                str = str + tempStr ;
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        try {

            str = str.replaceAll("###title###", title);
            str = str.replaceAll("###content###", context);
            str = str.replaceAll("###author###", editer);

            File f = new File(HtmlFile);
            BufferedWriter o = new BufferedWriter(new FileWriter(f));
            o.write(str);
            o.close();
            System.out.println("共用时：" + ((new Date()).getTime() - beginDate) + "ms");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据url生成静态页面
     *
     * @param u        动态文件路经 如：http://www.163.com/x.jsp

     * @param path 文件存放路经如：x:\\abc\bbb.html
     * @return
     */
    public static boolean JspToHtmlByURL(String u, String path) {
        //从utl中读取html存为str
        String str = "";
        try {
            URL url = new URL(u);
            URLConnection uc = url.openConnection();
            InputStream is = uc.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while (br.ready()) {
                str += br.readLine() + "\n";

            }

            is.close();
            //写入文件
            File f = new File(path);
            BufferedWriter o = new BufferedWriter(new FileWriter(f));
            o.write(str);
            o.close();
            str = "";
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据url生成静态页面
     *
     * @param url 动态文件路经 如：http://www.163.com/x.jsp

     * @return d
     */
    public static StringBuffer getHtmlTextByURL(String url) {
        //从utl中读取html存为str
        StringBuffer sb = new StringBuffer();
        try {
            URL u = new URL(url);
            URLConnection uc = u.openConnection();
            InputStream is = uc.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while (br.ready()) {
                sb.append(br.readLine() + "\n");
            }
            is.close();
            return sb;
        } catch (Exception e) {
            e.printStackTrace();
            return sb;
        }
    }

    public static List<File> getHtmlFile(){
        String path = "./target/surefire-reports/html"; // 路径
        File f = new File(path);
        List<File> files=new ArrayList<File>();
        if (!f.exists()) {
            System.out.println(path + " not exists");
            return null;
        }
        File fa[] = f.listFiles();
        int j=0;

        for (int i = 0; i < fa.length; i++) {
            File fs = fa[i];
            if (!fs.isDirectory()){//&&fs.getName().contains(".html")) {
                files.add(fs);
                System.out.println(files.get(j++).getPath());

            } else {
                System.out.println(fs.getName());
            }
        }
        return files;
    }

    public static void mergeHtmlFile(String mergeFile) throws IOException {
        String str = "";
        String tempStr = "";
        String tempStr2 = "";
        FileInputStream is = new FileInputStream("./target/overview.html");//自定义文件
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((tempStr = br.readLine()) != null) {
            if(tempStr.contains("link")){
                String cssStr="<style type=\"text/css\"> \n" +
                        "h1{color:red} \n" +
                        "p{color:blue} \n" +
                        "</style> ";
                str=str+"<style type=\"text/css\"> \n";
                FileInputStream is2 = new FileInputStream("./target/reportng.css");//自定义文件
                BufferedReader br2 = new BufferedReader(new InputStreamReader(is));
                while ((tempStr2 = br.readLine()) != null) {
                    str = str + tempStr2;
                }
                is2.close();
                str=str+ "</style> ";
            }else{
                str = str + tempStr;
            }

        }
        is.close();


        File f = new File(mergeFile);
        BufferedWriter o = new BufferedWriter(new FileWriter(f));
        o.write(str);
        o.close();

    }


    /**
     * 测试main 函数
     *
     * @param arg
     */
    public static void main(String[] arg) throws IOException {

//        long begin = System.currentTimeMillis();
////        for (int k = 0; k < 20; k++) {
//            String url = "E:\\workspace\\oa\\golatel\\utils\\Temp\\mb.htm";//模板文件地址
            String savepath = "/Users/huangjingqing/Desktop/abc.html";//生成文件地址
            mergeHtmlFile( savepath);
////        }
//        System.out.println("用时:" + (System.currentTimeMillis() - begin) + "ms");
    }
}
