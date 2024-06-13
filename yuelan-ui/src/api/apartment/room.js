import request from "@/utils/request";


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
