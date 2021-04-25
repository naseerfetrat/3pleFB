<template>
  <div id="app">

    <div class="container">
    <header id="index"> 
      <div id="title">
        <h1><i class="fa fa-cutlery"></i><a href="#"><span class="highlight">3ple</span>-F-<span class="highlight">B!</span></a></h1>
      </div>
      <nav class="topNav">
        <a v-if="showAdminBoard">
          <router-link to="/admin">Admin Board</router-link>
        </a>
        <a >
          <router-link to="/home" >
            <font-awesome-icon icon="home" />Home
          </router-link>
        </a>
        <a href="#about">
          <router-link to="#about" >
            <font-awesome-icon icon="about" />About
          </router-link>
        </a>
        <div class="dropdown">
            <a class="dropbtn"><a><router-link to="/menu" >
            <font-awesome-icon icon="menu" />Menu
          </router-link><i class="fa fa-caret-down"></i></a></a>
            <div class="dropdown-content">
                <a href="menu#meal">Meal</a>
                <a href="menu#snacks">Snacks</a>
                <a href="menu#drinks">Drinks</a>
                <a href="menu#combo">Combo</a>
            </div>
        </div>
        <a v-if="!currentUser">
          <router-link to="/register" >
            <font-awesome-icon icon="user-plus" />Sign Up
          </router-link>
        </a>
        <a v-if="!currentUser">
          <router-link to="/login" >
            <font-awesome-icon icon="sign-in-alt" />Login
          </router-link>
        </a>

        <a  v-if="currentUser">
          <router-link to="">
          <i class="fa fa-shopping-cart fa-1x" id="cartInfo"><span id="totalItems">0</span> Cart</i>
          </router-link>
        </a>

        <a  v-if="currentUser" @click.prevent="logOut">
          <router-link to="">
            <font-awesome-icon icon="sign-out-alt" />LogOut
          </router-link>
        </a>
        <a href="javascript:void(0);" class="icon" onclick="toggleNav()">&#9776;</a>
      </nav>
  	</header>
   <!-- Modal -->
    <div id="modalDiv" class="modal" style="text-align:center">
      <!-- Modal content -->
      <div class="modalContent">
        <span class="close" id="ccc">&times;</span>
        <h1>SHOPPING CART</h1>
        <p><b>Username: </b>ANONYMOUS</p>
        <p><b>Address: <input type="text" placeholder="Please enter your delivery address" id="userAddr"></b></p>
        <p><b>PhoneNo: <input type="number" placeholder="Please enter your phone number" id="userPhone"></b></p>
        <table id="cartTable">
          <thead>
            <th>Image</th>
            <th>Name</th>
            <th>Quantity</th>
            <th>Price</th>
            <th>Action</th>
          </thead>
          </table>
        <h3>
          TOTAL PRICE: &#x20a6; <span id="totalPrice">0.00</span>
        </h3>
        <button type="submit" id="checkoutBtn">Submit Order</button>
      </div>
    </div>
     <!-- Add To Cart Modal -->
     <div id="generalModal" class="modal">
      <!-- Modal content -->
      <div class="modalContent">
        <span class="close">&times;</span>
        <h1>Message From FastFoodFast</h1>
        <h3 id="generalInfo"></h3>
      </div>
    </div>
    
    <div class="container">
      <router-view />
    </div>
  </div>
  <footer>
        <p>Contact us on <i class="fa fa-address-book fa-1x"> </i> +92748116898 | naseerfetrat@gmail.com</p>
        <p>Subscribe to our <i class="fa fa-github fa-1x"> </i> <i class="fa fa-facebook fa-1x"> </i> <i class="fa fa-instagram fa-1x"></i></p>
        <p><span class="highlight">3ple</span>-F-<span class="highlight">B!</span>, Copyright &copy; 2021</p>
  </footer>
  </div>
</template>

