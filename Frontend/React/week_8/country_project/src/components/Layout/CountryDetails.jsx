import { useEffect, useState, useTransition } from "react";
import { NavLink, useParams } from "react-router-dom";
import { getCountryIndData } from "../../api/postApi";
import { Loader } from "../UI/Loader";

export const CountryDetails = () => {
  const params = useParams();

  const [country, setCountry] = useState();
  const [isPending, startTransition] = useTransition();
  const [loading, setLoading] = useState(true);

  useEffect(() => { 
    const fetchData = async () => { 
      try { 
        const res = await getCountryIndData(params.id); 
        console.log(res)
        if (res.status === 200) { 
          setCountry(res.data[0]); 
        } 
      } finally { 
        setLoading(false); 
      } 
    }; 
    fetchData(); 
  }, [params.id]);

  if (loading) return <Loader />;

  return (
    <section className="min-h-screen bg-gradient-to-br from-gray-950 via-gray-900 to-black py-16">
      <div className="max-w-6xl mx-auto px-6">

        {/* Glass Card */}
        <div className="container-card
                        bg-white/10 backdrop-blur-xl
                        border border-white/20
                        rounded-3xl p-10
                        shadow-2xl shadow-black/50
                        hover:shadow-indigo-500/30
                        transition duration-500">

          {country && (
            <div className="grid md:grid-cols-2 gap-12 items-center">

              {/* Flag */}
              <div className="overflow-hidden rounded-2xl">
                <img
                  src={country.flags.svg}
                  alt={country.flags.alt}
                  className="w-full h-auto object-cover
                             transition duration-500 hover:scale-105"
                />
              </div>

              {/* Country Details */}
              <div className="text-gray-200 space-y-3">

                {/* Country Name */}
                <p className="text-3xl font-bold text-white mb-6">
                  {country.name.official}
                </p>

                {/* Info */}
                <div className="space-y-2">
                  <p>
                    <span className="text-indigo-400 font-semibold">
                      Native Names:
                    </span>{" "}
                    {Object.keys(country.name.nativeName)
                      .map((key) => country.name.nativeName[key].common)
                      .join(", ")}
                  </p>

                  <p>
                    <span className="text-indigo-400 font-semibold">
                      Population:
                    </span>{" "}
                    {country.population.toLocaleString()}
                  </p>

                  <p>
                    <span className="text-indigo-400 font-semibold">
                      Region:
                    </span>{" "}
                    {country.region}
                  </p>

                  <p>
                    <span className="text-indigo-400 font-semibold">
                      Sub Region:
                    </span>{" "}
                    {country.subregion}
                  </p>

                  <p>
                    <span className="text-indigo-400 font-semibold">
                      Capital:
                    </span>{" "}
                    {country.capital}
                  </p>

                  <p>
                    <span className="text-indigo-400 font-semibold">
                      Top Level Domain:
                    </span>{" "}
                    {country.tld[0]}
                  </p>

                  <p>
                    <span className="text-indigo-400 font-semibold">
                      Currencies:
                    </span>{" "}
                    {country.currencies &&
                      Object.values(country.currencies)
                        .map((cur) => `${cur.name} (${cur.symbol})`)
                        .join(", ")}
                  </p>

                  <p>
                    <span className="text-indigo-400 font-semibold">
                      Languages:
                    </span>{" "}
                    {Object.values(country.languages).join(", ")}
                  </p>
                </div>
              </div>
            </div>
          )}

          {/* Back Button */}
          <div className="mt-12 text-center">
            <NavLink to="/country">
              <button className="px-6 py-2 rounded-lg
                                 bg-indigo-600 hover:bg-indigo-500
                                 text-white font-semibold
                                 transition duration-300
                                 shadow-lg hover:shadow-indigo-500/40">
                Go Back
              </button>
            </NavLink>
          </div>

        </div>
      </div>
    </section>
  );
};