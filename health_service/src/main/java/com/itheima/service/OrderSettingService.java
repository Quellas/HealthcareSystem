package com.itheima.service;

import com.itheima.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

/**
 * 预约设置管理业务接口
 */
public interface OrderSettingService {
    /**
     * 批量预约设置
     */
    void batchOrderSetting(List<OrderSetting> orderSettingList);

    List<Map>getOrderSettingByMonth(String date);

    /**
     * 预约日期有记录则修改editNumberByDate
     */
    void editNumberByDate(OrderSetting orderSetting);

}
