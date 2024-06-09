package com.yuelan.apartment.service;

import com.yuelan.apartment.domain.TenantInfo;

import java.util.Map;

/**
 * @description:
 * @Author: ZhaoYi
 * @date: 24-5-23 16:59
 */
public interface TenantService {

    /**
     * 新增
     */
    public void insert(TenantInfo tenantInfo);

    /**
     * 删除
     */
    public void delete(Integer id);

    /**
     * 更新
     */
    public void update(TenantInfo tenantInfo);

    /**
     * 根据主键 id 查询
     */
    public TenantInfo load(Integer id);

    /**
     * 分页查询
     */
    public Map<String,Object> pageList(int offset, int pagesize);
}
