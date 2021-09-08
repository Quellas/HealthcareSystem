package com.itheima.service.impl;

import com.itheima.dao.SetmealDao;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealDao setmealDao;
    @Override
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        //a.保存套餐表
        setmealDao.add(setmeal);
        //b.获取套餐id
        Integer setmealId = setmeal.getId();
        commonRs(checkgroupIds, setmealId);

    }

    /**
     * 操作中间表   control+alt+m
     * @param checkgroupIds
     * @param setmealId
     */
    private void commonRs(Integer[] checkgroupIds, Integer setmealId) {
        //c.循环往中间表插入数据
        if(checkgroupIds != null && checkgroupIds.length>0){
            for (Integer checkgroupId : checkgroupIds) {//检查项id
                Map map = new HashMap<>();
                map.put("setmealId",setmealId);
                map.put("checkgroupId",checkgroupId);
                setmealDao.setCheckGroupAndSetmeal(map);
            }
        }
    }

}
