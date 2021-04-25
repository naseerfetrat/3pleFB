<template>
  <div class="col-md-12" style="text-align:center">
    <h1 class="formTitle">Login</h1>
			<h4>Have an account with us? Log in below. Otherwise, <a><router-link to="/register" >
            <font-awesome-icon icon="user-plus" />Sign Up
          </router-link></a> to register.</h4>
			<br>
			<div id="loginForm">
				<form name="form" @submit.prevent="handleLogin">
					<i class="fa fa-user fa-2x" aria-hidden="true"></i>
					<div id="usernameErr"></div>
					<label for="username">Username</label>
					<input v-model="user.username"
            v-validate="'required'"
            type="text"
            class="userForm"
            name="username"  id="usernameEmail"  placeholder="Enter your username or email" required >
					<div id="emailErr" v-if="errors.has('username')"></div>
					<label for="password">Password</label>
					<input v-model="user.password"
            v-validate="'required'"
             class="userForm" id="password" name="password" type="password" placeholder="Enter your password" required>
					<div id="passwordErr" v-if="errors.has('password')"></div>
					<div><input type="checkbox" id="checkbox" onclick="togglePassword()">Show Password</div>
					<div id="password2Err"></div>
					<button id="loginBtn"  :disabled="loading">
            <span v-show="loading" class="spinner-border spinner-border-sm"></span>
            <span>Login</span></button>
					<a href="#" class="white">Forgot Password?</a>
          
          <div v-if="message" class="alert alert-danger" role="alert">{{message}}</div>
         
				</form>
			</div>
			<h4 class="last">Having difficulty signing in? <a href="./contact">Contact Us</a></h4>
  </div>
</template>

<script>
import User from '../models/user';

export default {
  name: 'Login',
  data() {
    return {
      user: new User('', ''),
      loading: false,
      message: ''
    };
  },
  mounted(){
    function togglePassword() {
                const input = document.getElementById("password");
                if (input.type === "password") {
                    input.type = "text";
                } else {
                    input.type = "password";
                }
            }
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    }
    
  },
  created() {
    if (this.loggedIn) {
      this.$router.push('/profile');
    }
  },
  methods: {
    handleLogin() {
      this.loading = true;
      this.$validator.validateAll().then(isValid => {
        if (!isValid) {
          this.loading = false;
          return;
        }

        if (this.user.username && this.user.password) {
          this.$store.dispatch('auth/login', this.user).then(
            () => {
              this.$router.push('/profile');
            },
            error => {
              this.loading = false;
              this.message =
                (error.response && error.response.data && error.response.data.message) ||
                error.message ||
                error.toString();
            }
          );
        }
      });
    }
  }
};
</script>

<style scoped>
.formTitle{
    margin-top: 100px;
}

#signupForm, #loginForm {
    padding: 100px 0;
    margin: 200px 0 280px;
    position: relative;
}

#loginForm {
    margin: 150px 0;
    margin-bottom: 150px;
}

/* SignUp and Login forms */
form {
    width: 300px;
    position: absolute;
    top: 20%;
    left: 50%;
    transform: translate(-50%, -50%);
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    border: 1px solid #2ec371;
    padding: 15px;
    margin-top: 15px;
    margin-bottom: 10px;
    border-radius: 15px;
    background-color: darkgoldenrod;
    box-shadow: 0px 2px 2px 1px rgba(0, 0, 0, 0.2);
}
.white, .white a{
    font-size: 13px;
    color: white;
    text-shadow: 1.5px 1.5px 2px #000000;
}

input {
    height: 36px;
    width: 250px;
    font-size: 16px;
    margin: 12px 0 10px;
    padding: 10px;
    border: 0;
}
label{
    color:white;
    font: 18px/1.5 Arial, Helvetica, sans-serif;
    text-shadow: 1.5px 1.5px 2px #000000;
}

button {
    margin: 15px 0 10px;
    border: 0;
    border-radius: 5px;
    font-size: 20px;
    color: #fff;
    padding: 20px 50px;
    background-color: #2ec371;
    opacity: 0.6;
    width: 250px;
}

#checkbox{
    height: 15px;
    width: 15px;
    margin: 0 0 0 -120px;
}

.fa-2x {
    color: #212121;
    border: 2px solid #2ec371;
    border-radius: 100%;
    padding: 10px;
    margin-bottom: 10px;;
}

#usernameErr, #emailErr, #passwordErr, #password2Err{
    color: red;
    text-shadow: 1px 1px 1px #000000;
}
</style>