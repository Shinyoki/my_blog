package com.senko.controller.test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author senko
 * @date 2022/5/3 22:02
 */
public class T1 {
    public static void main(String[] args) {
        LinkedList<TUser> list = new LinkedList<>(Arrays.asList(new TUser("s3-1", 3),new TUser("s2", 2), new TUser("s1", 1), new TUser("s3-2", 3)));
        Map<Integer, List<TUser>> collect = list.stream().collect(Collectors.groupingBy(TUser::getAge));
        for (Map.Entry<Integer, List<TUser>> entry : collect.entrySet()) {
            System.out.println("key：" + entry.getKey());
            for (TUser tUser : entry.getValue()) {
                System.out.println(tUser);
            }
        }
    }
}

class TUser {
    String name;
    Integer age;

    @Override
    public String toString() {
        return "TUser{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public TUser(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
