<template>
  <div>

    <router-link :to="tweetId">

      <div class="card-wrapper">

        <md-card md-with-hover>

          <md-card-header>
            <md-avatar>
              <img src="../../assets/youbo-logo.png" alt="Avatar">
            </md-avatar>

            <div class="md-title">{{tweet.user.nickname}}</div>
            <div class="md-subhead">{{tweet.user.addTime.year}}/{{tweet.user.addTime.monthValue}}/{{tweet.user.addTime.dayOfMonth}}</div>
          </md-card-header>

          <!--<md-card-media>-->
            <!--<img src="../../assets/tweet-image-test.jpg" alt="Tweet Image">-->

            <!--<md-ink-ripple></md-ink-ripple>-->
          <!--</md-card-media>-->

          <md-card-content>{{tweet.content}}</md-card-content>

          <md-card-actions>
            <md-button class="md-icon-button">
              <md-icon>favorite</md-icon>
            </md-button>

            <md-button class="md-icon-button">
              <md-icon>bookmark</md-icon>
            </md-button>

            <md-button class="md-icon-button">
              <md-icon>share</md-icon>
            </md-button>
          </md-card-actions>
        </md-card>

      </div>

    </router-link>

  </div>
</template>

<script>
  export default {
    props: {
      tweet: {
        type: Object
      }
    },
    computed: {
      // TODO: solve the route problem with the dirty method, will be modified later
      tweetId () {
        return 'tweets/' + this.tweet.id
      }
    }
  }
</script>

<style scoped>

</style>
