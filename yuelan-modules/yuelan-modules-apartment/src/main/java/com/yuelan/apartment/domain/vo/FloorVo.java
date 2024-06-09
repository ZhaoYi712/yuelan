package com.yuelan.apartment.domain.vo;

import com.yuelan.apartment.domain.ApaRoomInfo;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @description:
 * @Author: ZhaoYi
 * @date: 24-6-3 18:37
 */

public class FloorVo {

    private Integer floor;

    private List<ApaRoomInfo> roomVo;

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public List<ApaRoomInfo> getRoomVo() {
        return roomVo;
    }

    public void setRoomVo(List<ApaRoomInfo> roomVo) {
        this.roomVo = roomVo;
    }

    // 用于过滤、排序或计算房间数量等
    public int getRoomCount() {
        return (roomVo != null) ? roomVo.size() : 0;
    }

    // 按照某个条件过滤房间列表
    public List<ApaRoomInfo> filterRoomsByState(Integer roomState) {
        return roomVo.stream()
                .filter(room -> Objects.equals(room.getState() , roomState))
                .collect(Collectors.toList());
    }
}
