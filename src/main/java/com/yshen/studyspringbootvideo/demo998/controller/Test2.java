package com.yshen.studyspringbootvideo.demo998.controller;

import com.yshen.studyspringbootvideo.demo998.util.ReturnDate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * @author 郑悦恺
 * @Classname Test2
 * @Description TODO
 * @Date 2020/2/28 17:45
 */
@Controller
public class Test2 {

    @RequestMapping("/")
    public String test() {

//        System.out.println(123);
        return "page1";

    }

    @RequestMapping("/2")
    public String test2() {

//        System.out.println(123);
        return "page1";

    }


    /**
     * 获取multifile.html页面
     */
    @RequestMapping("3")
    public String multifile() {
        return "/file";
    }
    @RequestMapping("4")
    public String mp4file() {
        return "/mp4file";
    }


    /**
     * 实现多文件上传
     */
    @RequestMapping(value = "multifileUpload", method = RequestMethod.POST)
//　　 public @ResponseBody String multifileUpload(@RequestParam("fileName")List<MultipartFile> files) */
    public @ResponseBody
    String multifileUpload(HttpServletRequest request) {

        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("fileName");

        if (files.isEmpty()) {
            return "false";
        }

        String path = "D:/test";

        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            int size = (int) file.getSize();
//            System.out.println(fileName + "-->" + size);

            if (file.isEmpty()) {
                return "false";
            } else {
                File dest = new File(path + "/" + fileName);
                if (!dest.getParentFile().exists()) {
                    //判断文件父目录是否存在
                    dest.getParentFile().mkdir();
                }
                try {
                    file.transferTo(dest);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return "false";
                }
            }
        }
        return "true";
    }



    @Value("${filepath}")
    private String path;

    /**
     * 实现文件上传
     * @return
     */
    @RequestMapping(value = "fileUpload",method = RequestMethod.POST)
    @ResponseBody
    public ReturnDate fileUpload(@RequestParam("file") MultipartFile file) {
        ReturnDate date = new ReturnDate();
        date.setCode(0);
        date.setMsg("empty");

        if (file.isEmpty()) {
            return date;
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
//        System.out.println(fileName + "-->" + size);

        File dest = new File(path + File.separator + fileName);
        if (!dest.getParentFile().exists()) {
            //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }

        try {
            file.transferTo(dest);
            //保存文件
            date.setMsg("ok");


        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            date.setMsg("no");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            date.setMsg("nno");

        }

        return date;
    }


    @RequestMapping("/download")
    public String downLoad(HttpServletResponse response) throws UnsupportedEncodingException {
        String filename = "第13天_10_对象序列化工具类实现及项目业务对象加入缓存测试第1版完成.mp4";
        String filePath = "D:\\javak课件";
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





}
