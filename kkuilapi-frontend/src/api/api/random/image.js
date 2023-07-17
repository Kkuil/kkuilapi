import request from '../../../utils/request-api';

/**
 * @description 获取随机图片
 * @param type 图片类型
 * @return {Promise<*>}
 */
export const getRandom = async (type) => {
  return request({
    url: '/image',
    method: 'GET',
    params: {
      type,
    },
  });
};