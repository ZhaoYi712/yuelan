package com.yuelan.apartment.service.impl;

import com.yuelan.apartment.domain.ApartmentInfo;
import com.yuelan.apartment.mapper.ApartmentMapper;
import com.yuelan.apartment.service.ApartmentService;
import com.yuelan.common.core.context.SecurityContextHolder;
import com.yuelan.common.core.exception.ServiceException;
import com.yuelan.common.core.utils.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @Author: ZhaoYi
 * @date: 24-5-18 11:05
 */
@Service
public class ApartmentServiceImpl implements ApartmentService {

    @Resource
    private ApartmentMapper apartmentMapper;


    @Override
    public void add(ApartmentInfo apartmentInfo){
        if (apartmentInfo == null) {
            throw new ServiceException("参数异常");
        }
        apartmentInfo.setOwner(SecurityContextHolder.getUserName());
        apartmentInfo.setState("正常");
        apartmentInfo.setCreate_time(DateUtils.getNowDate());
        apartmentMapper.insert(apartmentInfo);
    }


    @Override
    public void delete(String id) {
        if (id == null) {
            throw new ServiceException("id cannot is null");
        }
        apartmentMapper.delete(id);
    }


    @Override
    public void update(ApartmentInfo apartmentInfo) {
        if (apartmentInfo == null) {
            throw new ServiceException("from cannot null");
        }
        apartmentMapper.update(apartmentInfo);
    }


    @Override
    public ApartmentInfo load(String id) {
        if (id == null) {
            throw new ServiceException("id cannot is null");
        }
        return apartmentMapper.load(id);
    }


    @Override
    public Map<String,Object> pageList(int offset, int pagesize) {
        // 获取当前用户线程
        String username = SecurityContextHolder.getUserName();
        // 根据用户名查询房源
        List<ApartmentInfo> pageList = apartmentMapper.pageList(username, offset, pagesize);
        int totalCount = apartmentMapper.pageListCount(offset, pagesize);

        Map<String, Object> result = new HashMap<>();
        result.put("pageList", pageList);
        result.put("totalCount", totalCount);

        return result;
    }

}
