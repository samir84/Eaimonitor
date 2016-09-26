<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
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
                      <c:if test="${ not empty projects }">
                      	<c:forEach var="projectDetail" items="${projects}" >
                      	<tr>
                          <td class="p-name">
                              <a href="project_details.html">${projectDetail.project.pname}</a>
                              <br>
                              <small>${projectDetail.project.pkey}</small>
                          </td>
                          <c:if test="${ not empty projectDetail.assignees }">
                          <td class="p-team">
                          <c:forEach var="assignee" items="${projectDetail.assignees}">
                              <a href="#"><img alt="image" width="32" height="32" class="img-circle" src="" avatar="${assignee}"></a>
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
                              <%-- <span class="label label-primary">${projectDetail.openIssues}</span> --%>
                              <span class="badge bg-red">${projectDetail.openIssues}</span>
                          </td>
                          <!-- <td>
                              <a href="project_details.html" class="btn btn-primary btn-xs"><i class="fa fa-folder"></i> View </a>
                              <a href="#" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> Edit </a>
                              <a href="#" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Delete </a>
                          </td> -->
                          <td>
                          	<a href="http://vdelnnap46.ce.hsi.local:9080/browse/${projectDetail.project.pkey}"><i class="fa fa-link"></i> ${projectDetail.project.pkey} </a>
                          	
                          </td>
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
		<tag:paginate max="15" offset="${maxResult}" count="${totalProjects}"
   				uri="#" onclick ="return PaginationProjects(2,5)" next="&raquo;" previous="&laquo;" />
   	</div>
		
	</div>
	</div>
                <%--   <script src="<c:url value="/resources/dist/js/letterAvatar.js"/>"></script>	
		<script src="<c:url value="/resources/dist/js/projects.js"/>"></script>	 --%>