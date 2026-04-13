import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { useAuth } from '../context/AuthContext'
import { toast } from 'react-toastify'

const Dashboard = () => {
  const navigate = useNavigate()
  const { user, isAuthenticated, loading, logout, sendOtp, verifyOtp } = useAuth()
  const [showVerifyModal, setShowVerifyModal] = useState(false)
  const [otp, setOtp] = useState('')
  const [verifying, setVerifying] = useState(false)
  const [otpSent, setOtpSent] = useState(false)
  const [sendingOtp, setSendingOtp] = useState(false)
  const [isLoggingOut, setIsLoggingOut] = useState(false)

  useEffect(() => {
    if (!loading && !isAuthenticated && !isLoggingOut) {
      toast.error("Please login first")
      navigate("/")
    }
  }, [isAuthenticated, loading, navigate, isLoggingOut])

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
      {/* Header */}
      <div className="bg-white border-b border-gray-200">
        <div className="max-w-6xl mx-auto px-6 py-4 flex justify-between items-center">
          <div>
            <h1 className="text-xl font-semibold text-gray-900">Dashboard</h1>
          </div>
          <div className="flex gap-2">
            {user?.isAccountVerified ? (
              <div className="flex items-center gap-2 px-4 py-2 text-green-600 bg-green-50 rounded-lg border border-green-200">
                <span className="text-lg">✓</span>
                <span className="text-sm font-medium">Email Verified</span>
              </div>
            ) : (
              <button
                onClick={openVerifyModal}
                className="text-sm px-4 py-2 text-blue-600 hover:bg-blue-50 rounded-lg transition border border-blue-200"
              >
                Verify Email
              </button>
            )}
            <button
              onClick={handleLogout}
              className="text-sm px-4 py-2 text-red-600 hover:bg-red-50 rounded-lg transition"
            >
              Logout
            </button>
          </div>
        </div>
      </div>

      {/* Main Content */}
      <div className="max-w-6xl mx-auto px-6 py-8">
        {/* Welcome Section */}
        <div className="mb-8">
          <p className="text-gray-600 text-sm">Welcome back,</p>
          <h2 className="text-3xl font-light text-gray-900 mt-1">
            {user?.name || user?.email}
          </h2>
        </div>

        {/* Stats Grid */}
        <div className="grid grid-cols-1 md:grid-cols-3 gap-4 mb-8">
          {/* Card 1 */}
          <div className="bg-white rounded-lg border border-gray-200 p-6">
            <p className="text-xs font-medium text-gray-500 uppercase tracking-wide">
              Email
            </p>
            <p className="text-gray-900 text-sm mt-2">{user?.email}</p>
          </div>

          {/* Card 2 */}
          <div className="bg-white rounded-lg border border-gray-200 p-6">
            <p className="text-xs font-medium text-gray-500 uppercase tracking-wide">
              Email Status
            </p>
            <div className="flex items-center gap-2 mt-2">
              <div className={`w-2 h-2 rounded-full ${user?.isAccountVerified ? 'bg-green-500' : 'bg-yellow-500'}`}></div>
              <p className={`text-sm ${user?.isAccountVerified ? 'text-green-600' : 'text-yellow-600'}`}>
                {user?.isAccountVerified ? 'Verified' : 'Pending'}
              </p>
            </div>
          </div>

          {/* Card 3 */}
          <div className="bg-white rounded-lg border border-gray-200 p-6">
            <p className="text-xs font-medium text-gray-500 uppercase tracking-wide">
              Account
            </p>
            <p className="text-gray-900 text-sm mt-2">Active</p>
          </div>
        </div>

        {/* Actions */}
        <div className="bg-white rounded-lg border border-gray-200 p-6">
          <p className="text-xs font-medium text-gray-500 uppercase tracking-wide mb-4">
            Quick Actions
          </p>
          <div className="flex gap-3">
            <p className="text-sm text-gray-600">No additional actions available</p>
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