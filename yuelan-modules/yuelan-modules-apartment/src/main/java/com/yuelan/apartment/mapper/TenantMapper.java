package com.yuelan.apartment.mapper;

import com.yuelan.apartment.domain.TenantInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 房客信息
 * @Author: ZhaoYi
 * @date: 24-5-23 16:55
 */
public interface TenantMapper {

    /**
     * 新增
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    int insert(TenantInfo tenantInfo);

    /**
     * 刪除
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    int delete(Integer id);

    /**
     * 更新
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    int update(TenantInfo tenantInfo);

    /**
     * 查询 根据主键 id 查询
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    TenantInfo load(Integer id);

    /**
     * 查询 分页查询
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    List<TenantInfo> pageList(@Param("offset") int offset, @Param("pagesize") int pagesize);

    /**
     * 查询 分页查询 count
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    int pageListCount(@Param("offset") int offset, @Param("pagesize") int pagesize);



}
