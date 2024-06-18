import request from '@/utils/request'

// 查询公寓信息列表
export function listInfo(query){
  return request({
    url: '/apartment/house/list',
    method: 'get',
    params: query
  })
}

// 查询公寓信息详细
export function getInfo(id){
  return request({
    url: '/apartment/house/' + id,
    method: 'get'
  })
}

// 新增公寓信息
export function addInfo(data){
  return request({
    url: '/apartment/house',
    method: 'post',
    data: data
  })
}

// 删除公寓信息
export function delInfo(id){
  return request({
    url: '/apartment/house/'+ id,
    method: 'delete'
  })
}

// 修改公寓信息
export function updateInfo(data){
  return request({
    url: '/apartment/house',
    method: 'put',
    data: data
  })
}



