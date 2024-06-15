package com.yuelan.apartment.mapper;

import com.yuelan.apartment.domain.ApartmentInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description: 公寓信息Mapper
 * @Author: ZhaoYi
 * @date: 24-5-18 11:01
 */
@Repository
public interface ApartmentMapper{

    /**
     * 新增
     * @author BEJSON
     * @date 2024/05/18
     **/
    int insert(ApartmentInfo apartmentInfo);

    /**
     * 刪除
     * @author BEJSON
     * @date 2024/05/18
     **/
    int delete(Integer id);

    /**
     * 更新
     * @author BEJSON
     * @date 2024/05/18
     **/
    int update(ApartmentInfo apartmentInfo);

    /**
     * 查询 根据主键 id 查询
     * @author BEJSON
     * @date 2024/05/18
     **/
    ApartmentInfo load(Long id);

    /**
     * 查询 分页查询
     * @author BEJSON
     * @date 2024/05/18
     **/
    List<ApartmentInfo> pageList(@Param("username") String username, @Param("offset") int offset, @Param("pagesize") int pagesize);

    /**
     * 查询 分页查询 count
     * @author BEJSON
     * @date 2024/05/18
     **/
    int pageListCount(@Param("offset") int offset, @Param("pagesize") int pagesize);
}
