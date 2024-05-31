package com.yuelan.apartment.controller;

import com.yuelan.apartment.domain.ApartmentInfo;
import com.yuelan.apartment.service.ApartmentInfoService;
import com.yuelan.common.core.context.SecurityContextHolder;
import com.yuelan.common.core.utils.StringUtils;
import com.yuelan.common.core.web.domain.AjaxResult;
import com.yuelan.common.security.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    /**
     * 房源添加
     * @author ZhaoYi
     * @date 2024/05/18
     **/
    @RequestMapping( value = "/add", method = RequestMethod.POST)
    public AjaxResult addApartment(@RequestBody ApartmentInfo apartmentInfo){
        try {
            apartmentInfoService.add(apartmentInfo);
            return AjaxResult.success();
        }
        catch (Exception e){
            return AjaxResult.error();
        }
    }

    /**
     * 房源删除
     * @author ZhaoYi
     * @date 2024/05/18
     **/
    @RequestMapping("/delete")
    public Object delApartment(String id){
        try {
            apartmentInfoService.delete(id);
            return AjaxResult.success();
        }
        catch (Exception e){
            return AjaxResult.error();
        }
    }

    /**
     * 房源编辑
     * @author ZhaoYi
     * @date 2024/05/18
     **/
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object updateApartment(@RequestBody ApartmentInfo apartmentInfo){
        try {
            apartmentInfoService.update(apartmentInfo);
            return AjaxResult.success();
        }
        catch (Exception e){
            return AjaxResult.error();
        }
    }

    /**
     * 房源信息查询
     * @author ZhaoYi
     * @date 2024/05/18
     **/
    @RequestMapping("/load")
    public AjaxResult load(String id){
        try {
            ApartmentInfo load = apartmentInfoService.load(id);
            return AjaxResult.success(load);
        }
        catch (Exception e){
            return AjaxResult.error();
        }

    }

    /**
     * 房源查询 分页查询
     * @author ZhaoYi
     * @date 2024/05/18
     **/
    @RequestMapping("/pageList")
    public AjaxResult pageList(@RequestParam(required = false, defaultValue = "0") int offset,
                               @RequestParam(required = false, defaultValue = "10") int pagesize) {
        try {
            Map<String, Object> pageList = apartmentInfoService.pageList(offset , pagesize);
            return AjaxResult.success(pageList);
        }
        catch (Exception e){
            return AjaxResult.error();
        }
    }



}
