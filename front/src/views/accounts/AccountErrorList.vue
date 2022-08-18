<template>
  <div class="account-error-list">
    <p v-for="(errors, field) in authError" :key="field">
      {{ field }}
      <ul>
        <li v-for="(error, idx) in errors" :key="idx">
          {{ error }}
        </li>
      </ul>
    </p>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex'

  export default {
    name: 'AccountErrorList',
    components: {
        
    },
    data() {
      return {
        sampleData: ''
      }
    },
    setup() {
        
    },
    created() {
        
    },
    mounted() {
        
    },
    unmounted() {
        
    },
    computed: {
      ...mapGetters(['authError']),
    },
    methods: {
      
    }
  }
</script>

<style>
  .account-error-list {
    color: red;
  }
</style>