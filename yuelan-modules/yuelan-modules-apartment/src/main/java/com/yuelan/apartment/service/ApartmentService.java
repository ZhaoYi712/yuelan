package com.yuelan.apartment.service;

import com.yuelan.apartment.domain.ApartmentInfo;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @Author: ZhaoYi
 * @date: 24-5-18 11:04
 */
public interface ApartmentService {

    /**
     * 新增
     *
     * @return
     */
    public int add(ApartmentInfo apartmentInfo);

    /**
     * 删除
     *
     * @return
     */
    public int delete(Long id);

    /**
     * 批量删除
     */
    public int deleteIds(Long[] ids);

    /**
     * 更新
     *
     * @return
     */
    public int update(ApartmentInfo apartmentInfo);

    /**
     * 根据主键 id 查询
     */
    public ApartmentInfo load(Long id);

    /**
     * 分页查询
     */
    public List<ApartmentInfo> pageList(ApartmentInfo apartmentInfo);
}
