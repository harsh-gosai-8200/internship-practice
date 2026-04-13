import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { useAuth } from '../context/AuthContext'
import Navigation from '../components/Navigation'
import { transactionAPI } from '../api'
import { toast } from 'react-toastify'

// Reusable Components
const InputField = ({ label, name, value, onChange, type = 'text', placeholder, step }) => (
  <div>
    <label className="block text-sm font-medium text-gray-700 mb-1">{label}</label>
    <input
      type={type}
      name={name}
      value={value}
      onChange={onChange}
      placeholder={placeholder}
      step={step}
      className="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 outline-none text-sm"
    />
  </div>
)

const SelectField = ({ label, name, value, onChange, options }) => (
  <div>
    <label className="block text-sm font-medium text-gray-700 mb-1">{label}</label>
    <select
      name={name}
      value={value}
      onChange={onChange}
      className="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 outline-none text-sm"
    >
      {options.map(opt => (
        <option key={opt.value} value={opt.value}>{opt.label}</option>
      ))}
    </select>
  </div>
)

const TransactionRow = ({ transaction, onEdit, onDelete }) => (
  <tr className="border-b border-gray-100 hover:bg-gray-50">
    <td className="py-3 px-4 text-gray-900">{transaction.title}</td>
    <td className="py-3 px-4 text-gray-600">{transaction.categoryName}</td>
    <td className="py-3 px-4">
      <span className={`px-2 py-1 rounded text-xs font-medium ${transaction.type === 'INCOME' ? 'bg-green-100 text-green-700' : 'bg-red-100 text-red-700'}`}>
        {transaction.type}
      </span>
    </td>
    <td className={`py-3 px-4 font-semibold ${transaction.type === 'INCOME' ? 'text-green-600' : 'text-red-600'}`}>
      {transaction.type === 'INCOME' ? '+' : '-'}₹{transaction.amount.toFixed(2)}
    </td>
    <td className="py-3 px-4 text-gray-600">{new Date(transaction.transactionDate).toLocaleDateString('en-IN')}</td>
    <td className="py-3 px-4 text-gray-600 max-w-xs truncate">{transaction.description}</td>
    <td className="py-3 px-4">
      <div className="flex gap-2">
        <button onClick={() => onEdit(transaction)} className="text-blue-600 hover:text-blue-800 text-xs font-medium">Edit</button>
        <button onClick={() => onDelete(transaction.id)} className="text-red-600 hover:text-red-800 text-xs font-medium">Delete</button>
      </div>
    </td>
  </tr>
)