<script>
export default {
  data(){
    return{
      cartItem:null
    }
  },
  mounted(){
          //  Toggle between adding and removing 'responsiveNav' class
      const icon = document.getElementsByClassName('icon');
      icon[0].onclick = () => {
        const navbar = document.querySelector('nav');
        if (navbar.className === 'topNav' || navbar.className === 'topNav stickyNav') {
          navbar.className += ' responsiveNav';
        } else {
          navbar.className = 'topNav';
        }
      };

      // Add sticky menu navbar and footer
      const nav = document.querySelector('nav');
      const footer = document.querySelector('footer');
      // Get the offset position of the menuNav
      const stickyNav = nav.offsetTop;

      window.onscroll = () => {
        // Add the sticky class to the menuNav when you reach its scroll position.
        if (window.pageYOffset >= stickyNav) {
          nav.classList.add('stickyNav');
          // Add a sticky footer
          footer.style.position = 'fixed';
        } else {
          // Remove "sticky" when you leave the scroll position
          nav.classList.remove('stickyNav');
          footer.style.position = 'relative';
        }
      };

      function displayModal(modal, span) {
      modal.style.display = 'block';
      // Close the modal when the user clicks on <span> (x)
      span.onclick = () => {
        modal.style.display = 'none';
      };
      no.onclick = () => {
        modal.style.display = 'none';
      };
      // Also close when anywhere in the window is clicked
      window.onclick = (event) => {
        if (event.target === modal) {
          modal.style.display = 'none';
        }
      };
    }

    const cartTable = document.getElementById('cartTable');
const cartErr = document.getElementById('cartErr');
const checkoutBtn = document.getElementById('checkoutBtn');

const generalModal = document.getElementById('generalModal');
const msg = document.getElementById('generalInfo');
const span1 = document.getElementsByClassName('close')[1]; // Get the <span> element that closes the modal

// Total price for cart items
const total = document.getElementById('totalPrice');
const totalItems = document.getElementById('totalItems');
let totalPrice = 0;

// Hold all the cells in an array so as to transfer to order history
let cartCellArr = [];
let trArr = [];

// Helper function that appends row containing data to table
function appendtoTable(cellArr, tr, tableName) {
  cellArr.forEach((cell) => { // append each cell to td then to tr
    const td = document.createElement('TD'); // create table data
    td.appendChild(cell);
    tr.appendChild(td);
  });
  tableName.appendChild(tr); // append to table
}

const modal = document.getElementById('modalDiv'); // Get the modal
const cart = document.getElementById('cartInfo'); // Get the cart that opens the modal
const span = document.getElementsByClassName('close')[0]; // Get the <span> element that closes the modal

const address = document.getElementById('userAddr');
const phone = document.getElementById('userPhone');
// Open the modal when the user clicks on the cart,
cart.onclick = () => {
  if (localStorage.getItem('address')) {
    address.value = localStorage.getItem('address');
  }
  if (localStorage.getItem('phone')) {
    phone.value = localStorage.getItem('phone');
  }

  modal.style.display = 'block';
  const condition = Number(total.innerHTML) === 0;

  // Style checkout button
  checkoutBtn.style.cursor = condition ? 'not-allowed' : 'pointer';
  checkoutBtn.style.backgroundColor = condition ? '#212121' : '#2ec371';
  checkoutBtn.style.color = condition ? 'goldenrod' : 'white';
  checkoutBtn.style.opacity = condition ? 0.6 : 1;

  displayModal(modal, span);
};

  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    showAdminBoard() {
      if (this.currentUser && this.currentUser.roles) {
        return this.currentUser.roles.includes('ROLE_ADMIN');
      }

      return false;
    },
    showModeratorBoard() {
      if (this.currentUser && this.currentUser.roles) {
        return this.currentUser.roles.includes('ROLE_MODERATOR');
      }

      return false;
    },
   
  },
  methods: {
    logOut() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/login');
    }
  }
};
</script>

<style scoped>
* {box-sizing: border-box;}

body, html{
    height:100%;
    font: 15px/1.5 Verdana, sans-serif;
    padding: 0;
    margin:0;
    position:relative;
    background-color: white;
    text-align: center;
}
.containerr{
    width: 90%;
    min-height: 100vh;
    margin: auto;
}

