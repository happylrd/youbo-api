<template>
  <div>
    <div class="phone-viewport">

      <md-toolbar class="md-dense">
        <h2 class="md-title" style="flex: 1">Youbo</h2>

        <md-button class="md-icon-button">
          <md-icon>search</md-icon>
        </md-button>

        <md-button id="register" @click.native="openDialog('registerDialog')">注册</md-button>

        <md-button id="login" @click.native="openDialog('loginDialog')">登录</md-button>

      </md-toolbar>

      <md-dialog md-open-from="#login" md-close-to="#login" ref="loginDialog">
        <Login></Login>
      </md-dialog>

      <md-dialog md-open-from="#register" md-close-to="#register" ref="registerDialog">
        <Register></Register>
      </md-dialog>
    </div>
  </div>
</template>

<script>
  import Login from '../login/Login'
  import Register from '../register/Register'

  export default {
    methods: {
      openDialog (ref) {
        this.$refs[ref].open()
      },
      closeDialog (ref) {
        this.$refs[ref].close()
      }
    },
    components: {
      Login,
      Register
    }
  }
</script>

<style scoped>

</style>
