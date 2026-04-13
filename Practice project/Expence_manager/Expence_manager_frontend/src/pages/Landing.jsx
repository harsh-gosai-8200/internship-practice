import React from 'react'
import { Link } from 'react-router-dom'

// Reusable Action Card Component
const ActionCard = ({ to, color, title, description, icon: Icon, buttonText }) => (
  <Link to={to} className="group">
    <div className={`bg-white rounded-lg border border-gray-200 p-8 hover:border-${color}-300 hover:shadow-md transition`}>
      <div className="mb-6">
        <div className={`w-12 h-12 bg-${color}-100 rounded-lg flex items-center justify-center`}>
          {Icon}
        </div>
      </div>
      <h3 className="text-lg font-semibold text-gray-900 mb-2">{title}</h3>
      <p className="text-gray-600 text-sm mb-6">{description}</p>
      <button className={`w-full px-4 py-2 text-${color}-600 font-medium hover:bg-${color}-50 rounded-lg transition`}>
        {buttonText} →
      </button>
    </div>
  </Link>
)

// Reusable Feature Card Component
const FeatureCard = ({ label, description }) => (
  <div className="bg-white rounded-lg border border-gray-200 p-6">
    <p className="text-xs font-medium text-gray-500 uppercase tracking-wide mb-3">
      {label}
    </p>
    <p className="text-gray-700 text-sm">{description}</p>
  </div>
)

// SVG Icons
const LoginIcon = (
  <svg className="w-6 h-6 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M11 16l-4-4m0 0l4-4m-4 4h14m-5 4v2a2 2 0 01-2 2H7a2 2 0 01-2-2v-2m14-4V7a2 2 0 00-2-2H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2z" />
  </svg>
)

const RegisterIcon = (
  <svg className="w-6 h-6 text-green-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M18 9v3m0 0v3m0-3h3m-3 0h-3m-2-5a4 4 0 11-8 0 4 4 0 018 0zM3 20a6 6 0 0112 0v1H3v-1z" />
  </svg>
)

const Landing = () => {
  const actionCards = [
    {
      to: '/login',
      color: 'blue',
      title: 'Sign In',
      description: 'Access your account with your email and password',
      icon: LoginIcon,
      buttonText: 'Login'
    },
    {
      to: '/register',
      color: 'green',
      title: 'Create Account',
      description: 'Join us by creating your new account in seconds',
      icon: RegisterIcon,
      buttonText: 'Register'
    }
  ]

  const features = [
    {
      label: 'Secure',
      description: 'Your data is protected with industry-standard encryption and security measures'
    },
    {
      label: 'Fast',
      description: 'Quick registration and login process. Access your account instantly'
    },
    {
      label: 'Reliable',
      description: 'Built with modern technology for a dependable user experience'
    }
  ]

  return (
    <div className="min-h-screen bg-gray-50">
      {/* Header */}
      <div className="bg-white border-b border-gray-200">
        <div className="max-w-6xl mx-auto px-6 py-4">
          <h1 className="text-xl font-semibold text-gray-900">Auth Project</h1>
        </div>
      </div>

      {/* Hero Section */}
      <div className="max-w-6xl mx-auto px-6 py-20">
        <div className="text-center mb-16">
          <h2 className="text-5xl font-light text-gray-900 mb-4">
            Welcome to our platform
          </h2>
          <p className="text-gray-600 text-lg max-w-2xl mx-auto">
            A secure and simple authentication system. Sign up to get started or log in to your account.
          </p>
        </div>

        {/* Action Cards */}
        <div className="grid grid-cols-1 md:grid-cols-2 gap-8 max-w-2xl mx-auto">
          {actionCards.map((card) => (
            <ActionCard key={card.to} {...card} />
          ))}
        </div>

        {/* Features Section */}
        <div className="mt-20 grid grid-cols-1 md:grid-cols-3 gap-8">
          {features.map((feature, index) => (
            <FeatureCard key={index} {...feature} />
          ))}
        </div>
      </div>

      {/* Footer */}
      <div className="border-t border-gray-200 mt-20">
        <div className="max-w-6xl mx-auto px-6 py-8">
          <p className="text-gray-600 text-sm text-center">
            © 2026 Auth Project. All rights reserved.
          </p>
        </div>
      </div>
    </div>
  )
}

export default Landing