h1{
    margin-top: 60px;
    text-shadow: 1px 1px 1px goldenrod;
}
/* header*/
header{
    padding-top: 30px;
}
header#index{
    text-align: center;
    padding-bottom: 60px;
    border: none;
}
header a{
    color: #212121;
    text-decoration: none;
    text-transform: uppercase;
    font-size: 17px;
}

header #title{
    float: left;
}
header #title h1{
    margin: 0;
}
header #title h1 a{
    font-size: 30px;
    text-transform: none;
    margin-right: 45px
}

header a:hover{  /*Changes appearance of menu links when the mouse is moved over the element */
    color: goldenrod;
    font-weight: bold;
}
.highlight{
    color: goldenrod;
}
.card{
    border: none;
    box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2),0 6px 20px 0 rgba(0, 0, 0, 0.19);
}

.topNav {
  overflow: hidden;
  float: right;
}

/* Style the links inside the navigation bar */
.topNav a {
  float: left;
  display: block;
  text-align: center;
  padding: 14px;
  text-decoration: none;
  font-size: 15px;
  font-weight: 550;
}

.topNav .search{
  float: right;
  border: 1px solid goldenrod;
  margin: 10px 12px;
  padding: 0;
}
.topNav .search input[type=text] {
  padding: 7px;
  border: none;
  outline: none;
}
.topNav .search button {
  float: right;
  padding: 7px 12px;
  background: #cccccc;
  border: none;
  cursor: pointer;
  font-size: 14px
}
.topNav .search:hover{
  background: none;
}

/* Hide the link that should open and close the topNav on small screens */
.topNav .icon {
  display: none;
  padding: 14px;
  color: goldenrod;
}
.current{
  color: goldenrod;
  font-weight: bold;
  text-decoration: underline;
}

.dropdown {
  float: left;
  overflow: hidden;
}
.dropdown .dropbtn {
  border: none;
  outline: none;
  padding: 3px;
  background-color: inherit;
  font-family: inherit;
  text-decoration: none;
}
.dropdown-content {
  display: none;
  position: fixed;
  color: black;
  background-color: #cccccc;
  min-width: 100px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  margin-top: 40px;
  z-index: 1;
}

/* Style the links inside the dropdown */
.dropdown-content a {
  float: none;
  color: black;
  background:rgb(0,0,0,0.1);
  font-size: 15px;
  text-transform: capitalize;
  padding: 12px 14px;
  text-decoration: none;
  display: block;
  text-align: left;
  font-weight: 500;
}

.topNav .dropdown-content a:hover {
  background-color: #2c2c2c;
}

.dropdown:hover .dropdown-content {
  display: block;
  z-index: 1;
}

.welcome{
  margin-top: 50px;
}

/* Sticky navbar
/* The sticky class is added to the navbar with JS when it reaches its scroll position */
.stickyNav{
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  background: #212121;
  color: white;
  float: none;
  z-index: 1;
}

.stickyNav a{
  color: white; 
  padding-left: 18px; 
  text-align: center;
}

.stickyNav .dropdown-content {
  margin-top: 55px
}

.stickyNav a.search input[type=text], 
.stickyNav a.search button{
  height: 31px;
}
.stickyNav a.current{
  color: goldenrod; 
}
.stickyNav #cartInfo{
  float: left;
}
/* Add some top padding to the page content to prevent sudden quick movement (as the navigation bar gets a new position at the top of the page (position:fixed and top:0) */
.stickyNav + .content {
  padding-top: 60px;
}

