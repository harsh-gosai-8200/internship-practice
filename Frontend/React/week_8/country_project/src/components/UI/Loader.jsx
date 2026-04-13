export function Loader() {
  return (
    <div className="flex justify-center items-center h-screen bg-black">
      <div className="relative w-24 h-24">
        <div className="absolute top-0 left-0 w-24 h-24 rounded-full border-4 border-indigo-500 border-t-transparent animate-spin-slow shadow-[0_0_15px_rgba(99,102,241,0.6)]"></div>
        <div className="absolute top-2 left-2 w-20 h-20 rounded-full border-4 border-pink-500 border-b-transparent animate-spin-reverse shadow-[0_0_10px_rgba(236,72,153,0.5)]"></div>
        <div className="absolute top-1/2 left-1/2 w-6 h-6 bg-indigo-400 rounded-full -translate-x-1/2 -translate-y-1/2 animate-pulse shadow-lg shadow-indigo-500/50"></div>
      </div>
    </div>
  );
}