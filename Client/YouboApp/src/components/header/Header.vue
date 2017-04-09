<template>
  <div>
    <div class="phone-viewport">

      <md-toolbar class="md-dense">
        <h2 class="md-title" style="flex: 1">Youbo</h2>

        <md-button class="md-icon-button">
          <md-icon>search</md-icon>
        </md-button>


        <!--TODO: just a toy, will be modified later-->
        <md-avatar v-if="userinfo !== null">
          <img src="../../assets/youbo-logo.png" alt="Avatar">
        </md-avatar>
        <span v-if="userinfo !== null">{{userinfo.username}}</span>


        <md-button v-if="userinfo === null" id="register" @click.native="openDialog('registerDialog')">注册</md-button>
        <md-button v-if="userinfo === null" id="login" @click.native="openDialog('loginDialog')">登录</md-button>

      </md-toolbar>

      <md-dialog md-open-from="#login" md-close-to="#login" ref="loginDialog">
        <Login @hasLogin="onSuccessLogin"></Login>
      </md-dialog>

      <md-dialog md-open-from="#register" md-close-to="#register" ref="registerDialog">
        <Register></Register>
      </md-dialog>
    </div>
  </div>
</template>

<script>
  import { saveToLocal, loadFromLocal, MOCK_ID } from '../../common/js/store'
  import Login from '../login/Login'
  import Register from '../register/Register'

  export default {
    data () {
      return {
        userinfo: (() => {
          return loadFromLocal(MOCK_ID, 'userinfo', null)
        })()
      }
    },
    methods: {
      openDialog (ref) {
        this.$refs[ref].open()
      },
      closeDialog (ref) {
        this.$refs[ref].close()
      },
      onSuccessLogin (user) {
        saveToLocal(MOCK_ID, 'userinfo', user)
        this.closeDialog('loginDialog')
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
