import countryFacts from "../api/counteryDat.json";

export const About = () => {
  return (
    <section className="bg-black text-white py-12">
      <div className="max-w-6xl mx-auto px-16">
        <h2 className="text-3xl md:text-4xl font-bold mb-16 text-center">
          Here are the Interesting Facts <br /> we're proud of
        </h2>
        <div className="grid sm:grid-cols-2 lg:grid-cols-3 gap-8">
          {countryFacts.map((country) => {
            const { id, countryName, capital, population, interestingFact } = country;

            return (
              <div
                key={id}
                className="relative overflow-hidden rounded-2xl shadow-2xl hover:scale-105 transform transition duration-500 cursor-pointer group"
              >
                <div className="relative bg-green/10 backdrop-blur-md border-[0.5px] border-green/20 rounded-2xl p-6 flex flex-col h-full justify-between shadow-lg">
                  <p className="text-xl font-bold text-indigo-400 mb-4">{countryName}</p>
                  <div className="space-y-2 text-sm">
                    <p>
                      <span className="text-indigo-300 font-semibold">Capital: </span>
                      <span className="text-gray-200">{capital}</span>
                    </p>
                    <p>
                      <span className="text-indigo-300 font-semibold">Population: </span>
                      <span className="text-gray-200">{population.toLocaleString()}</span>
                    </p>
                    <p>
                      <span className="text-indigo-300 font-semibold">Interesting Fact: </span>
                      <span className="text-gray-200">{interestingFact}</span>
                    </p>
                  </div>
                  </div>
              </div>
            );
          })}
        </div>
      </div>
    </section>
  );
};