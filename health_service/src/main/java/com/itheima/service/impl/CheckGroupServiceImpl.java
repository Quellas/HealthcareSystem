package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.CheckGroupDao;
import com.itheima.entity.PageResult;
import com.itheima.pojo.CheckGroup;
import com.itheima.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {
   @Autowired
   private CheckGroupDao checkGroupDao;

    @Override
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
       checkGroupDao.add(checkGroup);

    }

    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        //使用分页插件PageHelper，设置当前页，每页最多显示的记录数
        PageHelper.startPage(currentPage,pageSize);
        //响应分页插件的Page对象
         Page<CheckGroup> page = checkGroupDao.selectByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public CheckGroup findById(Integer id) {
        return checkGroupDao.findById(id);
    }

    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
        return checkGroupDao.findCheckItemIdsByCheckGroupId(id);
    }

    //编辑检查组，同时需要更新和检查项的关联关系
    @Override
    public void edit(CheckGroup checkGroup, Integer[] checkitemIds) {
        //1 根据检查组id删除中间表数据（清理原有关联关系）

        checkGroupDao.deleteAssociation(checkGroup.getId());
        //2 向中间表（t_checkgroup_checkitem）插入数据（建立检查组和检查项关联关系）

        setCheckGroupAndCheckItem(checkGroup.getId(),checkitemIds);
        //3 更新检查组基本信息

        checkGroupDao.edit(checkGroup);


    }
    // 查询所有检查组
    @Override
    public List<CheckGroup> findAll() {
        return checkGroupDao.findAll();
    }

    //设置检查组合和检查项的关联关系，向中间表中添加数据
    public void setCheckGroupAndCheckItem(Integer id,Integer[] checkitemIds){

       if(checkitemIds!=null && checkitemIds.length>0){
           for (Integer checkitemId : checkitemIds) {
               Map map = new HashMap<>();

               map.put("checkgroup_id",id);
               map.put("checkitem_id",checkitemId);
               checkGroupDao.setCheckGroupAndCheckItem(map);

           }
       }
    }
}
