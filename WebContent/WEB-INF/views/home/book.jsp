<%@ page pageEncoding="utf-8"%>
<%@ include file = "header.jsp" %>
<!-- Start: Page Banner -->
   <section class="page-banner services-banner">
       	<div class="container">
			<div class="breadcrumb">
	           <ul>
	               <li><a href="home/index.htm">Home</a></li>
	               <li>Book</li>
	           </ul>
	       	</div>
       	</div>
   </section>
   <!-- End: Page Banner -->
<!-- Start: Book & Media Section -->
        <div id="content" class="site-content">
            <div id="primary" class="content-area">
                <main id="main" class="site-main">
                    <div class="books-media-list">
                        <div class="container">
                            <div class="row">
                                <!-- Start: Search Section -->
                                <section class="search-filters">
                                    <div class="container">
                                        <div class="filter-box">
                                            <h3>What are you looking for at the library?</h3>
                                            <c:if test="${message != null}">
												<div class="alert alert-success">${message}</div>
											</c:if>
											<c:if test="${error != null}">
												<div class="alert alert-danger">${error}</div>
											</c:if>
                                            <form action="http://libraria.demo.presstigers.com/index.html" method="get">
                                                <div class="col-md-4 col-sm-6">
                                                    <div class="form-group">
                                                        <label class="sr-only" for="keywords">Search by Keyword</label>
                                                        <input class="form-control" placeholder="Search by Keyword" id="keywords" name="keywords" type="text">
                                                    </div>
                                                </div>
                                                <div class="col-md-3 col-sm-6">
                                                    <div class="form-group">
                                                        <select name="catalog" id="catalog" class="form-control">
                                                            <option>Search the Catalog</option>
                                                            <option>Catalog 01</option>
                                                            <option>Catalog 02</option>
                                                            <option>Catalog 03</option>
                                                            <option>Catalog 04</option>
                                                            <option>Catalog 05</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-md-3 col-sm-6">
                                                    <div class="form-group">
                                                        <select name="category" id="category" class="form-control">
                                                            <option>All Categories</option>
                                                            <option>Category 01</option>
                                                            <option>Category 02</option>
                                                            <option>Category 03</option>
                                                            <option>Category 04</option>
                                                            <option>Category 05</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-md-2 col-sm-6">
                                                    <div class="form-group">
                                                        <input class="form-control" type="submit" value="Search">
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </section>
                                <!-- End: Search Section -->
                            </div>
                            <div class="row">
                                <div class="col-md-9 col-md-push-1">
                                    <div class="books-list">
                                    	<!-- Danh sách -->
                                    	<c:forEach var = "b" items = "${books}">
                                    		<article> 
	                                            <div class="single-book-box">                                                
	                                                <div class="post-thumbnail">
	                                                    <div class="book-list-icon yellow-icon"></div>
	                                                    <a href="book/detail/${b.id }.htm"><img alt="Book" src="resources/images-book/${b.image }"/></a>                                                                 
                                                    </div>
	                                                <div class="post-detail">
	                                                    <div class="books-social-sharing">
	                                                        <ul>
	                                                            <li><a href="#" target="_blank"><i class="fa fa-facebook"></i></a></li>
	                                                            <li><a href="#" target="_blank"><i class="fa fa-twitter"></i></a></li>
	                                                            <li><a href="#" target="_blank"><i class="fa fa-google-plus"></i></a></li>
	                                                            <li><a href="#" target="_blank"><i class="fa fa-rss"></i></a></li>
	                                                            <li><a href="#" target="_blank"><i class="fa fa-linkedin"></i></a></li>
	                                                        </ul>
	                                                    </div>
	                                                    <div class="optional-links">
	                                                        <ul>
	                                                            <li>
	                                                                <a href="#" target="_blank" data-toggle="blog-tags" data-placement="top" title="Add TO CART">
	                                                                    <i class="fa fa-shopping-cart"></i>
	                                                                </a>
	                                                            </li>
	                                                            <li>
	                                                                <a href="#" target="_blank" data-toggle="blog-tags" data-placement="top" title="Like">
	                                                                    <i class="fa fa-heart"></i>
	                                                                </a>
	                                                            </li>
	                                                            <li>
	                                                                <a href="#" target="_blank" data-toggle="blog-tags" data-placement="top" title="Mail"><i class="fa fa-envelope"></i>
	                                                                </a>
	                                                            </li>
	                                                            <li>
	                                                                <a href="#" target="_blank" data-toggle="blog-tags" data-placement="top" title="Search">
	                                                                    <i class="fa fa-search"></i>
	                                                                </a>
	                                                            </li>
	                                                            <li>
	                                                                <a href="#" target="_blank" data-toggle="blog-tags" data-placement="top" title="Print">
	                                                                    <i class="fa fa-print"></i>
	                                                                </a>
	                                                            </li>
	                                                        </ul>
	                                                    </div>
	                                                    <header class="entry-header">
	                                                        <div class="row">
	                                                            <div class="col-sm-6">
	                                                                <h3 class="entry-title">
	                                                                    <a href="book/detail/${b.id }.htm">${b.tittle }</a>
	                                                                </h3>
	                                                                <ul>
	                                                                    <li><strong>Author:</strong> ${b.author.name}</li>
	                                                                    <li><strong>ISBN:</strong> ISBN-${b.id }</li>
	                                                                </ul>
	                                                            </div>
	                                                            <div class="col-sm-6">
	                                                                <ul>
	                                                                    <li><strong>Publisher:</strong> ${b.publisher.pubName }</li>
	                                                                    <li><strong>Publish Year:</strong> ${b.year }</li>
	                                                                    <li>
	                                                                    	<strong>Genre:</strong> ${b.category.name }
	                                                                        <!-- <div class="rating">
	                                                                            <strong>Rating: </strong>
	                                                                            <span>☆</span>
	                                                                            <span>☆</span>
	                                                                            <span>☆</span>
	                                                                            <span>☆</span>
	                                                                            <span>☆</span>
	                                                                        </div> -->
	                                                                    </li>
	                                                                </ul>                                                                
	                                                            </div>
	                                                        </div>
	                                                    </header>
	                                                    <div class="entry-content">
	                                                        <p>${b.description }</p>
	                                                    </div>
	                                                    <footer class="entry-footer">
	                                                        <a class="btn btn-dark-gray" href="book/detail/${b.id }.htm">Read More</a>
	                                                    </footer>
	                                                </div>
	                                                <div class="clear"></div>
	                                            </div>
	                                        </article>
                                    	</c:forEach>
                                    </div>
                                    <!-- <nav class="navigation pagination text-center">
                                        <h2 class="screen-reader-text">Posts navigation</h2>
                                        <div class="nav-links">
                                            <a class="prev page-numbers" href="#."><i class="fa fa-long-arrow-left"></i> Previous</a>
                                            <a class="page-numbers" href="#.">1</a>
                                            <span class="page-numbers current">2</span>
                                            <a class="page-numbers" href="#.">3</a>
                                            <a class="page-numbers" href="#.">4</a>
                                            <a class="next page-numbers" href="#.">Next <i class="fa fa-long-arrow-right"></i></a>
                                        </div>
                                    </nav> -->
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
        </div>
        <!-- End: Books & Media Section -->
        <!-- Start: Social Network -->
        <section class="social-network section-padding">
            <div class="container">
                <div class="center-content">
                    <h2 class="section-title">Follow Us</h2>
                    <span class="underline center"></span>
                    <p class="lead">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                </div>
                <ul>
                    <li>
                        <a class="facebook" href="#" target="_blank">
                            <span>
                                <i class="fa fa-facebook-f"></i>
                            </span>
                        </a>
                    </li>
                    <li>
                        <a class="twitter" href="#" target="_blank">
                            <span>
                                <i class="fa fa-twitter"></i>
                            </span>
                        </a>
                    </li>
                    <li>
                        <a class="google" href="#" target="_blank">
                            <span>
                                <i class="fa fa-google-plus"></i>
                            </span>
                        </a>
                    </li>
                    <li>
                        <a class="rss" href="#" target="_blank">
                            <span>
                                <i class="fa fa-rss"></i>
                            </span>
                        </a>
                    </li>
                    <li>
                        <a class="linkedin" href="#" target="_blank">
                            <span>
                                <i class="fa fa-linkedin"></i>
                            </span>
                        </a>
                    </li>
                    <li>
                        <a class="youtube" href="#" target="_blank">
                            <span>
                                <i class="fa fa-youtube"></i>
                            </span>
                        </a>
                    </li>
                </ul>
            </div>
        </section>
        <!-- End: Social Network -->
<%@ include file = "footer.jsp" %>