package com.yuelan.apartment.mapper;

import com.yuelan.apartment.domain.TenantInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description: 房客信息
 * @Author: ZhaoYi
 * @date: 24-5-23 16:55
 */
@Repository
public interface TenantMapper{

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
    int delete(Long id);

    /**
     * 批量删除房客信息
     * @author ZhaoYi
     * @date 2024/06/15
     * @param ids 需要删除的数据主键集合
     */
    public int deleteTenantByIds(Long[] ids);

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
    TenantInfo load(Long id);

    /**
     * 查询所有租客
     * @author ZhaoYi
     * @date 2024/06/15
     */
    List<TenantInfo> tenantInfoList(TenantInfo apaTenantInfo);

}
