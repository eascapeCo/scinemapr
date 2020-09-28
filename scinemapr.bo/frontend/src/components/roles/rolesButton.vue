<script>
import Vue from 'vue'

export default Vue.extend({
  template: `
        <button type="button" class="v-btn v-btn--contained theme--dark v-size--default primary" v-on:click="invokeParentMethod">
          <span class="v-btn__content">Share</span>
        </button>
    `,
  data: function () {
    return {}
  },
  beforeMount () {},
  mounted () {},
  methods: {
    invokeParentMethod: function () {
      console.log('rune')
      console.log(this.params)
      /*
      this.params.context.componentParent.methodFromParent(
        `Row: ${this.params.node.rowIndex}, Col: ${this.params.colDef.headerName}`
      )
      */
    }
  }
})
</script>
