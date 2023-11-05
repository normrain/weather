<script setup>
import {ref} from "vue";
import axios from "axios";

const searchQuery = ref("")
const queryTimeOut = ref(null);
const searchResults = ref(null);
const forecasts = ref(null);
const searchError = ref(null);
const getSearchResults = () => {
  clearTimeout(queryTimeOut.value)
  queryTimeOut.value = setTimeout(async () => {
    if (searchQuery.value !== '') {
      try {
        const result = await axios.get(`http://localhost:8080/api/v1/forecast/${searchQuery.value}`);
        searchResults.value = result.data

      } catch {
        searchError.value = true
      }
      return;
    }
    searchResults.value = null
  }, 300)
};
const clearSearchQuery = () => {
  searchQuery.value ='';
  searchResults.value=null;
}

const fetchForecasts = () => {
  clearTimeout(queryTimeOut.value)
  queryTimeOut.value = setTimeout(async () => {
    const result = await axios.get(`http://localhost:8080/api/v1/forecast/`);
    forecasts.value = result.data

  }, 300)
};

</script>

<template>
  <main class="container text-black">
    <div class="pt-4 mb-8 ">
      <button @click="fetchForecasts"
              class="py-2 px-1 w-1/2 bg-transparent border-b focus:border-weather-secondary focus:outline-none">
        Display Forecasts
      </button>
      <input @input="getSearchResults" @focusout="clearSearchQuery" v-model="searchQuery" type="text" placeholder="Search for a city"
             class="w-1/2 text-black"
      />
      <ul v-if="searchResults"
          class="absolute bg-weather-secondary text-black w-4/5 shadow-md py-2 px-1 top-[66] ">
          <li class="text-2xl align-middle">Place: {{ searchResults[0].name }}</li>
          <li>Min. Temp: {{ searchResults[1].tempmin }}</li>
          <li>Max. Temp: {{ searchResults[0].tempmax }}</li>
          <li>Day: {{ searchResults[0].phenomenon }} / Night: {{ searchResults[1].phenomenon }}</li>
      </ul>
    </div>
    <div>
      <ul>
        <li v-for="forecast in forecasts"
            :key="forecast.id"
            class="py-2 text-black border-b border-weather-secondary">
          <p class="text-2xl"> Date: {{ new Date(forecast.date).toLocaleDateString() }}</p>
          <table class="table-fixed w-full border-separate">
            <thead class="table-header-group">
            <tr>
              <th>Day</th>
              <th>Night</th>
            </tr>
            </thead>
            <tbody>
            <tr class="row">
              <td>Min. Temperature: {{ forecast.day.tempmin }}</td>
              <td>Min. Temperature: {{ forecast.night.tempmin }}</td>
            </tr>
            <tr class="row">
              <td>Max. Temperature: {{ forecast.day.tempmax }}</td>
              <td>Max. Temperature: {{ forecast.night.tempmax }}</td>
            </tr>
            <tr class="row">
              <td>{{ forecast.day.text }}</td>
              <td>{{ forecast.night.text }}</td>
            </tr>
            </tbody>
          </table>
        </li>
      </ul>
    </div>

  </main>
</template>
