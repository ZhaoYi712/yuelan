import request from "@/utils/request";

// 查询房客信息列表
export function listInfo(query){
  return request({
    url: '/apartment/tenant/pageList',
    method: "GET",
    params: query
  })
}

// 新增房客信息
export function addInfo(data){
  return request({
    url: '/apartment/tenant/add',
    method: "POST",
    params: data
  })
}

// 查询房客信息详细
export function getInfo(id) {
  return request({
    url: '/apartment/tenant/' + id,
    method: 'GET'
  })
}

// 修改房客信息
export function updateInfo(data) {
  return request({
    url: '/apartment/tenant/update',
    method: 'put',
    data: data
  })
}

// 删除房客信息
export function delInfo(id) {
  return request({
    url: '/apartment/tenant/delete' + id,
    method: 'delete'
  })
}
