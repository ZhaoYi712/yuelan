package com.yuelan.apartment.controller;

import com.yuelan.apartment.domain.OwnerInfo;
import com.yuelan.apartment.service.OwnerService;
import com.yuelan.common.core.web.domain.AjaxResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.naming.spi.DirStateFactory;
import javax.xml.transform.Result;
import java.util.Map;

/**
 * @description: 房东管理
 * @Author: ZhaoYi
 * @date: 24-5-20 12:18
 */

@RestController
@RequestMapping(value = "/owner")
public class OwnerController {

    @Resource
    private OwnerService ownerService;


    @ApiOperation(value = "房东登录")
    @PostMapping("/login")
    public AjaxResult login(OwnerInfo ownerInfo){
        return null;
    }

    @ApiOperation(value = "房东注册")
    @PostMapping("/register")
    public AjaxResult register(OwnerInfo ownerInfo){

        return null;
    }

    @ApiOperation(value = "密码找回")
    @PostMapping("/findPwd")
    public AjaxResult findPwd(){

        return null;
    }


    /**
     * 新增
     * @author ZhaoYi
     * @date 2024/05/19
     **/
    @RequestMapping("/insert")
    public Object insert(@RequestBody OwnerInfo ownerInfo){
        return ownerService.insert(ownerInfo);
    }

    /**
     * 刪除
     * @author ZhaoYi
     * @date 2024/05/19
     **/
    @RequestMapping("/delete")
    public Object delete(int id){
        return ownerService.delete(id);
    }

    /**
     * 更新
     * @author ZhaoYi
     * @date 2024/05/19
     **/
    @RequestMapping("/update")
    public Object update(OwnerInfo ownerInfo){
        return ownerService.update(ownerInfo);
    }

    /**
     * 查询 根据主键 id 查询
     * @author ZhaoYi
     * @date 2024/05/19
     **/
    @RequestMapping("/load")
    public Object load(int id){
        return ownerService.load(id);
    }

    /**
     * 查询 分页查询
     * @author ZhaoYi
     * @date 2024/05/19
     **/
    @RequestMapping("/pageList")
    public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int offset,
                                        @RequestParam(required = false, defaultValue = "10") int pagesize) {
        return ownerService.pageList(offset, pagesize);
    }

}
