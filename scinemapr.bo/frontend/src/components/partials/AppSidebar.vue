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
    >
        <template v-slot:activator>
        <v-list-item-content>
            <v-list-item-title>Ao1</v-list-item-title>
        </v-list-item-content>
        </template>
    </v-list-group>

    <v-list-group
        prepend-icon="mdi-view-dashboard"
        no-action
    >
        <template v-slot:activator>
        <v-list-item-content>
            <v-list-item-title>Ao2</v-list-item-title>
        </v-list-item-content>
        </template>
        <v-list-item link>
        <v-list-item-content>
            <v-list-item-title>Ao2 - 1</v-list-item-title>
        </v-list-item-content>
        </v-list-item>
        <v-list-item link>
        <v-list-item-content>
            <v-list-item-title>Ao2 - 2</v-list-item-title>
        </v-list-item-content>
        </v-list-item>
        <v-list-item link>
        <v-list-item-content>
            <v-list-item-title>Ao2 - 3</v-list-item-title>
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
            <v-list-item-title>Ao3 - 2</v-list-item-title>
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
    this.getMenuList()
  },
  data () {
    return {
      items: [
        {
          action: 'local_activity',
          title: 'Attractions',
          items: [
            { title: 'List Item' }
          ]
        },
        {
          action: 'restaurant',
          title: 'Dining',
          active: true,
          items: [
            { title: 'Breakfast & brunch' },
            { title: 'New American' },
            { title: 'Sushi' }
          ]
        },
        {
          action: 'school',
          title: 'Education',
          items: [
            { title: 'List Item' }
          ]
        },
        {
          action: 'directions_run',
          title: 'Family',
          items: [
            { title: 'List Item' }
          ]
        },
        {
          action: 'healing',
          title: 'Health',
          items: [
            { title: 'List Item' }
          ]
        },
        {
          action: 'content_cut',
          title: 'Office',
          items: [
            { title: 'List Item' }
          ]
        },
        {
          action: 'local_offer',
          title: 'Promotions',
          items: [
            { title: 'List Item' }
          ]
        }
      ]
    }
  },
  methods: {
    getMenuList: function () {
      console.log('11')
    }
  }
}
</script>
