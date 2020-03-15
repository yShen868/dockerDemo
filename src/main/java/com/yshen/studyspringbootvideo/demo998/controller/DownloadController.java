package com.yshen.studyspringbootvideo.demo998.controller;

import com.yshen.studyspringbootvideo.demo998.model.Download;
import com.yshen.studyspringbootvideo.demo998.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 郑悦恺
 * @Classname DownloadController
 * @Description TODO
 * @Date 2020/3/14 21:44
 */

@Controller
public class DownloadController {

    @Autowired
    DownloadService downloadService;

    @RequestMapping("/tt")
    public String tests(HttpServletResponse response) throws UnsupportedEncodingException {

        Download one = downloadService.getOne();
        System.out.println(one);
        String filename = one.getName();
        String filePath = one.getPath();
        System.out.println(filename);
        System.out.println(filePath);
        File file = new File(filePath + "/" + filename);
        if (file.exists()) {
            //判断文件父目录是否存在
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            /**
             response.setContentType("application/force-download");
             */
            response.setHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode(filename, "UTF-8"));
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null;
            //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("----------file download---" + filename);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;

    }






    @RequestMapping("ww")
    public void Downimage(HttpServletResponse response) throws IOException {

        Long s = 202003150153L;

        Date date = new Date();

        SimpleDateFormat sm = new SimpleDateFormat("yyyyMMddHHmm");
        String format = sm.format(date);
        long l = Long.parseLong(format);
        l = l-800;

        String strUrl = "http://pi.weather.com.cn/i/product/pic/m/sevp_nsmc_wxbl_fy4a_etcc_achn_lno_py_"+l+"00000.jpg";
        strUrl = "http://pi.weather.com.cn/i/product/pic/m/sevp_nsmc_wxbl_fy4a_etcc_achn_lno_py_20200315023000000.jpg";
        System.out.println(strUrl);
        String filePath = "D:\\image\\";


        //构造URL
        URL url = new URL(strUrl);

        //构造连接
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();

        conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; rv:11.0) like Gecko");

        conn.connect();

        InputStream inStream = conn.getInputStream();

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        OutputStream os = response.getOutputStream();


        byte [] buf = new byte[10240];

        int len = 0;
//读取图片数据
        while((len=inStream.read(buf))!=-1){
//            System.out.println(len);
            outStream.write(buf,0,len);
            os.write(buf,0,len);
        }
        inStream.close();
        outStream.close();
        os.close();
        //把图片数据填入文件中
        File file = new File(filePath+"bg.png");

        FileOutputStream op = new FileOutputStream(file);

        op.write(outStream.toByteArray());

        op.close();

    }







    @RequestMapping("p2")
    public String haha(){
        return "page2";
    }

}