const Transactions = () => {
  const navigate = useNavigate()
  const { isAuthenticated, loading } = useAuth()

  const [transactions, setTransactions] = useState([])
  const [filteredTransactions, setFilteredTransactions] = useState([])
  const [isLoadingTransactions, setIsLoadingTransactions] = useState(false)

  const [showModal, setShowModal] = useState(false)
  const [editingId, setEditingId] = useState(null)
  const [isSubmitting, setIsSubmitting] = useState(false)
  const [formData, setFormData] = useState({
    title: '',
    amount: '',
    description: '',
    categoryName: '',
    type: 'EXPENSE',
    transactionDate: new Date().toISOString().split('T')[0],
  })

  const [filters, setFilters] = useState({
    type: '',
    categoryName: '',
    minAmount: '',
    maxAmount: '',
    startDate: '',
    endDate: '',
  })

  // Redirect if not authenticated
  useEffect(() => {
    if (!loading && !isAuthenticated) {
      toast.error("Please login first")
      navigate("/")
    }
  }, [isAuthenticated, loading, navigate])

  // Load transactions
  useEffect(() => {
    if (isAuthenticated) loadTransactions()
  }, [isAuthenticated])

  // Apply filters whenever transactions or filters change
  useEffect(() => applyFilters(), [transactions, filters])

  const loadTransactions = async () => {
    try {
      setIsLoadingTransactions(true)
      const response = await transactionAPI.getTransactions()
      setTransactions(response.data)
    } catch (error) {
      console.error(error)
      toast.error("Failed to load transactions")
    } finally {
      setIsLoadingTransactions(false)
    }
  }

  const applyFilters = () => {
    let result = [...transactions]

    if (filters.type) result = result.filter(t => t.type === filters.type)
    if (filters.categoryName) result = result.filter(t => t.categoryName.toLowerCase().includes(filters.categoryName.toLowerCase()))
    if (filters.minAmount) result = result.filter(t => t.amount >= parseFloat(filters.minAmount))
    if (filters.maxAmount) result = result.filter(t => t.amount <= parseFloat(filters.maxAmount))
    if (filters.startDate) result = result.filter(t => new Date(t.transactionDate) >= new Date(filters.startDate))
    if (filters.endDate) result = result.filter(t => new Date(t.transactionDate) <= new Date(filters.endDate))

    setFilteredTransactions(result)
  }

  const handleOpenModal = (transaction = null) => {
    if (transaction) {
      setEditingId(transaction.id)
      setFormData({ ...transaction, amount: transaction.amount.toString() })
    } else {
      setEditingId(null)
      setFormData({
        title: '',
        amount: '',
        description: '',
        categoryName: '',
        type: 'EXPENSE',
        transactionDate: new Date().toISOString().split('T')[0],
      })
    }
    setShowModal(true)
  }

  const handleCloseModal = () => setShowModal(false)

  const handleFormChange = e => {
    const { name, value } = e.target
    setFormData(prev => ({ ...prev, [name]: value }))
  }

  const handleFilterChange = e => {
    const { name, value } = e.target
    setFilters(prev => ({ ...prev, [name]: value }))
  }

  const handleSubmitForm = async e => {
    e.preventDefault()
    if (!formData.title.trim() || !formData.categoryName.trim() || !formData.amount || parseFloat(formData.amount) <= 0) {
      toast.error("Please fill all required fields with valid values")
      return
    }

    try {
      setIsSubmitting(true)
      const submitData = { ...formData, amount: parseFloat(formData.amount) }
      if (editingId) {
        await transactionAPI.updateTransaction(editingId, submitData)
        toast.success("Transaction updated successfully")
      } else {
        await transactionAPI.addTransaction(submitData)
        toast.success("Transaction added successfully")
      }
      handleCloseModal()
      loadTransactions()
    } catch (error) {
      toast.error(error.response?.data?.message || "Failed to save transaction")
    } finally {
      setIsSubmitting(false)
    }
  }

  const handleDeleteTransaction = async id => {
    if (!window.confirm("Are you sure you want to delete this transaction?")) return
    try {
      await transactionAPI.deleteTransaction(id)
      toast.success("Transaction deleted successfully")
      loadTransactions()
    } catch (error) {
      toast.error(error.response?.data?.message || "Failed to delete transaction")
    }
  }

  const handleResetFilters = () => setFilters({ type: '', categoryName: '', minAmount: '', maxAmount: '', startDate: '', endDate: '' })

  if (loading) return <div className="min-h-screen flex items-center justify-center bg-gray-50 text-gray-600">Loading...</div>
  if (!isAuthenticated) return null

  // Filter config
  const filterFields = [
    { name: 'type', label: 'Type', type: 'select', options: [{ value: '', label: 'All Types' }, { value: 'INCOME', label: 'Income' }, { value: 'EXPENSE', label: 'Expense' }] },
    { name: 'categoryName', label: 'Category', type: 'text', placeholder: 'Search category...' },
    { name: 'minAmount', label: 'Min Amount', type: 'number', placeholder: 'Min...' },
    { name: 'maxAmount', label: 'Max Amount', type: 'number', placeholder: 'Max...' },
    { name: 'startDate', label: 'Start Date', type: 'date' },
    { name: 'endDate', label: 'End Date', type: 'date' },
  ]

  const formFields = [
    { name: 'title', label: 'Title *', type: 'text', placeholder: 'e.g., Grocery Shopping' },
    { name: 'amount', label: 'Amount *', type: 'number', placeholder: '0.00', step: '0.01' },
    { name: 'type', label: 'Type *', type: 'select', options: [{ value: 'EXPENSE', label: 'Expense' }, { value: 'INCOME', label: 'Income' }] },
    { name: 'categoryName', label: 'Category *', type: 'text', placeholder: 'e.g., Food, Transport' },
    { name: 'transactionDate', label: 'Date *', type: 'date' },
  ]

  return (
    <div className="min-h-screen bg-gray-50">
      <Navigation currentPage="transactions" />

      <div className="max-w-7xl mx-auto px-6 py-8">
        {/* Header */}
        <div className="flex justify-between items-center mb-8">
          <div>
            <h2 className="text-3xl font-light text-gray-900">All Transactions</h2>
            <p className="text-gray-600 text-sm mt-1">Manage your income and expenses</p>
          </div>
          <button onClick={() => handleOpenModal()} className="px-6 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition text-sm font-medium shadow-sm">
            + Add Transaction
          </button>
        </div>

        {/* Filters */}
        <div className="bg-white rounded-lg border border-gray-200 p-6 mb-8">
          <div className="flex justify-between items-center mb-4">
            <h3 className="text-sm font-semibold text-gray-900">Filters</h3>
            <button onClick={handleResetFilters} className="text-xs text-gray-600 hover:text-gray-900 underline">Reset Filters</button>
          </div>
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
            {filterFields.map(field =>
              field.type === 'select' ? (
                <SelectField key={field.name} label={field.label} name={field.name} value={filters[field.name]} onChange={handleFilterChange} options={field.options} />
              ) : (
                <InputField key={field.name} label={field.label} name={field.name} value={filters[field.name]} onChange={handleFilterChange} type={field.type} placeholder={field.placeholder} />
              )
            )}
          </div>
        </div>

        {/* Transactions Table */}
        <div className="bg-white rounded-lg border border-gray-200 p-6">
          <h3 className="text-lg font-semibold text-gray-900 mb-4">Transactions ({filteredTransactions.length})</h3>

          {isLoadingTransactions ? (
            <div className="text-center py-8 text-gray-600">Loading transactions...</div>
          ) : filteredTransactions.length === 0 ? (
            <div className="text-center py-8 text-gray-600">
              {transactions.length === 0 ? "No transactions yet. Create one to get started!" : "No transactions match your filters."}
            </div>
          ) : (
            <div className="overflow-x-auto">
              <table className="w-full text-sm">
                <thead>
                  <tr className="border-b border-gray-200">
                    {['Title','Category','Type','Amount','Date','Description','Actions'].map(h => (
                      <th key={h} className="text-left py-3 px-4 font-semibold text-gray-700">{h}</th>
                    ))}
                  </tr>
                </thead>
                <tbody>
                  {filteredTransactions.map(t => (
                    <TransactionRow key={t.id} transaction={t} onEdit={handleOpenModal} onDelete={handleDeleteTransaction} />
                  ))}
                </tbody>
              </table>
            </div>
          )}
        </div>
      </div>

      {/* Add/Edit Modal */}
      {showModal && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
          <div className="bg-white rounded-lg shadow-lg p-6 w-full max-w-2xl max-h-[90vh] overflow-y-auto">
            <div className="flex justify-between items-center mb-4">
              <h3 className="text-lg font-semibold text-gray-900">{editingId ? 'Edit Transaction' : 'Add New Transaction'}</h3>
              <button onClick={handleCloseModal} className="text-gray-400 hover:text-gray-600 text-2xl">×</button>
            </div>

            <form onSubmit={handleSubmitForm}>
              <div className="grid grid-cols-1 md:grid-cols-2 gap-4 mb-4">
                {formFields.map(field =>
                  field.type === 'select' ? (
                    <SelectField key={field.name} label={field.label} name={field.name} value={formData[field.name]} onChange={handleFormChange} options={field.options} />
                  ) : (
                    <InputField key={field.name} label={field.label} name={field.name} value={formData[field.name]} onChange={handleFormChange} type={field.type} placeholder={field.placeholder} step={field.step} />
                  )
                )}
              </div>

              <div className="mb-6">
                <label className="block text-sm font-medium text-gray-700 mb-1">Description</label>
                <textarea name="description" value={formData.description} onChange={handleFormChange} placeholder="Add notes about this transaction..." rows="3" className="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 outline-none text-sm"></textarea>
              </div>

              <div className="flex gap-3 justify-end border-t border-gray-200 pt-4">
                <button type="button" onClick={handleCloseModal} className="px-4 py-2 text-gray-700 border border-gray-300 rounded-lg hover:bg-gray-50 text-sm font-medium">Cancel</button>
                <button type="submit" disabled={isSubmitting} className="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition disabled:bg-gray-400 text-sm font-medium">{isSubmitting ? 'Saving...' : (editingId ? 'Update' : 'Add')} Transaction</button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  )
}

export default Transactions