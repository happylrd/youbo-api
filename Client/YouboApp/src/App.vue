<template>
  <div>
    <Header></Header>

    <keep-alive>
      <router-view :tweetList="tweetList"></router-view>
    </keep-alive>
  </div>
</template>

<script>
  import Axios from 'axios'
  import Header from './components/header/Header'

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
      Header
    }
  }
</script>

<style scoped>

</style>
