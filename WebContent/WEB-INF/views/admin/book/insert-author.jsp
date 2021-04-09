<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "header.jsp" %>
<%@ include file = "body.jsp" %>
	 <div id="layoutSidenav_content">
       	<div class="container">
	       <div class="row">
	           <div class="col-md-12">
	               <br>
	               <div class="card">
	                   <div class="card-body">
		                   <div class = "card-tittle">
		                   		<div>
				                   <a href="admin/book/insert-book.htm" class="btn btn-primary">Thêm sách</a>
				               	</div>
		                   </div>
		                   <c:if test="${message != null}">
								<div class="alert alert-success mt-3">${message}</div>
							</c:if>
							<c:if test="${error != null}">
								<div class="alert alert-danger mt-3">${error}</div>
							</c:if>
	                       <form:form action="admin/book/insert-author.htm" method="POST" modelAttribute = "author">
	                           <div class="form-group">
	                               	<label for="id">Mã tác giả</label>
	                               	<form:input type="text" path = "id" class="form-control" placeholder="Mã tác giả"/>
	                           		<form:errors path = "id"/>
	                           </div>
	                           <div class="form-group">
	                               	<label for="name">Họ tên</label>
	                               	<form:input type="text" path = "name" class="form-control" placeholder="Họ tên"/>
	                           		<form:errors path = "name"/>
	                           </div>
	                           <div class="form-group">
	                               	<label for="phone">Số điện thoại</label>
	                               	<form:input type="text" path = "phone" class="form-control" placeholder="Số điện thoại"/>
	                           		<form:errors path = "phone"/>
	                           </div>
	                           <button type="submit" class="btn btn-success">Thêm tác giả</button>
	                       </form:form>
	                   </div>
	               </div>
	           </div>
	       </div>
   		</div>
      </div>
<%@ include file = "footer.jsp" %>