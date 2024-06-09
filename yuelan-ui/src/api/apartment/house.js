import request from '@/utils/request'


export function houseList(param){
  return request({
    url: '/apartment/house/pageList',
    method: 'get',
    params: param
  })
}

export function deleteHouse(id){
  return request({
    url: '/apartment/house/delete'+"?id="+id,
    method: 'get'
  })
}

export function queryHouse(id){
  return request({
    url: '/apartment/house/load'+"?id="+id,
    method: 'get'
  })
}

export function updateHouseInfo(from){
  return request({
    url: '/apartment/house/update',
    method: 'post',
    data: from
  })
}

export function addHouseApi(from){
  return request({
    url: '/apartment/house/add',
    method: 'post',
    data: from
  })
}

