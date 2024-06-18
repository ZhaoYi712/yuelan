import request from "@/utils/request";

// 查询房间信息列表
export function listInfo(id) {
  return request({
    url: '/apartment/room/list?id=' + id,
    method: 'get'
  })
}

// 查询房间信息详细
export function getInfo(id) {
  return request({
    url: '/apartment/room?id=' + id,
    method: 'get',
  })
}

// 新增房间信息
export function addInfo(data) {
  return request({
    url: '/apartment/room',
    method: 'post',
    data: data
  })
}

// 修改房租信息
export function updateInfo(data) {
  return request({
    url: '/apartment/room',
    method: 'put',
    data: data
  })
}

// 删除房租信息
export function delInfo(id) {
  return request({
    url: '/apartment/room/' + id,
    method: 'delete'
  })
}





// 添加房租
export function addRoomApi(from){
  return request({
    url: '/apartment/room/add',
    method: "POST",
    data: from
  })
}

// 查询当前公寓的所有的房租
export function queryAllRoom(params){
  return request({
    url: '/apartment/room/list',
    method: "GET",
    params: params
  })
}

// 删除房租
export function delRoomApi(id){
  return request({
    url: 'apartment/room/delete',
    method: "GET",
    params: {
      id: id
    }
  })
}

// 查询房间
export function loadRoomApi(id){
  return request({
    url: 'apartment/room/load',
    method: "GET",
    params: {
      id
    }
  })
}

// 更新房间
export function updateRoomApi(data){
  return request({
    url: 'apartment/room/update',
    method: "POST",
    data: data
  })
}
