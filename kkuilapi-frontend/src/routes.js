import { Navigate, useRoutes } from 'react-router-dom';
// layouts
import DashboardLayout from './layouts/dashboard';
import SimpleLayout from './layouts/simple';
//
import InvokeApiInlinePage from './pages/InvokeApiInlinePage';
import LoginPage from './pages/LoginPage';
import Page404 from './pages/Page404';
import InterfacePage from './pages/InterfacePage';
import DashboardApiPage from './pages/DashboardApiPage';
import Invoke from './pages/Invoke';

// ----------------------------------------------------------------------

function Router() {
  return useRoutes([
    {
      path: '/',
      element: <Navigate to="/dashboard/api" />,
      index: true,
    },
    {
      path: '/dashboard',
      element: <DashboardLayout />,
      children: [
        { element: <Navigate to="/dashboard/api" />, index: true },
        { path: 'api', element: <DashboardApiPage /> },
        { path: 'interface', element: <InterfacePage /> },
        { path: 'invoke-api', element: <InvokeApiInlinePage /> },
        { path: 'invoke/:id', element: <Invoke /> },
      ],
    },
    {
      path: 'login',
      element: <LoginPage />,
    },
    {
      element: <SimpleLayout />,
      children: [
        { element: <Navigate to="/dashboard/app" />, index: true },
        { path: '404', element: <Page404 /> },
        { path: '*', element: <Navigate to="/404" /> },
      ],
    },
    {
      path: '*',
      element: <Navigate to="/404" replace />,
    },
  ]);
}

export default Router;
