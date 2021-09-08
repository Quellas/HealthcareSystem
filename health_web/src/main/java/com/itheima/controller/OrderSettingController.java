package com.itheima.controller;

import com.itheima.constant.MessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.OrderSettingService;
import com.itheima.utils.POIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {
    @Autowired
    private OrderSettingService orderSettingService;

    /*
    批量预约设置
     */
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public Result batchOrderSetting(MultipartFile excelFile){

        try {
            //a 调用工具类获取excel数据
            List<String[]> list = POIUtils.readExcel(excelFile);
            //if(list != null && list.size()>0){
            if(!CollectionUtils.isEmpty(list)){
                //b 将List<String[]>转为List<OrderSetting>
                List<OrderSetting> orderSettingList = new ArrayList<>();
                for (String[] row : list) {
                    OrderSetting os = new OrderSetting();
                    os.setOrderDate(new Date(row[0]));//预约日期 2021/09/2
                    os.setNumber(Integer.parseInt(row[1]));
                    orderSettingList.add(os);
                }
                //c 调用service（list<OrderSetting>)
                orderSettingService.batchOrderSetting(orderSettingList);
            }
            return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
    }

    @RequestMapping("/getOrderSettingByMonth")
    public Result  getOrderSettingByMonth(String date){

        try {  //参数格式为：2019-03
            //获取预约设置数据成功
            List<Map> list = orderSettingService.getOrderSettingByMonth(date);
            return new Result(true,MessageConstant.GET_ORDERSETTING_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            //获取预约设置数据失败
            return new Result(false,MessageConstant.GET_ORDERSETTING_FAIL);
        }

    }

    /**
     * 根据指定日期修改可预约人数
     * @param orderSetting
     * @return
     */

    @RequestMapping("/editNumberByDate")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting){
        try {
            orderSettingService.editNumberByDate(orderSetting);
            //预约设置成功
            return new Result(true,MessageConstant.ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            //预约设置失败
            return new Result(false,MessageConstant.ORDERSETTING_FAIL);
        }
    }


















































}
