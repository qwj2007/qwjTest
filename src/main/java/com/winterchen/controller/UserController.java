package com.winterchen.controller;



import com.winterchen.model.City;
import com.winterchen.model.Teachers;

import com.winterchen.model.Userinfo;
import com.winterchen.service.right.IUserInfoService;

import com.winterchen.service.teacher.ITeacherService;
import com.winterchen.service.world.ICityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ITeacherService teacherService;
    @Autowired
    private ICityService cityService;
    @Autowired

    private IUserInfoService userInfoService;

    @ResponseBody
    @RequestMapping(value="/addTeacher",method = {RequestMethod.GET,RequestMethod.POST})
    public int addTeaher(@RequestParam(value = "name", required = true) String name,
                         @RequestParam(value = "url", required = true) String url) {
        Teachers teachers=new Teachers();
        teachers.setName(name);
        teachers.setUrl(url);
        try {
            return teacherService.addTeacher(teachers);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
       return  0;
    }


    @ResponseBody
    @RequestMapping(value="/addCity",method = {RequestMethod.GET,RequestMethod.POST})
    public int addTeaher(@RequestParam(value = "name", required = true) String name,
                         @RequestParam(value = "contrycode", required = true) String contrycode,
                         @RequestParam(value = "district", required = true) String district,
                         @RequestParam(value = "population", required = true) Integer population
                         ) {
        City city=new City();
        city.setCountrycode(contrycode);
        city.setName(name);
        city.setDistrict(district);
        city.setPopulation(population);
        try {
            return cityService.insert(city);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }




@RequestMapping(value = "/GetUserName", method = { RequestMethod.GET, RequestMethod.POST }, produces = {
        "application/json;charset=UTF-8" })

    public String GetUserName(ModelMap map, HttpServletRequest request, HttpServletResponse response)
    {
        map.put("username","张三");
        return "User/user";
    }
    @RequestMapping(value = "/Va", method = { RequestMethod.GET, RequestMethod.POST }, produces = {
            "application/json;charset=UTF-8" })
    @ResponseBody
    public String getV()
    {
        return "dfdfdfdfd";
    }

    @RequestMapping(value = "/insert", method = { RequestMethod.GET, RequestMethod.POST }, produces = {
            "application/json;charset=UTF-8" })
    @ResponseBody
public String addUserInfo()
{
    Userinfo model=new Userinfo();
    model.setUsercode("8989898989");
    model.setIsdeleted(0);
   model.setUsername("张三");
   model.setPwd("111111");
 int i=  userInfoService.addUserInfo(model);
 if(i>0)
 {
     return "插入成功";
 }
 else {
     return "插入失败";
 }
}
}
