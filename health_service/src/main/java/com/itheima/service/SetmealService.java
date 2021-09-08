package com.itheima.service;

import com.itheima.pojo.Setmeal;

/*
检查组业务接口
 */
public interface SetmealService {
    /*
    新增检查组
     */
    void add(Setmeal setmeal,Integer[] checkgroupIds);

}
