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
      <!--
      <v-list-group
        no-action
        sub-group
        v-for="secondMenu in menu.subMenus"
        :key="secondMenu.mnuName"
      >
        <template v-slot:activator>
          <v-list-item-content>
            <v-list-item-title v-text="secondMenu.mnuName"></v-list-item-title>
          </v-list-item-content>
        </template>
        <v-list-item
          link
          v-for="thirdMenu in secondMenu.subMenus"
          :key="thirdMenu.mnuName"
        >
          <v-list-item-title v-text="thirdMenu.mnuName"></v-list-item-title>
        </v-list-item>
      </v-list-group>
      -->
        <span
          v-for="secondMenu in menu.subMenus"
          :key="secondMenu.mnuName"
        >
          <template v-if="secondMenu.subMenus.length !== 0">
            <v-list-group
              no-action
              sub-group
            >
              <template v-slot:activator>
                <v-list-item-content>
                  <v-list-item-title v-text="secondMenu.mnuName"></v-list-item-title>
                </v-list-item-content>
              </template>
              <v-list-item
                link
                v-for="thirdMenu in secondMenu.subMenus"
                :key="thirdMenu.mnuName"
              >
                <v-list-item-title v-text="thirdMenu.mnuName"></v-list-item-title>
              </v-list-item>
            </v-list-group>
          </template>
          <template v-if="secondMenu.subMenus.length === 0">
            <v-list-item link>
              <v-list-item-content>
                <v-list-item-title v-text="secondMenu.mnuName"></v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </template>
        </span>
      <v-list-item link>
        <v-list-item-content>
          <v-list-item-title @click="test">Ao3 - 3</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </v-list-group>
    <v-list-group
        prepend-icon="mdi-view-dashboard"
        no-action
    >
        <template v-slot:activator>
        <v-list-item-content>
            <v-list-item-title>Ao3</v-list-item-title>
        </v-list-item-content>
        </template>
        <v-list-group
        no-action
        sub-group
        >
        <template v-slot:activator>
            <v-list-item-content>
            <v-list-item-title>Ao3 - 1</v-list-item-title>
            </v-list-item-content>
        </template>
        <v-list-item link>
            <v-list-item-title v-text="123"></v-list-item-title>
        </v-list-item>
        <v-list-item link>
            <v-list-item-title v-text="456"></v-list-item-title>
        </v-list-item>
        </v-list-group>
        <v-list-item link>
        <v-list-item-content>
            <v-list-item-title @click="test">Ao3 - 2</v-list-item-title>
        </v-list-item-content>
        </v-list-item>
        <v-list-item link>
          <v-list-item-content>
            <v-list-item-title @click="test">Ao3 - 3</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
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
    test: function () {
      console.log(this.menus)
    }
  }
}
</script>
