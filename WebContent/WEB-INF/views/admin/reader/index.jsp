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
		                                    <th>Mã độc giả</th>
		                                    <th>Tên độc giả</th>
		                                    <th>Địa chỉ</th>
		                                    <th>Ngày đăng ký</th>
		                                    <th></th>
		                                </tr>
		                                </thead>
		                                <tbody>
		                                <c:forEach var = "r" items = "${readers}">
		                                	<tr>
		                                        <td>${r.id }</td>
		                                        <td>${r.name }</td>
		                                        <td>${r.address }</td>
		                                        <td>${r.registrationDate }</td>
		                                        <td>
		                                            <a href = "admin/reader/update/${r.id }.htm" class = "btn btn-success">Sửa</a>
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