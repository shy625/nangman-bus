<template>
  <div>
    <button>뒤로</button>
  </div>
  <div>
    <h3>이메일을 입력해주세요.</h3>
  </div>

  <account-error-list v-if="authError"></account-error-list>

  <form @submit.prevent="login(credentials)">
    <div>
      <label for="useremail">이메일</label><br>
      <input v-model="credentials.email" id="useremail" type="useremail" autofocus placeholder="이메일을 입력해주세요" required />
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
import { mapActions, mapGetters } from 'vuex'
import AccountErrorList from './AccountErrorList.vue'

export default {
  name: 'LoginView',
  components: {
    AccountErrorList,
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
    
  },
  unmounted() {
    
  },
  computed: {
    ...mapGetters(['authError'])
  },
  methods: {
    ...mapActions(['login'])
  }
}
</script>