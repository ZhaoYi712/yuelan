package com.yuelan.apartment.controller;

import com.yuelan.apartment.domain.ApartmentInfo;
import com.yuelan.apartment.service.ApartmentInfoService;
import com.yuelan.common.core.context.SecurityContextHolder;
import com.yuelan.common.core.utils.StringUtils;
import com.yuelan.common.core.web.domain.AjaxResult;
import com.yuelan.common.log.annotation.Log;
import com.yuelan.common.log.enums.BusinessType;
import com.yuelan.common.security.annotation.RequiresPermissions;
import com.yuelan.common.security.handler.GlobalExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

/**
 * @description: 公寓房源管理
 * @Author: ZhaoYi
 * @date: 24-5-18 10:53
 */
@RestController
@RequestMapping(value = "/house")
public class ApartmentInfoController {

    @Resource
    private ApartmentInfoService apartmentInfoService;

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 房源添加
     * @author ZhaoYi
     * @date 2024/05/18
     **/
    @PostMapping("/add")
    @Log(title = "房源添加", businessType = BusinessType.INSERT)
    public AjaxResult addApartment(@RequestBody @Valid ApartmentInfo apartmentInfo){
        try {
            apartmentInfoService.add(apartmentInfo);
            return AjaxResult.success();
        }
        catch (Exception e){
            log.error(e.getMessage());
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 房源删除
     * @author ZhaoYi
     * @date 2024/05/18
     **/
    @GetMapping("/delete")
    @Log(title = "房源删除", businessType = BusinessType.DELETE)
    public Object delApartment(String id){
        try {
            apartmentInfoService.delete(id);
            return AjaxResult.success();
        }
        catch (Exception e){
            log.error(e.getMessage());
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 房源编辑
     * @author ZhaoYi
     * @date 2024/05/18
     **/
    @PostMapping("/update")
    @Log(title = "房源编辑", businessType = BusinessType.UPDATE)
    public Object updateApartment(@RequestBody ApartmentInfo apartmentInfo){
        try {
            apartmentInfoService.update(apartmentInfo);
            return AjaxResult.success();
        }
        catch (Exception e){
            log.error(e.getMessage());
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 房源信息查询
     * @author ZhaoYi
     * @date 2024/05/18
     **/
    @GetMapping("/load")
    @Log(title = "房源信息查询", businessType = BusinessType.QUERY)
    public AjaxResult load(String id){
        try {
            ApartmentInfo load = apartmentInfoService.load(id);
            return AjaxResult.success(load);
        }
        catch (Exception e){
            log.error(e.getMessage());
            return AjaxResult.error(e.getMessage());
        }

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
        try {
            Map<String, Object> pageList = apartmentInfoService.pageList(offset , pagesize);
            return AjaxResult.success(pageList);
        }
        catch (Exception e){
            log.error(e.getMessage());
            return AjaxResult.error(e.getMessage());
        }
    }



}
