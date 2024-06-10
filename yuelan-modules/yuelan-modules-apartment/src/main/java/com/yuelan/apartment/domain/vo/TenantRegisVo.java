package com.yuelan.apartment.domain.vo;

import com.yuelan.apartment.domain.ApaRoomInfo;
import com.yuelan.apartment.domain.TenantInfo;


/**
 * @description:
 * @Author: ZhaoYi
 * @date: 24-6-9 16:28
 */
public class TenantRegisVo {

    private TenantInfo tenantInfo;

    private ApaRoomInfo apaRoomInfo;


    public TenantInfo getTenantInfo() {
        return tenantInfo;
    }

    public void setTenantInfo(TenantInfo tenantInfo) {
        this.tenantInfo = tenantInfo;
    }

    public ApaRoomInfo getApaRoomInfo() {
        return apaRoomInfo;
    }

    public void setApaRoomInfo(ApaRoomInfo apaRoomInfo) {
        this.apaRoomInfo = apaRoomInfo;
    }
}
