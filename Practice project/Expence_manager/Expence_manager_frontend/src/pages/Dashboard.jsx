import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { useAuth } from '../context/AuthContext'
import Navigation from '../components/Navigation'
import { transactionAPI } from '../api'
import { toast } from 'react-toastify'

const Dashboard = () => {
  const navigate = useNavigate()
  const { user, isAuthenticated, loading, logout, sendOtp, verifyOtp } = useAuth()
  
  // Dashboard state
  const [dashboardData, setDashboardData] = useState({
    totalIncome: 0,
    totalExpense: 0,
    balance: 0,
  })
  const [isLoadingDashboard, setIsLoadingDashboard] = useState(false)

  // Email verification state
  const [showVerifyModal, setShowVerifyModal] = useState(false)
  const [otp, setOtp] = useState('')
  const [verifying, setVerifying] = useState(false)
  const [otpSent, setOtpSent] = useState(false)
  const [sendingOtp, setSendingOtp] = useState(false)

  // Page state
  const [isLoggingOut, setIsLoggingOut] = useState(false)

  // Check authentication
  useEffect(() => {
    if (!loading && !isAuthenticated && !isLoggingOut) {
      toast.error("Please login first")
      navigate("/")
    }
  }, [isAuthenticated, loading, navigate, isLoggingOut])

  // Load dashboard data
  useEffect(() => {
    if (isAuthenticated) {
      loadDashboardData()
    }
  }, [isAuthenticated])

  const loadDashboardData = async () => {
    try {
      setIsLoadingDashboard(true)
      const response = await transactionAPI.getDashboardSummary()
      setDashboardData(response.data)
    } catch (error) {
      console.error("Failed to load dashboard data:", error)
      toast.error("Failed to load dashboard data")
    } finally {
      setIsLoadingDashboard(false)
    }
  }

  const handleLogout = () => {
    setIsLoggingOut(true)
    logout()
    toast.success("Logged out successfully!")
    navigate("/")
  }

  const handleSendOtp = async () => {
    setSendingOtp(true)
    const result = await sendOtp()
    
    if (result.success) {
      toast.success(result.message)
      setOtpSent(true)
    } else {
      toast.error(result.message)
    }
    
    setSendingOtp(false)
  }

  const handleVerifyOtp = async (e) => {
    e.preventDefault()
    
    if (!otp.trim()) {
      toast.error("Please enter OTP")
      return
    }
    
    setVerifying(true)
    const result = await verifyOtp(otp)
    
    if (result.success) {
      toast.success(result.message)
      setShowVerifyModal(false)
      setOtp('')
      setOtpSent(false)
      // Refresh user data to update verification status
      setTimeout(() => {
        navigate("/dashboard")
        window.location.reload()
      }, 1500)
    } else {
      toast.error(result.message)
    }
    
    setVerifying(false)
  }

  const openVerifyModal = async () => {
    setShowVerifyModal(true)
    setOtpSent(false)
    setOtp('')
  }

  if (loading) {
    return (
      <div className="min-h-screen flex items-center justify-center bg-gray-50">
        <div className="text-gray-600">Loading...</div>
      </div>
    )
  }

  if (!isAuthenticated) {
    return null
  }

  return (
    <div className="min-h-screen bg-gray-50">
      {/* Navigation */}
      <Navigation currentPage="dashboard" onVerifyEmail={openVerifyModal} />

      {/* Main Content */}
      <div className="max-w-7xl mx-auto px-6 py-8">
        {/* Welcome Section */}
        <div className="mb-8">
          <p className="text-gray-600 text-sm">Welcome back,</p>
          <h2 className="text-3xl font-light text-gray-900 mt-1">
            {user?.name || user?.email}
          </h2>
        </div>

        {/* Dashboard Stats */}
        {isLoadingDashboard ? (
          <div className="grid grid-cols-1 md:grid-cols-3 gap-4 mb-8">
            {[1, 2, 3].map((i) => (
              <div key={i} className="bg-white rounded-lg border border-gray-200 p-6 animate-pulse">
                <div className="h-4 bg-gray-200 rounded w-24 mb-2"></div>
                <div className="h-8 bg-gray-200 rounded w-32"></div>
              </div>
            ))}
          </div>
        ) : (
          <div className="grid grid-cols-1 md:grid-cols-3 gap-4 mb-8">
            {/* Income Card */}
            <div className="bg-white rounded-lg border border-green-200 p-6 hover:shadow-lg transition">
              <p className="text-xs font-medium text-gray-500 uppercase tracking-wide">
                Total Income
              </p>
              <p className="text-green-600 text-3xl font-semibold mt-2">
                ₹{dashboardData.totalIncome.toFixed(2)}
              </p>
            </div>

            {/* Expense Card */}
            <div className="bg-white rounded-lg border border-red-200 p-6 hover:shadow-lg transition">
              <p className="text-xs font-medium text-gray-500 uppercase tracking-wide">
                Total Expense
              </p>
              <p className="text-red-600 text-3xl font-semibold mt-2">
                ₹{dashboardData.totalExpense.toFixed(2)}
              </p>
            </div>

            {/* Balance Card */}
            <div className="bg-white rounded-lg border border-blue-200 p-6 hover:shadow-lg transition">
              <p className="text-xs font-medium text-gray-500 uppercase tracking-wide">
                Balance
              </p>
              <p className={`text-3xl font-semibold mt-2 ${dashboardData.balance >= 0 ? 'text-blue-600' : 'text-red-600'}`}>
                ₹{dashboardData.balance.toFixed(2)}
              </p>
            </div>
          </div>
        )}

        {/* Account Info */}
        <div data-section="account-info" className="bg-white rounded-lg border border-gray-200 p-6">
          <h3 className="text-lg font-semibold text-gray-900 mb-6">Account Information</h3>
          
          {/* Profile Info Row */}
          <div className="grid grid-cols-1 md:grid-cols-2 gap-6 mb-6 pb-6 border-b border-gray-100">
            {/* Email */}
            <div>
              <p className="text-xs font-medium text-gray-500 uppercase tracking-wide mb-2">Email Address</p>
              <p className="text-gray-900 text-sm font-medium">{user?.email}</p>
            </div>

            {/* Name */}
            <div>
              <p className="text-xs font-medium text-gray-500 uppercase tracking-wide mb-2">Full Name</p>
              <p className="text-gray-900 text-sm font-medium">{user?.name || 'N/A'}</p>
            </div>
          </div>

          {/* Verification Status Card */}
          <div className="bg-gradient-to-br from-blue-50 to-indigo-50 rounded-lg border border-blue-100 p-6">
            <div className="flex items-start gap-4">
              {/* Status Indicator */}
              <div className="flex-shrink-0">
                <div className={`w-12 h-12 rounded-full flex items-center justify-center ${user?.isAccountVerified ? 'bg-green-100' : 'bg-yellow-100'}`}>
                  <div className={`w-3 h-3 rounded-full ${user?.isAccountVerified ? 'bg-green-500' : 'bg-yellow-500'}`}></div>
                </div>
              </div>

              {/* Status Info */}
              <div className="flex-grow">
                <p className="text-xs font-medium text-gray-500 uppercase tracking-wide mb-1">Email Verification</p>
                <p className={`text-sm font-semibold ${user?.isAccountVerified ? 'text-green-700' : 'text-yellow-700'}`}>
                  {user?.isAccountVerified ? '✓ Verified' : '⚠ Pending Verification'}
                </p>
                <p className="text-xs text-gray-600 mt-2">
                  {user?.isAccountVerified 
                    ? 'Your email has been successfully verified.' 
                    : 'Please verify your email to unlock all features and better security.'}
                </p>
              </div>

              {/* Verify Button */}
              {!user?.isAccountVerified && (
                <div className="flex-shrink-0">
                  <button
                    onClick={openVerifyModal}
                    className="px-4 py-2 bg-blue-600 text-white text-xs font-medium rounded-lg hover:bg-blue-700 transition whitespace-nowrap"
                  >
                    Verify Now
                  </button>
                </div>
              )}
              {user?.isAccountVerified && (
                <div className="flex-shrink-0">
                  <div className="px-4 py-2 bg-green-100 text-green-700 text-xs font-medium rounded-lg">
                    ✓ Verified
                  </div>
                </div>
              )}
            </div>
          </div>
        </div>
      </div>

      {/* Email Verification Modal */}
      {showVerifyModal && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
          <div className="bg-white rounded-lg shadow-lg p-6 w-96">
            <h3 className="text-lg font-semibold text-gray-900 mb-4">
              Verify Your Email
            </h3>

            {!otpSent ? (
              <div>
                <p className="text-sm text-gray-600 mb-4">
                  We'll send an OTP to verify your email address.
                </p>
                <button
                  onClick={handleSendOtp}
                  disabled={sendingOtp}
                  className="w-full bg-blue-600 text-white py-2 rounded-lg hover:bg-blue-700 transition disabled:bg-gray-400 text-sm font-medium"
                >
                  {sendingOtp ? "Sending..." : "Send OTP"}
                </button>
              </div>
            ) : (
              <form onSubmit={handleVerifyOtp}>
                <div className="mb-4">
                  <label className="block text-sm font-medium text-gray-700 mb-2">
                    Enter OTP
                  </label>
                  <input
                    type="text"
                    value={otp}
                    onChange={(e) => setOtp(e.target.value)}
                    placeholder="Enter 6-digit OTP"
                    className="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 outline-none text-sm"
                  />
                </div>

                <button
                  type="submit"
                  disabled={verifying}
                  className="w-full bg-blue-600 text-white py-2 rounded-lg hover:bg-blue-700 transition disabled:bg-gray-400 text-sm font-medium mb-2"
                >
                  {verifying ? "Verifying..." : "Verify"}
                </button>

                <button
                  type="button"
                  onClick={() => {
                    setOtpSent(false)
                    setOtp('')
                  }}
                  className="w-full text-sm text-gray-600 hover:text-gray-900 py-2"
                >
                  Edit email
                </button>
              </form>
            )}

            <button
              onClick={() => {
                setShowVerifyModal(false)
                setOtp('')
                setOtpSent(false)
              }}
              className="w-full text-sm text-gray-600 hover:text-gray-900 py-2 mt-4 border-t border-gray-200 pt-4"
            >
              Cancel
            </button>
          </div>
        </div>
      )}
    </div>
  )
}

export default Dashboard;