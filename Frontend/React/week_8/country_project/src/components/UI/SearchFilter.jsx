export const SearchFilter = ({ search, setSearch, filter, setFilter, countries, setCountries }) => {

    const handleInputChange = (event) => {
        event.preventDefault();
        setSearch(event.target.value);
    };

    const handleSelectChange = (event) => {
        event.preventDefault();
        setFilter(event.target.value);
    };

    const sortCountries = (value) => {
        const sortCountry = [...countries].sort((a, b) => {
            return value === "asc" ? a.name.common.localeCompare(b.name.common) : b.name.common.localeCompare(a.name.common);
        });
        setCountries(sortCountry);
    };

    return (
        <section className="flex flex-wrap justify-between items-center
                    w-full px-4 sm:px-6 md:px-12 lg:px-20 py-6
                    bg-gradient-to-br from-gray-950 via-gray-900 to-black
                    backdrop-blur-xl border border-white/10
                    shadow-2xl shadow-black/50 gap-4">

            {/* Left: Search */}
            <div className="flex-1 min-w-[200px]">
                <input
                    type="text"
                    placeholder="Search for a country..."
                    value={search}
                    onChange={handleInputChange}
                    className="w-full px-4 py-2 rounded-lg
                     bg-black/30 backdrop-blur-sm border border-white/20 text-white placeholder-gray-400
                     focus:outline-none focus:ring-2 focus:ring-indigo-500
                     transition duration-300"
                />
            </div>

            {/* Middle Space for future buttons */}
            {/* Middle Space for future buttons */}
            <div className="flex-1 flex justify-center items-center gap-4 min-w-[150px]">
                <button
                    onClick={() => sortCountries("asc")}
                    className="px-4 py-2 rounded-lg
               bg-black/30 backdrop-blur-sm border border-white/20
               text-white font-semibold
               hover:bg-black/50 hover:scale-105
               transition duration-300 shadow-md shadow-black/40"
                >
                    Asc
                </button>

                <button
                    onClick={() => sortCountries("des")}
                    className="px-4 py-2 rounded-lg
               bg-black/30 backdrop-blur-sm border border-white/20
               text-white font-semibold
               hover:bg-black/50 hover:scale-105
               transition duration-300 shadow-md shadow-black/40"
                >
                    Desc
                </button>
            </div>

            {/* Right: Filter */}
            <div className="flex-1 min-w-[200px] flex justify-end">
                <select
                    value={filter}
                    onChange={handleSelectChange}
                    className="px-4 py-3 rounded-lg
             bg-black/30 backdrop-blur-sm border border-white/20
             text-white font-semibold
             focus:outline-none focus:ring-2 focus:ring-indigo-500
             transition duration-300"
                >
                    <option value="all">All</option>
                    <option value="Africa">Africa</option>
                    <option value="Americas">Americas</option>
                    <option value="Asia">Asia</option>
                    <option value="Europe">Europe</option>
                    <option value="Oceania">Oceania</option>
                </select>
            </div>

        </section>
    );
};