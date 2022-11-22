package com.linruifeng.boot.controller;

import com.linruifeng.boot.bean.Person;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @author linruifeng
 * @create 2022-11-10 20:21
 */
@Controller
public class ResponseTestController {

    @ResponseBody
    @GetMapping("he11") //--RequestResponseBodyMethodProcessor ---> messageConverter
    public FileSystemResource file(){

        //文件以这样的方式返回看是谁处理的
        return null;
    }

    /**
     * 1.浏览器发请求直接返回xml      [application/xml]  jacksonXmlConverter
     * 2.如果是ajax请求 返回json       [application/json]  jacksonJsonConverter
     * 3.如果是硅谷app发请求，返回自定义协议数据   [application/x-guigu] xxxxConverter
     *      属性值1；属性值2；
     *
     *
     * 完成自定义类型的converter
     * 步骤
     * 1.添加自定义的MessageConverter进系统底层
     * 2.系统底层就会统计出所有MessageConverter能够操作哪些数据类型
     * 3.客户端内容协商 [guigu ---> guigu]

     * 作业：如何以参数的方式进行内容协商
     *
     * @return
     */
    @ResponseBody //利用返回值处理器里边的消息转换器进行处理
    @GetMapping(value = "/test/person")
    public Person getPerson(){
        Person person = new Person();
        person.setAge(28);
        person.setBirth(new Date());
        person.setUserName("zhangsan");
        return person;
    }
}
