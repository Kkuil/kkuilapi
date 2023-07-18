import { useEffect, useState } from 'react';
// @mui
import PropTypes from 'prop-types';
import { Box, Stack, Link, Card, Button, Divider, Typography, CardHeader } from '@mui/material';
import { useNavigate } from 'react-router-dom';
// utils
import { fToNow } from '../../../utils/formatTime';
// components
import Iconify from '../../../components/iconify';
import Scrollbar from '../../../components/scrollbar';
import { listInterfaceWithUser } from '../../../api/common/interface';

// ----------------------------------------------------------------------

ApiNewsUpdate.propTypes = {
  title: PropTypes.string,
  subheader: PropTypes.string,
  list: PropTypes.array.isRequired,
};

export default function ApiNewsUpdate({ title, subheader, list, ...other }) {
  const navigate = useNavigate();
  const [listParam, setListParam] = useState({
    current: 1,
    pageSize: 5,
    apiName: '',
  });
  const [interfaces, setInterfaces] = useState([]);

  // 初始化接口列表
  useEffect(() => {
    listInterfaceOperation();
    return () => {};
  }, [listParam]);

  // 获取接口列表
  const listInterfaceOperation = async () => {
    const result = await listInterfaceWithUser(listParam);
    if (result.data) {
      setInterfaces(result.data.list);
    }
  };
  return (
    <Card {...other}>
      <CardHeader title={title} subheader={subheader} />

      <Scrollbar>
        <Stack spacing={3} sx={{ p: 3, pr: 0 }}>
          {interfaces.map((interfaceInfo) => (
            <InterfaceItem key={interfaceInfo.id} interfaceInfo={interfaceInfo} />
          ))}
        </Stack>
      </Scrollbar>

      <Divider />

      <Box sx={{ p: 2, textAlign: 'right' }}>
        <Button
          size="small"
          color="inherit"
          onClick={() => navigate({ pathname: '/dashboard/invoke-api' })}
          endIcon={<Iconify icon={'eva:arrow-ios-forward-fill'} />}
        >
          查看全部
        </Button>
      </Box>
    </Card>
  );
}

// ----------------------------------------------------------------------

InterfaceItem.propTypes = {
  news: PropTypes.shape({
    apiDesc: PropTypes.string,
    apiName: PropTypes.string,
  }),
};

function InterfaceItem({ interfaceInfo }) {
  const { id, apiName, apiDesc } = interfaceInfo;
  const navigate = useNavigate();
  return (
    <Stack
      direction="row"
      alignItems="center"
      spacing={2}
      style={{ cursor: 'pointer' }}
      onClick={() => navigate({ pathname: `/dashboard/invoke/${id}` })}
    >
      <Box sx={{ minWidth: 240, flexGrow: 1 }}>
        <Link color="inherit" variant="subtitle2" underline="hover" noWrap>
          {apiName}
        </Link>

        <Typography variant="body2" sx={{ color: 'text.secondary' }} noWrap>
          {apiDesc}
        </Typography>
      </Box>
    </Stack>
  );
}
