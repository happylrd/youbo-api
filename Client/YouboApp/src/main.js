// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import VueRouter from 'vue-router'
import VueMaterial from 'vue-material'
import 'vue-material/dist/vue-material.css'
import App from './App'
import TweetList from './components/tweetlist/TweetList'
import TweetDetail from './components/tweetdetail/TweetDetail'
import MePage from './components/mepage/MePage'

Vue.config.productionTip = false

Vue.use(VueRouter)
Vue.use(VueMaterial)

Vue.material.registerTheme('default', {
  primary: 'cyan'
})

const routes = [
  {
    path: '/',
    component: TweetList,
    redirect: '/tweets'
  },
  {
    path: '/tweets',
    component: TweetList
  },
  {
    path: '/tweets/:id',
    component: TweetDetail
  },
  {
    path: '/users/:id/info',
    component: MePage
  }
]

const router = new VueRouter({
  mode: 'history',
  routes
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  render: h => h(App)
})
