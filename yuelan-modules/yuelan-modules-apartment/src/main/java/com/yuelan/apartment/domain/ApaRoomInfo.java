package com.yuelan.apartment.domain;

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
     * 房间编号
     */
    @Id
    @ApiModelProperty("房间编号")
    private Long id;

    /**
     * 楼层
     */
    @ApiModelProperty("楼层")
    private String floor;

    /**
     * 所属公寓id
     */
    @ApiModelProperty("所属公寓id")
    private Long apartment_id;

    /**
     * 租户id
     */
    @ApiModelProperty("租户id")
    private Integer tenants_id;

    /**
     * 房间状态（1已租，0空闲）
     */
    @ApiModelProperty("房间状态（1已租，0空闲）")
    private Integer state;

    /**
     * 房间类型：mini小型、normal标准、large大型
     */
    @ApiModelProperty("房间类型：mini小型、normal标准、large大型")
    private String room_type;

    /**
     * 房间面积（平方）
     */
    @ApiModelProperty("房间面积（平方）")
    private String area;

    /**
     * 租金
     */
    @ApiModelProperty("租金")
    private Double rent;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date create_time;

    public ApaRoomInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public Long getApartment_id() {
        return apartment_id;
    }

    public void setApartment_id(Long apartment_id) {
        this.apartment_id = apartment_id;
    }

    public Integer getTenants_id() {
        return tenants_id;
    }

    public void setTenants_id(Integer tenants_id) {
        this.tenants_id = tenants_id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
