<template>
  <div class="card-wrapper">
    <md-card>
      <md-card-header>
        <div class="md-title">登录</div>
      </md-card-header>

      <md-card-content>
        <md-input-container>
          <md-icon>account_circle</md-icon>
          <label>用户名</label>
          <md-input type="text" v-model="username"></md-input>
        </md-input-container>

        <md-input-container md-has-password>
          <md-icon>lock</md-icon>
          <label>密码</label>
          <md-input type="password" v-model="password"></md-input>
        </md-input-container>

        <md-layout md-align="center">
          <md-button class="md-raised md-primary" @click.native="doLogin">登录</md-button>
        </md-layout>
      </md-card-content>
    </md-card>
  </div>
</template>

<script>
  import Axios from 'axios'

  const BASE_URL = 'http://localhost:8000/'

  export default {
    data () {
      return {
        user: {},
        username: '',
        password: ''
      }
    },
    methods: {
      doLogin () {
        const USER_API = BASE_URL + 'users/' + this.username

        Axios.get(USER_API)
          .then((response) => {
            this.user = response.data.data
            console.log(this.user)

            if (this.username === this.user.username) {
              if (this.password === this.user.password) {
                console.log('登录成功，欢迎你' + this.username)

                // TODO: will be improved later
                this.$emit('loginSuccess', this.user)
              } else {
                console.log('密码错误')
              }
            }
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
