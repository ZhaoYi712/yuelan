package com.yuelan.apartment.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @Author: ZhaoYi
 * @date: 24-5-20 3:07
 */
@ApiModel("房东信息")
public class OwnerInfo implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 房东编号
     */
    @Id
    @ApiModelProperty("房东编号")
    private Integer id;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String phone;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;

    /**
     * 昵称（姓名）
     */
    @ApiModelProperty("昵称（姓名）")
    private String nickname;

    /**
     * 身份证号
     */
    @ApiModelProperty("身份证号")
    private String card_id;

    /**
     * 头像
     */
    @ApiModelProperty("头像")
    private String avatar;

    /**
     * 类型（0超级房东，1普通房东）
     */
    @ApiModelProperty("类型（0超级房东，1普通房东）")
    private Integer type;

    /**
     * 账号状态（0异常，1正常）
     */
    @ApiModelProperty("账号状态（0异常，1正常）")
    private Integer state;

    /**
     * 注册时间
     */
    @ApiModelProperty("注册时间")
    private Date create_time;

    public OwnerInfo() {
    }

    public Integer getId() {
        return id;
    }

    public void setOwner_id(Integer owner_id) {
        this.id = owner_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
