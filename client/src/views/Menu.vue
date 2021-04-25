<template>
  <div class="container" style="text-align:center">
      <div class="menu">
      <h1 id="menuWelcome" v-if="!currentUser"><i>What's on the Menu?</i></h1>
      <h1 id="menuWelcome" v-if="currentUser"><i>Welcome!</i></h1>
      <h3>Please select from our variety of meals, snacks and drinks and add your choices to cart.</h3>
      <p><i>Click the shopping cart <i class="fa fa-shopping-cart" aria-hidden="true"></i> above to view cart</i></p>
    </div>		
    <section class="menu" id="meals">
		<h2>MEALS</h2>

		<div v-for="item in items.slice(0,4)" :key="item.id" class="responsive">
			<div class="gallery">
          <div class="flip-box">
              <div class="flip-box-inner">
                <div class="flip-box-front">
                  <img src="../assets/imgs/meals/chicken.jpg" alt="ofada_plantain" id="img01">
                </div>
                <div class="flip-box-back">
                  <h2>Description:</h2>
                  <p>{{item.discription}}</p>
                </div>
            </div>
          </div>
          <div class="desc">
            <h1 id="item01">{{item.name}}</h1>
            <p class="price" id="price01">&#x20a6; {{item.price}}.00</p>
            <p><span class="fa fa-star checked"></span>
              <span class="fa fa-star checked"></span>
              <span class="fa fa-star checked"></span>
              <span class="fa fa-star checked"></span>
              <span class="fa fa-star"></span>
            </p>
            <p>Select Quantity: 
              <select id="selectQty01">
                <option id="qty01">1</option>
                <option id="qty02">2</option>
                <option id="qty03">3</option>
                <option id="qty04">4</option>
                <option id="qty05">5</option>
              </select>
            </p>
            <button class="cartBtn" id="btn05" v-if="currentUser">Add to Cart</button>
            <button class="btn btn-dark btn-block" @click="login"  id="btn05" v-if="!currentUser">Login To Buy</button>
        </div>
			</div>
		</div>      

		<div class="clearfix"></div> 
	</section>
  <!-- SNACKS -->
  <section class="menu" id="snacks" style="display: block;">
    <h2>SNACKS</h2>

    <div class="responsive" v-for="item in items.slice(4,8)" :key="item.id">
			<div class="gallery">
          <div class="flip-box">
              <div class="flip-box-inner">
                <div class="flip-box-front">
          <img src="../assets/imgs/snacks/scotchegg1.jpg" alt="burger" id="img05">
        </div>
        <div class="flip-box-back">
          <h2>Description:</h2>
          <p>{{item.discription}}</p>
        </div>
      </div>
  </div>
          <div class="desc">
            <h1 id="item05">{{item.name}}</h1>
            <p class="price" id="price05">&#x20a6; {{item.price}}.00</p>
            <p><span class="fa fa-star checked"></span>
              <span class="fa fa-star checked"></span>
              <span class="fa fa-star checked"></span>
              <span class="fa fa-star checked"></span>
              <span class="fa fa-star"></span>
            </p>
            <p>
              Select Quantity: 
                <select id="selectQty05">
                  <option id="qty01">1</option>
                  <option id="qty02">2</option>
                  <option id="qty03">3</option>
                  <option id="qty04">4</option>
                  <option id="qty05">5</option>
                  <option id="qty06">6</option>
                </select>
            </p>
            <button class="cartBtn" id="btn05" v-if="currentUser">Add to Cart</button>
            <button class="btn btn-dark btn-block" @click="login" id="btn05" v-if="!currentUser">Login To Buy</button>
        </div>
      </div>
    </div>  
     
    
    <div class="clearfix"></div>
  </section>
    
  <!--DRINKS-->
  <section class="menu" id="drinks" style="display: block;">
    <h2>DRINKS</h2>

    <div class="responsive" v-for="item in items.slice(8,12)" :key="item.id">
			<div class="gallery">
          <div class="flip-box">
              <div class="flip-box-inner">
                <div class="flip-box-front">
          <img src="../assets/imgs/drinks/cocktailspecial.jpg" alt="chocolatemousse" id="img09">
        </div>
        <div class="flip-box-back">
          <h2>Description:</h2>
          <p>{{item.discription}}</p>
        </div>
      </div>
  </div>
          <div class="desc">
            <h1 id="item09">{{item.name}}</h1>
            <p class="price" id="price09">&#x20a6; {{item.price}}.00</p>
            <p><span class="fa fa-star checked"></span>
              <span class="fa fa-star checked"></span>
              <span class="fa fa-star checked"></span>
              <span class="fa fa-star checked"></span>
              <span class="fa fa-star"></span>
            </p>
            <p>
              Select Quantity: 
                <select id="selectQty09">
                  <option id="qty01">1</option>
                  <option id="qty02">2</option>
                  <option id="qty03">3</option>
                  <option id="qty04">4</option>
                  <option id="qty05">5</option>
                  <option id="qty06">6</option>
                  
                </select>
            </p>
            <button class="cartBtn" id="btn05" v-if="currentUser">Add to Cart</button>
            <button class="btn btn-dark btn-block" @click="login"  id="btn05" v-if="!currentUser">Login To Buy</button>
        </div>
      </div>
	</div> 
      
    
    <!-- <div class="center" style="display:block;">
      <div class="pagination">
        <a href="#">&laquo;</a>
        <a href="#">1</a>
        <a class="current" href="#">2</a>
        <a href="#">3</a>
        <a href="#">4</a>
        <a href="#">5</a>
        <a href="#">6</a>
        <a href="#">&raquo;</a>
      </div>
    </div> -->
    <div class="clearfix"></div>
  </section>

 <!--COMBO/SPECIALS-->
  <section class="menu last" id="combo" style="margin-bottom:100px;">
  <h2>COMBO/SPECIALS</h2>

  <div class="responsive" v-for="item in items.slice(12,16)" :key="item.id">
    <div class="gallery">
        <div class="flip-box">
            <div class="flip-box-inner">
              <div class="flip-box-front">
          <img src="../assets/imgs/combo/chicken3.jpg" alt="special_rice" id="img15">
        </div>
        <div class="flip-box-back">
          <h2>Description:</h2>
          <p>{{item.discription}}</p>
        </div>
      </div>
  </div>
          <div class="desc">
            <h1 id="item15">{{item.name}}</h1>
            <p class="price" id="price15">&#x20a6; {{item.price}}.00</p>
            <p><span class="fa fa-star checked"></span>
              <span class="fa fa-star checked"></span>
              <span class="fa fa-star checked"></span>
              <span class="fa fa-star checked"></span>
              <span class="fa fa-star"></span>
            </p>
            <p>
              Select Quantity: 
                <select id="selectQty15">
                  <option id="qty01">1</option>
                  <option id="qty02">2</option>
                  <option id="qty03">3</option>
                  <option id="qty04">4</option>
                  <option id="qty05">5</option>
                  <option id="qty06">6</option>
                </select>
            </p>
            <button class="cartBtn" @click="login"  id="btn15">Add to Cart</button>
        </div>
      </div>
    </div> 
      
    <!-- <div class="center" >
      <div class="pagination">
        <a href="#">&laquo;</a>
        <a class="current" href="#">1</a>
        <a href="#">2</a>
        <a href="#">3</a>
        <a href="#">4</a>
        <a href="#">5</a>
        <a href="#">6</a>
        <a href="#">&raquo;</a>
      </div>
    </div> -->
    <div class="clearfix" ></div>
  </section>

     

  </div>
