<template>
  <div class="container" style="text-align:center">
    <!-- <header class="jumbotron">
      <h3>{{content}}</h3>
    </header> -->

  <!-- The sidebar -->
				<div id="mySidenav" class="sidenav">
					<a href="javascript:void(0)" id="closeBtn">&times;</a>
					<a href="#" id="viewStats">View Statistics</a>
					<a href="#" id="getOrders">Manage Orders</a>
					<a href="#" id="createMenu">Add Food to Menu</a>
					<a href="#" id="getMenu">Manage Menu</a>
				</div>
 <div id="main">
			<section class="main">
				<div class="container">
					<h2 id="openBtn">&#9776; Manage Application</h2>						
					<div id="allStats" style="margin-bottom:330px">
							<div class= "err" id="ordersStatsErr"></div>
							<div class= "err" id="usersStatsErr"></div>
							<div class= "err" id="menuStatsErr"></div>

							<div class="statsCards">
							  <div id="statsErr"></div>

								<div class="statsCard card">
										<h4>Total Sales</h4>
										<h3>&#x20a6; <span id="sales">0</span>.00</h3>
								</div>

								<div class="statsCard card">
										<h4>No of Orders</h4>
										<h3 id="orders"><i>Loading...</i></h3>
								</div>

								<div class="statsCard card">
										<h4>No of Users</h4>
										<h3 id="users"><i>Loading...</i></h3>
								</div>

								<div class="statsCard card">
										<h4>No of Items</h4>
										<h3 id="menu"><i>Loading...</i></h3>
								</div>
							</div>
					</div>

					<div id="manageOrdersDiv">
					<section class="admin" id="allOrders">
						<h2>MANAGE ALL ORDERS</h2>
						<div id="manageOrdersMsg"></div>
						<div id="search">
						<i class="fa fa-search"></i>
						<input type="text" id="orderSearch" placeholder="Search for order(s) by any column value...">
						</div>		
							<table id="adminOrdersTable">
								<thead>
									<tr>
										<th>Order ID</th>
										<th>Food</th>
										<th>Qty</th>
										<th>Price</th>
										<th>Status</th>
										<th>Created At</th>
										<th>Last Modified</th>
										<th colspan="2">Action</th>
										<th><i>Mark as completed</i></th>
									</tr>
								</thead>
								<tbody id="adminOrdersTableBody"></tbody>
							</table>
						</section>
					</div>

						<div id="generalAdminModal" class="modal">
								<!-- Modal content -->
								<div class="modalContent general">
									<span class="close">&times;</span>
									<h1>FastFoodFast Admin</h1>
									<h3 id="generalInfo"></h3>
									<button id="yes" class="approveBtn">YES</button>
									<button id="no" class="declineBtn">NO</button>
								</div>
						</div>

							<section id="createMenuModal" class="modal">
								<!-- Modal content -->
								<div class="modalContent">
									<span class="close">&times;</span>
									<h1>Add Food to Menu</h1>
										<form method="post" id="addForm">
												<table>
													<tr>
														<td class="label"><label for="name">Name:</label></td>
														<td><input type="text" id="name" name="name" v-model="item.name"></td>
                                                        
													</tr>
												<tr>
													<td class="label"><label for="price">Price:</label></td>
													<td><input type="number" id="price" name="price" v-model="item.price"></td>

												</tr>
												<tr>
													<td class="label"><label for="category" >Category:</label></td>
													<td>
														<select id="category" name="category" v-model="item.category">
															<option value="meal">Meal</option>
															<option value="snack">Side/Snack</option>
															<option value="drink">Drink</option>
															<option value="combo">Combo</option>
														</select>
													</td>
												</tr>
												<tr>
													<td class="label"><label for="img">Image:</label></td>
													<!-- <td><input type="file" name="img" id="img"></td> -->
                                                    <input type="text" name="img" v-model="item.image_url">
												</tr>
												<tr>
													<td class="label"><label for="description">Description:</label></td>
													<td><textarea name="description" id="desc" v-model="item.discription" placeholder="Add a description of your food here"></textarea></td>
												</tr>
												</table>
												<h3 id="createMenuMsg" class="err"></h3>
												<button type="submit" id="addBtn" @click="add">Add Food</button>
												<button type="reset" id="resetBtn" class="reset">Reset</button>
										</form>
								</div>
							</section>

					<section class="admin" id="allFood">
						<h2>MANAGE MENU ITEMS</h2>
						<div id="manageMenuMsg" class="err"></div>
						<div id="searchMenu">
							<i class="fa fa-search"></i>
							<input type="text" id="menuSearchInp" placeholder="Search for food item...">
							</div>	
									<table id="adminMenuTable">
										<thead>
											<tr>
												<th>ID</th>
												<th>Name</th>
												<th>Price(&#x20a6;)</th>
												<th>Genre</th>
												<th>Available?</th>
												<th>Created At</th>
												<th>Last Modified</th>
												<th colspan="2">Action</th>
											</tr>
										</thead>
										<tbody id="adminMenuTableBody"></tbody>
									</table>
					</section>

				</div>
			</section>
		</div>

  </div>
</template>

<script>
import UserService from '../services/user.service';
import itemService from '../services/item.service';
export default {
  name: 'Admin',
  data() {
    return {
      content: '',
      item:{
            name:null,
            image_url:null,
            category:null,
            discription:null,   
            price:null
      }
    };
  },
  methods:{
      async add(){
          try {
                await itemService.addItem(this.item);
                this.$router.push({
                name: "menu",
                });
            } catch (error) {
                console.log(error);
            }
      }
  },
  mounted() {
    UserService.getAdminBoard().then(
      response => {
        this.content = response.data;
      },
      error => {
        this.content =
          (error.response && error.response.data && error.response.data.message) ||
          error.message ||
          error.toString();
      }
    );
    const icon = document.getElementsByClassName('icon');
		icon[0].onclick = () => {
			const navbar = document.querySelector('nav');
			if (navbar.className === 'topNav' || navbar.className === 'topNav stickyNav') {
				navbar.className += ' responsiveNav';
			} else {
				navbar.className = 'topNav';
			}
		};
        const open = document.getElementById('openBtn');
        const close = document.getElementById('closeBtn');

        function toggleSideNav(width) {
        document.getElementById('mySidenav').style.width = width;
        document.getElementById('main').style.marginLeft = width;
        }

        open.onclick = () => {
        toggleSideNav('250px');// 0,0,0,0.4
        };

        close.onclick = () => {
        toggleSideNav('0');
        };

        window.onclick = (event) => {
        if (event.target !== open) {
            toggleSideNav('0');
        }
        };

        // Modal display
        const generalAdminModal = document.getElementById('generalAdminModal');
        const modalTxt = document.getElementById('generalInfo');
        const span0 = document.getElementsByClassName('close')[0];
        const yes = document.getElementById('yes');
        const no = document.getElementById('no');
        // Display modal
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
        

        const addForm = document.getElementById('addForm');
            const createMenuLink = document.getElementById('createMenu');
            const createMenuModal = document.getElementById('createMenuModal');
            const span1 = document.getElementsByClassName('close')[0];
            const addBtnInput = document.getElementById('addBtn');
            const menuMsg = document.getElementById('createMenuMsg');

            createMenuLink.onclick = () => {
                displayModal(createMenuModal, span1);
                toggleSideNav('0');
            };
  }
};
</script>

<style scoped>
#openBtn{
    font-size: 25px;
    cursor: pointer;
    color: goldenrod;
    background: #212121;
    text-shadow: none;
    padding: 5px;
    margin-top: 5%
}
.sidenav {
    height: 100%;
    width: 0;
    position: fixed;
    z-index: 1;
    top: 0;
    left: 0;
    background-color: #212121;
    overflow-x: hidden;
    transition: 0.5s;
    padding-top: 60px;
}

