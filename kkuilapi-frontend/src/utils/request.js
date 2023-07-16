import axios from 'axios';
import { ADMIN_TOKEN_KEY } from '../constant/admin';

const request = axios.create({
  baseURL: 'http://127.0.0.1:3170/api',
  timeout: 5000,
});

request.interceptors.request.use(
  (config) => {
    const { url } = config;
    if (url === '/admin/auth') {
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

request.interceptors.response.use(
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

export default request;
