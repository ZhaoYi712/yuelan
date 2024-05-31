package com.yuelan.apartment.service;

import com.yuelan.apartment.domain.OwnerInfo;

import java.util.Map;

/**
 * @description: 房东信息
 * @Author: ZhaoYi
 * @date: 24-5-20 3:14
 */
public interface OwnerService {

    /**
     * 新增
     */
    public Object insert(OwnerInfo ownerInfo);

    /**
     * 删除
     */
    public Object delete(int id);

    /**
     * 更新
     */
    public Object update(OwnerInfo ownerInfo);

    /**
     * 根据主键 id 查询
     */
    public OwnerInfo load(int id);

    /**
     * 分页查询
     */
    public Map<String,Object> pageList(int offset, int pagesize);
}
