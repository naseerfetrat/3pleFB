<template>
  <!-- <div class="container">
    <header class="jumbotron">
      <h3>{{content}}</h3>
    </header>
  </div> -->

  <div class="container" style="text-align:center">
    
    <h1 class="welcome">Welcome to 3pleFB!</h1><br>
    <h2><i>"We quench your yawns with mouth watering meals."</i></h2>
    <p>A peek at our <a href="menu" class="current">MENU</a> will convince you</p>

    <div class="slideshow-container" style="text-align:center">

        <div class="mySlides fade">
            <div class="numbertext">1 / 6</div>
            <img src="../assets/imgs/restaurant3.jpg" style="width:100%">
            <div class="text">Come on in<br>Our restaurant is always warm and open!</div>
        </div>
        <div class="mySlides fade">
            <div class="numbertext">2 / 6</div>
            <img src="../assets/imgs/smallchops.jpg" style="width:100%">
            <div class="text">Feast yourself on our delightful small chops</div>
        </div>
        <div class="mySlides fade">
            <div class="numbertext">3 / 6</div>
            <img src="../assets/imgs/background.png" style="width:100%">
            <div class="text">Our Burgers are deliciously awesome</div>
        </div>          
        <div class="mySlides fade">
            <div class="numbertext">4 / 6</div>
            <img src="../assets/imgs/combo/burger-fries-drink.png" style="width:100%">
            <div class="text">Did I hear you say OMG!</div>
        </div>

        <div class="mySlides fade">
            <div class="numbertext">5 / 6</div>
            <img src="../assets/imgs/drinks/cocktailspecial.jpg" style="width:100%">
            <div class="text">Tried one of our arousing cocktail flavours?</div>
        </div>
        <div class="mySlides fade">
            <div class="numbertext">6 / 6</div>
            <img src="../assets/imgs/specials.jpg" style="width:100%">
            <div class="text">Hmmm... a taste is all you need</div>
        </div>
    </div>
    <br>
    <div style="text-align:center">
    <span class="dot"></span> 
    <span class="dot"></span> 
    <span class="dot"></span> 
    <span class="dot"></span> 
    <span class="dot"></span> 
    <span class="dot"></span> 
    </div>
    <section id="boxes" class="last">
        <div class="container">
            <br><h1 id="about">ABOUT US</h1>
            <div class="box">
                <i class="fa fa-cutlery fa-4x"></i>
                <h3>Yummy Meals</h3>
                <p>We provide you with that sumptous meal, however and whenever you want it!</p>
            </div>
            <div class="box">
                <i class="fa fa-glass fa-4x"></i>
                <h3>Thirst-quenching chilled drinks</h3>
                <p>Our drinks are as chilled and cheap as they can get.</p>
            </div>
            <div class="box">
                <i class="fa fa-shopping-cart fa-4x"></i>
                <h3>Speedy Lagos-wide Delivery</h3>
                <p>We fast-forward your food to any location in Lagos at little or no cost to you.</p>
            </div>
            <div class="box" style="padding-bottom:10px">
                <i class="fa fa-user-plus fa-4x"></i>
                <h3>Spectacular Catering Services</h3>
                <p>Should you need a skilled professional to cater for that occasion - wedding, birthday, parties... look no further.</p>
            </div>
        </div>
		</section>
		   <!-- End container div -->
		</div>

</template>

<script>
import UserService from '../services/user.service';
export default {
  name: 'Home',
  data() {
    return {
      content: ''
    };
  },
  mounted() {
    UserService.getPublicContent().then(
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
    /******SLIDESHOW***** */
        let slideIndex = 0;
        const showSlides = () => {
            const slides = document.getElementsByClassName('mySlides');
            const dots = document.getElementsByClassName('dot');
            for (let i = 0; i < slides.length; i++) {
                slides[i].style.display = 'none';
            }
            slideIndex += 1;
            if (slideIndex > slides.length) { slideIndex = 1; }

            for (let i = 0; i < dots.length; i++) {
                dots[i].className = dots[i].className.replace(' active', '');
            }
            slides[slideIndex - 1].style.display = 'block';
            dots[slideIndex - 1].className += ' active';
            setTimeout(showSlides, 1500); // Change image every 3 seconds
        };
        showSlides()
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
.container{
    width: 90%;
    min-height: 100vh;
    margin: auto;
}
h1{
    margin-top: 60px;
    text-shadow: 1px 1px 1px goldenrod;
}
.highlight{
    color: goldenrod;
}
.card{
    border: none;
    box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2),0 6px 20px 0 rgba(0, 0, 0, 0.19);
}
/******* Slideshow ********/
.mySlides {display: none;}
img {
	vertical-align: middle;
	max-height: 400px;    
}

/* Slideshow container */
.slideshow-container {
  max-width: 800px;
  position: relative;
  margin: auto;
}

/* Caption text */
.text {
  color: white;
  text-shadow: 2px 2px 4px #000000;
  font-size: 25px;
  padding: 8px 12px;
  position: absolute;
  bottom: 18px;
  width: 100%;
  text-align: center;
}

/* Number text (1/3 etc) */
.numbertext {
  color: #f2f2f2;
  font-size: 12px;
  padding: 8px 12px;
  position: absolute;
  top: 0;
}

/* The dots/bullets/indicators */
.dot {
  height: 15px;
  width: 15px;
  margin: 0 2px;
  background-color: #bbb;
  border-radius: 50%;
  display: inline-block;
  transition: background-color 0.6s ease;
}

.active {
  background-color: goldenrod;
}

/* Fading animation */
.fade {
  -webkit-animation-name: fade;
  -webkit-animation-duration: 1.5s;
  animation-name: fade;
  animation-duration: 1.5s;
}

@-webkit-keyframes fade {
  from {opacity: .4} 
  to {opacity: 1}
}

@keyframes fade {
  from {opacity: .4} 
  to {opacity: 1}
}


.fa-1x{
    border: 1px solid goldenrod;
    border-radius: 100%;
    padding: 5px;
    text-decoration: none;
    background-color: none;
}

.fa-4x {
    color: #212121;
    border: 2px solid goldenrod;
    border-radius: 100%;
    padding: 15px;
    margin-bottom :5px;
}

.success{
    color: green;
}
.last{
    margin-bottom: 150px;
}

</style>
