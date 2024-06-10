package com.yuelan.apartment.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 房租信息
 * @Author: ZhaoYi
 * @date: 24-5-23 9:10
 */

@ApiModel("房租信息")
public class ApaRoomInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 房间编号
     */
    private String room_id;

    /**
     * 楼层
     */
    private Integer floor;

    /**
     * 公寓id
     */
    private String apartment_id;

    /**
     * 租户id
     */
    private String tenants_id;

    /**
     * 租金
     */
    private Double rent;

    /**
     * 房间状态（1已租，0空闲）
     */
    private Integer state;

    /**
     * 房间类型
     */
    private String type;

    /**
     * 房间面积
     */
    private String area;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date create_time;

    /**
     * 当前用电量
     */
    private Long power;

    /**
     * 当前用水量
     */
    private Long water;


    public ApaRoomInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getApartment_id() {
        return apartment_id;
    }

    public void setApartment_id(String apartment_id) {
        this.apartment_id = apartment_id;
    }

    public String getTenants_id() {
        return tenants_id;
    }

    public void setTenants_id(String tenants_id) {
        this.tenants_id = tenants_id;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Long getPower() {
        return power;
    }

    public void setPower(Long power) {
        this.power = power;
    }

    public Long getWater() {
        return water;
    }

    public void setWater(Long water) {
        this.water = water;
    }
}
