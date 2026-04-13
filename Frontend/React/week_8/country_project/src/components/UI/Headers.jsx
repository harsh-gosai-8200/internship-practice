import { NavLink } from "react-router-dom";

export const Headers = () => {
  return (
    <header className="bg-black text-white py-6 border-b border-gray-800">
      <div className="max-w-5xl mx-auto px-24 flex items-center justify-between">
        <NavLink to="/">
          <h1 className="text-2xl font-bold tracking-wide hover:text-indigo-500 transition duration-300">
            WorldAtlas
          </h1>
        </NavLink>
        <nav>
          <ul className="flex items-center gap-8 text-base font-medium">
            <li>
              <NavLink
                to="/"
                className={({ isActive }) =>
                  `transition duration-300 ${
                    isActive
                      ? "text-indigo-500"
                      : "text-gray-400 hover:text-indigo-500"
                  }`
                }
              >
                Home
              </NavLink>
            </li>
            <li>
              <NavLink
                to="/about"
                className={({ isActive }) =>
                  `transition duration-300 ${
                    isActive
                      ? "text-indigo-500"
                      : "text-gray-400 hover:text-indigo-500"
                  }`
                }
              >
                About
              </NavLink>
            </li>
            <li>
              <NavLink
                to="/country"
                className={({ isActive }) =>
                  `transition duration-300 ${
                    isActive
                      ? "text-indigo-500"
                      : "text-gray-400 hover:text-indigo-500"
                  }`
                }
              >
                Country
              </NavLink>
            </li>
            <li>
              <NavLink
                to="/contact"
                className={({ isActive }) =>
                  `transition duration-300 ${
                    isActive
                      ? "text-indigo-500"
                      : "text-gray-400 hover:text-indigo-500"
                  }`
                }
              >
                Contact
              </NavLink>
            </li>
          </ul>
        </nav>
      </div>
    </header>
  );
};