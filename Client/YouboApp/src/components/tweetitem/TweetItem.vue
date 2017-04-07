<template>
  <div>
    <div class="card-wrapper">

      <md-card md-with-hover>

        <md-card-header>
          <md-avatar>
            <img src="../../assets/youbo-logo.png" alt="Avatar">
          </md-avatar>

          <div class="md-title">Username</div>
          <div class="md-subhead">2017/01/01</div>
        </md-card-header>

        <md-card-media>
          <img src="../../assets/tweet-image-test.jpg" alt="Tweet Image">

          <md-ink-ripple></md-ink-ripple>
        </md-card-media>

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
  </div>
</template>

<script>
  export default {
    props: {
      tweet: {
        type: Object
      }
    }
  }
</script>

<style scoped>
  .card-wrapper {
    width: 300px;
  }
</style>
