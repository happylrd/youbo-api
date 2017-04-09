<template>
  <div>
    <md-card>
      <md-card-header>
        <div class="md-title">我的</div>
      </md-card-header>

      <md-card-content>
        <md-input-container>
          <md-icon>account_circle</md-icon>
          <label>昵称</label>
          <md-input type="text" :placeholder="userinfo.nickname"
                    v-model="nickname" disabled></md-input>
        </md-input-container>

        <md-input-container>
          <md-icon>phone</md-icon>
          <label>手机号</label>
          <md-input type="number" :placeholder="userinfo.username"
                    v-model="mobile" disabled></md-input>
        </md-input-container>

        <md-input-container>
          <md-icon>account_circle</md-icon>
          <label>真实姓名</label>
          <md-input type="text" :placeholder="userinfo.realname"
                    v-model="realname" disabled></md-input>
        </md-input-container>

        <div>
          <md-radio v-model="gender" md-value="male" disabled>男</md-radio>
          <md-radio v-model="gender" md-value="female" disabled>女</md-radio>
          <md-radio v-model="gender" md-value="unknown" disabled>未知</md-radio>
        </div>

        <md-input-container>
          <md-icon>lock</md-icon>
          <label>头像</label>
          <md-file v-model="avatar" accept="image/*" disabled></md-file>
        </md-input-container>

        <md-button class="md-raised md-primary" @click.native="doModify">更改</md-button>
      </md-card-content>
    </md-card>
  </div>
</template>

<script>
  import { loadFromLocal, MOCK_ID } from '../../common/js/store'
  export default {
    data () {
      return {
        userinfo: (() => {
          return loadFromLocal(MOCK_ID, 'userinfo', null)
        })()
      }
    },
    methods: {
      doModify () {
      }
    }
  }
</script>

<style scoped>
</style>
