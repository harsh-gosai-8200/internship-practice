import React, { createContext, useState, useContext, useEffect } from "react";
import api from "../api";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [loading, setLoading] = useState(true);

  // Check if user is already authenticated (on mount)
  useEffect(() => {
    checkAuthStatus();
  }, []);

  const checkAuthStatus = async () => {
    try {
      const res = await api.get("/is-authenticated");
      if (res.status === 200) {
        setIsAuthenticated(true);
        // Optionally fetch user profile
        try {
          const profileRes = await api.get("/profile");
          setUser(profileRes.data);
        } catch (error) {
          // Silently fail if profile can't be fetched
        }
      }
    } catch (error) {
      // 401 Unauthorized is expected when user is not logged in - silently ignore
      setIsAuthenticated(false);
      setUser(null);
    } finally {
      setLoading(false);
    }
  };

  const register = async (name, email, password) => {
    try {
      const res = await api.post("/register", { name, email, password });
      if (res.status === 201) {
        return { success: true, data: res.data };
      }
    } catch (error) {
      return {
        success: false,
        message: error.response?.data?.message || "Registration failed",
      };
    }
  };

  const login = async (email, password) => {
    try {
      const res = await api.post("/login", { email, password });
      if (res.status === 200) {
        setIsAuthenticated(true);
        setUser({ email, ...res.data });
        
        // Fetch full profile to get all user data including verification status
        try {
          const profileRes = await api.get("/profile");
          setUser(profileRes.data);
        } catch (error) {
          // Continue with partial user data if profile fetch fails
        }
        
        return { success: true, data: res.data };
      }
    } catch (error) {
      return {
        success: false,
        message: error.response?.data?.message || "Login failed",
      };
    }
  };

  const logout = () => {
    setUser(null);
    setIsAuthenticated(false);
    // Optional: call backend logout endpoint if it exists
  };

  const sendResetOtp = async (email) => {
    try {
      await api.post("/send-reset-otp", null, { params: { email } });
      return { success: true, message: "OTP sent to your email" };
    } catch (error) {
      return {
        success: false,
        message: error.response?.data?.message || "Failed to send OTP",
      };
    }
  };

  const resetPassword = async (email, otp, newPassword) => {
    try {
      await api.post("/reset-password", { email, otp, newPassword });
      return { success: true, message: "Password reset successfully" };
    } catch (error) {
      return {
        success: false,
        message: error.response?.data?.message || "Failed to reset password",
      };
    }
  };

  const sendOtp = async () => {
    try {
      await api.post("/send-otp");
      return { success: true, message: "OTP sent to your email" };
    } catch (error) {
      return {
        success: false,
        message: error.response?.data?.message || "Failed to send OTP",
      };
    }
  };

  const verifyOtp = async (otp) => {
    try {
      await api.post("/verify-otp", { otp });
      // Update user after verification
      await checkAuthStatus();
      return { success: true, message: "Email verified successfully" };
    } catch (error) {
      return {
        success: false,
        message: error.response?.data?.message || "Verification failed",
      };
    }
  };

  const changePassword = async (oldPassword, newPassword) => {
    try {
      await api.post("/change-password", { oldPassword, newPassword });
      return { success: true, message: "Password changed successfully" };
    } catch (error) {
      return {
        success: false,
        message: error.response?.data?.message || "Failed to change password",
      };
    }
  };

  const value = {
    user,
    isAuthenticated,
    loading,
    register,
    login,
    logout,
    sendResetOtp,
    resetPassword,
    sendOtp,
    verifyOtp,
    changePassword,
    checkAuthStatus,
  };

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error("useAuth must be used within AuthProvider");
  }
  return context;
};
