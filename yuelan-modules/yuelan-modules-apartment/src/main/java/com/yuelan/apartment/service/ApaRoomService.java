package com.yuelan.apartment.service;

import com.yuelan.apartment.domain.ApaRoomInfo;
import com.yuelan.apartment.domain.vo.FloorVo;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @Author: ZhaoYi
 * @date: 24-5-23 9:20
 */
public interface ApaRoomService {

    /**
     * 新增
     *
     * @return
     */
    public int addRoom(ApaRoomInfo apaRoomInfo);

    /**
     * 删除
     *
     * @return
     */
    public int delete(Long id);

    /**
     * 批量删除房租信息
     *
     * @param ids 需要删除的房租信息主键集合
     * @return 结果
     */
    public int deleteRoomIds(Long[] ids);

    /**
     * 更新
     *
     * @return
     */
    public int update(ApaRoomInfo apaRoomInfo);

    /**
     * 根据主键 id 查询
     */
    public ApaRoomInfo load(Long id);


    /**
     * 查询当前房源下所有房租
     */
    List<FloorVo> roomList(Long id);


    /**
     * 查询房租信息列表
     *
     * @param apaRoomInfo 房租信息
     * @return 房租信息集合
     */
    public List<ApaRoomInfo> selectApaRoomInfoList(ApaRoomInfo apaRoomInfo);

}
