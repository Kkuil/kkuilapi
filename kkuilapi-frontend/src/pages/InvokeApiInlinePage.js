import { Helmet } from 'react-helmet-async';
// @mui
import { Grid, Container, Stack, Typography, TablePagination } from '@mui/material';
// components
import { useEffect, useState } from 'react';
import { InvokePostCard, InvokePostsSearch } from '../sections/@dashboard/invoke/index';
import { listInterfaceWithUser } from '../api/interface';

// ----------------------------------------------------------------------

export default function InvokeApiInlinePage() {
  const [listParam, setListParam] = useState({
    current: 1,
    pageSize: 12,
    apiName: '',
  });

  // 数据总数
  const [total, setTotal] = useState(0);

  const [interfaces, setInterfaces] = useState([]);

  // 获取接口列表
  const listInterfaceOperation = async () => {
    const result = await listInterfaceWithUser(listParam);
    if (result.data) {
      setInterfaces(result.data.list);
      setTotal(result.data.total);
    }
  };

  /**
   *  获取列表页面的接口列表数据
   * @param event
   * @param current
   */
  const handleChangePage = (event, current) => {
    console.log(current);
    setListParam({
      ...listParam,
      current: current > 0 ? current + 1 : 1,
    });
  };

  /**
   * 获取列表页面的接口列表数据
   * @param event
   */
  const handleChangeRowsPerPage = (event) => {
    console.log(event.target.value);
    setListParam({
      ...listParam,
      current: 0,
      pageSize: parseInt(event.target.value > 0 ? event.target.value : 12, 10),
    });
  };

  // 初始化接口列表
  useEffect(() => {
    listInterfaceOperation();
    return () => {};
  }, [listParam]);
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
          <InvokePostsSearch posts={interfaces} />
        </Stack>

        <Grid container spacing={3}>
          {interfaces.map((interfaceInfo, index) => (
            <InvokePostCard key={interfaceInfo.id} interfaceInfo={interfaceInfo} index={index} />
          ))}
        </Grid>
        <TablePagination
          rowsPerPageOptions={[5, 10, 25]}
          component="div"
          count={total}
          rowsPerPage={listParam.pageSize}
          page={listParam.current - 1}
          onPageChange={handleChangePage}
          onRowsPerPageChange={handleChangeRowsPerPage}
        />
      </Container>
    </>
  );
}