</template>

<script>
import ItemService from "@/services/item.service.js";
export default {
    data() {
    return {
      items: null,
      cartItem:null
    };
  },

  async mounted() {
    //do a request to the backend for all items
    this.items = (await ItemService.index()).data;  
    
  },
  computed:{
    currentUser() {
      return this.$store.state.auth.user;
    }
  },
  methods:{
    login(){
      this.$router.push({
          name: "login",
        });
    }
  }
}
</script>

<style>
.menu h1{
    margin-top: 120px;
    padding: 5px;
    text-shadow: 1px 1px 1px goldenrod;
}
.menu h3{
    font: 20px/1.5 Arial, Helvetica, sans-serif;
}
  
.menu h2{
    color: goldenrod;
    text-shadow: 1.5px 1.5px 2px #000000;
    box-shadow: 0px 2px 2px 1px rgba(0, 0, 0, 0.2);
    background-color: #cccccc;
    width: 99%;
    margin-top: 50px;
    margin-left: auto;
    margin-right: auto;
}

#hist{
    margin: 5px;
    text-align: left;
    font: 15px/1.5 Arial, Helvetica, sans-serif;
}
/*Responsive Image Gallery */

div.gallery {
    border: 1px solid #ccc;
    box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2),0 6px 20px 0 rgba(0, 0, 0, 0.19);;
    transition: 0.3s;
    border-radius: 5px;
}

