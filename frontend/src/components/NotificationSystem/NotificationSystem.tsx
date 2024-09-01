import React, { useState } from 'react';
import { Tabs, Tab, Box } from '@mui/material';
import { Header, MessageForm, LogHistory, Footer } from '../';

function TabPanel(props: { children?: React.ReactNode; index: number; value: number }) {
  const { children, value, index, ...other } = props;

  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`simple-tabpanel-${index}`}
      aria-labelledby={`simple-tab-${index}`}
      {...other}
    >
      {value === index && (
        <Box sx={{ p: 3 }}>
          {children}
        </Box>
      )}
    </div>
  );
}

export const NotificationSystem: React.FC = () => {
  const [value, setValue] = useState(0);
  const [refreshTrigger, setRefreshTrigger] = useState(false);

  const handleTabChange = (event: React.SyntheticEvent, newValue: number) => {
    setValue(newValue);
  };

  const handleRefreshLogs = () => {
    setRefreshTrigger((prev) => !prev); // Toggle state to trigger log refresh
  };

  return (
    <Box sx={{ width: '100%' }}>
      <Header />
      <Tabs value={value} onChange={handleTabChange} centered>
        <Tab 
          label="Send Notification" 
          sx={{ fontWeight: 'bold' }} 
        />
        <Tab 
          label="View Logs" 
          sx={{ fontWeight: 'bold' }} 
        />
      </Tabs>
      <TabPanel value={value} index={0}>
        <MessageForm onSubmit={handleRefreshLogs} />
      </TabPanel>
      <TabPanel value={value} index={1}>
        <LogHistory refreshTrigger={refreshTrigger} />
      </TabPanel>
      <Footer />
    </Box>
  );
};
