package com.example.category.api;

import com.example.category.model.Category;
import com.example.category.model.Result;
import com.example.category.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(path = "/category")
public class CategoryApi {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(path = "/create")
    @ResponseBody
    public Result<Category> add(@RequestBody Category category) {
        Result<Category> result = new Result<>();
        Category resultCategory = categoryService.add(category);
        if (resultCategory == null) {
            result.setSuccess(false);
            result.setMessage("插入不成功！");
            return result;
        }
        result.setSuccess(true);
        result.setMessage("插入成功！");
        result.setData(resultCategory);
        return result;
    }
}
