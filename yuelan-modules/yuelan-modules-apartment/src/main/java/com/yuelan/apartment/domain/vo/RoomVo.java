package com.yuelan.apartment.domain.vo;

import com.yuelan.apartment.domain.ApaRoomInfo;
import com.yuelan.apartment.domain.TenantInfo;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * @description:
 * @Author: ZhaoYi
 * @date: 24-5-26 4:23
 */
public class RoomVo{

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
}
