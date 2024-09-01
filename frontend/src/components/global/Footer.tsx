import React from 'react';
import { Box, Typography } from '@mui/material';

export const Footer: React.FC = () => {
  return (
    <Box 
      sx={{ 
        width: '100%', 
        position: 'fixed', 
        bottom: 0, 
        backgroundColor: '#d1d1d1', 
        padding: '10px 0', 
        textAlign: 'center' 
      }}
    >
      <Typography variant="body2" color="textSecondary">
        By Carlos Salamanca
      </Typography>
    </Box>
  );
};

export {};
