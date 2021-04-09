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
				                   <a href="admin/staff/index.htm" class="btn btn-primary">Danh sách nhân viên</a>
				               	</div>
		                   </div>
	                       <form:form action="admin/staff/insert.htm" method="POST" modelAttribute = "staff">
	                           <div class="form-group">
	                               	<label for="id">Mã nhân viên</label>
	                               	<form:input type="text" path = "id" class="form-control" placeholder="Mã nhân viên"/>
	                           		<form:errors path = "id"/>
	                           </div>
	                           <div class="form-group">
	                               	<label for="name">Tên nhân viên</label>
	                               	<form:input type="text" path = "name" class="form-control" placeholder="Tên nhân viên"/>
	                           		<form:errors path = "name"/>
	                           </div>
	                           <div class="form-group">
	                               	<label for="email">Email (Dùng để lấy lại mật khẩu)</label>
	                               	<form:input type="text" path = "email" class="form-control" placeholder="Email"/>
	                           		<form:errors path = "email"/>
	                           </div>
	                           <div class="form-group">
	                               	<label for="role">Chức vụ</label>
	                               	<form:input type="text" path = "role" class="form-control" placeholder="Chức vụ"/>
	                           		<form:errors path = "role"/>
	                           </div>
	                           <div class="form-group">
	                               	<label for="salary">Lương</label>
	                               	<form:input type="number" path = "salary" class="form-control" placeholder="Lương"/>
	                           		<form:errors path = "salary"/>
	                           </div>
	                           <button type="submit" class="btn btn-success">Thêm nhân viên</button>
	                       </form:form>
	                   </div>
	               </div>
	           </div>
	       </div>
   		</div>
      </div>
<%@ include file = "footer.jsp" %>