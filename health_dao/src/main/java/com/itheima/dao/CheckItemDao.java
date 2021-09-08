package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckItem;


import java.util.List;

public interface CheckItemDao {
      /**
       * 新增检查项
       * @param checkItem
       */
      void add(CheckItem checkItem);

      /**
       * 检查项分页
       * @return
       */
      Page<CheckItem> selectCondition(String queryString);
      /**
       * 查询t_checkgroup_checkitem 看关系是否存值
       * @param checkitemId
       * @return
       */
      int selectCount(Integer checkitemId);

      /**
       * 删除t_checkitem记录
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
      List<CheckItem>findAll();

}
