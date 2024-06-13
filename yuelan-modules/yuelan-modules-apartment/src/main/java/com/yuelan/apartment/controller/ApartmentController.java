package com.yuelan.apartment.controller;

import com.yuelan.apartment.domain.ApartmentInfo;
import com.yuelan.apartment.service.ApartmentService;
import com.yuelan.common.core.web.domain.AjaxResult;
import com.yuelan.common.log.annotation.Log;
import com.yuelan.common.log.enums.BusinessType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description: 公寓房源管理
 * @Author: ZhaoYi
 * @date: 24-5-18 10:53
 */
@RestController
@RequestMapping(value = "/house")
public class ApartmentController {

    @Resource
    private ApartmentService apartmentService;

    /**
     * 房源添加
     * @author ZhaoYi
     * @date 2024/05/18
     **/
    @PostMapping("/add")
    @Log(title = "房源添加", businessType = BusinessType.INSERT)
    public AjaxResult addApartment(@RequestBody @Validated ApartmentInfo apartmentInfo){
        apartmentService.add(apartmentInfo);
        return AjaxResult.success();
    }

    /**
     * 房源删除
     * @author ZhaoYi
     * @date 2024/05/18
     **/
    @GetMapping("/delete")
    @Log(title = "房源删除", businessType = BusinessType.DELETE)
    public Object delApartment(String id){
        apartmentService.delete(id);
        return AjaxResult.success();
    }

    /**
     * 房源编辑
     * @author ZhaoYi
     * @date 2024/05/18
     **/
    @PostMapping("/update")
    @Log(title = "房源编辑", businessType = BusinessType.UPDATE)
    public AjaxResult updateApartment(@RequestBody @Validated ApartmentInfo apartmentInfo){
        apartmentService.update(apartmentInfo);
        return AjaxResult.success();
    }

    /**
     * 房源信息查询
     * @author ZhaoYi
     * @date 2024/05/18
     **/
    @GetMapping("/load")
    @Log(title = "房源信息查询", businessType = BusinessType.QUERY)
    public AjaxResult load(String id){
        ApartmentInfo load = apartmentService.load(id);
        return AjaxResult.success(load);
    }

    /**
     * 房源查询 分页查询
     * @author ZhaoYi
     * @date 2024/05/18
     **/
    @GetMapping("/pageList")
    @Log(title = "房源信息查询-分页查询", businessType = BusinessType.QUERY)
    public AjaxResult pageList(@RequestParam(required = false, defaultValue = "0") int offset,
                               @RequestParam(required = false, defaultValue = "10") int pagesize) {
        Map<String, Object> pageList = apartmentService.pageList(offset , pagesize);
        return AjaxResult.success(pageList);

    }

}
