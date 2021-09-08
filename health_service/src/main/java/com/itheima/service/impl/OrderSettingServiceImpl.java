package com.itheima.service.impl;

import com.itheima.dao.OrderSettingDao;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 预约设置管理业务接口实现类
 */
@Service
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {
    @Autowired
    private OrderSettingDao orderSettingDao;
    /**
     * 批量预约设置
     */
    @Override
    public void batchOrderSetting(List<OrderSetting> orderSettingList) {
        if(!CollectionUtils.isEmpty(orderSettingList)){
            for (OrderSetting orderSetting : orderSettingList) {
                //a 查询（根据预约日期查询记录是否存在）
                int count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
                //b 不存在则保存
                if(count==0){
                    orderSetting.setReservations(0);//默认已经预约人数是0
                    orderSettingDao.add(orderSetting);
                }else{
                    //c 存在则更新（根据预约日期更新预约人数）
                    orderSettingDao.updateNumberByOrderDate(orderSetting);
                }
            }
        }

    }

    //根据日期查询预约设置数据
    @Override
    public List<Map> getOrderSettingByMonth(String date) {//2019-03
        //1组织查询Map,dateBegin表示月份开始时间，dateEnd月份结束时间
        String dateBegin= date + "-1";
        String dateEnd = date + "-31";
         Map map = new HashMap();
         map.put("dateBegin",dateBegin);
         map.put("dateEnd",dateEnd);

         //2 查询当前月份的预约设置
        List<OrderSetting> list = orderSettingDao.getOrderSettingByMonth(map);
         List<Map> data = new ArrayList<>();

         //3 将List<OrderSetting> 组织成List<Map>
        for (OrderSetting orderSetting : list) {
            Map orderSettingMap=new HashMap();
            orderSettingMap.put("date",orderSetting.getOrderDate().getDate());//获得日期（几号）
            orderSettingMap.put("number",orderSetting.getNumber());//可预约人数
            //已预约人数
            orderSettingMap.put("reservations",orderSetting.getReservations());
            data.add(orderSettingMap);
        }
        return data;
    }
    //根据日期修改可预约人数
    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
        if(count>0){
            //当前日期已经进行了预约设置，需要进行修改操作
            orderSettingDao.editNumberByDate(orderSetting);
        }else{
            //当前日期没有进行了预约设置，进行增加操作
            orderSettingDao.add(orderSetting);
        }

    }
}








































