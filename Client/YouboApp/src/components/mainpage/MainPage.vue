<template>
  <div>
    <Header></Header>

    <TweetItem></TweetItem>
  </div>
</template>

<script>
  import Header from '../header/Header'
  import TweetItem from '../tweetitem/TweetItem'

  export default {
    components: {
      Header,
      TweetItem
    }
  }
</script>

<style scoped>

</style>
