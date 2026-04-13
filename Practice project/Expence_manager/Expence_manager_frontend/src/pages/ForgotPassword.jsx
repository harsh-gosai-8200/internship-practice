import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { useAuth } from "../context/AuthContext";

const ForgotPassword = () => {
  const navigate = useNavigate();
  const { sendResetOtp } = useAuth();

  const [email, setEmail] = useState("");
  const [loading, setLoading] = useState(false);

  const handleSendOtp = async (e) => {
    e.preventDefault();
    setLoading(true);

    const result = await sendResetOtp(email);

    if (result.success) {
      toast.success(result.message);
      sessionStorage.setItem("resetEmail", email);
      navigate("/reset-password");
    } else {
      toast.error(result.message);
    }

    setLoading(false);
  };

  return (
    <div className="min-h-screen bg-gray-50">
      {/* Header */}
      <div className="bg-white border-b border-gray-200">
        <div className="max-w-6xl mx-auto px-6 py-4">
          <Link to="/" className="text-xl font-semibold text-gray-900 hover:text-gray-600">
            Auth Project
          </Link>
        </div>
      </div>

      {/* Form Container */}
      <div className="max-w-6xl mx-auto px-6 py-20 flex items-center justify-center">
        <div className="bg-white rounded-lg border border-gray-200 p-8 w-full max-w-md">
          <h2 className="text-2xl font-semibold text-gray-900 mb-2">
            Reset Password
          </h2>
          <p className="text-gray-600 text-sm mb-8">
            Enter your email to receive a password reset OTP
          </p>

          <form onSubmit={handleSendOtp}>
            <div className="mb-6">
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Email Address
              </label>
              <input
                type="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 outline-none text-sm"
                placeholder="Enter your email"
              />
            </div>

            <button
              type="submit"
              disabled={loading}
              className="w-full bg-blue-600 text-white py-2 rounded-lg hover:bg-blue-700 transition disabled:bg-gray-400 text-sm font-medium"
            >
              {loading ? "Sending OTP..." : "Send OTP"}
            </button>
          </form>

          <div className="mt-6 text-center space-y-2">
            <Link to="/login" className="block text-sm text-gray-600 hover:text-gray-900">
              Remember your password? Sign in
            </Link>
            <Link to="/" className="block text-sm text-gray-600 hover:text-gray-900">
              Back to home
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ForgotPassword;
