package com.yuelan.apartment.service;

import com.yuelan.apartment.domain.TenantInfo;
import com.yuelan.apartment.domain.vo.TenantRegisVo;
import com.yuelan.apartment.domain.vo.TenantVo;

import java.util.List;

/**
 * @description:
 * @Author: ZhaoYi
 * @date: 24-5-23 16:59
 */
public interface TenantService {


    /**
     * 新增租户及房间
     *
     * @param tenantRegisVo
     * @return
     */
    public int insert(TenantRegisVo tenantRegisVo);

    /**
     * 删除
     *
     * @return
     */
    public int delete(Long id);

    /**
     * 批量删除房客信息
     * @param ids 需要删除的房客信息主键集合
     */
    public int deleteTenantByIds(Long[] ids);

    /**
     * 更新
     *
     * @return
     */
    public int update(TenantInfo tenantInfo);

    /**
     * 根据主键 id 查询
     */
    public TenantInfo load(Long id);

    /**
     * 分页查询
     */
    public List<TenantVo> list(TenantInfo tenantInfo);



}
