export const Contact = () => {

const handleFormSubmit = (formData) => {
    const formInputData = Object.fromEntries(formData.entries());
    console.log(formInputData)
}

  return (
    <section className="bg-black text-white py-16">
      <div className="max-w-4xl mx-auto px-10">

        {/* Section Heading */}
        <h2 className="text-3xl md:text-4xl font-bold mb-10 text-center">
          Contact Us
        </h2>

        {/* Glass Container */}
        <div className="bg-blue/10 backdrop-blur-md border-[0.5px] border-white/20 rounded-2xl p-6 shadow-2xl max-w-xl mx-auto hover:shadow-indigo-500/30 transition-shadow duration-500">
          
          {/* Form */}
          <form action={handleFormSubmit} className="flex flex-col gap-3">
            <input
              type="text"
              required
              autoComplete="off"
              placeholder="Enter your name"
              name="username"
              className="bg-white/10 backdrop-blur-md border-[0.5px] border-white/20 rounded-lg p-3 text-white placeholder-gray-400 focus:outline-none focus:ring-2 focus:ring-indigo-500 transition"
            />
            <input
              type="email"
              required
              autoComplete="off"
              placeholder="Enter your email"
              name="email"
              className="bg-white/10 backdrop-blur-md border-[0.5px] border-white/20 rounded-lg p-3 text-white placeholder-gray-400 focus:outline-none focus:ring-2 focus:ring-indigo-500 transition"
            />
            <textarea
              rows="6"
              required
              autoComplete="off"
              placeholder="Enter your message"
              name="message"
              className="bg-white/10 backdrop-blur-md border-[0.5px] border-white/20 rounded-lg p-3 text-white placeholder-gray-400 focus:outline-none focus:ring-2 focus:ring-indigo-500 transition resize-none"
            ></textarea>

            <button
              type="submit"
              className="bg-indigo-600 hover:bg-indigo-700 px-5 py-2.5 rounded-md font-semibold text-white text-lg transition-transform duration-300 hover:scale-105"
            >
              Send Message
            </button>
          </form>
        </div>
      </div>
    </section>
  );
};