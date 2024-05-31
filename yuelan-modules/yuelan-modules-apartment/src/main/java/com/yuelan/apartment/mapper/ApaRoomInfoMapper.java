package com.yuelan.apartment.mapper;

import com.yuelan.apartment.domain.ApaRoomInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @Author: ZhaoYi
 * @date: 24-5-23 9:13
 */
public interface ApaRoomInfoMapper {

    /**
     * 新增
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    int insert(ApaRoomInfo apaRoomInfo);

    /**
     * 刪除
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    int delete(int id);

    /**
     * 更新
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    int update(ApaRoomInfo apaRoomInfo);

    /**
     * 查询 根据主键 id 查询
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    ApaRoomInfo load(int id);

    /**
     * 查询 分页查询
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    List<ApaRoomInfo> pageList(@Param("offset") int offset, @Param("pagesize") int pagesize);

    /**
     * 查询 分页查询 count
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    int pageListCount(@Param("offset")int offset, @Param("pagesize") int pagesize);
}