/* When the screen is less than 700 pixels wide, only show the link that should open and close the topNav (.icon) */
@media screen and (max-width: 780px) {
  header #title h1 a{
    font-size: 26px;
    margin-right: 5px;
  }
  .menu h1{margin-top: 50px;}
  .topNav{float: none;}
  .topNav a, #cartInfo{
    display: none;
    font-size: 14px;
  }
  .dropdown .dropbtn{
    display: none;
  }
  .topNav a.icon {
    float: right;
    display: block;
    background: #212121;
  }
  .topNav a:hover {background: #212121;}
  .topNav .icon:hover{
    background: #757575;
  }
  /* Adding responsive class to .topNav */
  .responsiveNav {
    position: relative;
    background: #f5f5f5;
    padding-bottom: 10px;
  }

  .responsiveNav a {
    float: none;
    display: block;
    text-align: left;
    font: 14px;
  }
  .responsiveNav #cartInfo{
    float: left;
    display: inline;
    font-size: 16px;
  }
  .responsiveNav .search{
    float: left;
  }

  .responsiveNav a.icon {
    position: absolute;
    padding-left: 12px;
    right: 0;
    top: 0;
  }

  .responsiveNav .dropdown {float: none;}
  .responsiveNav .dropdown-content {
    position: relative;
    margin-top: 0;
  }

  .responsiveNav .dropdown .dropbtn{
    display: block;
    width: 100%;
    text-align: left;
    padding: 0px;
  }
  .responsiveNav .dropdown-content a:hover {
    background-color: #f9f9f9;
  }

  .responsiveNav.stickyNav{
    position: fixed;
    background: #212121;
  }

  .responsiveNav.stickyNav a:hover{
    background: #cccccca6
  }
  .welcome{
    margin-top: 10px;
  }
}

@media screen and (max-width: 550px){
  body, html{
    overflow-x: hidden;
    overflow-y: auto;
  }

  header #title h1 a{
    font-size: 32px;
    margin-right: 300px;
  }
  .topNav{
    display: inline-block;
    float:none;
    margin-top: 15px;
  }
  .topNav a{
    padding-right: 50px;
  }
  .stickyNav{
    margin-top: 0;
  }
  .formTitle{
    margin-top: 20px;
  }
}

@media only screen and (max-width: 400px) {
  body, html{
      font: 12px/20px "Helvetica neue", Helvetica, Arial, sans-serif;
      font-weight: normal;
  }
}

/* Footer */
footer{
    margin-top: 5%;
    bottom: 0;
    width: 100%;
    color: #ffffff;
    background-color: #212121;
    text-align: center;
    /* text-shadow: #353637; */
    font-size: 12px;
}

footer a{
    text-decoration: none;
}

@media only screen and (max-width: 600px){
    h2{
        font-size: 18px;
    }
}
/* On smaller screens, decrease text size */
@media screen and (max-width: 500px) {
  .text {font-size: 18px}
}

/*****MODAL*****/
.modal {
    display: none; /* Hidden by default */
    position: fixed;
    left: 0;
    top: 0;
    width: 100%; 
    height: 100%; 
    overflow: auto; /* Enable scroll if needed */
    background-color: rgba(0, 0, 0, 0.4);
    transition: visibility 0s linear 0.25s, opacity 0.25s 0s, transform 0.25s;
  }

  /* Modal Content/Box */
.modalContent {
    position: absolute;
    overflow-y: auto;
    overflow-x: hidden;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    padding: 20px;
    width: 100%;
    border-radius: 0.5rem;
    border: 1px solid #888;
    background-color: #fefefe;
    margin-bottom: 100px;
}
.modalContent h1{
    margin-top: 20px;
}
.modalContent.cart{
    max-height: 100%;
    margin-top: 40px;
}
.modalContent.general {
    width: 80%;
}

#cartInfo{
    margin: 10px;
    font-size: 18px;
}
#cartInfo:hover {
    cursor: pointer;
    color: goldenrod;
}

  
  /* Close (x) Button */
.close, .closeLogin{
    float: right;
    font-size: 28px;
    font-weight: bold;
    color: #aaa;
}
  
.close:hover,
.close:focus,
.closeLogin:hover,
.closeLogin:focus {
    text-decoration: none;
    cursor: pointer;
    color: black;
} 

#userAddr, #userPhone,
#buyNowAddr, #buyNowPhone{
    height: 36px;
    width: 300px;
    font-size: 16px;
    padding: 10px;
    border: 0;
    font: bold;
}

</style>