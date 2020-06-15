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
                <v-list-item-title v-text="secondMenu.mnuName" v-on:click="test(secondMenu.mnuName)"></v-list-item-title>
              </v-list-item-content>
            </template>
            <v-list-item
              link
              v-for="thirdMenu in secondMenu.subMenus"
              :key="thirdMenu.mnuNo"
            >
              <v-list-item-title v-text="thirdMenu.mnuName" v-on:click="test(thirdMenu.mnuName)"></v-list-item-title>
            </v-list-item>
          </v-list-group>
        </template>
        <template v-else>
          <v-list-item link v-bind:key="secondMenu.mnuNo">
            <v-list-item-content>
              <v-list-item-title v-text="secondMenu.mnuName" v-on:click="test(secondMenu.mnuName)"></v-list-item-title>
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
    this.$axios.get('/api/menus')
      .then((res) => {
        this.menus = res.data
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
    }
  }
}
</script>
