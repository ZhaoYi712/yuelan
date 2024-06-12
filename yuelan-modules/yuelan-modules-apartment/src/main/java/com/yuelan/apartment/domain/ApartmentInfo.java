package com.yuelan.apartment.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @Author: ZhaoYi
 * @date: 24-5-18 10:58
 */
public class ApartmentInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 公寓编号
     */
    private Long id;

    /**
     * 公寓名称
     */
    private String apartment_name;

    /**
     * 公寓负责人
     */
    private String owner;

    /**
     * 公寓地址
     */
    private String address;

    /**
     * 单元/楼栋
     */
    private String unit;

    /**
     * 公寓状态（正常，异常）
     */
    private String state;

    /**
     * 创建日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date create_time;


    public ApartmentInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApartment_name() {
        return apartment_name;
    }

    public void setApartment_name(String apartment_name) {
        this.apartment_name = apartment_name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
