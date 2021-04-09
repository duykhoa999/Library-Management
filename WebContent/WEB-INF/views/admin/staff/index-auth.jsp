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
			                    <div class="card-tittle ml-2 pb-3">
				                    <a href="admin/staff/auth/insert.htm" class="btn btn-primary">Thêm tài khoản</a>
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
		                                    <th>Mã nhân viên</th>
		                                    <th>Tên nhân viên</th>
		                                    <th>Chức vụ</th>
		                                    <th>Tên đăng nhập</th>
		                                    <th></th>
		                                </tr>
		                                </thead>
		                                <tbody>
		                                <c:forEach var = "a" items = "${auths}">
		                                	<tr>
		                                        <td>${a.staff.id }</td>
		                                        <td>${a.staff.name }</td>
		                                        <td>${a.staff.role }</td>
		                                        <td>${a.username }</td>
		                                        <td>
		                                            <a href="admin/staff/auth/delete/${a.username}.htm"
		                                               class="btn btn-danger" onclick = "myFunction()">Xóa</a>
	                                               	
			                                         <script>
														function myFunction() {
															if(confirm("Bạn đã chắc chắn xóa chưa?") == true) {
															 	document.getElementById("deleteStaff").submit();
															}
															else {
																event.preventDefault();
																return;
															}
														}
													</script>
													<form action="admin/staff/auth/delete/${a.username}.htm" id = "deleteStaff" method="get"></form>
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
      </div>
<%@ include file = "footer.jsp" %>