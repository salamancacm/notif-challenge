import { MessageFormData, Log } from '../types';

const api = 'http://localhost:8080/api';

export const sendNotification = async (data: MessageFormData): Promise<void> => {
  
  const response = await fetch(`${api}/send`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(data),
  });

  if (!response.ok) {
    throw new Error('Failed to send notification');
  }
};

export const fetchLogs = async (): Promise<Log[]> => {
  const response = await fetch(`${api}/logs`);
  if (!response.ok) {
    throw new Error('Failed to fetch logs');
  }
  return response.json();
};
