package com.yuelan.apartment.service.impl;

import com.yuelan.apartment.domain.TenantInfo;
import com.yuelan.apartment.mapper.TenantMapper;
import com.yuelan.apartment.service.TenantService;
import com.yuelan.common.core.web.domain.AjaxResult;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @Author: ZhaoYi
 * @date: 24-5-23 17:00
 */
@Service
public class TenantServiceImpl implements TenantService {

    @Resource
    private TenantMapper tenantInfoMapper;


    @Override
    public Object insert(TenantInfo tenantInfo) {

        // valid
        if (tenantInfo == null) {
            return AjaxResult.error("必要参数缺失");
        }

        tenantInfoMapper.insert(tenantInfo);
        return AjaxResult.success();
    }


    @Override
    public Object delete(int id) {
        int ret = tenantInfoMapper.delete(id);
        return ret>0?AjaxResult.success():AjaxResult.error();
    }


    @Override
    public Object update(TenantInfo tenantInfo) {
        int ret = tenantInfoMapper.update(tenantInfo);
        return ret>0?AjaxResult.success():AjaxResult.error();
    }


    @Override
    public TenantInfo load(int id) {
        return tenantInfoMapper.load(id);
    }


    @Override
    public Map<String,Object> pageList(int offset, int pagesize) {

        List<TenantInfo> pageList = tenantInfoMapper.pageList(offset, pagesize);
        int totalCount = tenantInfoMapper.pageListCount(offset, pagesize);

        // result
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("pageList", pageList);
        result.put("totalCount", totalCount);

        return result;
    }
}
