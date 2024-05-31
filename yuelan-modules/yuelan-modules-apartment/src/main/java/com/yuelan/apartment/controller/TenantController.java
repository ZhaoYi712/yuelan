package com.yuelan.apartment.controller;

import com.yuelan.apartment.domain.TenantInfo;
import com.yuelan.apartment.service.TenantService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.Map;

/**
 * @description:
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
    @RequestMapping("/insert")
    public Object insert(TenantInfo tenantInfo){
        return tenantInfoService.insert(tenantInfo);
    }

    /**
     * 刪除
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    @RequestMapping("/delete")
    public Object delete(int id){
        return tenantInfoService.delete(id);
    }

    /**
     * 更新
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    @RequestMapping("/update")
    public Object update(TenantInfo tenantInfo){
        return tenantInfoService.update(tenantInfo);
    }

    /**
     * 租户信息查看与编辑
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    @RequestMapping("/load")
    public Object load(int id){
        return tenantInfoService.load(id);
    }

    /**
     * 查询 分页查询
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    @RequestMapping("/pageList")
    public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int offset,
                                        @RequestParam(required = false, defaultValue = "10") int pagesize) {
        return tenantInfoService.pageList(offset, pagesize);
    }
}
