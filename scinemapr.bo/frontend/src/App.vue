<template>
  <v-app>
      <router-view/>
  </v-app>
</template>

<script>

export default {
  name: 'App',
  props: {
    source: String
  },
  components: {
  },
  data: () => ({
  }),
  created () {
    this.$vuetify.theme.dark = true
  },
  methods: {
    test: function () {
      alert('a')
      this.$axios.get('/api/users')
        .then((res) => {
          console.log(res)
        })
    }
  }
}
</script>
