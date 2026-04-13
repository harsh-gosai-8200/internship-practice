import { useEffect, useState, useTransition } from "react";
import { getCountryData } from "../api/postApi";
import { Loader } from "../components/UI/Loader";
import { CountryCard } from "../components/Layout/CountryCard";
import { SearchFilter } from "../components/UI/SearchFilter";


export const Country = () => {

    const [countries, setCountries] = useState([]);
    const [isPending, startTransition] = useTransition();
    const [search, setSearch] = useState();
    const [filter, setFilter] = useState("all");

    useEffect(() => {
        startTransition(async () => {
            const res = await getCountryData();
            setCountries(res.data);
        });
    }, []);

    if (isPending) return <Loader />;

    const searchCountry = (country) => {
        if(search){
            return country.name.common.toLowerCase().includes(search.toLowerCase())
        }
        return country;
    }

    const filterRegion = (country) => {
        if(filter==="all") return country;
        return country.region === filter;
    }

    const filteredCountry = countries.filter((country) => searchCountry(country) && filterRegion(country));

    return (
        <section>
            <SearchFilter search={search} setSearch={setSearch} filter={filter} setFilter={setFilter} countries={countries} setCountries={setCountries} />
            <div className="min-h-screen bg-gradient-to-br from-gray-950 via-gray-900 to-black py-14">
                <div className="max-w-[1400px] mx-auto px-[5%]">
                    <ul className="grid gap-8
                   grid-cols-1
                   sm:grid-cols-2
                   md:grid-cols-3
                   lg:grid-cols-4
                   xl:grid-cols-5">
                        {filteredCountry.map((country) => (
                            <CountryCard key={country.name.common} country={country} />
                        ))}
                    </ul>
                </div>
            </div>
        </section>
    )
};
