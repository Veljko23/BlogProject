<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta http-equiv="x-ua-compatible" content="ie=edge">

  <title>AdminLTE 3 | Starter</title>

  <!-- Font Awesome Icons -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/plugins/fontawesome-free/css/all.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/dist/css/adminlte.min.css">
  <!-- Google Font: Source Sans Pro -->
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">

  <!-- Navbar -->
  <nav class="main-header navbar navbar-expand navbar-white navbar-light">
    <!-- Left navbar links -->
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
      </li>
    </ul>

    
    <!-- Right navbar links -->
    <ul class="navbar-nav ml-auto">
      <li class="nav-item dropdown">
        <a class="nav-link"  href="message-list">
          <i class="far fa-comments"></i>
          <span class="badge badge-danger navbar-badge">${messageCount }</span>
        </a>
       
      </li>
      <!-- Messages Dropdown Menu -->
      <li class="nav-item dropdown">
        <a class="nav-link" data-toggle="dropdown" href="#">
          <i class="far fa-user"></i>
        </a>
           <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
          <a href="author-edit-profile" class="dropdown-item">
            <!-- Message Start -->
            <div class="media align-items-center">
              <img src="${pageContext.request.contextPath }/dist/img/user1-128x128.jpg" alt="User Avatar" class="img-brand-50 mr-3 img-circle">
              <div class="media-body">
                <h3 class="dropdown-item-title">
                   <sec:authentication property="principal.username"/> 
                </h3>
              </div>
            </div>
            <!-- Message End -->
          </a>
          <div class="dropdown-divider"></div>
          <a href="author-change-password" class="dropdown-item">
            <i class="fas fa-user"></i> Change password
          </a>
          <div class="dropdown-divider"></div>
          <a href="login.html" class="dropdown-item">
              
              <form:form action="${pageContext.request.contextPath }/logout">
              	<input type="submit" value="Sign out">
              </form:form>
              
          </a>
        </div>
      </li>
    </ul>
  </nav>
  <!-- /.navbar -->

  <!-- Main Sidebar Container -->
<aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Product Logo -->
    <a href="post-list" class="brand-link">
      <img src="${pageContext.request.contextPath }/dist/img/AdminLTELogo.png" alt="Cubes School Logo" class="brand-image img-circle elevation-3"
           style="opacity: .8">
      <span class="brand-text font-weight-light">Cubes School</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
      <!-- Sidebar Menu -->
            <nav class="mt-2">
        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
          <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
          <li class="nav-item has-treeview">
            <a href="#" class="nav-link">
              <i class="nav-icon far fa-plus-square"></i>
              <p>
                Posts
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="post-list" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Posts list</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="post-form" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Add Post</p>
                </a>
              </li>
            </ul>
          </li>
          <li class="nav-item has-treeview">
            <a href="#" class="nav-link">
              <i class="nav-icon far fa-plus-square"></i>
              <p>
                Categories
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="category-list" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Categories list</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="category-form" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Add Category</p>
                </a>
              </li>
            </ul>
          </li>
          <li class="nav-item has-treeview">
            <a href="#" class="nav-link">
              <i class="nav-icon far fa-plus-square"></i>
              <p>
                Tags
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="tag-list" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Tags list</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="tag-form" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Add Tag</p>
                </a>
              </li>
            </ul>
          </li>
          
          <li class="nav-item has-treeview">
            <a href="#" class="nav-link">
              <i class="nav-icon far fa-plus-square"></i>
              <p>
                Other
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="message-list" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Messages List</p>
                </a>
              </li>
            </ul>
          </li>
           <li class="nav-item has-treeview">
            <a href="#" class="nav-link">
              <i class="nav-icon far fa-plus-square"></i>
              <p>
                Authors
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="author-list" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Author list</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="author-form" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Add Author</p>
                </a>
              </li>
            </ul>
          </li>
           <li class="nav-item has-treeview">
            <a href="#" class="nav-link">
              <i class="nav-icon far fa-plus-square"></i>
              <p>
                Comments
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="comment-list" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Comment list</p>
                </a>
              </li>
            </ul>
          </li>
          <li class="nav-item has-treeview">
            <a href="#" class="nav-link">
              <i class="nav-icon far fa-plus-square"></i>
              <p>
                Slides
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="slider-list" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Slides list</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="slider-form" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Add Slide</p>
                </a>
              </li>
            </ul>
          </li>
        </ul>
      </nav>
      <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1>Posts</h1>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <div class="row">
          <div class="col-md-12">
            <div class="card">
              <div class="card-header">
                <h3 class="card-title">All Posts</h3>
                <div class="card-tools">
                  <a href="post-form" class="btn btn-success">
                    <i class="fas fa-plus-square"></i>
                    Add new Post
                  </a>
                </div>
              </div>
              </div>
              <!-- /.card-header -->
              <div class="card-body">
               <div class="col-sm-2">
                	<form class="center-block" role="form" action="post-order">
                     	<div class="form-group center-block text-center">
                     		<ul class="nav navbar-nav list-inline text-center clearfix">
                      			<li>
                     				<select class="form-control" id="sel1" name="orderBy">
                     					<option value="5">--Choose option--</option>
                    					<option value="0">By date created</option>
                   						<option value="1">By title</option>
                   						<option value="2">By author name</option>
                   						<option value="3">By enabled post</option>
                   						<option value="4">By disabled post</option>
                   						<option value="6">Most viewed</option>
                   						<option value="7">Most commented</option>
                   					</select>
                   				</li>
                   				<li>
                                  <input type="submit" value="Sort">
                                </li>
                  			</ul>
                  		</div>
                  </form>
                  <div class="widget search">
            	<header>
              		<h3 class="h6">Search the blog</h3>
            	</header>
            	<form class="center-block" action="post-search">
                  <div class="form-group">
                    <input type="search" name="search" id="search" placeholder="What are you looking for?">
                    <button type="submit" class="submit"><i class="icon-search-1"></i></button>
                  </div>
                </form>
          		</div>
          		</div>
                <table class="table table-bordered">
                  <thead>                  
                    <tr>
                      <th class="text-center">ID</th>
                      <th class="text-center">Title</th>
                      <th class="text-center">Number of Comments</th>
                      <th class="text-center">Number of Views</th>
                      <th class="text-center">Author</th>
                      <th class="text-center">Image</th>
                      <th class="text-center">Date created</th>
                      <th class="text-center">Actions</th>
                    </tr>
                  </thead>
                  <tbody>
                    
             <c:forEach var="post" items="${postList }">
                    
                    <tr>
                      <td>${post.id }</td>
                      
                      <td class="text-center">
                        <strong>${post.title }</strong>
                      </td>
                      <td>
                        ${post.numComments }
                      </td>
                      <td>${post.numViews }</td>
                      <td>${post.author.name }</td>
                      <td class="text-center">
                        <img src="${post.image }" style="max-width: 80px;">
                      </td>
                      <td>
                        ${post.date }
                      </td>
                      <td class="text-center">
                        <div class="btn-group">
                        
                        <a href="form-post-enable?id=${post.id }" class="btn btn-info">
                            <c:if test="${post.isEnabled }">
                            <i class="fas fa-eye" style="color: #b6fc03"></i>
                          	</c:if>
                          	
                          	<c:if test="${!post.isEnabled }">
                            <i class="fas fa-eye-slash"></i>
                          	</c:if>
                          	
                          </a>
                          
                          <a href="form-post-important?id=${post.id }" class="btn btn-info">
                            <c:if test="${!post.isImportant }">
                            <i class="fas fa-star"></i>
                          	</c:if>
                          	
                          	<c:if test="${post.isImportant }">
                            <i class="far fa-star" style="color: #b6fc03"></i>
                          	</c:if>
                          	
                          </a>
                          
                        
                          <a href="form-post-update?id=${post.id }" class="btn btn-info" >
                            <i class="fas fa-edit"></i>
                          </a>
                          <button type="button" class="btn btn-info" data-toggle="modal" data-target="#delete-modal-${post.id }">
                            <i class="fas fa-trash"></i>
                          </button>
                        </div>
                      </td>
                    </tr>
                    
                    
                    
					     <div class="modal fade" id="delete-modal-${post.id }">
					      <div class="modal-dialog">
					        <div class="modal-content">
					          <div class="modal-header">
					            <h4 class="modal-title">Delete Post</h4>
					            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
					              <span aria-hidden="true">&times;</span>
					            </button>
					          </div>
					          <div class="modal-body">
					            <p>Are you sure you want to delete post?</p>
					            <strong></strong>
					          </div>
					          <div class="modal-footer justify-content-between">
					            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					            <a href="form-post-delete?id=${post.id }" class="btn btn-danger">Delete</a>
					          </div>
					        </div>
					        <!-- /.modal-content -->
					      </div>
					      <!-- /.modal-dialog -->
					    </div>
             </c:forEach>
                  </tbody>
                </table>
              </div>
              <!-- /.card-body -->
              <div class="card-footer clearfix">
                
              </div>
            </div>
            <!-- /.card -->
          </div>
          <!-- /.col -->
        </div>
        <!-- /.row -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->


    <!-- /.modal -->
  </div>
  <!-- /.content-wrapper -->

  

  <!-- Main Footer -->
  <footer class="main-footer">
    <!-- To the right -->
    <div class="float-right d-none d-sm-inline">
      Java
    </div>
    <!-- Default to the left -->
    <strong>Copyright &copy; 2019 <a href="https://cubes.edu.rs">Cubes School</a>.</strong> All rights reserved.
  </footer>
</div>
<!-- ./wrapper -->

<!-- REQUIRED SCRIPTS -->

<!-- jQuery -->
<script src="${pageContext.request.contextPath }/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="${pageContext.request.contextPath }/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath }/dist/js/adminlte.min.js"></script>
</body>
</html>
