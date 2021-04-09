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
				                    <a href="admin/book/index.htm" class="btn btn-primary">Danh sách các sách</a>
		                    		<a href="admin/book/insert-copy/${id }.htm" class="btn btn-primary">Thêm một bản copy mới</a>  	
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
		                                    <th>Tên sách</th>
		                                    <th>Trạng thái</th>
		                                </tr>
		                                </thead>
		                                <tbody>
		                                <c:forEach var = "b" items = "${book_coppies}">
		                                	<tr>
		                                        <td>${b.id }</td>
		                                        <td>${b.book.tittle }</td>
		                                        <td>${b.status == 0 ? 'Chưa mượn' : 'Đã mượn' }</td>
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