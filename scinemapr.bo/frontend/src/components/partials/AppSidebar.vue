<template>
<!--
  <v-navigation-drawer
      v-model="drawer"
      app
      clipped
  >
-->
  <v-list>
    <v-list-group
      prepend-icon="mdi-view-dashboard"
      no-action
      v-for="menu in menus"
      :key="menu.mnuName"
    >
      <template v-slot:activator>
        <v-list-item-content>
          <v-list-item-title v-text="menu.mnuName"></v-list-item-title>
        </v-list-item-content>
      </template>
      <template
        v-for="secondMenu in menu.subMenus"
      >
        <template v-if="secondMenu.subMenus.length !== 0">
          <v-list-group
            no-action
            sub-group
            v-bind:key="secondMenu.mnuNo"
          >
            <template v-slot:activator>
              <v-list-item-content>
                <template v-if="secondMenu.subMenus.length !== 0">
                  <v-list-item-title v-text="secondMenu.mnuName"></v-list-item-title>
                </template>
                <template v-else>
                  <v-list-item-title v-text="secondMenu.mnuName"  v-on:click="test(secondMenu.urlAdr)"></v-list-item-title>
                </template>
              </v-list-item-content>
            </template>
            <v-list-item
              link
              v-for="thirdMenu in secondMenu.subMenus"
              :key="thirdMenu.mnuNo"
            >
              <v-list-item-title v-text="thirdMenu.mnuName" v-on:click="test(thirdMenu.urlAdr)"></v-list-item-title>
            </v-list-item>
          </v-list-group>
        </template>
        <template v-else>
          <v-list-item link v-bind:key="secondMenu.mnuNo">
            <v-list-item-content>
              <v-list-item-title v-text="secondMenu.mnuName" v-on:click="test(secondMenu.urlAdr)"></v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </template>
      </template>
    </v-list-group>
    <v-list-group
      prepend-icon="mdi-view-dashboard"
      no-action
    >
      <template v-slot:activator>
        <v-list-item-content>
          <v-list-item-title>sample</v-list-item-title>
        </v-list-item-content>
      </template>
      <v-list-item link>
        <v-list-item-content>
          <v-list-item-title>
            <router-link to="/">
              main
            </router-link>
          </v-list-item-title>
        </v-list-item-content>
      </v-list-item>
      <v-list-item link>
        <v-list-item-content>
          <v-list-item-title>
            <router-link to="/grid">
              grid
            </router-link>
          </v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </v-list-group>
  </v-list>
<!-- </v-navigation-drawer> -->
</template>

<script>
export default {
  name: 'AppSidebar',
  created () {
    this.$axios.get('/api/menus', {
      headers: {
        Authorization: this.$store.state.access_token,
        // Authorization: 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJhZG1JZCI6ImFkbWluIiwicm9sZXMiOlsiQURNSU4iXSwiYWRtTm8iOjEsImlhdCI6MTYwMTg4MDM1MCwiZXhwIjoxNjAxODgyMTUwfQ.cVMgyyhNVn6VBU3YfTLC0yqkXz1WbDB11hwMGfhX0sU',
        'Content-Type': 'application/json'
      }
    }).then((res) => {
      this.menus = res.data
    }).catch(err => {
      console.error(err)
      if (this.$alert('만료시간이 지났습니다. 로그인페이지로 이동합니다')) {
        this.$router.push('/loginForm')
      }
    })
  },
  data: () => ({
    menus: []
  }),
  methods: {
    getMenuList: function () {
      console.log('11')
    },
    test: function (url) {
      console.log(url)
      console.log(this.$route)
      this.$router.push(url).catch(error => {
        if (error.name !== 'NavigationDuplicated') {
          throw error
        }
      })
    }
  }
}
</script>
