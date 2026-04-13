import axios from "axios";

const api = axios.create({
    baseURL: "http://localhost:8080/api/v1.0",
    withCredentials: true,
});

// Suppress 401 errors in console (expected when user is not authenticated)
api.interceptors.response.use(
  response => response,
  error => {
    // Silently fail for 401 errors - these are expected when not logged in
    if (error.response?.status === 401) {
      return Promise.reject(error);
    }
    // Log other errors
    console.error("API Error:", error.response?.status, error.message);
    return Promise.reject(error);
  }
);

// Transaction API endpoints
export const transactionAPI = {
  // Add new transaction
  addTransaction: (transactionData) => 
    api.post("/transaction", transactionData),

  // Update transaction
  updateTransaction: (id, transactionData) => 
    api.put(`/transaction/${id}`, transactionData),

  // Delete transaction
  deleteTransaction: (id) => 
    api.delete(`/transaction/${id}`),

  // Get all transactions with filters
  getTransactions: (filters = {}) => {
    const params = new URLSearchParams();
    
    if (filters.type) params.append('type', filters.type);
    if (filters.categoryName) params.append('categoryName', filters.categoryName);
    if (filters.minAmount !== undefined && filters.minAmount !== null) 
      params.append('minAmount', filters.minAmount);
    if (filters.maxAmount !== undefined && filters.maxAmount !== null) 
      params.append('maxAmount', filters.maxAmount);
    if (filters.startDate) params.append('startDate', filters.startDate);
    if (filters.endDate) params.append('endDate', filters.endDate);

    return api.get(`/transaction?${params.toString()}`);
  },

  // Get dashboard summary
  getDashboardSummary: () => 
    api.get("/transaction/dashboard"),
};

export default api;