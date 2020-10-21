package com.example.category.dataobject;

import com.example.category.model.Category;
import com.example.category.util.UUIDUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class CategoryDO {

    private String id;

    private String name;

    private String description;

    private String parentCategoryId;

    private Date gmtCreated;

    private Date gmtModified;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(String parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public CategoryDO() {
    }

    public CategoryDO(Category category) {
        BeanUtils.copyProperties(category, this);
        if (StringUtils.isEmpty(category.getId())) {
            this.setId(UUIDUtils.getUUID());
        }
        if (!StringUtils.isEmpty(category.getParentCategoryId())) {
            this.setParentCategoryId(category.getParentCategoryId());
        }
    }

    public Category convertToModel() {
        Category category = new Category();
        BeanUtils.copyProperties(this, category);
        if (!StringUtils.isEmpty(this.getParentCategoryId())) {
            category.setParentCategoryId(this.getParentCategoryId());
        }
        Date date = this.getGmtCreated();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        category.setGmtCreated(localDateTime);
        Date date1 = this.getGmtModified();
        Instant instant1 = date1.toInstant();
        ZoneId zoneId1 = ZoneId.systemDefault();
        LocalDateTime localDateTime2 = instant1.atZone(zoneId1).toLocalDateTime();
        category.setGmtModified(localDateTime2);
        return category;
    }
}
