<template>
  <div class="card-wrapper">
    <md-card>
      <md-card-header>
        <div class="md-title">注册</div>
      </md-card-header>

      <md-card-content>
        <md-input-container>
          <md-icon>account_circle</md-icon>
          <label>用户名</label>
          <md-input type="text" v-model="username" required></md-input>
        </md-input-container>

        <md-input-container md-has-password>
          <md-icon>lock</md-icon>
          <label>密码</label>
          <md-input type="password" v-model="password" required></md-input>
        </md-input-container>

        <md-input-container>
          <md-icon>phone</md-icon>
          <label>手机号</label>
          <md-input type="number" v-model="mobile"></md-input>
        </md-input-container>

        <div>
          <md-radio v-model="gender" md-value="male">男</md-radio>
          <md-radio v-model="gender" md-value="female">女</md-radio>
          <md-radio v-model="gender" md-value="unknown">未知</md-radio>
        </div>

        <md-input-container>
          <md-icon>lock</md-icon>
          <label>头像</label>
          <md-file v-model="avatar" accept="image/*"></md-file>
        </md-input-container>

        <md-button class="md-raised md-primary" @click.native="doRegister">注册</md-button>
      </md-card-content>
    </md-card>
  </div>
</template>

<script>
  import Axios from 'axios'
  import querystring from 'querystring'

  const BASE_URL = 'http://localhost:8000/'

  export default {
    data () {
      return {
        username: '',
        password: '',
        mobile: '',
        gender: '',
        avatar: null
      }
    },
    methods: {
      doRegister () {
        const USER_API = BASE_URL + 'users'

        Axios.post(USER_API, querystring.stringify({
          username: this.username,
          password: this.password,
          mobile: this.mobile,
          gender: this.gender
        }))
          .then((response) => {
            this.user = response.data.data
            console.log(this.user)
          })
          .catch(error => {
            console.log(error)
          })
      }
    }
  }
</script>

<style scoped>
  .card-wrapper {
  }
</style>
