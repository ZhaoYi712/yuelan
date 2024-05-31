package com.yuelan.apartment.service.impl;

import com.yuelan.apartment.domain.OwnerInfo;
import com.yuelan.apartment.mapper.OwnerMapper;
import com.yuelan.apartment.service.OwnerService;
import com.yuelan.common.core.web.domain.AjaxResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 房东信息
 * @Author: ZhaoYi
 * @date: 24-5-20 3:15
 */
@Service
public class OwnerServiceImpl implements OwnerService {

    @Resource
    private OwnerMapper ownerMapper;

    @Override
    public Object insert(OwnerInfo ownerInfo) {

        // valid
        if (ownerInfo == null) {
            return AjaxResult.error("必要参数缺失");
        }

        ownerInfo.setCreate_time(new Date());

        ownerMapper.insert(ownerInfo);
        return AjaxResult.success();
    }


    @Override
    public Object delete(int id) {
        int ret = ownerMapper.delete(id);
        return ret>0?AjaxResult.success(): AjaxResult.error();
    }


    @Override
    public Object update(OwnerInfo ownerInfo) {
        int ret = ownerMapper.update(ownerInfo);
        return ret>0?AjaxResult.success():AjaxResult.error();
    }


    @Override
    public OwnerInfo load(int id) {
        return ownerMapper.load(id);
    }


    @Override
    public Map<String,Object> pageList(int offset, int pagesize) {

        List<OwnerInfo> pageList = ownerMapper.pageList(offset, pagesize);
        int totalCount = ownerMapper.pageListCount(offset, pagesize);

        // result
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("pageList", pageList);
        result.put("totalCount", totalCount);

        return result;
    }

}