.sidenav a {
    padding: 8px 8px 8px 32px;
    text-decoration: none;
    font-size: 22px;
    color: #818181;
    display: block;
    transition: 0.3s;
}

.sidenav a:hover {
    color: goldenrod;
}

.sidenav .closebtn {
    position: absolute;
    top: 0;
    right: 25px;
    font-size: 36px;
    margin-left: 50px;
}

#main {
    transition: margin-left .5s;
    padding: 16px;
}

.admin{
    padding: 50px;
    border: goldenrod groove 5px;
    margin: 3%;
    overflow: auto;
    background: white;
    float: none;
}

textarea {
    width: 100%;
    height: 100px;
    padding: 12px 20px;
    border: 2px solid #ccc;
    border-radius: 4px;
    background-color: #f8f8f8;
    resize: none;
}

#yes, #no{
    margin-left: -30px;
    padding: 12px 14px;
    outline: none;
    border: none;
}
#no{
    margin-left: 30px;
}

div#search, div#searchMenu{
    width: 100%;
    margin: 10px 0;
    padding: 1px;
    background: white;
    border: 1px solid #ddd;
}

div#search i,
div#searchMenu i{
    margin-left: 10px;
    color: goldenrod;
    text-shadow: 1px 1px 2px black;
    border: none;
    outline: none;
}

