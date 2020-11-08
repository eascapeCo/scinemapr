<template>
  <div class="register">
    <v-container fluid>
      <v-layout align-center justify-center>
        <v-flex>
          <v-card class="elevation-12">
            <v-card class="px-4">
              <v-card-text>
                <v-form ref="registerForm" v-model="valid" lazy-validation>
                  <v-row>
                    <v-col cols="12">
                      <v-text-field v-model="userId" :rules="[rules.required]" label="Admin ID" maxlength="20" required></v-text-field>
                    </v-col>
                    <v-col cols="12">
                      <v-select
                        v-model="selectedRoles"
                        :items="roles"
                        label="Select Roles"
                        multiple
                      >
                        <template v-slot:prepend-item>
                          <v-list-item
                            ripple
                            @click="toggle"
                          >
                            <v-list-item-action>
                              <v-icon :color="selectedRoles.length > 0 ? 'indigo darken-4' : ''">
                                {{ icon }}
                              </v-icon>
                            </v-list-item-action>
                            <v-list-item-content>
                              <v-list-item-title>
                                Select All
                              </v-list-item-title>
                            </v-list-item-content>
                          </v-list-item>
                          <v-divider class="mt-2"></v-divider>
                        </template>
                        <template v-slot:append-item>
                          <v-divider class="mb-2"></v-divider>
                          <v-list-item disabled>
                            <v-list-item-avatar color="grey lighten-3">
                              <v-icon>
                                mdi-food-apple
                              </v-icon>
                            </v-list-item-avatar>

                            <v-list-item-content v-if="likesAllFruit">
                              <v-list-item-title>
                                Holy smokes, someone call the fruit police!
                              </v-list-item-title>
                            </v-list-item-content>

                            <v-list-item-content v-else-if="likesSomeFruit">
                              <v-list-item-title>
                                Roles Count
                              </v-list-item-title>
                              <v-list-item-subtitle>
                                {{ selectedRoles.length }}
                              </v-list-item-subtitle>
                            </v-list-item-content>

                            <v-list-item-content v-else>
                              <v-list-item-title>
                                How could you not like fruit?
                              </v-list-item-title>
                              <v-list-item-subtitle>
                                Go ahead, make a selection above!
                              </v-list-item-subtitle>
                            </v-list-item-content>
                          </v-list-item>
                        </template>
                      </v-select>
                      </v-col>
                    <v-spacer></v-spacer>
                    <v-col class="d-flex ml-auto" cols="12" sm="2" xsm="12">
                      <v-btn x-large block :disabled="!valid" color="success" @click="register">Register</v-btn>
                    </v-col>
                  </v-row>
                </v-form>
              </v-card-text>
            </v-card>
          </v-card>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>

export default {
  created () {
    this.$axios.get('/api/roleList', {
      headers: {
        Authorization: this.$store.state.access_token,
        'Content-Type': 'application/json'
      }
    }).then((res) => {
      res.data.forEach(element => {
        this.roles.push(element.rolNm)
      })
    })
  },
  data: () => ({
    dialog: true,
    tab: 0,
    tabs: [
      { name: 'Register', icon: 'mdi-account-outline' }
    ],
    valid: true,
    userId: '',
    verify: '',
    loginPassword: '',
    loginEmail: '',
    loginEmailRules: [
      v => !!v || 'Required',
      v => /.+@.+\..+/.test(v) || 'E-mail must be valid'
    ],
    emailRules: [
      v => !!v || 'Required',
      v => /.+@.+\..+/.test(v) || 'E-mail must be valid'
    ],
    roles: [],
    selectedRoles: [],
    show1: false,
    rules: {
      required: value => !!value || 'Required.',
      min: v => (v && v.length >= 8) || 'Min 8 characters'
    }
  }),
  computed: {
    passwordMatch () {
      return () => this.password === this.verify || 'Password must match'
    },
    likesAllFruit () {
      return this.selectedRoles.length === this.roles.length
    },
    likesSomeFruit () {
      return this.selectedRoles.length > 0 && !this.likesAllFruit
    },
    icon () {
      if (this.likesAllFruit) return 'mdi-close-box'
      if (this.likesSomeFruit) return 'mdi-minus-box'
      return 'mdi-checkbox-blank-outline'
    }
  },
  methods: {
    reset () {
      this.$refs.form.reset()
    },
    resetValidation () {
      this.$refs.form.resetValidation()
    },
    toggle () {
      this.$nextTick(() => {
        if (this.likesAllFruit) {
          this.selectedRoles = []
        } else {
          this.selectedRoles = this.roles.slice()
        }
      })
    },
    register () {
      const data = {
        id: this.userId,
        roles: this.selectedRoles
      }
      this.$axios.post('/api/admin', data, {
        headers: {
          Authorization: this.$store.state.access_token,
          'Content-Type': 'application/json'
        }
      }).then((res) => {
        console.log(res)
      }).catch((error) => {
        console.log('Error: ' + JSON.stringify(error.response))
        console.log(error.response.data.detail)
      })
    }
  }
}

</script>
