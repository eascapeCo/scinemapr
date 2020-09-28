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
// import RolesButton from '@/components/roles/rolesButton.vue'

export default {
  name: 'rolesList',
  created () {
    console.log(1)
    this.$axios.get('/api/roles')
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
    console.log(2)
    console.log(this.rolresButton)
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
    test: function (param) {
      console.log('test!!')
      console.log(param)
      console.log(param.data.rolNo)
      // const sellData = param.data
      // console.log(this.data)
      // return '<button color="primary" v-on:click="this.rolesPopup">권한보기</button>'
      // this.rolesPopup()
      /*
      const propsObj = {
        props: {
          overlap: true,
          left: true,
          color: 'success',
        },
      };
      const icon = createElement('v-icon', { props: { color: 'success', large: true } }, 'account_circle');
      const span = document('span', { slot: 'badge' }, '5');
      return document.createElement('v-badge', propsObj, [span, icon])
      */
      // const html = document.createElement('v-btn', '1123123')
      // html.innerHTML('123123')
      // return '<button type="button" class="v-btn v-btn--contained theme--dark v-size--default primary"><span class="v-btn__content">Share</span></button>'
      // console.log(this.frameworkComponents.rolresButton.template)
      return this.frameworkComponents.rolresButton.template
    },
    rolesPopup: function (createElement) {
      console.log('1')
      // console.log(createElement('v-btn', 1))
      this.gridApi.refreshCells()
      console.log(this.frameworkComponents.rolresButton.template)
    }
  },
  mounted () {
    console.log(3)
    this.gridApi = this.gridOptions.api
    // this.gridOptions.api.sizeColumnsToFit()
  }
}
</script>
