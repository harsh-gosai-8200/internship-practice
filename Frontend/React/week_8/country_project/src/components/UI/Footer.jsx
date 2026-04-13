import FooterContact from "../../api/footerApi.json";
import * as FaIcons from "react-icons/fa";
import { NavLink } from "react-router-dom";

export const Footer = () => {
  return (
    <footer className="bg-black text-white py-12">
      {/* Outer Glass Container */}
      <div className="max-w-6xl mx-auto px-16 bg-white/10 backdrop-blur-md border-[0.5px] border-white/20 rounded-2xl p-8">

        {/* Grid for footer items */}
        <div className="grid sm:grid-cols-2 md:grid-cols-3 gap-8 mb-8">
          {FooterContact.map((curData) => {
            const { id, icon, title, details } = curData;
            const IconComponent = FaIcons[icon];

            return (
              <div key={id} className="flex items-start gap-4">
                <div className="text-indigo-400 text-2xl mt-1">
                  {IconComponent && <IconComponent />}
                </div>
                <div className="flex flex-col">
                  <p className="font-semibold text-indigo-300">{title}</p>
                  <p className="text-gray-400">{details}</p>
                </div>
              </div>
            );
          })}
        </div>

        {/* Footer Navigation Links */}
        <div className="flex justify-center gap-8 mb-6 flex-wrap">
          <NavLink to="/" className="text-gray-400 hover:text-indigo-400 transition-colors">
            Home
          </NavLink>
          <NavLink to="/about" className="text-gray-400 hover:text-indigo-400 transition-colors">
            About
          </NavLink>
          <NavLink to="/country" className="text-gray-400 hover:text-indigo-400 transition-colors">
            Country
          </NavLink>
          <NavLink to="/contact" className="text-gray-400 hover:text-indigo-400 transition-colors">
            Contact
          </NavLink>
        </div>

        {/* Copyright */}
        <p className="text-center text-gray-500 text-sm">
          &copy; {new Date().getFullYear()} WorldAtlas. All rights reserved.
        </p>
      </div>
    </footer>
  );
};