import request from '../utils/request';

/**
 * 获取接口列表
 * @param current 当前页
 * @param pageSize  每页显示数量
 * @param params 查询参数
 * @return {*} 返回接口列表
 */
export function listInterface({ current, pageSize, params }) {
  return request({
    url: '/interface',
    method: 'GET',
    params: {
      current,
      pageSize,
      ...params,
    },
  });
}

/**
 * 新增接口
 * @param data 接口数据
 * @return {*} 是否新增成功
 */
export function addInterface(data) {
  return request({
    url: '/interface',
    method: 'POST',
    data,
  });
}

/**
 * 删除接口
 * @param id 接口ID
 * @return {*} 是否删除成功
 */
export function deleteInterface(id) {
  return request({
    url: `/interface/${id}`,
    method: 'DELETE'
  });
}

/**
 * 修改接口
 * @param data 接口数据
 * @return {*} 是否修改成功
 */
export function updateInterface(data) {
  return request({
    url: '/interface',
    method: 'PUT',
    data,
  });
}

/**
 * 获取接口
 * @param id  接口ID
 * @return {*} 接口数据
 */
export function getInterface(id) {
  return request({
    url: `/interface/${id}`,
    method: 'GET',
  });
}
