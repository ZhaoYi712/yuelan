package com.yuelan.apartment.service.impl;

import com.yuelan.apartment.domain.ApaRoomInfo;
import com.yuelan.apartment.domain.vo.FloorVo;
import com.yuelan.apartment.mapper.RoomMapper;
import com.yuelan.apartment.service.ApaRoomService;
import com.yuelan.common.core.exception.ServiceException;
import com.yuelan.common.core.utils.DateUtils;
import com.yuelan.common.security.handler.GlobalExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Resource
    private RoomMapper roomMapper;


    @Transactional
    @Override
    public void addRoom(ApaRoomInfo apaRoomInfo) {
        if (apaRoomInfo == null) {
            throw new ServiceException("from cannot be blank or null");
        }
        ApaRoomInfo load = roomMapper.queryRoomId(apaRoomInfo.getRoom_id());
        if (load != null) {
            throw new ServiceException(load.getRoom_id()+ "房间已存在");
        }
        apaRoomInfo.setCreate_time(DateUtils.getNowDate());
        roomMapper.insert(apaRoomInfo);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        if (id == null){
            throw new ServiceException("id cannot be blank or null");
        }
        roomMapper.delete(id);
    }

    @Override
    public void update(ApaRoomInfo apaRoomInfo) {
        if (apaRoomInfo == null) {
            throw new ServiceException("apaRoomInfo cannot be blank or null");
        }
        roomMapper.update(apaRoomInfo);
    }

    @Override
    public ApaRoomInfo load(Integer id){
        if (id == null) {
            throw new ServiceException("id cannot be blank or null");
        }
        return roomMapper.load(id);
    }

    @Override
    public Map<String,Object> pageList(int offset, int pagesize, Integer apartmentId) {

        List<ApaRoomInfo> pageList = roomMapper.pageList(offset, pagesize, apartmentId);
        int totalCount = roomMapper.pageListCount(offset, pagesize);

        Map<String, Object> result = new HashMap<>();
        result.put("pageList", pageList);
        result.put("totalCount", totalCount);

        return result;
    }

    @Override
    public List<FloorVo> roomList(Integer apartmentId) {

        List<ApaRoomInfo> apaRoomInfoList = roomMapper.roomList(apartmentId);

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

            // 对房间号进行排序
            List<ApaRoomInfo> sortedRoomVo = roomsByFloor.get(floor).stream()
                    .sorted(Comparator.comparing(ApaRoomInfo::getRoom_id))
                    .collect(Collectors.toList());

            floorVo.setRoomVo(sortedRoomVo);
            floorVoList.add(floorVo);
        }
        return floorVoList;
    }
}
