import { faker } from '@faker-js/faker';
import { sample } from 'lodash';

// ----------------------------------------------------------------------

const users = [...Array(24)].map((_, index) => ({
  id: faker.datatype.uuid(),
  username: faker.name.fullName(),
  email: '3024067144@qq.com',
}));

export default users;
