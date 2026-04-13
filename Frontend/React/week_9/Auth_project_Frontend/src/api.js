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

export default api;