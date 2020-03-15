package com.yshen.studyspringbootvideo.demo998.test;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import org.jsoup.Jsoup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * @author 郑悦恺
 * @Classname CloudImages
 * @Description TODO
 * @Date 2020/3/14 19:40
 */

public class CloudImages {

    public static void main(String[] args) throws Exception {

        String s = SendGET("http://www.weather.com.cn/satellite/", "");
        System.out.println(s);
//        getimage();
    }

//    $("#wxyt_img_a").attr("href", "http://pi.weather.com.cn/i/product/pic/l/sevp_nsmc_" + t.radars[n].fn + "_lno_py_" + t.radars[n].ft + ".jpg"),
//    url: t + "?jsoncallback=readSatellite",
    public static void getimage() throws Exception{

        String url = "http://www.weather.com.cn/satellite/";
        String contents = Jsoup.connect(url).get().html();

        HtmlCleaner hc = new HtmlCleaner();
        TagNode tn = hc.clean(contents);
        //代表class="article-title"的div标签下面的h2标签里面的内容
//        String xpath = "//div[@class='container']/div[@class='wrap']/div[@class='colspan2 clearfix']/div[@class='fleft'/div[@class='fleft']";
//        a/@href
        String xpath = "//a[@id='wxyt_img_a']/@href";
        Object[] objects = tn.evaluateXPath(xpath);
        for (Object object : objects) {
            System.out.println(object);
        }
        System.out.println(objects.length);

        System.out.println("---------------------------------");
        //代表class="article-content"的div标签下面的p标签下的span标签里面的内容
        String xpath1 = "//div[@class='article-content']/p/span/text()";
        Object[] objects1 = tn.evaluateXPath(xpath1);
        for (Object object : objects1) {
            System.out.println(object);
        }
        System.out.println(objects1.length);

    }



    public static String SendGET(String url,String param){
        String result="";
        //访问返回结果
        BufferedReader read=null;
        //读取访问结果

        try {
            //创建url
//            URL realurl=new URL(url+"?"+param);
//            System.setProperty("javax.net.ssl.trustStore", "D:\\APPS\\jssecacerts");
            URL realurl=new URL(url);
            //打开连接
            URLConnection connection=realurl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //建立连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段，获取到cookies等
            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            read = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"UTF-8"));
            String line;//循环读取
            while ((line = read.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(read!=null){//关闭流
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

}
