import { Helmet } from 'react-helmet-async';
// @mui
import { Grid, Container, Stack, Typography } from '@mui/material';
// components
import { InvokePostCard, InvokePostsSort, InvokePostsSearch } from '../sections/@dashboard/invoke/index';
// mock
import POSTS from '../_mock/invoke';

// ----------------------------------------------------------------------

const SORT_OPTIONS = [
  { value: 'latest', label: '新上线' },
  { value: 'popular', label: '热门' }
];

// ----------------------------------------------------------------------

export default function InvokeApiInlinePage() {
  return (
    <>
      <Helmet>
        <title>在线调用接口</title>
      </Helmet>

      <Container>
        <Stack direction="row" alignItems="center" justifyContent="space-between" mb={5}>
          <Typography variant="h4" gutterBottom>
            在线调用接口
          </Typography>
        </Stack>

        <Stack mb={5} direction="row" alignItems="center" justifyContent="space-between">
          <InvokePostsSearch posts={POSTS} />
          <InvokePostsSort options={SORT_OPTIONS} />
        </Stack>

        <Grid container spacing={3}>
          {POSTS.map((post, index) => (
            <InvokePostCard key={post.id} post={post} index={index} />
          ))}
        </Grid>
      </Container>
    </>
  );
}
