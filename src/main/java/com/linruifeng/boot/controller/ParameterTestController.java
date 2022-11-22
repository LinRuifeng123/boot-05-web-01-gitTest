package com.linruifeng.boot.controller;

import com.linruifeng.boot.bean.Person;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author linruifeng
 * @create 2022-11-06 21:35
 */
@RestController
public class ParameterTestController {

    /**
     * 数据绑定：页面提交的请求数据（GET、POST）都可以和对象属性进行绑定
     * @param person
     * @return
     */
    @PostMapping("/saveuser")
    public Person saveuser(Person person){

        return person;
    }


    //car/2/owner/zhangsan
    @GetMapping("/car/{id}/owner/{username}")
    public Map<String,Object> getCar(@PathVariable("id") Integer id,
                                     @PathVariable("username") String name,
                                     @PathVariable Map<String,String> pv,
                                     @RequestHeader("User-Agent") String userAgent,
                                     @RequestHeader Map<String,String> header,
                                     @RequestParam("age") Integer age,
                                     @RequestParam("inters") List<String> inters,
                                     @RequestParam Map<String,String> params) {
        Map<String, Object> map = new HashMap<>();

        // map.put("id",id);
        // map.put("name",name);
        // map.put("pv",pv);
        // map.put("User-Agent",userAgent);
        // map.put("headers",header);

        map.put("age", age);
        map.put("inters", inters);
        map.put("params", params);
        return map;
    }

    @PostMapping("/save")
    public Map<String,Object> postMethod(@RequestBody String content){
        Map<String, Object> map = new HashMap<>();
        map.put("content",content);
        return map;

    }

    //1.语法：/cars/sell;low=34;brand=byd,audi,yd
    //2.SpringBoot默认是禁用了矩阵变量的功能
    //  手动开启:原理。对于路径的处理  都是使用UrlPathHelper进行解析。
    //  removeSemicolonContent（移除分号内容）支持矩阵变量
    //3.矩阵变量必须由url路径变量才能被解析
    @GetMapping("/cars/{path}")
    public Map carsSell(@MatrixVariable("low") Integer low,
                        @MatrixVariable("brand") List<String> brand,
                        @PathVariable("path") String path){

        Map<String, Object> map = new HashMap<>();
        map.put("low",low);
        map.put("brand",brand);
        map.put("path",path);
        return map;
    }

    // /boss/1;age=20/2;age=10
    @GetMapping("/boss/{bossId}/{empId}")
    public Map boss(@MatrixVariable(value="age",pathVar = "bossId") Integer bossAge,
                    @MatrixVariable(value="age",pathVar = "empId") Integer empAge){

        Map<String, Object> map = new HashMap<>();

        map.put("bossAge",bossAge);
        map.put("empAge",empAge);

        return map;
    }

}
