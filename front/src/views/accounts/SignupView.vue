<template>
  <div>
    <button @click="$router.go(-1)" type="button">뒤로</button>
  </div>
  <div>
    <h3>이메일을 입력해주세요.</h3>
  </div>
  <!-- <account-error-list v-if="store.getters['accounts/authError']"></account-error-list> -->

  <!-- <form @submit.prevent="store.dispatch('accounts/signup', credentials)"> -->
  <form @submit="signup">
    <label for="useremail">이메일
      <input v-model="credentials.useremail" id="useremail" type="email" autofocus placeholder="이메일을 입력하세요" required /><button disabled>인증요청</button>
    </label>
    <label for="password1">비밀번호
      <input v-model="credentials.password1" id="password1" type="password" placeholder="비밀번호를 입력하세요" required />
    </label>
    <label for="password2">비밀번호 확인
      <input v-model="credentials.password2" id="password2" type="password" placeholder="비밀번호를 다시 입력하세요" required />
    </label>
    <label for="userbirthday">생년월일 (선택)
      <input v-model="credentials.userbirthday" id="userbirthday" type="date" />
    </label>
    <button>시작하기</button>
  </form>
</template>
<script setup>
import { useStore } from 'vuex'
import { ref, onMounted } from 'vue'
// import AccountErrorList from './AccountErrorList.vue.js'
// element-plus에 Form, Input, Datepicker로 CSS
const store = useStore()
onMounted(() => {
})
const credentials = ref({
    useremail: '',
    password1: '',
    password2: '',
    userbirthday: '',
})
const signup = function (event) {
  event.preventDefault()
  const credentials = {
    useremail: event.target.useremail.value,
    password1: event.target.password1.value,
    password2: event.target.password2.value,
    userbirthday: event.target.userbirthday.value,
  }
  // console.log(event.target.useremail.value)
  store.dispatch('accounts/signup', credentials)
}
</script>