package com.linruifeng.boot.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author linruifeng
 * @create 2022-11-08 21:00
 */

/**
 *     姓名： <input name="userName"/> <br/>
 *     年龄： <input name="age"/> <br/>
 *     生日： <input name="birth"/> <br/>
 *     宠物姓名：<input name="pet.name"/><br/>
 *     宠物年龄：<input name="pet.age"/>
 */

@Data
public class Person {

    private String userName;
    private Integer age;
    private Date birth;
    private Pet pet;

}
