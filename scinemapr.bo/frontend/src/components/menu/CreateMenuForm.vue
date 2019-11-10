<template lang="html">
	<section class="alerts">
		<div class="row">
			<div class="col-lg-12 grid-margin stretch-card">
				<div class="card">
					<div class="card-body">
						메뉴 등록
					</div>
				</div>
			</div>
			<div class="col-lg-6 grid-margin stretch-card">
				<div class="card">
					<div class="card-body">
						asd
					</div>
				</div>
			</div>
			<div class="col-lg-6 grid-margin stretch-card">
				<div class="card">
					<div class="card-body">
						asd1
						<input type="button" value="fetch" v-on:click="test('asd')"/>
						<form id="myForm" @submit.prevent="sendPost">
							<h4 class="card-title">메누</h4>
							<p class="card-description">
								Basic form elements
							</p>
							<form class="forms-sample">
							<b-form-group label="Name"  label-for="input5">
							<b-form-input type="text" id="input5" placeholder="Name"></b-form-input>
							</b-form-group>
							<b-form-group label="Email address"  label-for="input6">
							<b-form-input type="email" id="input6" placeholder="Email"></b-form-input>
							</b-form-group>
							<b-form-group label="Password"  label-for="input7">
							<b-form-input type="password" id="input7" placeholder="Password"></b-form-input>
							</b-form-group>
							<b-form-group label="Upload file" label-for="input8">
							<b-form-file v-model="file" id="inpu8" :state="Boolean(file)" placeholder="Choose a file..."></b-form-file>
							</b-form-group>
							<b-form-group label="City"  label-for="input9">
							<b-form-input type="text" id="input9" placeholder="Location"></b-form-input>
							</b-form-group>
							<b-form-group label="Message"  label-for="input10">
							<b-form-textarea id="input10" v-model="text" placeholder="Message" :rows="3" :max-rows="6"></b-form-textarea>
							</b-form-group>
							<b-button type="submit" variant="success" class="mr-2">Submit</b-button>
							<b-button variant="light">Cancel</b-button>
							</form>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
</template>

<script>
// import * as types from '../../store/mutation-types'

export default {
  name: 'CreateMenuForm',
  data() {
    return {
      msg: 'Welcome to Your Vue.js App',
    };
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
