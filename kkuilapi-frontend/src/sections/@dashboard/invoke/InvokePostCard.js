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
  interfaceInfo: PropTypes.object.isRequired,
  index: PropTypes.number,
};

export default function InvokePostCard({ interfaceInfo, index }) {
  const { apiName, apiDesc, apiCount, apiUrl } = interfaceInfo;

  return (
    <StyledGrid item xs={12} sm={6} md={3} style={{ cursor: 'pointer' }}>
      <Card sx={{ position: 'relative' }}>
        <CardContent>
          <StyledTitle color="inherit" variant="subtitle2" underline="hover">
            {apiName}
          </StyledTitle>

          <StyledTitle color="inherit" variant="subtitle2" underline="hover">
            {apiDesc}
          </StyledTitle>

          <StyledTitle color="inherit" variant="subtitle2" underline="hover" style={{ cursor: 'pointer' }}>
            {apiUrl}
          </StyledTitle>

          <StyledInfo>
            <Box
              key={index}
              sx={{
                display: 'flex',
                alignItems: 'center',
                ml: index === 0 ? 0 : 1.5,
              }}
            >
              <Typography variant="caption">调用次数：{fShortenNumber(apiCount)}</Typography>
            </Box>
          </StyledInfo>
        </CardContent>
      </Card>
    </StyledGrid>
  );
}
