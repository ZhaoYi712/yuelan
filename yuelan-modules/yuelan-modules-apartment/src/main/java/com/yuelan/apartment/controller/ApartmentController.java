package com.yuelan.apartment.controller;

import com.yuelan.apartment.domain.ApartmentInfo;
import com.yuelan.apartment.service.ApartmentService;
import com.yuelan.common.core.utils.poi.ExcelUtil;
import com.yuelan.common.core.web.controller.BaseController;
import com.yuelan.common.core.web.domain.AjaxResult;
import com.yuelan.common.core.web.page.TableDataInfo;
import com.yuelan.common.log.annotation.Log;
import com.yuelan.common.log.enums.BusinessType;
import com.yuelan.common.security.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.Map;

/**
 * @description: 公寓房源管理
 * @Author: ZhaoYi
 * @date: 24-5-18 10:53
 */
@RestController
@RequestMapping(value = "/house")
public class ApartmentController extends BaseController {

    @Autowired
    private ApartmentService apartmentService;

    /**
     * 房源添加
     * @author ZhaoYi
     * @date 2024/05/18
     **/
    @PostMapping
    @RequiresPermissions("apartment:house:add")
    @Log(title = "房源添加", businessType = BusinessType.INSERT)
    public AjaxResult addApartment(@RequestBody @Validated ApartmentInfo apartmentInfo){
        return toAjax(apartmentService.add(apartmentInfo));
    }

    /**
     * 房源删除
     * @author ZhaoYi
     * @date 2024/05/18
     **/
    @DeleteMapping("/{ids}")
    @RequiresPermissions("apartment:house:delete")
    @Log(title = "房源删除", businessType = BusinessType.DELETE)
    public AjaxResult remove(@PathVariable @NotNull Long[] ids){
        return toAjax(apartmentService.deleteIds(ids));
    }

    /**
     * 房源编辑
     * @author ZhaoYi
     * @date 2024/05/18
     **/
    @PutMapping
    @RequiresPermissions("apartment:house:edit")
    @Log(title = "房源编辑", businessType = BusinessType.UPDATE)
    public AjaxResult edit(@RequestBody @Validated ApartmentInfo apartmentInfo) {
        return toAjax(apartmentService.update(apartmentInfo));
    }

    /**
     * 房源信息查询
     * @author ZhaoYi
     * @date 2024/05/18
     **/
    @GetMapping(value = "/{id}")
    @RequiresPermissions("apartment:house:query")
    @Log(title = "房源信息查询", businessType = BusinessType.QUERY)
    public AjaxResult getInfo(@NotNull @PathVariable("id") Long id) {
        return success(apartmentService.load(id));
    }

    /**
     * 房源查询 分页查询
     * @author ZhaoYi
     * @date 2024/05/18
     **/
    @GetMapping("/list")
    @RequiresPermissions("apartment:house:list")
    @Log(title = "房源信息查询-分页查询", businessType = BusinessType.QUERY)
    public TableDataInfo list(ApartmentInfo apartment) {
        startPage();
        List<ApartmentInfo> list = apartmentService.pageList(apartment);
        return getDataTable(list);
    }

    /**
     * 导出公寓信息列表
     */
    @RequiresPermissions("apartment:house:export")
    @Log(title = "公寓信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ApartmentInfo apaApartmentInfo) {
        List<ApartmentInfo> list = apartmentService.pageList(apaApartmentInfo);
        ExcelUtil<ApartmentInfo> util = new ExcelUtil<>(ApartmentInfo.class);
        util.exportExcel(response, list, "公寓信息数据");
    }

}
