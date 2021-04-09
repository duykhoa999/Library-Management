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
	                       <form:form action="admin/book/insert-pub.htm" method="POST" modelAttribute = "publisher">
	                           <div class="form-group">
	                               	<label for="id">Mã NXB</label>
	                               	<form:input type="text" path = "id" class="form-control" placeholder="Mã NXB"/>
	                           		<form:errors path = "id"/>
	                           </div>
	                           <div class="form-group">
	                               	<label for="pubName">Tên NXB</label>
	                               	<form:input type="text" path = "pubName" class="form-control" placeholder="Tên NXB"/>
	                           		<form:errors path = "pubName"/>
	                           </div>
	                           <div class="form-group">
	                               	<label for="address">Địa chỉ</label>
	                               	<form:input type="text" path = "address" class="form-control" placeholder="Địa chỉ (Có thể bỏ trống)"/>
	                           		<%-- <form:errors path = "name"/> --%>
	                           </div>
	                           <div class="form-group">
	                               	<label for="contactName">Tên người liên lạc</label>
	                               	<form:input type="text" path = "contactName" class="form-control" placeholder="Tên người liên lạc (Có thể bỏ trống)"/>
	                           		<%-- <form:errors path = "name"/> --%>
	                           </div>
	                           <div class="form-group">
	                               	<label for="phone">Điện thoại</label>
	                               	<form:input type="text" path = "phone" class="form-control" placeholder="Điện thoại"/>
	                           		<form:errors path = "phone"/>
	                           </div>
	                           <button type="submit" class="btn btn-success">Thêm NXB</button>
	                       </form:form>
	                   </div>
	               </div>
	           </div>
	       </div>
   		</div>
      </div>
<%@ include file = "footer.jsp" %>