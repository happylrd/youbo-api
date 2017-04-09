<template>
  <div class="card-wrapper">
    <md-card>
      <md-card-header>
        <div class="md-title">注册</div>
      </md-card-header>

      <md-card-content>

        <md-input-container>
          <md-icon>account_circle</md-icon>
          <label>昵称</label>
          <md-input type="text" v-model="nickname" required></md-input>
        </md-input-container>

        <md-input-container>
          <md-icon>account_circle</md-icon>
          <label>手机/邮箱</label>
          <md-input type="text" v-model="username" required></md-input>
        </md-input-container>

        <md-input-container md-has-password>
          <md-icon>lock</md-icon>
          <label>密码</label>
          <md-input type="password" v-model="password" required></md-input>
        </md-input-container>

        <md-layout md-align="center">
          <md-button class="md-raised md-primary" @click.native="doRegister">注册</md-button>
        </md-layout>
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
        nickname: ''
      }
    },
    methods: {
      doRegister () {
        const USER_API = BASE_URL + 'users'

        Axios.post(USER_API, querystring.stringify({
          username: this.username,
          password: this.password,
          nickname: this.nickname
        }))
          .then((response) => {
            this.user = response.data.data
            console.log('注册成功，欢迎你' + this.user.nickname)

            this.$emit('registerSuccess')
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
