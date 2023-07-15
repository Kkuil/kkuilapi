import { faker } from '@faker-js/faker';
import { sample } from 'lodash';

// ----------------------------------------------------------------------

const INTERFACE_LIST = [...Array(5)].map((_, index) => ({
  id: index,
  apiName: '4K高清图片',
  apiDesc: '随机4K高清图片',
  apiUrl: `https://codegeex.cn${faker.image.avatar()}`,
  apiMethod: 'GET',
  apiParam: "{ 'type': 'dawda' }",
  apiStatus: '已发布',
  apiReqExample: 'dawd',
  apiResExample: 'dawda',
}));

export default INTERFACE_LIST;
