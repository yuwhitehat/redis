package com.example.category.dao;

import com.example.category.dataobject.CategoryDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryDAO {

    /**
     * 添加类目
     * @param categoryDO
     * @return
     */
    public int insert(CategoryDO categoryDO);


}
