package com.yuelan.apartment.service;

import com.yuelan.apartment.domain.ApartmentInfo;
import com.yuelan.common.core.domain.R;

import java.util.Map;

/**
 * @description:
 * @Author: ZhaoYi
 * @date: 24-5-18 11:04
 */
public interface ApartmentInfoService {

    /**
     * 新增
     */
    public void add(ApartmentInfo apartmentInfo);

    /**
     * 删除
     */
    public void delete(String id);

    /**
     * 更新
     */
    public void update(ApartmentInfo apartmentInfo);

    /**
     * 根据主键 id 查询
     */
    public ApartmentInfo load(String id);

    /**
     * 分页查询
     */
    public Map<String,Object> pageList(int offset, int pagesize);
}