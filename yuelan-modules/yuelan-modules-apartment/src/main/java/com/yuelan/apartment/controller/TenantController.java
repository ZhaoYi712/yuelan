package com.yuelan.apartment.controller;

import com.yuelan.apartment.domain.TenantInfo;
import com.yuelan.apartment.domain.vo.TenantRegisVo;
import com.yuelan.apartment.service.TenantService;
import com.yuelan.common.core.web.domain.AjaxResult;
import com.yuelan.common.log.annotation.Log;
import com.yuelan.common.log.enums.BusinessType;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @description: 租户管理
 * @Author: ZhaoYi
 * @date: 24-5-23 17:01
 */

@RestController
@RequestMapping(value = "/tenant")
public class TenantController {

    @Resource
    private TenantService tenantInfoService;


    /**
     * 租户信息录入
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    @PostMapping("/addTenant")
    @Log(title = "租户信息录入", businessType = BusinessType.INSERT)
    public AjaxResult addTenant(@RequestBody @Valid TenantRegisVo tenantRegisVo){
        tenantInfoService.insert(tenantRegisVo);
        return AjaxResult.success();
    }

    /**
     * 租户清除管理
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    @DeleteMapping("/delTenant")
    @Log(title = "租户清除", businessType = BusinessType.DELETE)
    public AjaxResult delTenant(@NotNull Integer id){
        tenantInfoService.delete(id);
        return AjaxResult.success();
    }

    /**
     * 租户信息更新
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    @PostMapping("/updateTenant")
    @Log(title = "租户信息更新", businessType = BusinessType.UPDATE)
    public AjaxResult updateTenant(@RequestBody @Valid TenantInfo tenantInfo){
        tenantInfoService.update(tenantInfo);
        return AjaxResult.success();
    }

    /**
     * 租户信息查看
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    @GetMapping("/load")
    @Log(title = "租户信息查看", businessType = BusinessType.QUERY)
    public AjaxResult load(@NotNull Integer id){
        TenantInfo load = tenantInfoService.load(id);
        return AjaxResult.success(load);
    }

    /**
     * 查询当前房源所有租户 分页查询
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    @GetMapping("/tenantList")
    @Log(title = "查询当前房源所有租户-分页查询", businessType = BusinessType.QUERY)
    public AjaxResult pageList(@RequestParam(required = false, defaultValue = "0") int offset,
                               @RequestParam(required = false, defaultValue = "10") int pagesize) {
        Map<String, Object> pageList = tenantInfoService.pageList(offset , pagesize);
        return AjaxResult.success(pageList);
    }

}
