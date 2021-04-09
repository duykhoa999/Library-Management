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
	           <div class="col-md-8">
	               <br>
	               <div class="card">
	                   <div class="card-body">
		                   <div class = "card-tittle">
		                   		<a href="service/return.htm" class="btn btn-success">Quay lại</a>
		                   </div>
		                   <c:if test="${message != null}">
									<div class="alert alert-success">${message}</div>
							</c:if>
							<c:if test="${error != null}">
								<div class="alert alert-danger">${error}</div>
							</c:if>
	                        <div class="table-responsive">
	                            <table class="table">
	                                <thead>
	                                <tr>
	                                    <th>Mã copy</th>
	                                    <th>Tựa đề</th>
	                                    <th>Ngày mượn</th>
	                                    <th>Ngày phải trả</th>
	                                    <th>Trạng thái</th>
	                                    <th></th>
	                                </tr>
	                                </thead>
	                                <tbody>
	                                <c:forEach var = "l" items = "${loans}">
	                                	<tr>
	                                        <td>${l.bookCopy.id }</td>
	                                        <td>${l.bookCopy.book.tittle }</td>
	                                        <td>${l.loanDate}</td>
	                                        <td>${l.returnDate}</td>
	                                        <td>${l.status == 0 ? 'Chưa trả' : 'Đã trả'}</td>
	                                        <td>
	                                        	<c:if test="${l.status == 0 }">
	                                        		<a href = "service/return/submit/${l.id}/${l.bookCopy.id}.htm"
	                                               class="btn btn-success">Trả sách</a>
	                                        	</c:if>
	                                        </td>
	                                    </tr>
	                                </c:forEach>
	                                </tbody>
	                            </table>
	                        </div>
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