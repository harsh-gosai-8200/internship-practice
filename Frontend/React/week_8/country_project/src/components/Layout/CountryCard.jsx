import { NavLink } from "react-router-dom";

export const CountryCard = ({ country }) => {
  const { flags, name, population, region, capital } = country;

  return (
    <li className="list-none">
      <div className="group relative rounded-2xl 
                      bg-white/10 
                      backdrop-blur-xl 
                      border border-white/20 
                      shadow-lg shadow-black/40
                      hover:shadow-indigo-500/30
                      transition-all duration-500 
                      hover:-translate-y-2 hover:scale-[1.03]
                      overflow-hidden">

        {/* Flag */}
        <div className="h-40 overflow-hidden">
          <img
            src={flags.svg}
            alt={flags.alt}
            className="w-full h-full object-cover transition-transform duration-700 group-hover:scale-110"
          />
        </div>

        {/* Content */}
        <div className="p-5 text-gray-200">
          <h2 className="text-lg font-bold text-white mb-3">
            {name.common.length>10 ? name.common.slice(0,15) + "..." : name.common}
          </h2>

          <p className="text-sm mb-1">
            <span className="text-indigo-400 font-semibold">
              Population:
            </span>{" "}
            {population.toLocaleString()}
          </p>

          <p className="text-sm mb-1">
            <span className="text-indigo-400 font-semibold">
              Region:
            </span>{" "}
            {region}
          </p>

          <p className="text-sm mb-4">
            <span className="text-indigo-400 font-semibold">
              Capital:
            </span>{" "}
            {capital?.[0]}
          </p>

          <NavLink to={`/country/${name.common}`}>
            <button className="w-full py-2 rounded-lg 
                               bg-indigo-600/80 
                               hover:bg-indigo-500
                               backdrop-blur-md
                               text-white text-sm font-semibold
                               transition-all duration-300">
              Read More
            </button>
          </NavLink>
        </div>
      </div>
    </li>
  );
};