<template>
  <v-content>
    <v-container
      class="fill-height"
      fluid
    >
      <v-row
        align="center"
        justify="center"
      >
        <v-col
          cols="12"
          sm="8"
          md="4"
        >
          <v-card class="elevation-12">
            <v-toolbar
              color="primary"
              dark
              flat
            >
              <v-toolbar-title>Login form</v-toolbar-title>
              <v-spacer></v-spacer>
            </v-toolbar>
            <v-card-text>
              <v-form ref="form">
                <v-text-field
                  v-model="user"
                  label="User"
                  name="user"
                  type="text"
                ></v-text-field>

                <v-text-field
                  v-model="password"
                  label="Password"
                  name="password"
                  type="password"
                ></v-text-field>
              </v-form>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <!-- <router-link to="/"> -->
                <v-btn color="primary" @click="callLogin">Login</v-btn>
              <!-- </router-link> -->
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-content>
</template>

<script>

export default {
  name: 'login',
  data () {
    return {
      loginError: false,
      user: '',
      password: '',
      error: false,
      errors: []
    }
  },
  methods: {
    callLogin () {
      // this.errors = []
      this.$store.dispatch('LOGIN', {
        id: this.user,
        pwd: this.password
      }).then(() => {
        this.$router.push('/')
      }).catch(error => {
        this.loginError = true
        this.errors.push(error)
        this.error = true
      })
    }
  }
}
</script>
