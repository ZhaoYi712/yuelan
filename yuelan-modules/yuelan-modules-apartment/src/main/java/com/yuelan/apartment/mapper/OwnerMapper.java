package com.yuelan.apartment.mapper;

import com.yuelan.apartment.domain.OwnerInfo;

import java.util.List;

/**
 * @description:
 * @Author: ZhaoYi
 * @date: 24-5-20 3:10
 */
public interface OwnerMapper {

    /**
     * 新增
     * @author ZhaoYi
     * @date 2024/05/19
     **/
    int insert(OwnerInfo ownerInfo);

    /**
     * 刪除
     * @author ZhaoYi
     * @date 2024/05/19
     **/
    int delete(int id);

    /**
     * 更新
     * @author ZhaoYi
     * @date 2024/05/19
     **/
    int update(OwnerInfo ownerInfo);

    /**
     * 查询 根据主键 id 查询
     * @author ZhaoYi
     * @date 2024/05/19
     **/
    OwnerInfo load(int id);

    /**
     * 查询 分页查询
     * @author ZhaoYi
     * @date 2024/05/19
     **/
    List<OwnerInfo> pageList(int offset, int pagesize);

    /**
     * 查询 分页查询 count
     * @author ZhaoYi
     * @date 2024/05/19
     **/
    int pageListCount(int offset,int pagesize);

}
