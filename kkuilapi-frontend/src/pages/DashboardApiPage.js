import { useEffect, useState } from 'react';
import { Helmet } from 'react-helmet-async';
import { faker } from '@faker-js/faker';
import moment from 'moment';
// @mui
import { useTheme } from '@mui/material/styles';
import { Grid, Container, Typography } from '@mui/material';
// sections
import { AppNewsUpdate, AppCurrentVisits, AppWebsiteVisits, AppWidgetSummary } from '../sections/@dashboard/api';
import { getInterfaceInvokeTotal, getInterfaceTotal } from '../api/common/interface';

// ----------------------------------------------------------------------

const AFTER_DAY = 7;

export default function DashboardApiPage() {
  const theme = useTheme();
  const [interfaceTotal, setInterfaceTotal] = useState(0);
  const [interfaceInvokeTotal, setInvokeInterfaceTotal] = useState(0);

  const [afterDate, setAfterDate] = useState([]);

  useEffect(() => {
    // 初始化日期信息
    initAfterDate();
    // 获取接口总数
    handlerGetInterfaceTotal();
    // 获取接口调用总数
    handlerGetInterfaceInvokeTotal();
    return () => {};
  }, []);

  // 初始化日期信息
  const initAfterDate = () => {
    for (let i = 0; i < AFTER_DAY; i += 1) {
      setAfterDate([
        ...afterDate,
        moment()
          .add(i, 'days')
          .format('YYYY/MM/DD'),
      ]);
    }
    console.log(afterDate);
  };

  // 获取接口总数
  const handlerGetInterfaceTotal = async () => {
    const result = await getInterfaceTotal();
    if (result.data) {
      setInterfaceTotal(result.data);
    }
  };

  // 获取接口总调用次数
  const handlerGetInterfaceInvokeTotal = async () => {
    const result = await getInterfaceInvokeTotal();
    if (result.data) {
      setInvokeInterfaceTotal(result.data);
    }
  };

  return (
    <>
      <Helmet>
        <title>主页</title>
      </Helmet>

      <Container maxWidth="xl">
        <Typography variant="h4" sx={{ mb: 5 }}>
          欢迎来到 Kkuil-API 平台
        </Typography>

        <Grid container spacing={3}>
          <Grid item xs={6} sm={6} md={6}>
            <AppWidgetSummary title="总接口数" total={interfaceTotal} icon={'ant-design:android-filled'} />
          </Grid>

          <Grid item xs={6} sm={6} md={6}>
            <AppWidgetSummary
              title="接口调用次数"
              total={interfaceInvokeTotal}
              color="warning"
              icon={'ant-design:windows-filled'}
            />
          </Grid>

          <Grid item xs={12} md={6} lg={8}>
            <AppWebsiteVisits
              title="日均调用次数"
              subheader="比昨日上升43%"
              chartLabels={afterDate ?? []}
              chartData={[
                {
                  name: '调用次数',
                  type: 'area',
                  fill: 'gradient',
                  data: [44, 21, 45, 65, 94, 75, 79],
                },
              ]}
            />
          </Grid>

          <Grid item xs={12} md={6} lg={4}>
            <AppCurrentVisits
              title="接口调用排行榜"
              chartData={[
                { label: 'America', value: 4344 },
                { label: 'Asia', value: 5435 },
                { label: 'Europe', value: 1443 },
                { label: 'Africa', value: 4443 },
              ]}
              chartColors={[
                theme.palette.primary.main,
                theme.palette.info.main,
                theme.palette.warning.main,
                theme.palette.error.main,
              ]}
            />
          </Grid>

          <Grid item xs={12} md={12} lg={8}>
            <AppNewsUpdate
              title="最新接口"
              list={[...Array(5)].map((_, index) => ({
                id: faker.datatype.uuid(),
                title: faker.name.jobTitle(),
                description: faker.name.jobTitle(),
                image: `/assets/images/covers/cover_${index + 1}.jpg`,
                postedAt: faker.date.recent(),
              }))}
            />
          </Grid>
        </Grid>
      </Container>
    </>
  );
}
