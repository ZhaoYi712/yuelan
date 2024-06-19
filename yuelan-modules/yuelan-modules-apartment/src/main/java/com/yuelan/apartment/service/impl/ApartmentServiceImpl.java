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

import static com.yuelan.common.core.utils.PageUtils.startPage;

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
    public int add(ApartmentInfo apartmentInfo){
        if (apartmentInfo == null) {
            throw new ServiceException("参数异常");
        }
        Long userId = SecurityContextHolder.getUserId();
        ApartmentInfo house = apartmentMapper.queryByNickName(userId, apartmentInfo.getApartment_name());
        if (house != null) {
            throw new ServiceException("该房源昵称已存在");
        }
        apartmentInfo.setOwner(userId);
        apartmentInfo.setState("正常");
        apartmentInfo.setCreate_time(DateUtils.getNowDate());
        return apartmentMapper.insert(apartmentInfo);
    }


    @Override
    public int delete(Long id) {
        if (id == null) {
            throw new ServiceException("id cannot is null");
        }
        return apartmentMapper.delete(id);
    }

    /**
     * 批量删除公寓信息
     *
     * @param ids 需要删除的公寓信息主键
     * @return 结果
     */
    @Override
    public int deleteIds(Long[] ids) {
        if (ids == null) {
            throw new ServiceException("ids cannot is null");
        }
        return apartmentMapper.deleteIds(ids);
    }


    @Override
    public int update(ApartmentInfo apartmentInfo) {
        if (apartmentInfo == null) {
            throw new ServiceException("from cannot null");
        }
        return apartmentMapper.update(apartmentInfo);
    }


    @Override
    public ApartmentInfo load(Long id) {
        if (id == null) {
            throw new ServiceException("id cannot is null");
        }
        return apartmentMapper.load(id);
    }


    @Override
    public List<ApartmentInfo> pageList(ApartmentInfo apartmentInfo) {
        // 当前用户线程
        apartmentInfo.setOwner(SecurityContextHolder.getUserId());
        // 根据用户id查询房源
        return apartmentMapper.pageList(apartmentInfo);
    }

}
