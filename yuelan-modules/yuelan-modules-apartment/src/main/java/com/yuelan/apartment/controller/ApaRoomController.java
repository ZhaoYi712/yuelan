package com.yuelan.apartment.controller;

import com.yuelan.apartment.domain.ApaRoomInfo;
import com.yuelan.apartment.domain.vo.FloorVo;
import com.yuelan.apartment.service.ApaRoomService;
import com.yuelan.common.core.utils.poi.ExcelUtil;
import com.yuelan.common.core.web.controller.BaseController;
import com.yuelan.common.core.web.domain.AjaxResult;
import com.yuelan.common.core.web.page.TableDataInfo;
import com.yuelan.common.log.annotation.Log;
import com.yuelan.common.log.enums.BusinessType;
import com.yuelan.common.security.annotation.RequiresPermissions;
import com.yuelan.common.security.handler.GlobalExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @description: 房间信息
 * @Author: ZhaoYi
 * @date: 24-5-23 9:23
 */
@RestController
@RequestMapping(value = "/room")
@Validated
public class ApaRoomController extends BaseController {

    @Autowired
    private ApaRoomService apaRoomInfoService;

    /**
     * 导出房租信息列表
     */
    @RequiresPermissions("apartment:room:export")
    @Log(title = "房租信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ApaRoomInfo apaRoomInfo) {
        List<ApaRoomInfo> list = apaRoomInfoService.selectApaRoomInfoList(apaRoomInfo);
        ExcelUtil<ApaRoomInfo> util = new ExcelUtil<>(ApaRoomInfo.class);
        util.exportExcel(response, list, "房租信息数据");
    }

    /**
     * 新增房间
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    @PostMapping
    @RequiresPermissions("apartment:room:add")
    @Log(title = "新增房间", businessType = BusinessType.INSERT)
    public AjaxResult addRoom(@Validated @RequestBody ApaRoomInfo apaRoomInfo) {
        return toAjax(apaRoomInfoService.addRoom(apaRoomInfo));
    }

    /**
     * 刪除房间
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    @DeleteMapping("/{ids}")
    @RequiresPermissions("apartment:room:remove")
    @Log(title = "刪除房间", businessType = BusinessType.DELETE)
    public AjaxResult remove(@NotNull @PathVariable Long[] ids) {
        return toAjax(apaRoomInfoService.deleteRoomIds(ids));
    }

    /**
     * 更新房间
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    @PutMapping
    @RequiresPermissions("apartment:room:edit")
    @Log(title = "修改房间", businessType = BusinessType.UPDATE)
    public AjaxResult edit(@RequestBody @Validated ApaRoomInfo apaRoomInfo) {
        return toAjax(apaRoomInfoService.update(apaRoomInfo));
    }

    /**
     * 查询房间信息
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    @GetMapping
    @RequiresPermissions("apartment:room:query")
    @Log(title = "查询房间信息", businessType = BusinessType.QUERY)
    public AjaxResult load(@RequestParam("id") Long id) {
        return success(apaRoomInfoService.load(id));
    }

    /**
     * 查询当前房源下所有房租
     * @author ZhaoYi
     * @date 2024/05/23
     */
    @GetMapping("/list")
    @RequiresPermissions("apartment:room:list")
    @Log(title = "查询当前房源下所有房租", businessType = BusinessType.QUERY)
    public AjaxResult list(@RequestParam("id") Long id) {
        List<FloorVo> floorVoList = apaRoomInfoService.roomList(id);
        return success(floorVoList);
    }

}
