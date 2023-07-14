import PropTypes from 'prop-types';
// @mui
import { alpha, styled } from '@mui/material/styles';
import { Box, Link, Card, Grid, Avatar, Typography, CardContent } from '@mui/material';
// utils
import { fDate } from '../../../utils/formatTime';
import { fShortenNumber } from '../../../utils/formatNumber';

// ----------------------------------------------------------------------

const StyledCardMedia = styled('div')({
  position: 'relative',
  paddingTop: 'calc(100% * 3 / 4)',
});

const StyledTitle = styled(Link)({
  height: 44,
  overflow: 'hidden',
  WebkitLineClamp: 2,
  display: '-webkit-box',
  WebkitBoxOrient: 'vertical',
});

const StyledAvatar = styled(Avatar)(({ theme }) => ({
  zIndex: 9,
  width: 32,
  height: 32,
  position: 'absolute',
  left: theme.spacing(3),
  bottom: theme.spacing(-2),
}));

const StyledInfo = styled('div')(({ theme }) => ({
  display: 'flex',
  flexWrap: 'wrap',
  justifyContent: 'flex-end',
  marginTop: theme.spacing(3),
  color: theme.palette.text.disabled,
}));

const StyledCover = styled('img')({
  top: 0,
  width: '100%',
  height: '100%',
  objectFit: 'cover',
  position: 'absolute',
});

const StyledGrid = styled(Grid)({
  transition: 'transform .3s',
  '&:hover': {
    transform: 'translateY(-5px)',
  },
});

// ----------------------------------------------------------------------

InvokePostCard.propTypes = {
  post: PropTypes.object.isRequired,
  index: PropTypes.number,
};

export default function InvokePostCard({ post, index }) {
  const { title, count, createdAt } = post;
  const latestPostLarge = index === 0;
  const latestPost = index === 1 || index === 2;

  return (
    <StyledGrid item xs={12} sm={latestPostLarge ? 12 : 6} md={latestPostLarge ? 6 : 3} style={{ cursor: 'pointer' }}>
      <Card sx={{ position: 'relative' }}>
        <CardContent
          sx={{
            ...((latestPostLarge || latestPost) && {
              bottom: 0,
              width: '100%',
              position: 'absolute',
            }),
          }}
        >
          <Typography gutterBottom variant="caption" sx={{ color: 'text.disabled', display: 'block' }}>
              上线时间：{fDate(createdAt, 'yyyy-mm-dd')}
          </Typography>

          <StyledTitle
            color="inherit"
            variant="subtitle2"
            underline="hover"
            sx={{
              ...(latestPostLarge && { typography: 'h5' }),
              ...((latestPostLarge || latestPost) && {
                color: 'common.white',
              }),
            }}
            style={{ cursor: 'pointer' }}
          >
            {title}
          </StyledTitle>

          <StyledTitle
            color="inherit"
            variant="subtitle2"
            underline="hover"
            sx={{
              ...(latestPostLarge && { typography: 'h5', height: 60 }),
              ...((latestPostLarge || latestPost) && {
                color: 'common.white',
              }),
            }}
            style={{ cursor: 'pointer' }}
          >
            带着这些问题, 我们来审视一下随机一段废话. 问题的关键究竟为何? 罗曼·罗兰曾经提到过,
            只有把抱怨环境的心情，化为上进的力量，才是成功的保证。这句话把我们带到了一个新的维度去思考这个问题:
            所谓随机一段废话, 关键是随机一段废话需要如何写. 每个人都不得不面对这些问题. 在面对这种问题时, 那么,
            既然如何, 随机一段废话的发生, 到底需要如何做到, 不随机一段废话的发生, 又会如何产生.
          </StyledTitle>

          <StyledInfo>
            <Box
              key={index}
              sx={{
                display: 'flex',
                alignItems: 'center',
                ml: index === 0 ? 0 : 1.5,
                ...((latestPostLarge || latestPost) && {
                  color: 'grey.500',
                }),
              }}
            >
              <Typography variant="caption">调用次数：</Typography>
              <Typography variant="caption">{fShortenNumber(count)}</Typography>
            </Box>
          </StyledInfo>
        </CardContent>
      </Card>
    </StyledGrid>
  );
}
