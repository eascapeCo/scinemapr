<template>
  <div class="adminList">
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
import moment from 'moment'

export default {
  name: 'adminList',
  created () {
    console.log('여긴타냐')
    this.$axios.get('/api/admin/adminList', {
      headers: {
        Authorization: this.$store.state.access_token,
        'Content-Type': 'application/json'
      }
    })
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
      { headerName: '관리자 번호', field: 'admNo' },
      { headerName: '관리자 아이디', field: 'id' },
      { headerName: '등록일자', field: 'regDate', valueFormatter: this.dateFormatter },
      { headerName: '등록자 번호', field: 'regNo' },
      { headerName: '최종 수정 일자', field: 'modDate', valueFormatter: this.dateFormatter },
      { headerName: '최종 수정자 번호', field: 'modNo' },
      { headerName: '비밀번호 수정여부', fleld: 'pwdExpd', checkboxSelection: true }
    ]
    this.rowData = []
    this.context = { componentParent: this }
    this.frameworkComponents = {
      // rolresButton: RolesButton
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
    },
    dateFormatter: function (params) {
      console.log(params)
      return moment(params.value, 'YYYYMMDDhhmmss').format('YYYY/MM/DD HH:mm:ss')
    }
  },
  mounted () {
    console.log(3)
    this.gridApi = this.gridOptions.api
    // this.gridOptions.api.sizeColumnsToFit()
  }
}

</script>
<style lang="scss">
.ag-theme-alpine .ag-checkbox-input-wrapper {
  background: white;
}

.ag-theme-alpine .ag-checkbox-input-wrapper::after {
  font-family: 'Font Awesome 5 Free';
  font-weight: bold;
  content: '\f00d';
  color: red;
}
.ag-theme-alpine .ag-checkbox-input-wrapper.ag-checked::after {
  content: '\f00c';
  color: green;
}
.ag-theme-alpine .ag-checkbox-input-wrapper.ag-indeterminate::after {
  content: '\f068';
  color: gray;
}
</style>
