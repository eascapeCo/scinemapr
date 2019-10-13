<template lang="html">
    <div class="container-scroller">
      <app-header/>
      <div class="container-fluid page-body-wrapper">
        <app-sidebar/>
        <div class="main-panel">
          <div class="content-wrapper">
            <router-view></router-view>
          </div>
          <!-- content wrapper ends -->
          <app-footer/>
        </div>
        <!-- main panel ends -->
      </div>
      <!-- page-body-wrapper ends -->
    </div>
</template>

<script>
import AppHeader from '../partials/AppHeader'
import AppSidebar from '../partials/AppSidebar'
import AppFooter from '../partials/AppFooter'
export default {
  name: 'MainView',
	components: {
    AppHeader,
    AppSidebar,
    AppFooter
  },
  data() {
    return {
      msg: 'Welcome to Your Vue.js App',
    };
  },
	methods: {
		test: function (value) {
			console.log("JWT token => " + this.$store.getters.getJwt);
			console.log(value);
			let myInit = {
				headers: {
        "Authorization" : this.$store.getters.getJwt
      	}
    	};
			/*
			fetch('/api/home').then(function(res) {
				res.json().then(json => {
					console.log(json);
				})
			});
			*/
			this.$http.get('/api/home', myInit).then((res) => res)
                .catch((error) => (console.log(error.response)));
		}
	},
}
function tee () {
	
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
