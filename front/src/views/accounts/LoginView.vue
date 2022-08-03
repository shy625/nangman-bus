<template>
  <div>
    <button>뒤로</button>
  </div>
  <div>
    <h3>이메일을 입력해주세요.</h3>
  </div>

  <!-- <account-error-list v-if="authError"></account-error-list> -->

  <form @submit="login">
    <label for="useremail">이메일
      <input v-model="credentials.useremail" id="useremail" type="email" autofocus placeholder="이메일을 입력해주세요" required />
    </label>
    <label for="password">비밀번호
      <input v-model="credentials.password" id="password" type="password" placeholder="비밀번호를 입력해주세요" required />
    </label>
    <button>로그인</button>
  </form>
  <a href="https://127.0.0.1/">회원가입</a>
</template>

<script setup>
import { useStore } from 'vuex'
import { ref, onMounted } from 'vue'
// import AccountErrorList from './AccountErrorList.vue'
const store = useStore()
onMounted(() => {
})
const credentials = ref({
  useremail: '',
  password: '',
})
const login = function (event) {
  event.preventDefault()
  const credentials = {
    useremail: event.target.useremail.value,
    password: event.target.password.value,
  }
  store.dispatch('accounts/login', credentials)
}
</script>
