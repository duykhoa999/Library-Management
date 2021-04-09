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
				                    <a href="admin/book/insert-book.htm" class="btn btn-primary">Thêm sách mới</a>
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
		                                    <th>Mã sách</th>
		                                    <th>Tựa đề</th>
		                                    <th>Thể loại</th>
		                                    <th>Tác giả</th>
		                                    <th>Nhà xuất bản</th>
	                                    	<th>Năm XB</th>
		                                    <th></th>
		                                </tr>
		                                </thead>
		                                <tbody>
		                                <c:forEach var = "b" items = "${books}">
		                                	<tr>
		                                        <td>${b.id }</td>
		                                        <td>${b.tittle }</td>
		                                        <td>${b.category.name }</td>
		                                        <td>${b.author.name}</td>
	                                        	<td>${b.publisher.pubName }</td>
		                                        <td>${b.year }</td>
		                                        <td>
		                                        	<a href="admin/book/update-book/${b.id}.htm"
		                                               class="btn btn-success">Sửa</a>
	                                               <a href="admin/book/index-bookcopy/${b.id}.htm"
		                                               class="btn btn-success">Danh mục sách</a>
	                                               
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