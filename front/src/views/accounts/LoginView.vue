<template>
  <div>
    <button>뒤로</button>
  </div>
  <div>
    <h3>이메일을 입력해주세요.</h3>
  </div>
  <form @submit.prevent="login(credentials)">
    <div>
      <label for="email">이메일</label><br>
      <input v-model="credentials.email" id="email" type="email" autofocus placeholder="이메일을 입력해주세요" required />
    </div>
    <div>
      <label for="password">비밀번호</label><br>
      <input v-model="credentials.password" id="password" type="password" placeholder="비밀번호를 입력해주세요" required />
    </div>
    <button>로그인</button>
  </form>
  <a href="https://127.0.0.1/">회원가입</a>
</template>

<script>
import { mapActions } from 'vuex'

export default {
  name: '',
  components: {
    
  },
  data() {
    return {
      credentials: {
        email: '',
        password: '',
      }
    }
  },
  setup() {
    
  },
  created() {
    
  },
  mounted() {
    mapActions(['login'])
  },
  unmounted() {
    
  },
  methods: {
    
  }
}
</script>