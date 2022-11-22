package com.linruifeng.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author linruifeng
 * @create 2022-11-13 14:01
 */
@Controller
public class ViewTest {

    @GetMapping("/atguigu")
    public String atguigu(Model model){

        //model中的数据会被放在请求域中 request.setAttributr("a",aa);
        model.addAttribute("msg","你好，guigu");
        model.addAttribute("link","http://www.baidu.com");
        return "success";
    }

    @GetMapping("/lrf")
    public String toLrf(Model model){
        model.addAttribute("msg","林锐峰");
        return "lrf";
    }
}
