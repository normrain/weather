<script setup>
import {RouterLink} from "vue-router";
</script>

<template>
<header class="sticky top-0 bg-weather-primary shadow-lg">
  <nav class="container flex flex-col items-center gap-4 text-black py-6">
    <RouterLink :to="{name: 'home'}">
      <div class="flex items-center gap-3 flex-1">
        <p class="text-2xl">The Local Weather</p>
      </div>
    </RouterLink>
  </nav>
</header>
</template>
