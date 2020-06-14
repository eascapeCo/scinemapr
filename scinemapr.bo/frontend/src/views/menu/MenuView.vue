<template>
  <div class="menuView">
    <v-container>
      <v-row>
        <v-col>
          <v-card
            class="pa-2"
            tile
            outlined
          >
            <v-treeview
              v-model="selection"
              :items="items"
              :selection-type="selectionType"
              selectable
              return-object
              open-all
            ></v-treeview>
          </v-card>
        </v-col>
        <v-col>
          <v-card
            class="pa-2"
            tile
            outlined
          >
            <form>
              <v-text-field v-model="mnuName" label="메뉴명" />
              <v-text-field v-model="urlAdr" label="메뉴 URL" />
              <v-select v-model="useYn" :items="[true, false]" label="사용여부"></v-select>
              <v-btn class="mr-4" @click="save">등록</v-btn>
              <v-btn class="mr-4" @click="update">수정</v-btn>
            </form>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
export default {
  name: 'GirdSample',
  data: () => ({
    selectionType: 'independent',
    selection: [],
    items: [
      {
        id: 1,
        name: 'Root',
        children: [
          { id: 2, name: 'Child #1' },
          { id: 3, name: 'Child #2' },
          {
            id: 4,
            name: 'Child #3',
            children: [
              { id: 5, name: 'Grandchild #1' },
              { id: 6, name: 'Grandchild #2' }
            ]
          }
        ]
      }
    ],
    mnuName: null,
    urlAdr: null,
    useYn: false
  }),
  methods: {
    save: function () {
      console.log('submit')
      console.log(this.mnuName)
    },
    update: function () {
      console.log('submit')
    }
  }
}
</script>
