// component
import SvgColor from '../../../components/svg-color';

// ----------------------------------------------------------------------

const icon = (name) => <SvgColor src={`/assets/icons/navbar/${name}.svg`} sx={{ width: 1, height: 1 }} />;

const navConfig = [
  {
    title: '主页',
    path: '/dashboard/api',
    icon: icon('ic_analytics'),
  },
  {
    title: '接口管理',
    path: '/dashboard/interface',
    icon: icon('ic_cart'),
  },
  {
    title: '在线调用',
    path: '/dashboard/invoke-api',
    icon: icon('ic_blog'),
  }
];

export default navConfig;
