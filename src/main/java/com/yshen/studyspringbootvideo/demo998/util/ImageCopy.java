package com.yshen.studyspringbootvideo.demo998.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author 郑悦恺
 * @Classname ImageCopy
 * @Description TODO
 * @Date 2020/3/15 20:11
 */

public class ImageCopy {

    public BufferedImage watermark(File file, File waterFile, int x, int y, float alpha) throws IOException {
        // 获取底图
        BufferedImage buffImg = ImageIO.read(file);
        // 获取层图
        BufferedImage waterImg = ImageIO.read(waterFile);
        // 创建Graphics2D对象，用在底图对象上绘图
        Graphics2D g2d = buffImg.createGraphics();
        int waterImgWidth = waterImg.getWidth();
        // 获取层图的宽度
        int waterImgHeight = waterImg.getHeight();
        // 获取层图的高度
        // 在图形和图像中实现混合和透明效果
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
        // 绘制
        g2d.drawImage(waterImg, x, y, waterImgWidth, waterImgHeight, null);



        g2d.dispose();// 释放图形上下文使用的系统资源
        BufferedImage subimage = buffImg.getSubimage(1600, 800, 1000, 600);
        return buffImg;
    }




    public void Downimage(String strUrl,String filePath) throws IOException {

        //构造URL
        URL url = new URL(strUrl);

        //构造连接
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();

        conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; rv:11.0) like Gecko");

        conn.connect();

        InputStream inStream = conn.getInputStream();

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();


        byte [] buf = new byte[10240];

        int len = 0;
//读取图片数据
        while((len=inStream.read(buf))!=-1){
//            System.out.println(len);
            outStream.write(buf,0,len);
        }
        inStream.close();
        outStream.close();
        //把图片数据填入文件中
        File file = new File(filePath+"ceil.png");
        if (file.exists()){
            file.delete();
        }

        FileOutputStream op = new FileOutputStream(file);

        op.write(outStream.toByteArray());

        op.close();

    }



    /**
     * 输出水印图片
     *
     * @param buffImg
     *            图像加水印之后的BufferedImage对象
     * @param savePath
     *            图像加水印之后的保存路径
     */
    public void generateWaterFile(BufferedImage buffImg, String savePath) {
        int temp = savePath.lastIndexOf(".") + 1;
        try {
            ImageIO.write(buffImg, savePath.substring(temp), new File(savePath));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }



}


