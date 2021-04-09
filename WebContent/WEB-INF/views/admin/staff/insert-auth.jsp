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
				                   <a href="admin/staff/auth/index.htm" class="btn btn-primary">Danh sách tài khoản</a>
				               	</div>
		                   </div>
	                       <form:form action="admin/staff/auth/insert.htm" method="POST" modelAttribute = "auth">
	                           <div class="form-group">
	                               	<label for="name">Tên nhân viên</label>
	                               	<form:select path="staff.id" class = "form-control" items = "${staffs}" itemValue = "id" itemLabel = "name"></form:select>
	                           </div>
	                           <div class="form-group">
	                               	<label for="username">Tài khoản</label>
	                               	<form:input type="text" path = "username" class="form-control" placeholder="Tài khoản"/>
	                           		<form:errors path = "username"/>
	                           </div>
	                           <div class="form-group">
	                               	<label for="password">Mật khẩu</label>
	                               	<form:input type="password" path = "password" class="form-control" placeholder="Mật khẩu"/>
	                           		<form:errors path = "password"/>
	                           </div>
	                           <div class="form-group">
	                               	<label for="password-confirm">Xác nhận mật khẩu</label>
	                               	<input type="password" name = "password-confirm" class="form-control" placeholder="Xác nhận mật khẩu"/>
	                           </div>
	                           <button type="submit" class="btn btn-success">Thêm tài khoản</button>
	                       </form:form>
	                   </div>
	               </div>
	           </div>
	       </div>
   		</div>
      </div>
<%@ include file = "footer.jsp" %>