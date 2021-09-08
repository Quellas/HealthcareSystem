package com.itheima.dao;

import com.itheima.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 持久层Dao接口
 */
public interface OrderSettingDao {
    /**
     *根据预约日期查询记录是否存在
     * @param orderDate
     * @return
     */
    int findCountByOrderDate(Date orderDate);


    /**
     *不存在则保存
     * @param orderSetting
     */
    void add(OrderSetting orderSetting);



    /**
     * 根据预约日期更新预约人数
     * @param orderSetting
     */
    void updateNumberByOrderDate(OrderSetting orderSetting);

    /**
     * 根据月份查询预约设置信息
     */

    List<OrderSetting>getOrderSettingByMonth(Map map);

    /**
     * 根据预约日期查询记录是否存在
     */
    long findCountByOrderDate1(Date orderDate);

    /**
     * 预约日期有记录则修改editNumberByDate
     */
    void editNumberByDate(OrderSetting orderSetting);

    void add1(OrderSetting orderSetting);











}
