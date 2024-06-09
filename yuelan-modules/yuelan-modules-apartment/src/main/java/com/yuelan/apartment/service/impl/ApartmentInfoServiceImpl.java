package com.yuelan.apartment.service.impl;

import com.yuelan.apartment.domain.ApartmentInfo;
import com.yuelan.apartment.mapper.ApartmentInfoMapper;
import com.yuelan.apartment.service.ApartmentInfoService;
import com.yuelan.common.core.context.SecurityContextHolder;
import com.yuelan.common.core.exception.ServiceException;
import com.yuelan.common.core.utils.DateUtils;
import com.yuelan.common.core.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @description:
 * @Author: ZhaoYi
 * @date: 24-5-18 11:05
 */
@Service
public class ApartmentInfoServiceImpl implements ApartmentInfoService {

    @Resource
    private ApartmentInfoMapper apartmentInfoMapper;


    @Override
    public void add(ApartmentInfo apartmentInfo){
        if (apartmentInfo == null) {
            throw new ServiceException("参数异常");
        }
        apartmentInfo.setId(IdUtils.simpleUUID());
        apartmentInfo.setOwner(SecurityContextHolder.getUserName());
        apartmentInfo.setState("正常");
        apartmentInfo.setCreate_time(DateUtils.getNowDate());
        apartmentInfoMapper.insert(apartmentInfo);
    }


    @Override
    public void delete(String id) {
        apartmentInfoMapper.delete(id);
    }


    @Override
    public void update(ApartmentInfo apartmentInfo) {
        apartmentInfoMapper.update(apartmentInfo);
    }


    @Override
    public ApartmentInfo load(String id) {
        return apartmentInfoMapper.load(id);
    }


    @Override
    public Map<String,Object> pageList(int offset, int pagesize) {
        // 获取当前用户线程
        String username = SecurityContextHolder.getUserName();
        // 根据用户名查询房源
        List<ApartmentInfo> pageList = apartmentInfoMapper.pageList(username, offset, pagesize);
        int totalCount = apartmentInfoMapper.pageListCount(offset, pagesize);

        Map<String, Object> result = new HashMap<>();
        result.put("pageList", pageList);
        result.put("totalCount", totalCount);

        return result;
    }

}
