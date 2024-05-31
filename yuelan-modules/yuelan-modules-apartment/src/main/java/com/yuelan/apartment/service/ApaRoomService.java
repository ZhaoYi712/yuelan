package com.yuelan.apartment.service;

import com.yuelan.apartment.domain.ApaRoomInfo;

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
    public Object delete(int id);

    /**
     * 更新
     */
    public Object update(ApaRoomInfo apaRoomInfo);

    /**
     * 根据主键 id 查询
     */
    public ApaRoomInfo load(int id);

    /**
     * 分页查询
     */
    public Map<String,Object> pageList(int offset, int pagesize);
}
