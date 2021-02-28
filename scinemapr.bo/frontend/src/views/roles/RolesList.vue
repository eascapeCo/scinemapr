<template>
  <div class="rolesList">
    <v-container fluid>
      asd
      <v-row>
        <v-col>
          <v-card
            class="mt-2 pa-2"
          >
            검색조건 영역
            <v-layout row>
              <v-flex xs2>
                <v-subheader>Label here</v-subheader>
              </v-flex>
              <v-flex xs2>
                <v-text-field />
              </v-flex>
              <v-flex xs2>
                <v-subheader>Label here</v-subheader>
              </v-flex>
              <v-flex xs2>
                <v-text-field />
              </v-flex>
            </v-layout>
            <v-layout row>
              <v-flex xs2>
                <v-subheader>Label here</v-subheader>
              </v-flex>
              <v-flex xs2>
                <v-text-field
                ></v-text-field>
              </v-flex>
            </v-layout>
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
                         :rowData="rowData"
                         :context="context"
                         :frameworkComponents="frameworkComponents"
                         :defaultColDef="defaultColDef">
            </ag-grid-vue>
            <v-card-actions class="justify-center">
              <v-pagination
                v-model="currentPage"
                :length="totalPages"
                :total-visible="7"
                @input="getPage"
              />
            </v-card-actions>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="primary">Share</v-btn>
              <v-btn v-on:click="rolesPopup">Explore</v-btn>
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import { AgGridVue } from 'ag-grid-vue'
import RolesButton from '@/components/roles/rolesButton.vue'

export default {
  name: 'rolesList',
  created () {
    this.$axios.get('/api/roles?limit=3').then((res) => {
      this.rowData = res.data.content
      this.currentPage = res.data.number
      this.totalPages = res.data.totalPages
      this.gridApi.setRowData(this.rowData)
      console.log(res.data)
    })
  },
  data: () => ({
    menus: [],
    currentPage: 0,
    totalPages: 0,
    data: {
      gridOptions: null,
      columnDefs: null,
      rowData: [],
      frameworkComponents: null,
      context: null,
      defaultColDef: null
    }
  }),
  components: {
    AgGridVue
  },
  beforeMount () {
    this.gridOptions = {
      suppressCellSelection: false
    }
    this.columnDefs = [
      { headerName: 'rolNo', field: 'rolNo' },
      { headerName: 'rolNm', field: 'rolNm' },
      { headerName: 'roles', field: 'roles', cellRenderer: 'rolresButton' },
      { headerName: 'regDate', field: 'regDate' },
      { headerName: 'regNo', field: 'regNo' },
      { headerName: 'modDate', field: 'modDate' },
      { headerName: 'modNo', field: 'modNo' }
    ]
    this.rowData = []
    this.context = { componentParent: this }
    this.frameworkComponents = {
      rolresButton: RolesButton
    }
    this.defaultColDef = {
      editable: false,
      minWidth: 100
    }
    console.log(this.frameworkComponents.rolresButton)
  },
  methods: {
    rolesPopup: function (createElement) {
      this.gridApi.refreshCells()
      console.log(this.frameworkComponents.rolresButton.template)
    },
    getPage: function (page) {
      this.currentPage = page
      this.$axios.get('/api/roles?limit=3&page=' + this.currentPage).then((res) => {
        console.log(res.data.number)
        this.rowData = res.data.content
        this.currentPage = res.data.number
        this.totalPages = res.data.totalPages
        this.gridApi.setRowData(this.rowData)
      })
    }
  },
  mounted () {
    this.gridApi = this.gridOptions.api
    // this.gridOptions.api.sizeColumnsToFit()
  }
}
</script>
