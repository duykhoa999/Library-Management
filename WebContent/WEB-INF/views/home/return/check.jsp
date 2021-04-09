<%@ page pageEncoding="utf-8"%>
<%@ include file = "header.jsp" %>
<!-- Start: Page Banner -->
   <section class="page-banner services-banner">
       	<div class="container">
			<div class="breadcrumb">
	           <ul>
	               <li><a href="home/index.htm">Home</a></li>
	               <li>Return</li>
	           </ul>
	       	</div>
       	</div>
   </section>
   <!-- End: Page Banner -->
	<div class="container">
	       <div class="row">
	       	   <div class = "col-md-3"></div>
	           <div class="col-md-6">
	               <br>
	               <div class="card">
	                   <div class="card-body">
		                   <div class = "card-tittle">
		                   		<h3 style = "text-align: center;">ENTER READER CARD ID</h3>
		                   		<br>
		                   </div>
		                   <c:if test="${message != null}">
								<div class="alert alert-success mt-3">${message}</div>
							</c:if>
							<c:if test="${error != null}">
								<div class="alert alert-danger mt-3">${error}</div>
							</c:if>
	                       <form action="service/return/index.htm" method="POST">
	                           <div class="form-group pt-5">
	                               	<input type="text" name ="id" class="form-control" placeholder="Reader ID / Card ID"/>
	                           </div>
	                           <button style = "float: right;" type="submit" class="btn btn-success">Submit</button>
	                       </form>
	                   </div>
	               </div>
	           </div>
	       </div>
   		</div>
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