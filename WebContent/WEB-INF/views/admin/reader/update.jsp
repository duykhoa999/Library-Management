<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "header.jsp" %>
<%@ include file = "body.jsp" %>
    <div id="layoutSidenav_content">
		<div class="container">
	       <div class="row">
	           <div class="col-md-12">
	               <div class="card">
	                   <div class="card-body">
		                   <div class = "card-tittle">
		                   		<div>
				                   <a href="admin/reader/index.htm" class="btn btn-primary">Danh sách độc giả</a>
				               	</div>
		                   </div>
		                   <c:if test="${message != null}">
								<div class="alert alert-success mt-3">${message}</div>
							</c:if>
							<c:if test="${error != null}">
								<div class="alert alert-danger mt-3">${error}</div>
							</c:if>
	                       <form:form action="admin/reader/update/${reader.id }.htm" method="POST" modelAttribute="reader">
	                           <div class="form-group pt-5">
	                               	<label for="id">Mã độc giả</label>
	                               	<form:input readOnly = "true" path ="id" type="text" class="form-control"/>
	                           </div>
	                           <div class="form-group">
	                               	<label for="name">Tên độc giả</label>
	                               	<form:input path = "name" type="text" class="form-control" placeholder="Tên độc giả"/>
	                           		<form:errors path = "name"/>
	                           </div>
	                           <div class="form-group">
	                               	<label for="phone">Địa chỉ</label>
	                               	<form:input path ="address" type="text" class="form-control" placeholder="Địa chỉ"/>
	                           		<form:errors path = "address"/>
	                           </div>
	                           <button type="submit" class="btn btn-success">Submit</button>
	                       </form:form>
	                   </div>
	               </div>
	           </div>
	       </div>
   		</div>
 	</div>
<%@ include file = "footer.jsp" %>