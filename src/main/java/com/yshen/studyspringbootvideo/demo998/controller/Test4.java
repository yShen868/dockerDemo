package com.yshen.studyspringbootvideo.demo998.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yshen.studyspringbootvideo.demo998.model.User;
import com.yshen.studyspringbootvideo.demo998.util.ReturnDate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author 郑悦恺
 * @Classname Test4
 * @Description TODO
 * @Date 2020/2/28 23:20
 */
@Controller
public class Test4 {

    @Value("${filepath}")
    private String filepath;

    @RequestMapping("/page3")
    public String test5() {
        return "page3";
    }

    @ResponseBody
    @RequestMapping("/fileslist")
    public ReturnDate page1() {
        System.out.println(filepath);
        File file = new File(filepath);
        File[] files = file.listFiles();
        List<User> users = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            if (files[i].getName().endsWith(".mp4")) {
                User user = new User();
                user.setId(i);
                user.setName(files[i].getName());
                users.add(user);
            }
        }
//        Page<Object> value = PageHelper.startPage(page,limit);
//        PageInfo<User> pageInfo = new PageInfo<>(users);
        ReturnDate date = new ReturnDate();
        date.setCode(0);
        date.setData(users);
        date.setCount(users.size());
        date.setMsg("ok");
        return date;

    }


    @RequestMapping(value = "fileUploads", method = RequestMethod.POST)
    @ResponseBody
    public ReturnDate fileUploads(HttpServletRequest request) {

        ReturnDate date = new ReturnDate();
        date.setCode(0);
        date.setMsg("empty");
        System.out.println(123123);

        CommonsMultipartResolver multipartResolver =
                new CommonsMultipartResolver(request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(request)) {
            //将request变成多request
            MultipartHttpServletRequest multiRequest =
                    (MultipartHttpServletRequest) request;
            //获取multiRequest中所有的文件名
            Iterator iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                //一次遍历所有的文件
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                if (null != file && file.getSize() > 20 * 1024 * 1024) {
                } else if (file != null) {
                    try {
                        String fileName = file.getOriginalFilename();
                        int size = (int) file.getSize();
                        System.out.println(fileName + "-->" + size);
                        String path = "D://test";
                        File dest = new File(path + "/" + fileName);
                        if (!dest.getParentFile().exists()) {
                            //判断文件父目录是否存在
                            dest.getParentFile().mkdir();
                        }
                        file.transferTo(dest);
                        //保存文件
                        date.setMsg("ok");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }


        return date;
    }

}
