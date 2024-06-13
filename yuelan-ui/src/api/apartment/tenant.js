import request from "@/utils/request";

export function pageListTenantApi(){
  return request({
    url: '/apartment/tenant/tenantList',
    method: "GET",
  })
}
