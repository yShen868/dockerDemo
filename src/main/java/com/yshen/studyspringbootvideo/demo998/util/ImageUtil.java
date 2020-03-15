package com.yshen.studyspringbootvideo.demo998.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import sun.net.www.content.image.png;

import javax.xml.crypto.Data;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 郑悦恺
 * @Classname ImageUtil
 * @Description TODO
 * @Date 2020/3/15 20:10
 */


@Configuration
@EnableScheduling
public class ImageUtil {

    static int page= 5;

    public static void main(String[] args) throws IOException {
        ImageUtil imageUtil = new ImageUtil();
        imageUtil.image();
    }

//    @Scheduled(cron = "0 0 * * * ? ")
    public void image() throws IOException {
        ImageCopy imageCopy = new ImageCopy();

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        String format = simpleDateFormat.format(date);
        System.out.println(format);
        String substring = format.substring(0, format.length() - 1);


        String strUrl = "http://d9.weather.com.cn/newwebgis/radar/5m/QPFRef_"+substring+"0.png ";
        String path = "D:\\image\\";
        imageCopy.Downimage(strUrl,path);
        System.out.println(strUrl);

        File file1 =new File(path+"floor.png");
        File file2 =new File(path+"ceil.png");
        String saveFilePath = path+"save"+page+".png";
        page++;
        BufferedImage watermark = imageCopy.watermark(file1, file2, 0, 0, 1f);
        imageCopy.generateWaterFile(watermark, saveFilePath);


    }


}
