import axios from 'axios';
import { ADMIN_TOKEN_KEY } from '../constant/admin';

const requestCommon = axios.create({
  baseURL: `${process.env.REACT_APP_REQUEST_COMMON_BASE_URL}/api`,
  timeout: 5000,
});

requestCommon.interceptors.request.use(
  (config) => {
    const { url } = config;
    if (url === '/admin/auth' || url === '/interface') {
      const token = localStorage.getItem(ADMIN_TOKEN_KEY);
      if (token) {
        config.headers[ADMIN_TOKEN_KEY] = localStorage[ADMIN_TOKEN_KEY];
      } else {
        console.log('未登录');
      }
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

requestCommon.interceptors.response.use(
  (response) => {
    const { headers } = response;
    const token = headers.get(ADMIN_TOKEN_KEY);
    if (token) {
      localStorage.setItem(ADMIN_TOKEN_KEY, token);
    }
    return response.data;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default requestCommon;
