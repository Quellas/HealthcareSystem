package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/*
检查组持久层规则
 */
@Repository
public interface CheckGroupDao {

    /**
     * 新增检查组
     * @param checkGroup
     */
    void add(CheckGroup checkGroup);

    void setCheckGroupAndCheckItem(Map<String,Integer> map);

    Page<CheckGroup> selectByCondition(String queryString);

    CheckGroup findById(Integer id);

    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    void deleteAssociation(Integer id);

    void edit(CheckGroup checkGroup);

    List<CheckGroup> findAll();



}
