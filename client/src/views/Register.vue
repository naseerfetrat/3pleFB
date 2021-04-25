<template>
  <div class="col-md-12" style="text-align:center">
    <h1 class="formTitle">Create an Account</h1>
			<h2>New Customers</h2>
			<h4>	By creating an account with our restaurant, you will be able to move through the checkout process faster, view and track your orders in your account and more.</h4>
			<br>
			  <div id="signupForm">
					<form @submit.prevent="handleRegister">
            <div v-if="!successful">
						
						<i style="color: red; text-shadow: 1.2px 1.2px 1px black; ">All fields below are required</i>
						<br><i class="fa fa-user fa-2x" aria-hidden="true" ></i><br>
            <div 
            id="usernameErr"
            v-if="submitted && errors.has('username')"
            >{{errors.first('username')}}</div>
						<label for="username">Username</label>
							<input 
              v-model="user.username"
              v-validate="'required|min:3|max:20'"
              type="text"
              class="form-control"
              name="username" id="username"  placeholder="Enter a username" autocomplete="off" autofocus required>
						<label for="email">Email</label>
							<input 
              v-model="user.email"
              v-validate="'required|email|max:50'"
              type="email"
              class="form-control"
              name="email" id="email" placeholder="Enter your email address" required>
							<div id="emailErr" v-if="submitted && errors.has('email')">{{errors.first('email')}}</div>
							<label for="password">Password</label>
							<input 
              v-model="user.password"
              v-validate="'required|min:6|max:40'"
              type="password"
              class="form-control"
              name="password" id="password" placeholder="Create your password" required>
							<div 
              id="passwordErr"
              v-if="submitted && errors.has('password')"
              >{{errors.first('password')}}</div>
							<label for="password2">Confirm Password</label>
							<input id="password2" name="password2" type="password" placeholder="Retype your password" required>
							<div id="password2Err"></div>
						<button id="signupBtn" >Create Account</button>
						<span class="white">Already registered? <a><router-link to="/login" >
            <font-awesome-icon icon="sign-in-alt" />Login
          </router-link></a> instead</span>
            </div>
            <div
        v-if="message"
        class="alert"
        :class="successful ? 'alert-success' : 'alert-danger'"
      >{{message}}</div>
					</form>

				</div>			
  </div>
</template>

<script>
import User from '../models/user';

export default {
  name: 'Register',
  data() {
    return {
      user: new User('', '', ''),
      submitted: false,
      successful: false,
      error2:'Password Deos not Matched!',
      message: ''
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    }
  },
  mounted() {
    if (this.loggedIn) {
      this.$router.push('/profile');
    }
    // CONSUME SIGNUP ENDPOINT
const signupBtn = document.getElementById('signupBtn');
const name = document.getElementById('username');
const email = document.getElementById('email');
const password = document.getElementById('password');
const password2 = document.getElementById('password2');

const usernameErr = document.querySelector('div#usernameErr');
const emailErr = document.querySelector('div#emailErr');
const passwordErr = document.querySelector('div#passwordErr');
const password2Err = document.querySelector('div#password2Err');

/**
 * isValidPassword method
 * @param {string} password
 * @returns {string} true or error messages
 */
// Adapted from https://stackoverflow.com/questions/19605150/regex-for-password-must-contain-at-least-eight-characters-at-least-one-number-a
function isValidPassword(value) {
  if (!/[a-z]/.test(value)) {
    return 'Your password must contain at least one lowercase letter';
  } if (!/[A-Z]/.test(value)) {
    return 'Your password must contain at least one uppercase letter';
  } if (!/\d/.test(value)) {
    return 'Your password must contain at least one number';
  } if (!/[@$!%*?&]/.test(value)) {
    return 'Your password must contain at least one of these special characters: @, $, !, %, *, ?, &';
  } if (value.length < 6) {
    return 'Your password must be composed of at least 6 characters';
  }
  return 'true';
}

name.onchange = () => {
  password2Err.innerHTML = '';
};
email.onchange = () => {
  emailErr.innerHTML = /\S+@\S+\.\S+/.test(email.value) ? '' : 'Please enter a valid email';
  usernameErr.innerHTML = '';
  password2Err.innerHTML = '';
};
password.onchange = () => {
  passwordErr.innerHTML = isValidPassword(password.value) === 'true' ? '' : isValidPassword(password.value);
  password2Err.innerHTML = password2.value === password.value ? '' : 'Passwords don\'t match';
};
password2.oninput = () => {
  password2Err.innerHTML = password2.value === password.value ? '' : 'Passwords don\'t match';
};

signupBtn.onmouseover = () => {
  if (emailErr.innerHTML !== '' || passwordErr.innerHTML !== '' || password2Err.innerHTML !== '') {
    signupBtn.style.opacity = 0.6;
  } else {
    signupBtn.style.opacity = 1;
    signupBtn.style.cursor = 'pointer';
  }
};
  },
  methods: {
    handleRegister() {
      this.message = '';
      this.submitted = true;
      this.$validator.validate().then(isValid => {
        if (isValid) {
          this.$store.dispatch('auth/register', this.user).then(
            data => {
              this.message = data.message;
              this.successful = true;
            },
            error => {
              this.message =
                (error.response && error.response.data && error.response.data.message) ||
                error.message ||
                error.toString();
              this.successful = false;
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
    margin: 250px 0 270px;
    position: relative;
}

#loginForm {
    margin: 150px 0;
    margin-bottom: 100px;
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