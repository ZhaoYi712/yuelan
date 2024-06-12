package com.yuelan.apartment.controller;

import com.yuelan.apartment.domain.ApaRoomInfo;
import com.yuelan.apartment.domain.vo.FloorVo;
import com.yuelan.apartment.service.ApaRoomService;
import com.yuelan.common.core.web.domain.AjaxResult;
import com.yuelan.common.log.annotation.Log;
import com.yuelan.common.log.enums.BusinessType;
import com.yuelan.common.security.handler.GlobalExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
public class ApaRoomController {

    @Resource
    private ApaRoomService apaRoomInfoService;


    /**
     * 新增房间
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    @PostMapping("/add")
    @Log(title = "新增房间", businessType = BusinessType.INSERT)
    public AjaxResult addRoom(@Validated @RequestBody ApaRoomInfo apaRoomInfo) {
        apaRoomInfoService.addRoom(apaRoomInfo);
        return AjaxResult.success();
    }

    /**
     * 刪除房间
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    @GetMapping("/delete")
    @Log(title = "刪除房间", businessType = BusinessType.DELETE)
    public AjaxResult deleteRoom(Integer id) {
        apaRoomInfoService.delete(id);
        return AjaxResult.success();
    }

    /**
     * 更新房间
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    @GetMapping("/update")
    @Log(title = "更新房间", businessType = BusinessType.UPDATE)
    public AjaxResult update(@RequestBody @Validated ApaRoomInfo apaRoomInfo) {
        apaRoomInfoService.update(apaRoomInfo);
        return AjaxResult.success();
    }

    /**
     * 查询房间信息
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    @GetMapping("/load")
    @Log(title = "查询房间信息", businessType = BusinessType.QUERY)
    public AjaxResult load(Integer id) {
        ApaRoomInfo load = apaRoomInfoService.load(id);
        return AjaxResult.success(load);
    }

    /**
     * 查询房间 分页查询
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    @GetMapping("/pageList")
    @Log(title = "查询房间-分页查询", businessType = BusinessType.QUERY)
    public AjaxResult pageList(@RequestParam(required = false, defaultValue = "0") int offset,
                               @RequestParam(required = false, defaultValue = "10") int pagesize,
                               @RequestParam("apartment_id") Integer apartmentId) {
        Map<String, Object> pageList = apaRoomInfoService.pageList(offset, pagesize, apartmentId);
        return AjaxResult.success(pageList);
    }

    /**
     * 查询当前房源下所有房租
     * @author ZhaoYi
     * @date 2024/05/23
     */
    @RequestMapping("/list")
    @Log(title = "查询当前房源下所有房租", businessType = BusinessType.QUERY)
    public AjaxResult roomList(@RequestParam("apartment_id") @NotNull Integer apartmentId) {
        List<FloorVo> floorVoList = apaRoomInfoService.roomList(apartmentId);
        return AjaxResult.success(floorVoList);
    }
}
