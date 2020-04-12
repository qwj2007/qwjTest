package com.winterchen.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 作者：齐文杰
 * 时间：2018/9/16
 */
@Controller
@RequestMapping("/Login")
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST}, produces = {
            "application/json;charset=UTF-8"})
    public String Login() {
        return "login";
    }

    @RequestMapping(value = "/DoLogin", method = {RequestMethod.GET, RequestMethod.POST}, produces = {
            "application/json;charset=UTF-8"})
    public String doLogin(HttpServletRequest request,HttpServletResponse response,ModelMap map) {
        String usercode=request.getParameter("usercode");
        String pwd=request.getParameter("pwd");
        logger.info("usercode:"+usercode);
        logger.info("pwd:"+pwd);
        return "User/user";
    }
}
