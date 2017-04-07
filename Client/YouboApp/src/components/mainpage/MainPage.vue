<template>
  <div>
    <Header></Header>

    <TweetItem v-for="item in tweetList" :key="item.id" :tweet="item"></TweetItem>
  </div>
</template>

<script>
  import Axios from 'axios'
  import Header from '../header/Header'
  import TweetItem from '../tweetitem/TweetItem'

  const BASE_URL = 'http://localhost:8000/'

  export default {
    data () {
      return {
        tweetList: []
      }
    },
    created () {
      const TWEET_API = BASE_URL + 'tweets/'

      Axios.get(TWEET_API)
        .then(response => {
          this.tweetList = response.data.data
          console.log(this.tweetList)
        })
        .catch(error => {
          console.log(error)
        })
    },
    components: {
      Header,
      TweetItem
    }
  }
</script>

<style scoped>

</style>
