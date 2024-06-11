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
     */
    public Object insert(ApaRoomInfo apaRoomInfo);

    /**
     * 删除
     */
    public void delete(Integer id);

    /**
     * 更新
     */
    public void update(ApaRoomInfo apaRoomInfo);

    /**
     * 根据主键 id 查询
     */
    public ApaRoomInfo load(Integer id);

    /**
     * 分页查询
     */
    public Map<String,Object> pageList(int offset, int pagesize, Integer apartmentId);

    /**
     * 查询当前房源下所有房租
     */
    List<FloorVo> roomList(Integer apartmentId);
}
