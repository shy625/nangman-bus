<template>
  <Header/>
  <el-calendar v-model="value" />
</template>

<script setup>
import { ref } from 'vue'
import Header from '../../components/Header.vue'
const value = ref(new Date())

</script>