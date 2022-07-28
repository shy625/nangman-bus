<template>
  <div>
    <button>뒤로</button>
  </div>
  <div>
    <h3>이메일을 입력해주세요.</h3>
  </div>
  <form @submit.prevent="signup(credentials)">
    <div>
      <label for="useremail">이메일</label><br>
      <input v-model="credentials.email" id="useremail" type="useremail" autofocus placeholder="이메일을 입력하세요" required /><button>인증요청</button>
    </div>
    <div>
      <label for="password1">비밀번호</label><br>
      <input v-model="credentials.password1" id="password1" type="password" placeholder="비밀번호를 입력하세요" required />
    </div>
    <div>
      <label for="password2">비밀번호 확인</label><br>
      <input v-model="credentials.password2" id="password2" type="password" placeholder="비밀번호를 다시 입력하세요" required />
    </div>
    <div>
      <label for="userbirthday">생년월일 (선택)</label><br>
      <input v-model="credentials.userbirthday" id="userbirthday" type="date" />
    </div>
    <button>시작하기</button>
  </form>
</template>
<script>
import { mapActions } from 'vuex'
// element-plus에 Form, Input, Datepicker로 CSS
export default {
  name: 'SignupView',
  components: {
    
  },
  data() {
    return {
      credentials: {
        email: '',
        password1: '',
        password2: '',
        userbirthday: '',
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
  methods: {
    mapActions(['signup'])
  }
}
</script>