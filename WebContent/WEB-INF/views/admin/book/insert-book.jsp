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
				                   <a href="admin/book/index.htm" class="btn btn-primary">Danh sách các sách</a>
				                   <a href="admin/book/insert-author.htm" class="btn btn-primary">Thêm tác giả mới</a>
				                   <a href="admin/book/insert-cate.htm" class="btn btn-primary">Thêm thể loại mới</a>
				                   <a href="admin/book/insert-pub.htm" class="btn btn-primary">Thêm NXB mới</a>
				               	</div>
		                   </div>
		                   <c:if test="${message != null}">
								<div class="alert alert-success mt-3">${message}</div>
							</c:if>
							<c:if test="${error != null}">
								<div class="alert alert-danger mt-3">${error}</div>
							</c:if>
	                       <form:form action="admin/book/insert-book.htm" method="POST" modelAttribute = "book" enctype = "multipart/form-data">
	                           <div class="form-group">
	                               	<label for="tittle">Tựa đề</label>
	                               	<form:input type="text" path = "tittle" class="form-control" placeholder="Tựa đề"/>
	                           		<form:errors path = "tittle"/>
	                           </div>
	                           <div class="form-group">
	                               	<label for="year">Năm xuất bản</label>
	                               	<form:input type="number" path = "year" class="form-control" placeholder="Năm xuất bản"/>
	                           		<form:errors path = "year"/>
	                           </div>
	                           <div class="form-group">
	                               	<label for="category">Thể loại</label>
	                               	<form:select path="category.id" items = "${categories }" class="form-control" itemValue = "id" itemLabel = "name"/>
	                           		<%-- <form:errors path = "role"/> --%>
	                           </div>
	                           <div class="form-group">
	                               	<label for="author">Tác giả</label>
	                               	<form:select path="author.id" items = "${authors }" class="form-control" itemValue = "id" itemLabel = "name"/>
	                           		<%-- <form:errors path = "role"/> --%>
	                           </div>
	                           <div class="form-group">
	                               	<label for="publisher">Nhà xuất bản</label>
	                               	<form:select path="publisher.id" items = "${publishers }" class="form-control" itemValue = "id" itemLabel = "pubName"/>
	                           		<%-- <form:errors path = "role"/> --%>
	                           </div>
	                           <div class="form-group">
	                               	<label for="image">Hình ảnh</label>
	                               	<div class="btn btn-info">
	                               		<input type="file" name="attachment" />
									</div>
									<c:if test="${errorImage != null}">
										<span class="alert alert-danger">${errorImage}</span>
									</c:if>
	                           		<%-- <form:errors path = "name"/> --%>
	                           </div>
	                           <div class="form-group">
	                               	<label for="length">Số trang</label>
	                               	<form:input type="number" path = "length" class="form-control" placeholder="Số trang"/>
	                           		<form:errors path = "length"/>
	                           	</div>
	                           	<div class="form-group">
	                               	<label for="language">Ngôn ngữ</label>
	                               	<form:input type="text" path = "language" class="form-control" placeholder="Ngôn ngữ"/>
	                           		<form:errors path = "language"/>
	                           	</div>
	                           	<div class="form-group">
	                               	<label for="description">Mô tả</label>
	                               	<form:textarea path = "description" class="form-control" placeholder="Mô tả (Có thể để trống)"/>
	                           	</div>
	                           <button type="submit" class="btn btn-success">Thêm sách</button>
	                       </form:form>
	                   </div>
	               </div>
	           </div>
	       </div>
   		</div>
      </div>
<%@ include file = "footer.jsp" %>