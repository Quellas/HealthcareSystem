package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.constant.MessageConstant;
import com.itheima.dao.CheckItemDao;
import com.itheima.entity.PageResult;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    private CheckItemDao checkItemDao;
    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        //1 完成对分页初始化工作
        PageHelper.startPage(currentPage,pageSize);
        //2 查询
        List<CheckItem> list=checkItemDao.selectCondition(queryString);
        //3 后处理，pageHelper会根据查询的结果再封装成PageHealper对应的实体类
        PageInfo<CheckItem> pageInfo=new PageInfo<>(list);
        // 组织pageResult
        return new PageResult(pageInfo.getTotal(),pageInfo.getList());

    }

    @Override
    public void deleteById(Integer checkitemId) {
        //a 查询t_checkgroup_checkitem 看关系是否存在
        int count = checkItemDao.selectCount(checkitemId);
        //b 如果存在，抛出异常
        if(count>0){
            throw new RuntimeException(MessageConstant.DELETE_ITEM_GROUP_FAIL);
        }
        //c 如果不存在，删除t_checkitem记录
        checkItemDao.deleteById(checkitemId);

    }

    @Override
    public CheckItem findById(Integer checkitemId) {
        return checkItemDao.findById(checkitemId);


    }

    @Override
    public void edit(CheckItem checkItem) {
        checkItemDao.edit(checkItem);
    }

    @Override
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }
}
