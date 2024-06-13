package com.yuelan.apartment.service.impl;

import com.yuelan.apartment.domain.ApaRoomInfo;
import com.yuelan.apartment.domain.TenantInfo;
import com.yuelan.apartment.domain.vo.TenantRegisVo;
import com.yuelan.apartment.mapper.RoomMapper;
import com.yuelan.apartment.mapper.TenantMapper;
import com.yuelan.apartment.service.ApaRoomService;
import com.yuelan.apartment.service.TenantService;
import com.yuelan.common.core.context.SecurityContextHolder;
import com.yuelan.common.core.exception.ServiceException;
import com.yuelan.common.core.utils.DateUtils;
import com.yuelan.common.security.handler.GlobalExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @Author: ZhaoYi
 * @date: 24-5-23 17:00
 */
@Service
public class TenantServiceImpl implements TenantService {

    @Resource
    private TenantMapper tenantInfoMapper;

    @Resource
    private ApaRoomService apaRoomService;

    @Resource
    private RoomMapper roomMapper;


    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @Transactional
    @Override
    public void insert(TenantInfo tenantInfo) {
        tenantInfoMapper.insert(tenantInfo);
    }

    @Transactional
    @Override
    public void insert(TenantRegisVo tenantRegisVo) {

        // 房租创建时间
        tenantRegisVo.getApaRoomInfo().setCreate_time(DateUtils.getNowDate());

        // 查询房间是否已被出租
        ApaRoomInfo apaRoomInfo = roomMapper.queryRoomId(tenantRegisVo.getApaRoomInfo().getRoom_id());
        if (apaRoomInfo != null) {
           throw new ServiceException(apaRoomInfo.getRoom_id()+ "该房间已被出租");
        }
        // 新增租户
        tenantInfoMapper.insert(tenantRegisVo.getTenantInfo());
        // 新增房间
        apaRoomService.addRoom(tenantRegisVo.getApaRoomInfo());
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        tenantInfoMapper.delete(id);
    }

    @Transactional
    @Override
    public void update(TenantInfo tenantInfo) {
        tenantInfoMapper.update(tenantInfo);
    }


    @Override
    public TenantInfo load(Integer id) {
        return tenantInfoMapper.load(id);
    }


    @Override
    public Map<String,Object> pageList(int offset, int pagesize) {
        String username = SecurityContextHolder.getUserName();
        List<TenantInfo> pageList = tenantInfoMapper.pageList(offset, pagesize);
        int totalCount = tenantInfoMapper.pageListCount(offset, pagesize);

        Map<String, Object> result = new HashMap<>();
        result.put("pageList", pageList);
        result.put("totalCount", totalCount);

        return result;
    }
}
