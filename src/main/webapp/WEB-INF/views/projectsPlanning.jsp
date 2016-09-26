<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
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
        		<li class="active"><i class="fa fa-cubes"></i>Project Planning</li>
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
                          			<a href="#" class=" btn btn-success btn-xs"> Create New Planning</a>
                          			
                      			</span>
    
                  			</header>
                  			<c:url value="/uploadFile" var="uploadUrl" />
<form id="planningFileUpload" action="${uploadUrl}" method="post" enctype="multipart/form-data">

               <span class="btn btn-default btn-file " id="importFiles">
    		   	<i class="fa fa-upload">&nbsp;upload </i> <input type="file" name="file" id="file" multiple>
               </span>

   
	<div id="notifications"></div>
	

<div id="status"></div>
	</form>
						</div>

						<div class="box-body">
						
                             <div class="row">
	<div class="col-sm-4">
		<div class="form-inline" id="date">
                <label>Date from:</label>

                <div class="input-group date">
                  <div class="input-group-addon">
                    <i class="fa fa-calendar"></i>
                  </div>
                  <input type="text" class="form-control pull-right" id="DatepickerFrom" />
                </div>
                <!-- /.input group -->
              </div>
	</div>
	<div class="col-md-4 "></div>
	<div class="col-md-4 ">
            <div class="form-group has-feedback">
                <input id="projectPlanningTable_filter" type="text" class="form-control" placeholder="Search">
                <span class="glyphicon glyphicon-search form-control-feedback"></span>
            </div>
	</div>

	</div>
	         <div  id="projectPlanningTableWrapper">
				<table id ="projectPlanningTable" class="table table-hover p-table">
                      <thead>
                      <tr>
                      	  <th>Jira</th>
                          <th>Project Name</th>
                          <th>Project Acitivity</th>
                          <th>Team Member</th>
                          <th>Description</th>
                          <th>Remark</th>
                          <th>Week Number</th>
                          <th>Excpected Hours</th>
                          <sec:authorize access="hasRole('ROLE_ADMIN')">
   							<th>Actions</th>
  						  </sec:authorize>
                      </tr>
                      </thead>
                      <tbody>
                      
                      <c:if test="${not empty projectsPlanningList}">
                      	<c:forEach var="projectPlanning" items="${projectsPlanningList}" >
                      	<tr>
                          <td class="p-key">
                              <a href="http://vdelnnap46.ce.hsi.local:9080/browse/${projectPlanning.jiraIssue}" target="_blank">${projectPlanning.jiraIssue}</a>
                          </td>
                          <td>
                          	${projectPlanning.projectName}
                          </td>
                          <td>
                          	${projectPlanning.activity}
                          </td>
                          <td class="p-team">
                                <a href="#"><img alt="${projectPlanning.assignee}" width="32" height="32" class="img-circle" src="" avatar="${projectPlanning.assignee}"></a>
                          </td>
                          <td>
                          	${projectPlanning.description}
                          </td>
                          <td>
                          	${projectPlanning.remark}
                          </td>
                          <td id="weekNumber" class="test123">
                          	${projectPlanning.week}
                          </td>
                          <sec:authorize access="hasRole('ROLE_ADMIN')">
   							<td>
   								<div class="action-buttons">
									<a style="cursor: pointer;">
										<span class="glyphicon glyphicon-pencil" id="edit"/>
									</a>                                
									<a class="trash" style="cursor: pointer;">
										<span class="glyphicon glyphicon-trash" id="trash"/>
									</a>
								</div>
   							
   							</td>
  						  </sec:authorize>
                      </tr>
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
		