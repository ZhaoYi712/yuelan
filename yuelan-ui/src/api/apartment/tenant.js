import request from "@/utils/request";

export function listInfo(query){
  return request({
    url: '/apartment/tenant/pageList',
    method: "GET",
    params: query
  })
}
