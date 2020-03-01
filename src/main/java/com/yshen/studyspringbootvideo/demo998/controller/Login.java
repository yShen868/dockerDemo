package com.yshen.studyspringbootvideo.demo998.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import com.yshen.studyspringbootvideo.demo998.model.User;
import com.yshen.studyspringbootvideo.demo998.util.ReturnDate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
public class Login {

    @Value("${usernam}")
    private String user;
    @Value("${passwor}")
    private String pass;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }


    @RequestMapping("/getCode")
    public void getCode(HttpServletResponse response, HttpSession session) throws IOException {

// 自定义纯数字的验证码（随机4位数字，可重复）
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 4);
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(120, 57);
        lineCaptcha.setGenerator(randomGenerator);
// 重新生成code
        lineCaptcha.createCode();
        session.setAttribute("code", lineCaptcha.getCode());
        lineCaptcha.write(response.getOutputStream());
    }

    @ResponseBody
    @RequestMapping(value = "/loginin",method = RequestMethod.POST)
    public ReturnDate loginin(String username,String password,String code,HttpSession session){
        String codes = (String) session.getAttribute("code");
        ReturnDate returnDate = new ReturnDate();
//        System.out.println(username);
//        System.out.println(password);
//        System.out.println(codes);


        if (code.trim().equals(codes)) {
            System.out.println(user);
            System.out.println(pass);
            if (username.trim().equals(user) && password.trim().equals(pass)) {
                returnDate.setMsg("okk");
                session.setAttribute("USER",username);
            }else {
                returnDate.setMsg("账号密码错误");
            }
        }else {
            returnDate.setMsg("验证码错误");
        }


        return returnDate;
    }

}
