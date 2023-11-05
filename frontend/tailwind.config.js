/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./index.html", "./src/**/*.{vue,js,ts,jsx,tsx}"],
  theme: {
    extend: {
      colors: {
        "weather-primary": "white",
        "weather-secondary": "lightgreen"
      }
    },
    fontFamily: {
      Roboto: ["Roboto, sans-serif"]
    },
    container: {
      padding: "2rem",
      center: true
    },
  },
  plugins: [],
}

