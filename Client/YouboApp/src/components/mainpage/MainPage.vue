<template>
  <div>
    <Header></Header>

    <TweetItem></TweetItem>

    <Register></Register>
  </div>
</template>

<script>
  import Header from '../header/Header'
  import TweetItem from '../tweetitem/TweetItem'

  import Register from '../register/Register'

  export default {
    components: {
      Header,
      TweetItem,
      Register
    }
  }
</script>

<style scoped>

</style>
