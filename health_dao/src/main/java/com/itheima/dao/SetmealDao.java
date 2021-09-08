package com.itheima.dao;

import com.itheima.pojo.Setmeal;

import java.util.Map;

/**
 * 套餐持久层接口
 */
public interface SetmealDao {
    /**
     * 保存套餐表
     * @param setmeal
     */
    void add(Setmeal setmeal);

    /**
     * 循环往中间表插入数据
     * @param map
     */
    void setCheckGroupAndSetmeal(Map map);




}
