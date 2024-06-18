package com.yuelan.apartment.mapper;

import com.yuelan.apartment.domain.ApaRoomInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @Author: ZhaoYi
 * @date: 24-5-23 9:13
 */
@Repository
public interface RoomMapper {

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
    int delete(Long id);

    /**
     * 批量删除房租信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteRoomIds(Long[] ids);

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
    ApaRoomInfo load(Long id);

    /**
     * 查询 分页查询
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    List<ApaRoomInfo> pageList(@Param("offset") int offset, @Param("pagesize") int pagesize,
                               @Param("apartmentId") Integer apartmentId);


    /**
     * 查询当前房源下所有房租
     * @Author: ZhaoYi
     * @date 2024/06/07
     */
    List<ApaRoomInfo> roomList(@Param("id") Long id);


    /**
     * 查询房租是否已存在
     * @param room_id
     * @return
     */
    ApaRoomInfo queryRoomId(String room_id);


    /**
     * 查询房租信息列表
     *
     * @param apaRoomInfo 房租信息
     * @return 房租信息集合
     */
    List<ApaRoomInfo> selectApaRoomInfoList(ApaRoomInfo apaRoomInfo);

}
