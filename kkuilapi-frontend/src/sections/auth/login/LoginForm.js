import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
// @mui
import { Stack, IconButton, InputAdornment, TextField } from '@mui/material';
import { LoadingButton } from '@mui/lab';
// components
import Iconify from '../../../components/iconify';
import { login } from '../../../api/admin';

// ----------------------------------------------------------------------

export default function LoginForm() {
  const navigate = useNavigate();

  const [showPassword, setShowPassword] = useState(false);

  const [loginInfo, setLoginInfo] = useState({
    account: '',
    password: '',
  });

  const handleLogin = async () => {
    if (loginInfo.account === '' || loginInfo.password === '') {
      return;
    }
    console.log(loginInfo)
    const result = await login(loginInfo);
    if (result.data) {
      navigate('/dashboard', { replace: true });
    }
  };

  return (
    <>
      <Stack spacing={3}>
        <TextField
          name="account"
          label="账号"
          onInput={(e) => setLoginInfo({ ...loginInfo, account: e.target.value })}
        />

        <TextField
          name="password"
          label="密码"
          type={showPassword ? 'text' : 'password'}
          onInput={(e) => setLoginInfo({ ...loginInfo, password: e.target.value })}
          InputProps={{
            endAdornment: (
              <InputAdornment position="end">
                <IconButton onClick={() => setShowPassword(!showPassword)} edge="end">
                  <Iconify icon={showPassword ? 'eva:eye-fill' : 'eva:eye-off-fill'} />
                </IconButton>
              </InputAdornment>
            ),
          }}
        />
      </Stack>

      <LoadingButton
        fullWidth
        size="large"
        type="submit"
        variant="contained"
        onClick={handleLogin}
        style={{ marginTop: '10px' }}
      >
        登录
      </LoadingButton>
    </>
  );
}
