<template>
  <div class="container">
      <!-- Modal -->
    <div id="modalDiv" class="modal">
      <!-- Modal content -->
      <div class="modalContent">
        <span class="close">&times;</span>
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
  </div>
</template>

<script>
export default {

}
</script>

<style>
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
</style>