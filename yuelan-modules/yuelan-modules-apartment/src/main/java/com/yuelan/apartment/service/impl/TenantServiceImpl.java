package com.yuelan.apartment.service.impl;

import com.yuelan.apartment.domain.TenantInfo;
import com.yuelan.apartment.mapper.TenantMapper;
import com.yuelan.apartment.service.TenantService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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


    @Transactional
    @Override
    public void insert(TenantInfo tenantInfo) {
        tenantInfoMapper.insert(tenantInfo);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        tenantInfoMapper.delete(id);

    }

    @Transactional
    @Override
    public void update(TenantInfo tenantInfo) {
        tenantInfoMapper.update(tenantInfo);

    }


    @Override
    public TenantInfo load(Integer id) {
        return tenantInfoMapper.load(id);
    }


    @Override
    public Map<String,Object> pageList(int offset, int pagesize) {

        List<TenantInfo> pageList = tenantInfoMapper.pageList(offset, pagesize);
        int totalCount = tenantInfoMapper.pageListCount(offset, pagesize);

        Map<String, Object> result = new HashMap<>();
        result.put("pageList", pageList);
        result.put("totalCount", totalCount);

        return result;
    }
}
