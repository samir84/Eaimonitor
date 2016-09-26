<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<c:url value="/" var="contextBase" />
		<section class="content-header">
      		<h1>
        		Dashboard
        		<small>Project Managment</small>
     		 </h1>
      		<ol class="breadcrumb">
        		<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        		<li class="active"><i class="fa fa-cubes"></i>Project Managment</li>
      		</ol>
    	</section>
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-header">
							<header class="panel-heading">
                      			All projects List
                      			<span class="pull-right">
                          			<button type="button" id="loading-btn" class="btn btn-warning btn-xs" onclick="refreshProjectPage()"><i class="fa fa-refresh"></i> Refresh</button>
                          			<a href="#" class=" btn btn-success btn-xs"> Create New Project</a>
                      			</span>
                  			</header>
						</div>
						<div class="box-body">
						
                             <div class="row">
	<div class="col-sm-6">
		<%-- <div id="example1_length" class="dataTables_length">
		
		<label>Show 
		<form:select path="projectRecordsOptions" onchange="projectRecordselected()" class="form-control input-sm" aria-controls="example1" id="projectRecordsOptions" >
   			<form:options items="${projectRecordsOptions}" />
		</form:select> entries</label>
		</div>  --%>
	</div>
	<c:url value="/projects.html/search" var="SearchProjectsUrl"/>
	<div class="col-sm-6">
		<!--  search Form -->
		<div class="dataTables_filter" id="example1_filter">
			<div class="input-group input-group-sm">
					<input id="searchProjectsText" name="searchTerm" type="text" placeholder="Search" class="form-control pull-right"  />
						<div class="input-group-btn">
							<button class="btn btn-default" type="submit" onclick="projectFullTextSearch()"><i class="fa fa-search"></i></button>
						</div>
			</div>
			</div>
		<!-- Einde search form -->
		</div>
	</div>
	         <div  id="projectTableWrapper">
				<table id ="projectsTable" class="table table-hover p-table">
                      <thead>
                      <tr>
                          <th>Project Name</th>
                          <th>Team Member</th>
                          <th>Project Progress</th>
                          <th>Total Open Issues</th>
                          <th>URL</th>
                      </tr>
                      </thead>
                      <tbody>
                      
                      <c:if test="${not empty projects}">
                      	<c:forEach var="projectDetail" items="${projects}" >
                      	<c:if test="${projectDetail.project.pcounter !=0 }">
                      	<tr>
                          <td class="p-name">
                              <a href="${contextBase}project_details.html?id=${projectDetail.project.id}" >${projectDetail.project.pname}</a>
                              <br>
                              <small>${projectDetail.project.pkey}</small>
                          </td>
                          <c:if test="${ not empty projectDetail.assignees }">
                          <td class="p-team">
                          <c:forEach var="assignee" items="${projectDetail.assignees}">
                              <c:if test="${ not empty assignee}">
                                <a href="#"><img alt="${assignee}" width="32" height="32" class="img-circle" src="" avatar="${assignee}"></a>
                               </c:if>
                          </c:forEach>
                          </td>
                          </c:if>
                          <td class="p-progress">
                              <div class="progress progress-xs">
                                  <div style="width: ${projectDetail.progress};" class="progress-bar progress-bar-success"></div>
                              </div>
                              <small>${projectDetail.progress} Complete </small>
                          </td>
                          <td>
                              <span class="badge bg-red">${projectDetail.openIssues}</span>
                          </td>
                          <td>
                          	<a href="http://vdelnnap46.ce.hsi.local:9080/browse/${projectDetail.project.pkey}" target="_blank"><i class="fa fa-link"></i> ${projectDetail.project.pkey} </a>
                          	
                          </td>
                      </tr>
                      </c:if>
                      	</c:forEach>
                      </c:if>
                     
                      
                      </tbody>
                  </table>
                          <!-- Pagination -->
 <div class="row">                 
	<div class="col-sm-5">
		<div class="dataTables_info" id="example2_info" role="status" aria-live="polite">${totalProjectsMessage}</div>
	</div>
	<div class="col-sm-7">

   	</div>
		
	</div>
</div>
<!-- Ein Pagination -->
						</div>
					</div>
				</div>
			</div>
              </section>
			</div>
		<script src="<c:url value="/resources/dist/js/letterAvatar.js"/>"></script>	
		<script src="<c:url value="/resources/dist/js/projects.js"/>"></script>	