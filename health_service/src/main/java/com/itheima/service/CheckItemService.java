package com.itheima.service;

import com.alibaba.druid.sql.PagerUtils;
import com.itheima.entity.PageResult;
import com.itheima.pojo.CheckItem;

import java.util.List;

public interface CheckItemService {
    /**
     * 新增检查项
     */
    void add(CheckItem checkItem);

    /**
     * 检查项分页
     * @param currentPage
     * @param pageSize
     * @param queryString
     * @return
     */
    PageResult pageQuery(Integer currentPage, Integer pageSize,String queryString);

    /**
     * 删除检查项
     * @param checkitemId
     */
    void deleteById(Integer checkitemId);

    /**
     * 根据id查询检查项
     */

    CheckItem findById (Integer checkitemId);

    /**
     * 编辑项检查
     */
    void edit(CheckItem checkItem);

    /**
     * 查询所有检查项 checkItem
     * @param
     */
    List<CheckItem> findAll();


}
