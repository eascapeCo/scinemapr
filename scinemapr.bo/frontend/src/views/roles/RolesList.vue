<template>
  <div class="rolesList">
    <v-container>
      asd
      <v-row>
        <v-col>
          <v-card
            class="mt-2 pa-2"
          >asd
          </v-card>
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <v-card
            class="pa-2"
          >
            <ag-grid-vue domLayout="autoHeight"
                         class="ag-theme-alpine-dark mt-10"
                         :columnDefs="columnDefs"
                         :rowData="rowData">
            </ag-grid-vue>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="primary">Share</v-btn>
              <v-btn>Explore</v-btn>
            </v-card-actions>
          </v-card>

        </v-col>
      </v-row>
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
