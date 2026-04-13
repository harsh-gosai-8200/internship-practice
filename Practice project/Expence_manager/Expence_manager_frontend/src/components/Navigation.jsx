import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { useAuth } from '../context/AuthContext'
import { toast } from 'react-toastify'

const Navigation = ({ currentPage, onVerifyEmail }) => {
  const navigate = useNavigate()
  const { user, logout } = useAuth()
  const [showDropdown, setShowDropdown] = useState(false)

  const handleLogout = () => {
    logout()
    toast.success("Logged out successfully!")
    navigate("/")
    setShowDropdown(false)
  }

  const toggleDropdown = () => {
    setShowDropdown(!showDropdown)
  }

  const isActive = (page) => {
    return currentPage === page ? 'text-blue-600 border-b-2 border-blue-600' : 'text-gray-600 hover:text-gray-900'
  }

  return (
    <nav className="bg-white border-b border-gray-200 sticky top-0 z-50 shadow-sm">
      <div className="max-w-7xl mx-auto px-6 py-4">
        <div className="flex justify-between items-center">
          {/* Logo/Brand */}
          <div className="flex items-center gap-2">
            <div className="w-8 h-8 bg-blue-600 rounded-lg flex items-center justify-center">
              <span className="text-white font-bold text-sm">EM</span>
            </div>
            <button
              onClick={() => navigate('/dashboard')}
              className="text-xl font-semibold text-gray-900 hover:text-blue-600 transition"
            >
              Expense Manager
            </button>
          </div>

          {/* Navigation Links */}
          <div className="flex items-center gap-8">
            <button
              onClick={() => navigate('/dashboard')}
              className={`text-sm font-medium pb-2 transition ${isActive('dashboard')}`}
            >
              Dashboard
            </button>
            <button
              onClick={() => navigate('/transactions')}
              className={`text-sm font-medium pb-2 transition ${isActive('transactions')}`}
            >
              Transactions
            </button>
          </div>

          {/* User Dropdown */}
          <div className="relative">
            <button
              onClick={toggleDropdown}
              className="flex items-center gap-2 px-4 py-2 rounded-lg hover:bg-gray-100 transition"
            >
              <div className="w-8 h-8 bg-blue-100 rounded-full flex items-center justify-center">
                <span className="text-blue-600 font-semibold text-sm">
                  {user?.name?.charAt(0) || user?.email?.charAt(0) || 'U'}
                </span>
              </div>
              <div className="text-left">
                <p className="text-xs font-medium text-gray-600">
                  {user?.name || 'User'}
                </p>
                <p className="text-xs text-gray-500">
                  {user?.email}
                </p>
              </div>
              <svg
                className={`w-4 h-4 text-gray-600 transition ${showDropdown ? 'rotate-180' : ''}`}
                fill="none"
                stroke="currentColor"
                viewBox="0 0 24 24"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  strokeWidth={2}
                  d="M19 14l-7 7m0 0l-7-7m7 7V3"
                />
              </svg>
            </button>

            {/* Dropdown Menu */}
            {showDropdown && (
              <div className="absolute right-0 top-16 w-56 bg-white rounded-lg border border-gray-200 shadow-lg py-2 z-50">
                {/* Profile Section */}
                <div className="px-4 py-3 border-b border-gray-100">
                  <p className="text-xs font-medium text-gray-500 uppercase tracking-wide">Account</p>
                  <p className="text-sm font-medium text-gray-900 mt-1">{user?.name || 'N/A'}</p>
                  <p className="text-xs text-gray-600 mt-1">{user?.email}</p>
                </div>

                {/* Verification Status */}
                <div className="px-4 py-3 border-b border-gray-100">
                  <div className="flex items-center gap-2">
                    <div className={`w-2 h-2 rounded-full ${user?.isAccountVerified ? 'bg-green-500' : 'bg-yellow-500'}`}></div>
                    <span className={`text-xs font-medium ${user?.isAccountVerified ? 'text-green-600' : 'text-yellow-600'}`}>
                      {user?.isAccountVerified ? 'Email Verified' : 'Email Pending'}
                    </span>
                  </div>
                </div>

                {/* Actions */}
                <button
                  onClick={() => {
                    navigate('/dashboard')
                    setShowDropdown(false)
                    // Scroll to account info section
                    setTimeout(() => {
                      document.querySelector('[data-section="account-info"]')?.scrollIntoView({ behavior: 'smooth' })
                    }, 100)
                  }}
                  className="w-full text-left px-4 py-2 text-sm text-gray-700 hover:bg-gray-50 transition"
                >
                  View Profile
                </button>

                {!user?.isAccountVerified && (
                  <button
                    onClick={() => {
                      setShowDropdown(false)
                      if (onVerifyEmail) {
                        onVerifyEmail()
                      }
                    }}
                    className="w-full text-left px-4 py-2 text-sm text-blue-600 hover:bg-blue-50 transition font-medium"
                  >
                    Verify Email
                  </button>
                )}

                {/* Logout */}
                <div className="border-t border-gray-100 pt-2">
                  <button
                    onClick={handleLogout}
                    className="w-full text-left px-4 py-2 text-sm text-red-600 hover:bg-red-50 transition font-medium"
                  >
                    Logout
                  </button>
                </div>
              </div>
            )}
          </div>
        </div>
      </div>
    </nav>
  )
}

export default Navigation
