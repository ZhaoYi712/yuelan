package com.yuelan.apartment.mapper;

import com.yuelan.apartment.domain.ApartmentInfo;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description: 公寓信息Mapper
 * @Author: ZhaoYi
 * @date: 24-5-18 11:01
 */
@Repository
public interface ApartmentMapper{

    /**
     * 新增
     * @date 2024/05/18
     **/
    int insert(ApartmentInfo apartmentInfo);

    /**
     * 刪除
     * @date 2024/05/18
     **/
    int delete(Long id);

    /**
     * 批量删除公寓信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteIds(Long[] ids);

    /**
     * 更新
     * @date 2024/05/18
     **/
    int update(ApartmentInfo apartmentInfo);

    /**
     * 查询 根据主键 id 查询
     * @date 2024/05/18
     **/
    ApartmentInfo load(@Param("id") Long id);

    /**
     * 查询 分页查询
     * @date 2024/05/18
     **/
    List<ApartmentInfo> pageList(ApartmentInfo apartmentInfo);

    /**
     * 查询 根据房源昵称查询
     * @date 2024/06/19
     */
    ApartmentInfo queryByNickName(@Param("ownerId")Long ownerId, @Param("apartmentName")String apartmentName);

}
