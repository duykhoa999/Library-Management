<%@ page pageEncoding="utf-8"%>
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
		                <a href="admin/staff/insert.htm" class="btn btn-primary">Thêm nhân viên</a>
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
		                              <th>ID</th>
		                              <th>Tên</th>
		                              <th>Chức vụ</th>
		                              <th>Lương</th>
		                              <th></th>
		                          </tr>
		                          </thead>
		                          <tbody>
		                          <c:forEach var = "s" items = "${staffs}">
		                          	<tr>
		                                  <td>${s.id }</td>
		                                  <td>${s.name }</td>
		                                  <td>${s.role }</td>
		                                  <td>${s.salary }</td>
		                                  <td>
		                                      <a href="admin/staff/update/${s.id}.htm"
		                                         class="btn btn-primary">Sửa</a>
		                                      <a href="admin/staff/delete/${s.id}.htm"
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
							<form action="admin/staff/delete/${s.id}.htm" id = "deleteStaff" method="get"></form>
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