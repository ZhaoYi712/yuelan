package com.yuelan.apartment.controller;

import com.yuelan.apartment.domain.TenantInfo;
import com.yuelan.apartment.domain.vo.TenantRegisVo;
import com.yuelan.apartment.domain.vo.TenantVo;
import com.yuelan.apartment.service.TenantService;
import com.yuelan.common.core.web.controller.BaseController;
import com.yuelan.common.core.web.domain.AjaxResult;
import com.yuelan.common.core.web.page.TableDataInfo;
import com.yuelan.common.log.annotation.Log;
import com.yuelan.common.log.enums.BusinessType;
import com.yuelan.common.security.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * @description: 租户管理
 * @Author: ZhaoYi
 * @date: 24-5-23 17:01
 */

@RestController
@RequestMapping(value = "/tenant")
public class TenantController extends BaseController {

    @Resource
    private TenantService tenantInfoService;


    /**
     * 租户信息录入
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    @PostMapping("/add")
    @RequiresPermissions("apartment:tenant:add")
    @Log(title = "租户信息录入", businessType = BusinessType.INSERT)
    public AjaxResult addTenant(@RequestBody @Valid TenantRegisVo tenantRegisVo){
        return toAjax(tenantInfoService.insert(tenantRegisVo));
    }

    /**
     * 删除租户
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    @DeleteMapping("/delete")
    @RequiresPermissions("apartment:tenant:delete")
    @Log(title = "租户清除", businessType = BusinessType.DELETE)
    public AjaxResult remove(@NotNull Long id){
        return toAjax(tenantInfoService.delete(id));
    }

    /**
     * 租户信息更新
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    @PostMapping("/update")
    @RequiresPermissions("apartment:tenant:update")
    @Log(title = "租户信息更新", businessType = BusinessType.UPDATE)
    public AjaxResult updateTenant(@RequestBody @Valid TenantInfo tenantInfo){
        return toAjax(tenantInfoService.update(tenantInfo));
    }

    /**
     * 租户信息查看
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    @GetMapping("/load")
    @RequiresPermissions("apartment:tenant:load")
    @Log(title = "租户信息查看", businessType = BusinessType.QUERY)
    public AjaxResult load(@NotNull Long id){
        return success(tenantInfoService.load(id));
    }


    /**
     * 查询所有租户 分页查询
     * @author ZhaoYi
     * @date 2024/06/15
     **/
    @GetMapping("/pageList")
    @RequiresPermissions("apartment:tenant:list")
    @Log(title = "查询所有租户-分页查询", businessType = BusinessType.QUERY)
    public TableDataInfo pageList(TenantInfo tenantInfo){
        List<TenantVo> list = tenantInfoService.list(tenantInfo);
        return getDataTable(list);
    }


    /**
     * 清除房客
     * @author ZhaoYi
     * @date 2024/06/15
     **/
    @DeleteMapping("/{ids}")
    @RequiresPermissions("apartment:tenant:remove")
    @Log(title = "房客信息", businessType = BusinessType.DELETE)
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tenantInfoService.deleteTenantByIds(ids));
    }

}
