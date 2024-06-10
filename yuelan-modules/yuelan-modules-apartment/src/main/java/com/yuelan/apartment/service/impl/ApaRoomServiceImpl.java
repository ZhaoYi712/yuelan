package com.yuelan.apartment.service.impl;

import com.yuelan.apartment.domain.ApaRoomInfo;
import com.yuelan.apartment.domain.vo.FloorVo;
import com.yuelan.apartment.mapper.ApaRoomInfoMapper;
import com.yuelan.apartment.service.ApaRoomService;
import com.yuelan.common.core.exception.ServiceException;
import com.yuelan.common.core.utils.DateUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @description:
 * @Author: ZhaoYi
 * @date: 24-5-23 9:21
 */
@Service
public class ApaRoomServiceImpl implements ApaRoomService {

    @Resource
    private ApaRoomInfoMapper apaRoomInfoMapper;


    @Override
    public void insert(ApaRoomInfo apaRoomInfo) {
        if (apaRoomInfo == null) {
            throw new ServiceException("apaRoomInfo cannot be blank or null");
        }
        apaRoomInfo.setCreate_time(DateUtils.getNowDate());
        try {
            apaRoomInfoMapper.insert(apaRoomInfo);
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }

    }


    @Override
    public void delete(Integer id) {
        if (id == null){
            throw new ServiceException("id cannot be blank or null");
        }
        try {
            apaRoomInfoMapper.delete(id);
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }

    }


    @Override
    public void update(ApaRoomInfo apaRoomInfo) {
        if (apaRoomInfo == null) {
            throw new ServiceException("apaRoomInfo cannot be blank or null");
        }
        try {
            apaRoomInfoMapper.update(apaRoomInfo);
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }

    }


    @Override
    public ApaRoomInfo load(Integer id) {
        if (id == null) {
            throw new ServiceException("id cannot be blank or null");
        }
        try {
            return apaRoomInfoMapper.load(id);
        }
        catch (RuntimeException e){
            e.printStackTrace();
            throw new ServiceException("ApaRoomInfo插入异常");
        }
    }


    @Override
    public Map<String,Object> pageList(int offset, int pagesize, Integer apartmentId) {

        List<ApaRoomInfo> pageList = apaRoomInfoMapper.pageList(offset, pagesize, apartmentId);
        int totalCount = apaRoomInfoMapper.pageListCount(offset, pagesize);

        Map<String, Object> result = new HashMap<>();
        result.put("pageList", pageList);
        result.put("totalCount", totalCount);

        return result;
    }


    @Override
    public List<FloorVo> roomList(Integer apartmentId) {

        List<ApaRoomInfo> apaRoomInfoList = apaRoomInfoMapper.roomList(apartmentId);

        // 使用Map来按楼层值分组房间
        Map<Integer, List<ApaRoomInfo>> roomsByFloor =
                apaRoomInfoList.stream().collect(Collectors.groupingBy(ApaRoomInfo::getFloor));

        // 获取楼层的列表并排序
        List<Integer> sortedFloor = new ArrayList<>(roomsByFloor.keySet());
        Collections.sort(sortedFloor);

        // 将分组后的房间放入FloorVo列表，并根据楼层值排序
        List<FloorVo> floorVoList = new ArrayList<>();
        for (Integer floor : sortedFloor) {
            FloorVo floorVo = new FloorVo();
            floorVo.setFloor(floor);
            floorVo.setRoomVo(roomsByFloor.get(floor));
            floorVoList.add(floorVo);
        }
        return floorVoList;
    }
}
