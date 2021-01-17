<template>
  <div class="adminForm">
  </div>
</template>

<script>
export default {
  name: 'AdminForm',
  created () {
    console.log(1)
    console.log(this.$route.params)
  },
  mounted () {
    console.log(2)
    console.log(this.$route.params)
  }
}
</script>

<style scoped>

</style>
