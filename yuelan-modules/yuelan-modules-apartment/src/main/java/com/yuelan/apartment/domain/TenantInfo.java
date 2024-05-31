package com.yuelan.apartment.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 房客信息
 * @Author: ZhaoYi
 * @date: 24-5-23 16:52
 */
@ApiModel("房客信息")
public class TenantInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 房客编号
     */
    @Id
    @ApiModelProperty("房客编号")
    private Long id;

    @ApiModelProperty("房租编号")
    private Long room_id;

    /**
     * 房租编号
     */
    @ApiModelProperty("公寓编号")
    private Long apartment_id;

    /**
     * 房客姓名
     */
    @ApiModelProperty("房客姓名")
    private String name;

    /**
     * 房客手机号
     */
    @ApiModelProperty("房客手机号")
    private String phone;

    /**
     * 房客身份证号
     */
    @ApiModelProperty("房客身份证号")
    private String id_number;

    /**
     * 合同生效时间
     */
    @ApiModelProperty("合同生效时间")
    private Date create_time;

    /**
     * 合同失效时间
     */
    @ApiModelProperty("合同失效时间")
    private Date end_time;

    public TenantInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Long room_id) {
        this.room_id = room_id;
    }

    public Long getApartment_id() {
        return apartment_id;
    }

    public void setApartment_id(Long apartment_id) {
        this.apartment_id = apartment_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }
}
