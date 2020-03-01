package com.yshen.studyspringbootvideo.demo998.controller;

import com.yshen.studyspringbootvideo.demo998.util.RangeVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.thymeleaf.util.StringUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 郑悦恺
 * @Classname Test2
 * @Description TODO
 * @Date 2020/2/28 17:45
 */
@Controller
public class Test3 {


    @Value("${filepath}")
    private String filePath;

    @RequestMapping("/downs/{filename}")
    public Map<String,Object> downs(@PathVariable("filename")String name,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
//        System.out.println(name);
        String filename = "test2.mp4";
        filename = name;

        String files = filePath + File.separator + filename;
//        File file = new File(filePath + "/" + filename);
        return processVideoRequest(files,request,response);



    }

    private Map<String, Object> processVideoRequest(String files, HttpServletRequest request, HttpServletResponse response) {

        Map<String,Object> resultMap = null;
        File file = new File(files);

        if (!file.exists()) {

            resultMap = new HashMap<String, Object>();
            resultMap.put("resuleMsg","不合法");
        }else {
            randomAccessFile(file,request,response);
        }
        return resultMap;

    }

    private void randomAccessFile(File file, HttpServletRequest request, HttpServletResponse response) {
        try {

            RangeVo rangeVo = new RangeVo(file.length(),gerRange(request));

            response.setHeader("Content-Transfer-Encoding","binary");

            response.setHeader("accept-ranges","bytes");
            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
            response.setHeader("Content-Length",String.valueOf(rangeVo.getContentLength()));
            response.setHeader("Content-Range",rangeVo.getContentRange());
            response.setContentType("video/mp4");

            /**
             * 开始读取
             * */

            ServletOutputStream out = response.getOutputStream();

            RandomAccessFile rac = new RandomAccessFile(file,"r");
            long rangeStart = rangeVo.getRangeStart();

            rac.seek(rangeStart);
            byte[] buf = new byte[10240];
            int len = 0;
            int count = 0;
            while ((len=rac.read(buf))!=-1 && count<=rangeVo.getRangeEnd()){
                if(rangeStart!=0){
//                    System.out.println("读取文件");
                }
                count += len;
                out.write(buf,0, len);
            }

            rac.close();
            out.flush();
            out.close();
        }catch (Exception e){
//            e.printStackTrace();
            System.out.println(e.getMessage()+"什么原因呢");
        }
    }

    private String gerRange(HttpServletRequest request) {

        String rangeHeader = request.getHeader("range");
//        System.out.println(rangeHeader+"rangeHeader");
        String range = "";
        if (!StringUtils.isEmpty(rangeHeader)) {
            range = rangeHeader.replaceAll("bytes=","");
        }

        return range;
    }


}
