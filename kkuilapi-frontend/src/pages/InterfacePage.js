import { Helmet } from 'react-helmet-async';
import { filter } from 'lodash';
import { useEffect, useState } from 'react';
// @mui
import {
  Card,
  Table,
  Stack,
  Paper,
  Button,
  Popover,
  Checkbox,
  TableRow,
  MenuItem,
  TableBody,
  TableCell,
  Container,
  Typography,
  IconButton,
  TableContainer,
  TablePagination,
  TextField,
} from '@mui/material';
// components
import Drawer from '@mui/material/Drawer';
import { styled } from 'styled-components';
import Iconify from '../components/iconify';
import Scrollbar from '../components/scrollbar';
// sections
import { InterfaceListHead, InterfaceListToolbar } from '../sections/@dashboard/interface';
// mock
import { addInterface, deleteInterface, listInterface, updateInterface } from '../api/interface';

// ----------------------------------------------------------------------

const TABLE_HEAD = [
  { id: 'id', label: 'id', alignRight: false },
  { id: 'apiName', label: '接口名', alignRight: false },
  { id: 'apiDesc', label: '接口描述', alignRight: false },
  { id: 'apiUrl', label: '接口地址', alignRight: false },
  { id: 'apiMethod', label: '请求方法', alignRight: false },
  { id: 'apiParam', label: '请求参数', alignRight: false },
  { id: 'apiStatus', label: '接口状态', alignRight: false },
  { id: 'apiReqExample', label: '请求示例', alignRight: false },
  { id: 'apiResExample', label: '响应示例', alignRight: false },
  { id: 'operation', label: '操作', alignRight: false },
];

// 请求方法
const METHODS = ['GET', 'POST', 'DELETE', 'PUT'];

// 接口状态
const STATUS = [
  {
    label: '已发布',
    value: 1,
  },
  {
    label: '已下线',
    value: 0,
  },
];

// ----------------------------------------------------------------------

function descendingComparator(a, b, orderBy) {
  if (b[orderBy] < a[orderBy]) {
    return -1;
  }
  if (b[orderBy] > a[orderBy]) {
    return 1;
  }
  return 0;
}

function getComparator(order, orderBy) {
  return order === 'desc'
    ? (a, b) => descendingComparator(a, b, orderBy)
    : (a, b) => -descendingComparator(a, b, orderBy);
}

function applySortFilter(array, comparator, query) {
  const stabilizedThis = array.map((el, index) => [el, index]);
  stabilizedThis.sort((a, b) => {
    const order = comparator(a[0], b[0]);
    if (order !== 0) return order;
    return a[1] - b[1];
  });
  if (query) {
    return filter(array, (_interface) => _interface.name.toLowerCase().indexOf(query.toLowerCase()) !== -1);
  }
  return stabilizedThis.map((el) => el[0]);
}

// -----------------------------------------------------------------------------
const StyledAddInterfaceForm = styled.form`
  width: 360px;
  padding: 20px;
`;

