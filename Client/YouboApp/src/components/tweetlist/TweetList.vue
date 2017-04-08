<template>
  <div>

    <md-layout md-gutter>
      <md-layout md-align="end">
        <md-layout md-flex="33">
          <md-list>
            <md-list-item>
              <md-icon>whatshot</md-icon> <span>热门</span>
            </md-list-item>

            <md-list-item>
              <md-icon>chevron_right</md-icon> <span>明星</span>
            </md-list-item>

            <md-list-item>
              <md-icon>chevron_right</md-icon> <span>搞笑</span>
            </md-list-item>

            <md-list-item>
              <md-icon>chevron_right</md-icon> <span>社会</span>
            </md-list-item>

            <div class="md-divider"></div>

            <md-list-item>
              <md-icon>videocam</md-icon> <span>视频</span>
            </md-list-item>

            <div class="md-divider"></div>

            <md-list-item>
              <md-icon>toys</md-icon> <span>头条</span>
            </md-list-item>

            <md-list-item>
              <md-icon>chevron_right</md-icon> <span>情感</span>
            </md-list-item>

            <md-list-item>
              <md-icon>chevron_right</md-icon> <span>时尚</span>
            </md-list-item>

            <md-list-item>
              <md-icon>chevron_right</md-icon> <span>军事</span>
            </md-list-item>

            <div class="md-divider"></div>

            <md-list-item>
              <md-icon>spa</md-icon> <span>榜单</span>
            </md-list-item>

          </md-list>
        </md-layout>

      </md-layout>

      <md-layout md-flex="50">
        <div style="width: 100%">
          <TweetItem v-for="item in tweetList" :key="item.id" :tweet="item"></TweetItem>
        </div>
      </md-layout>

      <md-layout></md-layout>
    </md-layout>

  </div>
</template>

<script>
  import TweetItem from '../tweetitem/TweetItem'
  import MdListItem from '../../../node_modules/vue-material/src/components/mdList/mdListItemRouter'

  export default {
    props: {
      tweetList: {
        type: Array,
        default: []
      }
    },
    components: {
      MdListItem, TweetItem
    }
  }
</script>

<style scoped>

</style>