div.gallery:hover {
    border: 1px solid goldenrod;
    /* Add deeper shadow */
    box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
}

div.gallery img {
    width: 100%;
    height: 200px;
    border-radius: 5px 5px 0 0;
}
/* Image flip */
.flip-box{
    background-color: transparent;
    width: 100%;
	height: 200px;
    perspective: 1000px;
    
}
.flip-box-inner {
		position: relative;
		width: 100%;
		height: 100%;
		text-align: center;
		transition: transform 0.8s;
		transform-style: preserve-3d;
}
/* Do a horizontal flip when you move the mouse over the flip box container */
	.flip-box:hover .flip-box-inner {
	transform: rotateY(180deg);
}

  /* Position the front and back side */
.flip-box-front, .flip-box-back {
	border-radius: 5px 5px 0 0;
    position: absolute;
    width: 100%;
    height: 100%;
    backface-visibility: hidden;
}
/* Style the front side (fallback if image is missing) */
.flip-box-front {
    background-color: #bbb;
    color: black;
}
  
  /* Style the back side */
.flip-box-back {
		background-color: #212121;
		color: white;
		transform: rotateY(180deg);
}

.desc {
    text-align: center;
}
.desc h1{
    margin-top: 18px;
    font: 18px Arial, Helvetica, sans-serif;
    height: 25px;
}


.checked {
    color: orange;
}

.price {
    color: grey;
    font-size: 14px;
  }

.cartBtn, .buyBtn, #buyCheckoutBtn, #checkoutBtn{
    border: none;
    outline: 0;
    padding: 12px;
    color: goldenrod;
    background-color: #212121;
    text-align: center;
    cursor: pointer;
    width: 100%;
    margin-top: 10px;
}

#buyCheckoutBtn{
    background-color: #2ec371;
    color: white;
}

.buyBtn{
    background-color: goldenrod;
    color: black;
    width: auto;
    padding: 8px;
}

.buyBtn:hover, .cartBtn:hover{
    cursor: pointer;
    background-color: #2ec371;
    color:white;
    font-size: 14px;
}
#totalItems{color: goldenrod;}

.responsive {
    padding: 6px 6px;
    float: left;
    width: 25%;
}

.clearfix:after {
    content: "";
    display: table;
    clear: both;
}

table {
    border-collapse: collapse;
    width: 100%;
}

th, td {
    padding: 8px;
    text-align: center;
    border-bottom: 0.2px solid #747474;
}

#histTableBody tr:nth-child(odd){
    background-color: #f2f2f2;
}

.cancelOdr, .reorder, .approveBtn, .declineBtn{
    background-color: green;
    color: white;
    cursor: pointer;
    border: 1px solid black;
    border-radius: 5px;
}

.cancelOdr, .declineBtn{
    background: red;
}

#cancelOdr:hover, .reorder:hover{
    opacity: 1;
    font: bolder;
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

/* Pagination links */
.pagination{
    display: inline-block;
}
.pagination a {
    color: black;
    float: left;
    padding: 8px 16px;
    text-decoration: none;
    transition: background-color .3s;
}

/* Style the active/current link */
.pagination a.current {
    background-color: darkgoldenrod;
    color: white;
}

/* Add a grey background color on mouse-over */
.pagination a:hover:not(.current) {
    background-color: #cccccc;
    cursor: pointer;
}

.err{
    color: red;
}

@media only screen and (max-width: 800px) {
    .responsive {
        width: 50%;
        margin: 6px 0;
    }

    .modalContent.general {
        width: 90%;
    }
    .modalContent.general h1{
        font-size: 25px;
    }
}

@media screen and (max-width: 500px) {
    .responsive {
        width: 100%;
    }
    div.gallery img {
        height: 220px;
    }    
    h2{
        font-size: 22px;
    }
    .modalContent.general {
        width: 90%;
    }
    .modalContent.general h1{
        font-size: 20px;
    }
}


</style>