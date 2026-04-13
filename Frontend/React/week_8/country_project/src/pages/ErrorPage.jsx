import { useRouteError, NavLink } from "react-router-dom";


export const ErrorPage = () => {

    const error = useRouteError()
    return (
    <div className="min-h-screen flex items-center justify-center bg-gradient-to-br from-indigo-100 via-purple-100 to-pink-100 px-4">
      <div className="bg-white shadow-2xl rounded-2xl p-10 max-w-lg w-full text-center border border-gray-100">
        
        <h1 className="text-4xl font-extrabold text-gray-800 mb-4">
          Oops! Something went wrong
        </h1>

        {error && (
          <p className="text-gray-600 bg-red-50 border border-red-200 rounded-lg p-4 mb-6">
            {error.data || "An unexpected error occurred."}
          </p>
        )}

        <NavLink to="/">
          <button className="bg-indigo-600 hover:bg-indigo-700 text-white font-semibold px-6 py-3 rounded-lg shadow-md hover:shadow-lg transition-all duration-300 transform hover:-translate-y-1">
            Go Home
          </button>
        </NavLink>

      </div>
    </div>
  )
}