<template>
  <div>

    <md-layout md-gutter>
      <!--TODO: will be extracted later-->
      <md-layout md-align="end">
        <md-layout md-flex="33">
          <md-list>
            <md-list-item>
              <md-icon>whatshot</md-icon>
              <span>热门</span>
            </md-list-item>

            <md-list-item>
              <md-icon>chevron_right</md-icon>
              <span>明星</span>
            </md-list-item>

            <md-list-item>
              <md-icon>chevron_right</md-icon>
              <span>搞笑</span>
            </md-list-item>

            <md-list-item>
              <md-icon>chevron_right</md-icon>
              <span>社会</span>
            </md-list-item>

            <div class="md-divider"></div>

            <md-list-item>
              <md-icon>videocam</md-icon>
              <span>视频</span>
            </md-list-item>

            <div class="md-divider"></div>

            <md-list-item>
              <md-icon>toys</md-icon>
              <span>头条</span>
            </md-list-item>

            <md-list-item>
              <md-icon>chevron_right</md-icon>
              <span>情感</span>
            </md-list-item>

            <md-list-item>
              <md-icon>chevron_right</md-icon>
              <span>时尚</span>
            </md-list-item>

            <md-list-item>
              <md-icon>chevron_right</md-icon>
              <span>军事</span>
            </md-list-item>

            <div class="md-divider"></div>

            <md-list-item>
              <md-icon>spa</md-icon>
              <span>榜单</span>
            </md-list-item>

          </md-list>
        </md-layout>

      </md-layout>

      <md-layout md-flex="50">

        <div style="width: 100%">
          <md-input-container>
            <label>有什么新鲜事想告诉大家？</label>
            <md-textarea v-model="content" maxlength="150"></md-textarea>
          </md-input-container>

          <md-layout md-align="end">
            <md-button class="md-raised md-primary" @click.native="publishTweet">发布</md-button>
          </md-layout>

        </div>

        <div style="width: 100%">
          <TweetItem v-for="item in tweetList" :key="item.id" :tweet="item"></TweetItem>
        </div>
      </md-layout>

      <md-layout></md-layout>
    </md-layout>

  </div>
</template>

<script>
  import Axios from 'axios'
  import querystring from 'querystring'
  import { loadFromLocal, MOCK_ID } from '../../common/js/store'
  import TweetItem from '../tweetitem/TweetItem'

  const BASE_URL = 'http://localhost:8000/'

  export default {
    props: {
      tweetList: {
        type: Array,
        default: []
      }
    },
    data () {
      return {
        userinfo: (() => {
          return loadFromLocal(MOCK_ID, 'userinfo', null)
        })(),
        content: ''
      }
    },
    methods: {
      publishTweet () {
        const TWEET_API = BASE_URL + 'tweets'

        Axios.post(TWEET_API, querystring.stringify({
          content: this.content,
          user: this.userinfo.id
        }))
          .then((response) => {
            this.tweet = response.data.data
            console.log('发推文成功，内容是:' + this.tweet.content)

            this.$emit('publishTweetSuccess')
          })
          .catch(error => {
            console.log(error)
          })
      }
    },
    components: {
      TweetItem
    }
  }
</script>

<style scoped>

</style>
