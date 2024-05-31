package com.yuelan.apartment.service.impl;

import com.yuelan.apartment.domain.ApaRoomInfo;
import com.yuelan.apartment.mapper.ApaRoomInfoMapper;
import com.yuelan.apartment.service.ApaRoomService;
import com.yuelan.apartment.service.TenantService;
import com.yuelan.common.core.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @Author: ZhaoYi
 * @date: 24-5-23 9:21
 */
@Service
public class ApaRoomServiceImpl implements ApaRoomService {

    @Autowired
    private TenantService tenantService;

    @Resource
    private ApaRoomInfoMapper apaRoomInfoMapper;


    @Override
    public Object insert(ApaRoomInfo apaRoomInfo) {

        // valid
        if (apaRoomInfo == null) {
            return AjaxResult.error("必要参数缺失");
        }

        apaRoomInfoMapper.insert(apaRoomInfo);
        return AjaxResult.success();
    }


    @Override
    public Object delete(int id) {
        int ret = apaRoomInfoMapper.delete(id);
        return ret>0?AjaxResult.success():AjaxResult.error();
    }


    @Override
    public Object update(ApaRoomInfo apaRoomInfo) {
        int ret = apaRoomInfoMapper.update(apaRoomInfo);
        return ret>0?AjaxResult.success():AjaxResult.error();
    }


    @Override
    public ApaRoomInfo load(int id) {
        return apaRoomInfoMapper.load(id);
    }


    @Override
    public Map<String,Object> pageList(int offset, int pagesize) {

        List<ApaRoomInfo> pageList = apaRoomInfoMapper.pageList(offset, pagesize);
        int totalCount = apaRoomInfoMapper.pageListCount(offset, pagesize);

        // result
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("pageList", pageList);
        result.put("totalCount", totalCount);

        return result;
    }

}
