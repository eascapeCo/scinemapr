<template>
  <div class="adminForm">
      <v-main>
    404<br>
    <v-btn>메인으로 가기</v-btn>
  </v-main>
  </div>
</template>

<script>
export default {
  name: 'AdminForm',
  created () {
    console.log(this.$route.params)
  },
  mounted () {
    console.log(this.$route.params)
  }
}
</script>

<style scoped>

</style>
