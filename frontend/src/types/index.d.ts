export interface Log {
    id: number;
    timestamp: string;
    notificationType: string;
    userEmail: string | null;
    userPhoneNumber: string | null;
    messageCategory: string;
    details: string;
  }
  
  export interface MessageFormData {
    category: string;
    message: string;
  }

  export enum Category {
    SPORTS = 'Sports',
    FINANCE = 'Finance',
    MOVIES = 'Movies'
  }
  