#orderSearch, #menuSearchInp {
    width: 95%;
    font-size: 16px;
    padding: 8px 20px;
    border: none;
    outline: none;
}

#createMenuModal .modalContent,
#editMenuModal .modalContent{
    width: 85%;
    max-height: 100%;
    margin-top: 10px;
    margin-bottom: 10px;
}
#createMenuModal .modalContent h1{
    font-size: 25px;
}

#allOrders, #allFood{
    display: none;
}

form table td {
    padding: 18px 0;
    text-align: left;
    border-bottom: none;
}
#graphTable{
    table-layout: fixed;
    width: 300px;
    padding: 10px;
    margin: auto;
}
#graphTable td{
    border-bottom: none;
    font-size: 12px;
    padding: 3px;
    text-align: center;
}
#graphTable td select{
    font-size: 12px;
    padding: 4px;
}
#graphTable td button{
    padding: 3px;
}
#graphTable td.label{
    text-align: right;
    font: 14px/1.5 Arial, Helvetica, sans-serif;
    width: 10%;
    font-weight: 600;
}
#freq{
    width: 100%
}
#fromDay, #toDay{
    width: 60px
}
#fromMonth, #toMonth, #freq{
    width: 75px
}
td.label{
    font: 25px/1.5 Arial, Helvetica, sans-serif;
    text-align: right;
}

form input, textarea, select {
    width: 70%;
    font-size: 16px;
    font-weight: bolder;
    margin-left: 20px;
    padding: 10px 15px;
    border: 1px solid #ccc;
}

td.available input{
    width: 20px;
}
input#imgInfo, input#img{
    width: 255px;
    margin-left: 5px;
    border: none;
    outline: none;
    background-color: white;
    padding-right: 0;
}
input#imgInfo{
    margin-right: 0;
}
input.img{
    padding: 0;
    margin-left: 0;
}

textarea {
    height: 150px;
}

#addBtn, #saveBtn, #resetBtn, #plotBtn{
    width: 45%;
    height: 40px;
    color: white;
    background-color: #2ec371;
    opacity: 0.6;
    margin: 25px 15px;
    font-size: 20px;
    border-radius: 10px;
    border: none;
    outline: none;
}
#plotBtn{
    margin: 10px auto;
    font-size: 15px;
    opacity: 0.5;
    width: 100%;
    border-radius: 5px;
    /* background-color: goldenrod; */
}
#resetBtn{
    background-color: red;
}
#addBtn:hover, #saveBtn:hover, #resetBtn:hover, #plotBtn:hover{
    cursor: pointer;
    opacity: 0.8;
}

.statsCards{
    /* margin-top: 1px; */
    margin-bottom: 30px
}
.statsCard{
    background: white;
    padding: 5px;
    float: left;
    width: 23%;
    /* height: 100px; */
    margin: 1%;
    transition: 0.3s;
    border-radius: 5px;
}

.pie {
    float: left;
    border: goldenrod groove 5px;
    width: 40%;
    margin: 3% 5%;
    overflow: auto;
    padding: 10px;
    background: white
}

.card{
    border: none;
    box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2),0 6px 20px 0 rgba(0, 0, 0, 0.19);
    border-radius: 5px;
}

@media screen and (max-height: 500px) {
  .sidenav {padding-top: 15px;}
  .sidenav a {font-size: 15px;}
}
@media screen and (max-width: 800px) {
    input#imgInfo, input.img{
        padding-left: 20px;
    }
}
@media screen and (max-width: 750px) {
    #openBtn{font-size: 20px;}
    #orderSearch, #menuSearchInp{
        width: 90%;
        font-size: 14px;
        margin-top: 1px;
    } 
    td.label{
        font: 20px/1.5 Arial, Helvetica, sans-serif;
    }
    #addBtn, #resetBtn{
       width: 35%
    }
}
@media screen and (max-width: 600px) {
    .statsCard{
        width: 48%
    }
}
@media screen and (max-width: 540px) {
    #openBtn{font-size: 18px;}
    #orderSearch, #menuSearchInp{
        font-size: 12px;
        padding: 8px 10px;
    }
}
    form table td {
        padding: 12px 0;
    }
    
    td.label{
        font: 12px/1.5 Arial, Helvetica, sans-serif;
        text-align: center;
    }
    
    form input, textarea, select {
        width: 80%;
        font-size: 12px;
        padding: 6px;
        margin-left: 15px;
    }
    input#imgInfo, input.img{
        width: 200px;
        padding-left: 15px;
    }
    #addBtn, #resetBtn{
        margin-top: 10px;
        margin-bottom: 5px;
        font-size: 14px;
    }
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