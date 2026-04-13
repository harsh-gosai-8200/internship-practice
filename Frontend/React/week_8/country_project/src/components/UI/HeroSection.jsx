import { FaLongArrowAltRight } from "react-icons/fa";
import { NavLink } from "react-router-dom";

export const HeroSection = () => {
  return (
    <main className="bg-black text-white py-4">
      <div className="max-w-5xl mx-auto px-25 w-full grid md:grid-cols-2 gap-4 items-center">
        <div className="space-y-4">
          <h1 className="text-3xl md:text-4xl font-bold leading-snug">
            Explore the World, <span className="text-indigo-500">One Country</span> at a Time.
          </h1>
          <p className="text-gray-400 text-base leading-relaxed">
            Discover the history, culture, and beauty of every nation.
            Search and filter countries to quickly find the details you need.
          </p>
          <NavLink to="/about">
            <button className="group inline-flex items-center gap-3 bg-indigo-600 hover:bg-indigo-700 px-5 py-2.5 rounded-md font-medium transition-all duration-300">
            Start Exploring
            <FaLongArrowAltRight className="group-hover:translate-x-1 transition-transform duration-300" />
          </button>
          </NavLink>
        </div>
        <div className="flex justify-center md:justify-end">
          <img
            src="/imag/world.jpg"
            alt="world"
            className="w-full max-w-sm rounded-xl shadow-lg"
          />
        </div>

      </div>
    </main>
  );
};