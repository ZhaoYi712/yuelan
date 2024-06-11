package com.yuelan.apartment.controller;

import com.yuelan.apartment.domain.TenantInfo;
import com.yuelan.apartment.domain.vo.TenantRegisVo;
import com.yuelan.apartment.service.TenantService;
import com.yuelan.common.core.web.domain.AjaxResult;
import com.yuelan.common.log.annotation.Log;
import com.yuelan.common.log.enums.BusinessType;
import com.yuelan.common.security.handler.GlobalExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * 租户信息录入
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    @PostMapping("/addTenant")
    @Log(title = "租户信息录入", businessType = BusinessType.INSERT)
    public AjaxResult addTenant(@RequestBody @Valid TenantRegisVo tenantRegisVo){
        try {
            String res = tenantInfoService.insert(tenantRegisVo);
            return AjaxResult.success(res);
        }
        catch (Exception e){
            log.error(e.getMessage());
            return AjaxResult.error();
        }
    }

    /**
     * 租户清除管理
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    @DeleteMapping("/delTenant")
    @Log(title = "租户清除", businessType = BusinessType.DELETE)
    public AjaxResult delTenant(@NotNull Integer id){
        try{
            tenantInfoService.delete(id);
            return AjaxResult.success();
        }
        catch (Exception e){
            log.error(e.getMessage());
            return AjaxResult.error();
        }
    }

    /**
     * 租户信息更新
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    @PostMapping("/updateTenant")
    @Log(title = "租户信息更新", businessType = BusinessType.UPDATE)
    public AjaxResult updateTenant(@RequestBody @Valid TenantInfo tenantInfo){
        try {
            tenantInfoService.update(tenantInfo);
            return AjaxResult.success();
        }
        catch (Exception e){
            log.error(e.getMessage());
            return AjaxResult.error();
        }
    }

    /**
     * 租户信息查看
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    @GetMapping("/load")
    @Log(title = "租户信息查看", businessType = BusinessType.QUERY)
    public AjaxResult load(@NotNull Integer id){
        try {
            TenantInfo load = tenantInfoService.load(id);
            return AjaxResult.success(load);
        }
        catch (Exception e){
            log.error(e.getMessage());
            return AjaxResult.error();
        }
    }

    /**
     * 查询当前房源所有租户 分页查询
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    @GetMapping("/allTenant")
    @Log(title = "查询当前房源所有租户-分页查询", businessType = BusinessType.QUERY)
    public AjaxResult pageList(@RequestParam(required = false, defaultValue = "0") int offset,
                               @RequestParam(required = false, defaultValue = "10") int pagesize) {
        try {
            Map<String, Object> pageList = tenantInfoService.pageList(offset , pagesize);
            return AjaxResult.success(pageList);
        }
        catch (Exception e){
            log.error(e.getMessage());
            return AjaxResult.error();
        }
    }

}
