package com.winterchen.controller;


import com.winterchen.model.City;
import com.winterchen.model.Teachers;
import com.winterchen.service.teacher.ITeacherService;
import com.winterchen.service.world.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private ITeacherService teacherService;
    @Autowired
    private ICityService cityService;
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

    @RequestMapping("/hello")
    @ResponseBody
    public String addUser() {
        return "hello";
    }
    /*@ResponseBody
    @PostMapping("/add")
    public int addUser(UserDomain user) {
        return userService.addUser(user);
    }

    @ResponseBody
    @GetMapping("/all")
    public Object findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize) {
        return userService.findAllUser(pageNum, pageSize);
    }*/
}
