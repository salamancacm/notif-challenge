import React, { useEffect, useState } from 'react';
import {
  Box,
  Typography,
  Paper,
  Grid,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  IconButton,
  CircularProgress
} from '@mui/material';
import { fetchLogs } from '../../services/api';
import { Log } from '../../types';
import { RefreshRounded } from '@mui/icons-material';

interface LogHistoryProps {
  refreshTrigger: boolean;
}

export const LogHistory: React.FC<LogHistoryProps> = ({ refreshTrigger }) => {
  const [logs, setLogs] = useState<Log[]>([]);
  const [loading, setLoading] = useState<boolean>(false);

  const loadLogs = async () => {
    setLoading(true);
    try {
      const fetchedLogs = await fetchLogs();
      setLogs(fetchedLogs);
    } catch (error) {
      console.error('Failed to load logs:', error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadLogs();
  }, [refreshTrigger]);

  // Function to map channel to display name
  const getChannelDisplayName = (channel: string) => {
    switch (channel) {
      case 'PUSH_NOTIFICATION':
        return 'Push Notification';
      case 'EMAIL':
        return 'E-Mail';
      case 'SMS':
        return 'SMS';
      default:
        return channel;
    }
  };

  // Function to format the timestamp
  const formatTimestamp = (timestamp: string) => {
    const date = new Date(timestamp);
    return date.toLocaleString(); // Formats to 'MM/DD/YYYY, HH:MM:SS AM/PM'
  };

  return (
    <Grid container justifyContent="center">
      <Grid item xs={12} md={8}>
        <Box sx={{ mt: 4 }}>
          <Box display="flex" justifyContent="space-between" alignItems="center" mb={2}>
            <Typography variant="h5" component="h2" gutterBottom>
              Notification Log History
            </Typography>
            <IconButton
              aria-label="Refresh Logs"
              style={{ backgroundColor: '#57218a', color: 'white' }}
              onClick={loadLogs}
            >
              <RefreshRounded />
            </IconButton>
          </Box>
          <TableContainer component={Paper} sx={{ maxHeight: 600, overflow: 'auto', borderRadius: '8px' }}>
            {loading ? (
              <Box display="flex" justifyContent="center" alignItems="center" sx={{ height: 200 }}>
                <CircularProgress />
              </Box>
            ) : (
              <Table stickyHeader aria-label="notification logs table">
                <TableHead>
                  <TableRow>
                    <TableCell sx={{ fontWeight: 'bold', backgroundColor: '#57218a', color: '#fff' }}>Timestamp</TableCell>
                    <TableCell sx={{ fontWeight: 'bold', backgroundColor: '#57218a', color: '#fff' }}>Notification Type</TableCell>
                    <TableCell sx={{ fontWeight: 'bold', backgroundColor: '#57218a', color: '#fff' }}>User</TableCell>
                    <TableCell sx={{ fontWeight: 'bold', backgroundColor: '#57218a', color: '#fff' }}>Category</TableCell>
                    <TableCell sx={{ fontWeight: 'bold', backgroundColor: '#57218a', color: '#fff' }}>Details</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {logs.length > 0 ? (
                    logs.map((log) => (
                      <TableRow key={log.id}>
                        <TableCell>{formatTimestamp(log.timestamp)}</TableCell>
                        <TableCell>{getChannelDisplayName(log.notificationType)}</TableCell>
                        <TableCell>{log.userEmail || log.userPhoneNumber}</TableCell>
                        <TableCell>{log.messageCategory}</TableCell>
                        <TableCell>{log.details}</TableCell>
                      </TableRow>
                    ))
                  ) : (
                    <TableRow>
                      <TableCell colSpan={5} align="center">
                        No logs available.
                      </TableCell>
                    </TableRow>
                  )}
                </TableBody>
              </Table>
            )}
          </TableContainer>
        </Box>
      </Grid>
    </Grid>
  );
};
