import React, { useState, FormEvent } from 'react';
import {
  Button,
  TextField,
  MenuItem,
  Select,
  InputLabel,
FormControl,
  Box,
  Typography,
  CardContent,
  Card,
  Grid,
  Snackbar,
  Alert
} from '@mui/material';
import { MessageFormData, Category } from '../../types/index.d';
import { sendNotification } from '../../services/api';

interface MessageFormProps {
  onSubmit: (data: MessageFormData) => void;
}

export const MessageForm: React.FC<MessageFormProps> = ({ onSubmit }) => {
  const [category, setCategory] = useState<Category | ''>('');
  const [message, setMessage] = useState<string>('');
  const [snackbarOpen, setSnackbarOpen] = useState<boolean>(false);
  const [snackbarMessage, setSnackbarMessage] = useState<string>('');
  const [snackbarSeverity, setSnackbarSeverity] = useState<'success' | 'error'>('success');

  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault();
    if (category && message) {
      try {
        await sendNotification({ category, message });
        setCategory(''); // Reset category after submission
        setMessage('');
        setSnackbarMessage('Notification sent successfully!');
        setSnackbarSeverity('success');
        setSnackbarOpen(true);
        onSubmit({ category, message }); // Call the onSubmit prop after successful notification
      } catch (error) {
        console.error(error);
        setSnackbarMessage('Failed to send notification.');
        setSnackbarSeverity('error');
        setSnackbarOpen(true);
      }
    } else {
      setSnackbarMessage('Both fields are required!');
      setSnackbarSeverity('error');
      setSnackbarOpen(true);
    }
  };

  const handleSnackbarClose = () => {
    setSnackbarOpen(false);
  };

  return (
    <Grid container justifyContent="center" alignItems="center">
      <Grid item xs={12} md={6} lg={4}>
        <Card>
          <CardContent>
            <Typography variant="h4" component="h1" gutterBottom align="center">
              Notification System
            </Typography>

            <Box component="form" onSubmit={handleSubmit} sx={{ mt: 2 }}>
              <FormControl fullWidth sx={{ mb: 2 }}>
                <InputLabel id="category-label">Category</InputLabel>
                <Select
                  labelId="category-label"
                  value={category}
                  label="Category"
                  onChange={(e) => setCategory(e.target.value as Category)}
                >
                  <MenuItem value=""><em>Select Category</em></MenuItem>
                  {Object.values(Category).map((categoryValue) => (
                    <MenuItem key={categoryValue} value={categoryValue}>
                      {categoryValue}
                    </MenuItem>
                  ))}
                </Select>
              </FormControl>

              <TextField
                label="Message"
                multiline
                fullWidth
                rows={4}
                value={message}
                onChange={(e) => setMessage(e.target.value)}
                variant="outlined"
                sx={{ mb: 2 }}
              />

              <Button variant="contained" type="submit" style={{ backgroundColor: '#57218a' }} fullWidth>
                Send Notification
              </Button>
            </Box>
          </CardContent>
        </Card>
        <Snackbar
          open={snackbarOpen}
          autoHideDuration={6000}
          onClose={handleSnackbarClose}
          anchorOrigin={{ vertical: 'bottom', horizontal: 'center' }}
        >
          <Alert onClose={handleSnackbarClose} severity={snackbarSeverity} sx={{ width: '100%' }}>
            {snackbarMessage}
          </Alert>
        </Snackbar>
      </Grid>
    </Grid>
  );
};