export default function InterfacePage() {
  const [open, setOpen] = useState({
    e: null,
    id: null,
  });

  const [page, setPage] = useState(0);

  const [order, setOrder] = useState('asc');

  const [selected, setSelected] = useState([]);

  const [orderBy, setOrderBy] = useState('name');

  const [filterName, setFilterName] = useState('');

  const [rowsPerPage, setRowsPerPage] = useState(5);

  const handleOpenMenu = (event, id) => {
    setOpen({
      e: event.currentTarget,
      id,
    });
  };

  const handleCloseMenu = () => {
    setOpen({
      e: null,
      id: null,
    });
  };

  const handleRequestSort = (event, property) => {
    const isAsc = orderBy === property && order === 'asc';
    setOrder(isAsc ? 'desc' : 'asc');
    setOrderBy(property);
  };

  const handleSelectAllClick = (event) => {
    if (event.target.checked) {
      const newSelecteds = interfaces.map((n) => n.id);
      setSelected(newSelecteds);
      return;
    }
    setSelected([]);
  };

  const handleClick = (event, name) => {
    const selectedIndex = selected.indexOf(name);
    let newSelected = [];
    if (selectedIndex === -1) {
      newSelected = newSelected.concat(selected, name);
    } else if (selectedIndex === 0) {
      newSelected = newSelected.concat(selected.slice(1));
    } else if (selectedIndex === selected.length - 1) {
      newSelected = newSelected.concat(selected.slice(0, -1));
    } else if (selectedIndex > 0) {
      newSelected = newSelected.concat(selected.slice(0, selectedIndex), selected.slice(selectedIndex + 1));
    }
    setSelected(newSelected);
  };

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setPage(0);
    setRowsPerPage(parseInt(event.target.value, 10));
  };

  const handleFilterByName = (event) => {
    setPage(0);
    setFilterName(event.target.value);
  };

  // region
  const [listParam, setListParam] = useState({
    current: 1,
    pageSize: 10,
    apiName: '',
  });

  const [interfaces, setInterfaces] = useState([]);

  const [isShowAddInterfaceDrawer, setIsShowAddInterfaceDrawer] = useState(false);

  // 增加接口信息数据
  const [interfaceInfoWithAddOperation, setInterfaceInfoWithAddOperation] = useState({
    apiMethod: 'GET',
    apiPath: 1,
  });

  // 修改接口信息数据
  const [interfaceInfoWithUpdateOperation, setInterfaceInfoWithUpdateOperation] = useState({
    apiMethod: 'GET',
    apiPath: 1,
  });

  // 初始化接口列表
  useEffect(() => {
    listInterfaceOperation();
    return () => {};
  }, [listParam]);

  // 切换新增接口抽屉
  const toggleDrawer = (isShow) => {
    setIsShowAddInterfaceDrawer(isShow);
  };

  // 获取接口列表
  const listInterfaceOperation = async () => {
    const result = await listInterface(listParam);
    if (result.data) {
      setInterfaces(result.data.list);
    }
  };

  // 更新新增信息
  const changeInterfaceInfo = (key, value) => {
    setInterfaceInfoWithAddOperation({
      ...interfaceInfoWithAddOperation,
      [key]: value,
    });
  };

  // 增加接口
  const addInterfaceOperation = async () => {
    const result = await addInterface(interfaceInfoWithAddOperation);
    if (result.data) {
      // 清空新增信息
      setInterfaceInfoWithAddOperation({});
      await listInterface(listParam);
    }
  };

  // 删除接口
  const deleteInterfaceOperation = async () => {
    const result = await deleteInterface(open.id);
    await listInterface(listParam);
  };

  // 删除接口
  const updateInterfaceOperation = async () => {
    const result = await updateInterface(interfaceInfoWithUpdateOperation);
  };

  // endregion

  const emptyRows = page > 0 ? Math.max(0, (1 + page) * rowsPerPage - interfaces.length) : 0;

  const filteredInterfaces = applySortFilter(interfaces, getComparator(order, orderBy), filterName);

  const isNotFound = !filteredInterfaces.length && !!filterName;
  return (
    <>
      <Helmet>
        <title>接口管理</title>
      </Helmet>

      <Container>
        <Stack direction="row" alignItems="center" justifyContent="space-between" mb={3}>
          <Typography variant="h4" gutterBottom>
            接口管理
          </Typography>
          <Button variant="contained" startIcon={<Iconify icon="eva:plus-fill" />} onClick={() => toggleDrawer(true)}>
            新增接口
          </Button>
          <Drawer anchor="right" open={isShowAddInterfaceDrawer} onClose={() => toggleDrawer(false)}>
            <Scrollbar>
              <StyledAddInterfaceForm>
                <Typography variant="h4" gutterBottom>
                  增加接口
                </Typography>
                <TextField
                  id="apiName"
                  label="接口名"
                  onChange={(e) => changeInterfaceInfo('apiName', e.target.value)}
                  variant="standard"
                  style={{ width: '100%', marginBottom: '10px' }}
                />
                <TextField
                  id="apiDesc"
                  label="接口描述"
                  onChange={(e) => changeInterfaceInfo('apiDesc', e.target.value)}
                  variant="standard"
                  style={{ width: '100%', marginBottom: '10px' }}
                />
                <TextField
                  id="apiUrl"
                  label="接口地址"
                  onChange={(e) => changeInterfaceInfo('apiUrl', e.target.value)}
                  variant="standard"
                  style={{ width: '100%', marginBottom: '10px' }}
                />
                <TextField
                  id="apiParam"
                  label="请求参数"
                  onChange={(e) => changeInterfaceInfo('apiParam', e.target.value)}
                  multiline
                  rows={4}
                  variant="standard"
                  style={{ width: '100%', marginBottom: '20px' }}
                />
                <TextField
                  id="apMethod"
                  select
                  onChange={(e) => changeInterfaceInfo('apMethod', e.target.value)}
                  label="请求方法"
                  defaultValue="GET"
                  style={{ width: '100%', marginBottom: '20px' }}
                >
                  {METHODS.map((method) => (
                    <MenuItem key={method} value={method}>
                      {method}
                    </MenuItem>
                  ))}
                </TextField>
                <TextField
                  id="apiStatus"
                  select
                  label="接口状态"
                  onChange={(e) => changeInterfaceInfo('apiStatus', e.target.value)}
                  defaultValue={1}
                  style={{ width: '100%', marginBottom: '10px' }}
                >
                  {STATUS.map((status) => (
                    <MenuItem key={status.value} value={status.value}>
                      {status.label}
                    </MenuItem>
                  ))}
                </TextField>
                <TextField
                  id="apiReqExample"
                  label="请求示例"
                  multiline
                  rows={4}
                  onChange={(e) => changeInterfaceInfo('apiReqExample', e.target.value)}
                  variant="standard"
                  style={{ width: '100%', marginBottom: '10px' }}
                />
                <TextField
                  id="apiResExample"
                  label="响应示例"
                  multiline
                  rows={4}
                  onChange={(e) => changeInterfaceInfo('apiResExample', e.target.value)}
                  variant="standard"
                  style={{ width: '100%', marginBottom: '10px' }}
                />
                <div style={{ display: 'flex', justifyContent: 'space-around', marginTop: '15px' }}>
                  <Button variant="contained" onClick={addInterfaceOperation}>
                    添加
                  </Button>
                  <Button variant="contained" onClick={() => toggleDrawer(false)}>
                    取消
                  </Button>
                </div>
              </StyledAddInterfaceForm>
            </Scrollbar>
          </Drawer>
        </Stack>

        <Card>
          <InterfaceListToolbar
            numSelected={selected.length}
            filterName={filterName}
            onFilterName={handleFilterByName}
          />
          <Scrollbar>
            <TableContainer>
              <Table>
                <InterfaceListHead
                  headLabel={TABLE_HEAD}
                  rowCount={interfaces.length}
                  numSelected={selected.length}
                  onRequestSort={handleRequestSort}
                  onSelectAllClick={handleSelectAllClick}
                />
                <TableBody>
                  {interfaces.map((interfaceInfo) => {
                    const {
                      id,
                      apiName,
                      apiDesc,
                      apiUrl,
                      apiMethod,
                      apiParam,
                      apiStatus,
                      apiReqExample,
                      apiResExample,
                    } = interfaceInfo;
                    const selectedInterface = selected.indexOf(id) !== -1;

                    return (
                      <TableRow hover key={id} tabIndex={-1} role="checkbox" selected={selectedInterface}>
                        <TableCell padding="checkbox">
                          <Checkbox checked={selectedInterface} onChange={(event) => handleClick(event, id)} />
                        </TableCell>

                        <TableCell align={'center'} size={'small'}>
                          {id}
                        </TableCell>
                        <TableCell align={'center'} size={'small'}>
                          {apiName}
                        </TableCell>
                        <TableCell align={'center'} size={'small'}>
                          {apiDesc}
                        </TableCell>
                        <TableCell align={'center'} size={'small'} style={{ width: 'auto' }}>
                          {apiUrl}
                        </TableCell>
                        <TableCell align={'center'} size={'small'}>
                          {apiMethod}
                        </TableCell>
                        <TableCell align={'center'} size={'small'}>
                          {apiParam}
                        </TableCell>
                        <TableCell align={'center'} size={'small'}>
                          {apiStatus}
                        </TableCell>
                        <TableCell align={'center'} size={'small'}>
                          {apiReqExample}
                        </TableCell>
                        <TableCell align={'center'} size={'small'}>
                          {apiResExample}
                        </TableCell>

                        <TableCell align="center">
                          <IconButton size="large" color="inherit" onClick={(e) => handleOpenMenu(e, id)}>
                            <Iconify icon={'eva:more-vertical-fill'} />
                          </IconButton>
                        </TableCell>
                      </TableRow>
                    );
                  })}
                  {emptyRows > 0 && (
                    <TableRow style={{ height: 53 * emptyRows }}>
                      <TableCell colSpan={6} />
                    </TableRow>
                  )}
                </TableBody>

                {isNotFound && (
                  <TableBody>
                    <TableRow>
                      <TableCell align="center" colSpan={6} sx={{ py: 3 }}>
                        <Paper
                          sx={{
                            textAlign: 'center',
                          }}
                        >
                          <Typography variant="h6" paragraph>
                            没找到
                          </Typography>

                          <Typography variant="body2">
                            没找到&nbsp;
                            <strong>&quot;{filterName}&quot;</strong>.
                            <br /> 请检查输入是否有误
                          </Typography>
                        </Paper>
                      </TableCell>
                    </TableRow>
                  </TableBody>
                )}
              </Table>
            </TableContainer>
          </Scrollbar>

          <TablePagination
            rowsPerPageOptions={[5, 10, 25]}
            component="div"
            count={interfaces.length}
            rowsPerPage={rowsPerPage}
            page={page}
            onPageChange={handleChangePage}
            onRowsPerPageChange={handleChangeRowsPerPage}
          />
        </Card>
      </Container>

      <Popover
        open={Boolean(open.e)}
        anchorEl={open.e}
        onClose={handleCloseMenu}
        anchorOrigin={{ vertical: 'top', horizontal: 'left' }}
        transformOrigin={{ vertical: 'top', horizontal: 'right' }}
        PaperProps={{
          sx: {
            p: 1,
            width: 140,
            '& .MuiMenuItem-root': {
              px: 1,
              typography: 'body2',
              borderRadius: 0.75,
            },
          },
        }}
      >
        <MenuItem>
          <Iconify icon={'eva:edit-fill'} sx={{ mr: 2 }} />
          编辑
        </MenuItem>

        <MenuItem sx={{ color: 'error.main' }} onClick={() => deleteInterfaceOperation()}>
          <Iconify icon={'eva:trash-2-outline'} sx={{ mr: 2 }} />
          删除
        </MenuItem>
      </Popover>
    </>
  );
}
