import { faker } from '@faker-js/faker';

// ----------------------------------------------------------------------

const POST_TITLES = [
    "随机4K图片",
    "4K UHD视频",
    "随机一大段废话填充",
    "随机一大段废话填充",
    "随机一大段废话填充",
    "随机一大段废话填充",
    "随机一大段废话填充",
    "随机一大段废话填充",
    "随机一大段废话填充",
    "随机一大段废话填充",
    "随机一大段废话填充",
    "随机一大段废话填充",
];

const posts = [...Array(6)].map((_, index) => ({
  id: faker.datatype.uuid(),
  title: POST_TITLES[index + 1],
  createdAt: faker.date.past(),
  count: faker.datatype.number(),
  author: {
    name: faker.name.fullName(),
  },
}));

export default posts;
