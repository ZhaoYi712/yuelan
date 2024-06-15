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
import java.util.ArrayList;
import java.util.List;
import static com.yuelan.common.core.utils.PageUtils.startPage;

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

    @Autowired
    private ApartmentMapper apartmentMapper;


    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);



    @Transactional
    @Override
    public int insert(TenantRegisVo tenantRegisVo) {

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
        return 0;
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
        startPage();
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
