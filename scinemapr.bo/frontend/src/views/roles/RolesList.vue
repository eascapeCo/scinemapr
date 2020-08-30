<template>
  <div class="rolesList">
    <v-container>
      asd
      <ag-grid-vue domLayout="autoHeight"
                   class="ag-theme-alpine"
                   :columnDefs="columnDefs"
                   :rowData="rowData">
      </ag-grid-vue>
    </v-container>
  </div>
</template>

<script>
import { AgGridVue } from 'ag-grid-vue'

export default {
  name: 'rolesList',
  data: () => ({
    menus: [],
    data: {
      columnDefs: null,
      rowData: null
    }
  }),
  components: {
    AgGridVue
  },
  beforeMount () {
    this.columnDefs = [
      { headerName: 'Make', field: 'make' },
      { headerName: 'Model', field: 'model' },
      { headerName: 'Price', field: 'price' }
    ]
    this.rowData = [
      { make: 'Toyota', model: 'Celica', price: 35000 },
      { make: 'Ford', model: 'Mondeo', price: 32000 },
      { make: 'Porsche', model: 'Boxter', price: 72000 }
    ]
  }
}
</script>
