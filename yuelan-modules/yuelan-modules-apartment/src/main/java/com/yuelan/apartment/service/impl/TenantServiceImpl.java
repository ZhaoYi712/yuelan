package com.yuelan.apartment.service.impl;

import com.yuelan.apartment.domain.ApaRoomInfo;
import com.yuelan.apartment.domain.TenantInfo;
import com.yuelan.apartment.domain.vo.TenantRegisVo;
import com.yuelan.apartment.domain.vo.TenantVo;
import com.yuelan.apartment.mapper.ApartmentMapper;
import com.yuelan.apartment.mapper.RoomMapper;
import com.yuelan.apartment.mapper.TenantMapper;
import com.yuelan.apartment.service.ApaRoomService;
import com.yuelan.apartment.service.TenantService;
import com.yuelan.common.core.context.SecurityContextHolder;
import com.yuelan.common.core.exception.ServiceException;
import com.yuelan.common.core.utils.DateUtils;
import com.yuelan.common.core.utils.bean.BeanUtils;
import com.yuelan.common.security.handler.GlobalExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static com.yuelan.common.core.utils.PageUtils.startPage;

/**
 * @description: 租户管理
 * @Author: ZhaoYi
 * @date: 24-5-23 17:00
 */
@Service
public class TenantServiceImpl implements TenantService {

    @Resource
    private TenantMapper tenantInfoMapper;

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private ApartmentMapper apartmentMapper;

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @Transactional
    @Override
    public int insert(TenantVo tenantVo) {
        // 查询房间是否已被出租
        ApaRoomInfo apaRoomInfo = roomMapper.queryRoomId(tenantVo.getRoom_id());
        if (apaRoomInfo != null) {
            throw new ServiceException(apaRoomInfo.getRoom_id()+ "该房间已被出租");
        }
        // 新增房间
        ApaRoomInfo room = new ApaRoomInfo();
        room.setRoom_id(tenantVo.getRoom_id());
        room.setFloor(Integer.parseInt(tenantVo.getRoom_id().substring(0, 2)));
        room.setApartment_id(tenantVo.getApartment_id());
        room.setState(1);
        room.setCreate_time(DateUtils.getNowDate());
        int insert = roomMapper.insert(room);
        if (insert < 0) {
            throw new ServiceException("新增操作异常");
        }
        // 新增租户
        return tenantInfoMapper.insert(tenantVo);
    }


    @Transactional
    @Override
    public int delete(Long id) {
        return tenantInfoMapper.delete(id);
    }


    @Override
    public int deleteTenantByIds(Long[] ids) {
        return tenantInfoMapper.deleteTenantByIds(ids);
    }


    @Transactional
    @Override
    public int update(TenantInfo tenantInfo) {
        return tenantInfoMapper.update(tenantInfo);
    }


    @Override
    public TenantInfo load(Long id) {
        return tenantInfoMapper.load(id);
    }


    @Override
    public List<TenantVo> list(TenantInfo tenantInfo) {
        String userName = SecurityContextHolder.getUserName();
        List<TenantInfo> pageList = tenantInfoMapper.tenantInfoList(tenantInfo);

        List<TenantVo> tenantVoList = new ArrayList<>();
        for (TenantInfo item: pageList) {
            Long apartmentId = item.getApartment_id();
            String apartmentName = apartmentMapper.load(apartmentId).getApartment_name();
            TenantVo tenantVo = new TenantVo();
            tenantVo.setApartment_name(apartmentName);
            BeanUtils.copyBeanProp(tenantVo, item);
            tenantVoList.add(tenantVo);
        }
        return tenantVoList;
    }


}
