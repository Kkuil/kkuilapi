import React, { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
import { getInterface } from '../api/common/interface';
import Tag from '../components/common/Tag';

const styles = (theme) => ({
  root: {
    width: '100%',
    marginTop: theme.spacing.unit * 3,
    overflowX: 'auto',
  },
  table: {
    minWidth: 700,
  },
});

let id = 0;

const PARAMS_THEADER = [{ label: '参数名' }, { label: '传入位置' }, { label: '类型' }, { label: '参数说明' }];

function createData(name, calories, fat, carbs, protein) {
  id += 1;
  return { id, name, calories, fat, carbs, protein };
}

const rows = [
  createData('Frozen yoghurt', 159, 6.0, 24, 4.0),
  createData('Ice cream sandwich', 237, 9.0, 37, 4.3),
  createData('Eclair', 262, 16.0, 24, 6.0),
  createData('Cupcake', 305, 3.7, 67, 4.3),
  createData('Gingerbread', 356, 16.0, 49, 3.9),
];

export default function Invoke() {
  const location = useLocation();
  const [interfaceInfo, setInterfaceInfo] = useState({
    id: '',
    apiName: '',
    apiDesc: '',
    apiUrl: '',
    apiMethod: '',
    apiParam: '',
    apiStatus: '',
    apiReqExample: '',
    apiResExample: '',
    apiCount: '',
    apiCreateTime: '',
    apiModifyTime: '',
  });

  useEffect(() => {
    const path = location.pathname.split('/');
    const pathParams = path[path.length - 1];
    if (pathParams) {
      getInterfaceInfo(pathParams);
    }
    return () => {};
  }, [location]);

  // 获取接口信息
  const getInterfaceInfo = async (id) => {
    const result = await getInterface(id);
    if (result.data) {
      setInterfaceInfo({
        ...result.data,
        apiParam: JSON.parse(result.data.apiParam) ?? '',
      });
    }
  };

  // 复制接口地址
  const copyUrl = (e) => {
    e.preventDefault();
    globalThis.navigator.clipboard.writeText(interfaceInfo.apiUrl).then(() => {});
  };

  return (
    <div style={{ paddingLeft: '20px', paddingRight: '20px' }}>
      <div className="api-name" style={{ padding: '0 0 30px', borderBottom: '1px solid #ccc' }}>
        <h1>{interfaceInfo.apiName}</h1>
        <Tag color="#f0f9eb">{interfaceInfo.apiMethod}</Tag>
        <a href="" style={{ marginLeft: '5px' }} onClick={copyUrl}>
          {interfaceInfo.apiUrl}
        </a>
      </div>
      <div className="api-desc" style={{ padding: '0 0 30px', borderBottom: '1px solid #ccc' }}>
        <h1>接口描述</h1>
        <span>{interfaceInfo.apiDesc}</span>
      </div>
      <div className="api-param" style={{ padding: '0 0 30px', borderBottom: '1px solid #ccc' }}>
        <h1>请求参数</h1>
        <table>
          <thead>
            <tr>
              {PARAMS_THEADER.map((item) => (
                <td>{item.label}</td>
              ))}
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>{interfaceInfo?.apiParam.type}</td>
              <td>{interfaceInfo?.apiParam.location}</td>
              <td>{interfaceInfo?.apiParam.type}</td>
              <td>{interfaceInfo?.apiParam.desc}</td>
            </tr>
          </tbody>
        </table>
      </div>
      <div className="api-req-example" style={{ padding: '0 0 30px', borderBottom: '1px solid #ccc' }}>
        <h1>请求示例</h1>
        <a href="#">{interfaceInfo.apiReqExample}</a>
      </div>
      <div className="api-res-example" style={{ padding: '0 0 30px', borderBottom: '1px solid #ccc' }}>
        <h1>响应示例</h1>
        <span>{interfaceInfo.apiResExample}</span>
      </div>
    </div>
  );
}
