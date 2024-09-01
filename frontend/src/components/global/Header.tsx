import React from 'react';
import { AppBar, Toolbar, Box } from '@mui/material';

export const Header: React.FC = () => {
  return (
    <AppBar position="static" sx={{ backgroundColor: '#57218a' }}>
      <Toolbar>
        <Box sx={{ flexGrow: 1, textAlign: 'center' }}>
          <img
            src="https://gilasw.com/wp-content/themes/gilatheme/assets/img/Logo1.png"
            alt="Gila Logo"
            style={{ maxHeight: '50px' }}
          />
        </Box>
      </Toolbar>
    </AppBar>
  );
};
