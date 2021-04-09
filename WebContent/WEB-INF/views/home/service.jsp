<%@ page pageEncoding="utf-8"%>
<%@ include file = "header.jsp" %>
<!-- Start: Page Banner -->
   <section class="page-banner services-banner">
       	<div class="container">
			<div class="breadcrumb">
	           <ul>
	               <li><a href="home/index.htm">Home</a></li>
	               <li>Services</li>
	           </ul>
	       	</div>
       	</div>
   </section>
<!-- End: Page Banner -->
<!-- Start: Services Section -->
   <div id="content" class="site-content">
       <div id="primary" class="content-area">
           <main id="main" class="site-main">
               <div class="services-main">
                   <div class="services-pg">                            
                       <section class="services-offering">
                           <div class="container">
                               <div class="center-content">
                                   <h2 class="section-title">SERVICE WE ARE OFFERING</h2>
                                   <span class="underline center"></span>
                                   <p class="lead">The standard chunk of Lorem Ipsum used since</p>
                                   <div class="clearfix"></div>
                               </div>
                               <div class="clearfix"></div>
                               <div class="contact-location">
                                   <div class="flipcard">
                                       <div class="front">
                                           <div class="top-info">
                                               <h3><i class="fa fa-credit-card" aria-hidden="true"></i><span>Get a Card</span></h3>
                                           </div>
                                           <div class="bottom-info">
                                               <span class="top-arrow"></span>
                                               <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque dolor turpis.</p>
                                               <a href="#">View Selection <i class="fa fa-long-arrow-right" aria-hidden="true"></i></a>
                                           </div>
                                       </div>
                                       <div class="back">
                                           <div class="bottom-info orange-bg">
                                               <span class="bottom-arrow"></span>
                                               <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque dolor turpis.</p>
                                               <a href="service/card/add-card.htm">View Selection <i class="fa fa-long-arrow-right" aria-hidden="true"></i></a>
                                           </div>
                                           <div class="top-info dark-bg">
                                               <h3><i class="fa fa-credit-card" aria-hidden="true"></i><span>Get a Card</span></h3>
                                           </div>                                                
                                       </div>
                                   </div>
                                   <div class="flipcard">
                                       <div class="front">
                                           <div class="top-info">
                                               <h3><i class="fa fa-desktop" aria-hidden="true"></i><span>Borrow Book</span></h3>
                                           </div>
                                           <div class="bottom-info">
                                               <span class="top-arrow"></span>
                                               <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque dolor turpis.</p>
                                               <a href="#">View Selection <i class="fa fa-long-arrow-right" aria-hidden="true"></i></a>
                                           </div>
                                       </div>
                                       <div class="back">
                                           <div class="bottom-info orange-bg">
                                               <span class="bottom-arrow"></span>
                                               <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque dolor turpis.</p>
                                               <a href="service/borrow.htm">View Selection <i class="fa fa-long-arrow-right" aria-hidden="true"></i></a>
                                           </div>
                                           <div class="top-info dark-bg">
                                               <h3><i class="fa fa-desktop" aria-hidden="true"></i><span>Borrow Book</span></h3>
                                           </div>                                                
                                       </div>
                                   </div>
                                   <div class="flipcard">
                                       <div class="front">
                                           <div class="top-info">
                                               <h3><i class="fa fa-microphone" aria-hidden="true"></i><span>Return Book</span></h3>
                                           </div>
                                           <div class="bottom-info">
                                               <span class="top-arrow"></span>
                                               <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque dolor turpis.</p>
                                               <a href="#">View Selection <i class="fa fa-long-arrow-right" aria-hidden="true"></i></a>
                                           </div>
                                       </div>
                                       <div class="back">
                                           <div class="bottom-info orange-bg">
                                               <span class="bottom-arrow"></span>
                                               <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque dolor turpis.</p>
                                               <a href="service/return.htm">View Selection <i class="fa fa-long-arrow-right" aria-hidden="true"></i></a>
                                           </div>
                                           <div class="top-info dark-bg">
                                               <h3><i class="fa fa-microphone" aria-hidden="true"></i><span>Return Book</span></h3>
                                           </div>                                                
                                       </div>
                                   </div>
                                   <div class="clearfix"></div>
                               </div>
                           </div>
                       </section>
                       <section class="category-filter new-release">
                           <div class="container">
                               <div class="row">
                                   <div class="col-md-6 col-md-offset-3 text-center">
                                       <h2 class="section-title">Check Out The New Releases</h2>
                                       <span class="underline center"></span>
                                       <p class="lead">The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested.</p>
                                   </div>
                               </div>
                           </div>
                           <div id="category-filter">
                               <ul class="category-list">
                                   <c:forEach var = "b" items = "${books }" begin = "0" end = "3">
                                		<li class="category-item">
	                                       <figure>
	                                           <img src="resources/images-book/${b.image }" alt="New Releaase" />
	                                           <figcaption class="bg-orange">
	                                               <div class="info-block">
	                                                   <h4>${b.tittle }</h4>
	                                                   <span class="author"><strong>Author:</strong> ${author.name }</span>
	                                                   <span class="author"><strong>ISBN:</strong> ISBN-${b.id }</span>
	                                                   <div class="rating">
	                                                       <span>☆</span>
	                                                       <span>☆</span>
	                                                       <span>☆</span>
	                                                       <span>☆</span>
	                                                       <span>☆</span>
	                                                   </div>
	                                                   <p>${b.description }</p>
	                                                   <a href="book/detail/${b.id }.htm">Read More <i class="fa fa-long-arrow-right"></i></a>
	                                                   <ol>
	                                                       <li>
	                                                           <a href="#">
	                                                               <i class="fa fa-shopping-cart"></i>
	                                                           </a>
	                                                       </li>
	                                                       <li>
	                                                           <a href="#">
	                                                               <i class="fa fa-heart"></i>
	                                                           </a>
	                                                       </li>
	                                                       <li>
	                                                           <a href="#">
	                                                               <i class="fa fa-envelope"></i>
	                                                           </a>
	                                                       </li>
	                                                       <li>
	                                                           <a href="#">
	                                                               <i class="fa fa-share-alt"></i>
	                                                           </a>
	                                                       </li>
	                                                       <li>
	                                                           <a href="#">
	                                                               <i class="fa fa-search"></i>
	                                                           </a>
	                                                       </li>
	                                                   </ol>
	                                               </div>
	                                           </figcaption>
	                                       </figure>
	                                   </li>
                                   </c:forEach>
                               </ul>                                    
                               <div class="clearfix"></div>
                           </div>
                       </section>
                       <!-- Start: Category Filter -->
                   </div>
               </div>
           </main>
       </div>
   </div>
   <!-- End: Services Section -->
   
   <!-- Start: Social Network -->
   <section class="social-network section-padding">
       <div class="container">
           <div class="center-content">
               <h2 class="section-title">Follow Us</h2>
               <span class="underline center"></span>
               <p class="lead">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
           </div>
           <ul>
               <li>
                   <a class="facebook" href="#" target="_blank">
                       <span>
                           <i class="fa fa-facebook-f"></i>
                       </span>
                   </a>
               </li>
               <li>
                   <a class="twitter" href="#" target="_blank">
                       <span>
                           <i class="fa fa-twitter"></i>
                       </span>
                   </a>
               </li>
               <li>
                   <a class="google" href="#" target="_blank">
                       <span>
                           <i class="fa fa-google-plus"></i>
                       </span>
                   </a>
               </li>
               <li>
                   <a class="rss" href="#" target="_blank">
                       <span>
                           <i class="fa fa-rss"></i>
                       </span>
                   </a>
               </li>
               <li>
                   <a class="linkedin" href="#" target="_blank">
                       <span>
                           <i class="fa fa-linkedin"></i>
                       </span>
                   </a>
               </li>
               <li>
                   <a class="youtube" href="#" target="_blank">
                       <span>
                           <i class="fa fa-youtube"></i>
                       </span>
                   </a>
               </li>
           </ul>
       </div>
   </section>
<!-- End: Social Network -->
<%@ include file = "footer.jsp" %>