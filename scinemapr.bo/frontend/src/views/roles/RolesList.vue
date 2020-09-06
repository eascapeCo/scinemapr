<template>
  <div class="rolesList">
    <v-container fluid>
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
                         :gridOptions="gridOptions"
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
  created () {
    console.log(1)
    this.$axios.get('/api/menus')
      .then((res) => {
        this.rowData = res.data
        this.gridApi.setRowData(res.data)
        console.log(this.rowData)
      })
  },
  data: () => ({
    menus: [],
    data: {
      gridOptions: null,
      columnDefs: null,
      rowData: null
    }
  }),
  components: {
    AgGridVue
  },
  beforeMount () {
    console.log(2)
    this.gridOptions = {}
    this.columnDefs = [
      { headerName: 'mnuNo', field: 'mnuNo' },
      { headerName: 'preMnuNo', field: 'preMnuNo' },
      { headerName: 'mnuName', field: 'mnuName' },
      { headerName: 'mnuLv', field: 'mnuLv' },
      { headerName: 'urlAdr', field: 'urlAdr' },
      { headerName: 'useYn', field: 'useYn' },
      { headerName: 'dpYn', field: 'dpYn' },
      { headerName: 'dpSequence', field: 'dpSequence' },
      { headerName: 'regDate', field: 'regDate' },
      { headerName: 'regNo', field: 'regNo' },
      { headerName: 'modDate', field: 'modDate' },
      { headerName: 'modNo', field: 'modNo' }
    ]
    this.rowData = [
      {
        mnuNo: 1,
        preMnuNo: 1,
        mnuName: 'menu',
        mnuLv: 1,
        urlAdr: '/asda',
        useYn: true,
        dpYn: true,
        dpSequence: 1,
        regDate: '2020-08-26',
        regNo: 1,
        odDate: '2020-08-25',
        modNo: 1
      }
    ]
  },
  mounted () {
    console.log(3)
    this.gridApi = this.gridOptions.api
  }
}
</script>
