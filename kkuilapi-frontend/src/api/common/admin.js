import requestCommon from '../../utils/request-common';

/**
 * @description 管理员登录
 * @param account 管理员账号
 * @param password 管理员密码
 * @return {*}
 */
export function login({ account, password }) {
  return requestCommon({
    url: '/admin/login',
    method: 'POST',
    data: { account, password },
  });
}

/**
 * @description 管理员认证
 * @return {*}
 */
export function auth() {
    return requestCommon({
        url: '/admin/auth',
        method: 'POST'
    });
}
