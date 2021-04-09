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
		                     <form:form action="admin/staff/update/${staff.id}.htm" method="POST" modelAttribute = "staff">
		                         <div class="form-group">
		                            <label for="id">Mã nhân viên</label>
		                            <form:input readOnly = "true" type="text" path = "id" class="form-control" name="id" placeholder="Mã nhân viên"/>
		                        	<form:errors path = "id"/>
		                         </div>
		                         <div class="form-group">
		                            <label for="name">Tên nhân viên</label>
		                            <form:input type="text" path = "name" class="form-control" name="name" placeholder="Tên nhân viên"/>
		                         	<form:errors path = "name"/>
		                         </div>
		                         <div class="form-group">
		                            <label for="role">Chức vụ</label>
		                            <form:input type="text" path = "role" class="form-control" name="role" placeholder="Chức vụ"/>
		                         	<form:errors path = "role"/>
		                         </div>
		                         <div class="form-group">
		                            <label for="salary">Lương</label>
		                            <form:input type="number" path = "salary" class="form-control" name="salary" placeholder="Lương"/>
		                         </div>
		                         <button type="submit" class="btn btn-success">Cập nhật nhân viên</button>
		                     </form:form>
		                 </div>
		             </div>
		         </div>
		     </div>
		</div>
    </div>
<%@ include file = "footer.jsp" %>