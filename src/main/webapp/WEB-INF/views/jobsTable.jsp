<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<div  id="orderTableWrapper">
	<table id ="ordersTable" class="table table-hover p-table">
                      <thead>
                      <tr>
                          <th>order Name</th>
                          <th>Team Member</th>
                          <th>order Progress</th>
                          <th>Total Open Issues</th>
                          <th>URL</th>
                      </tr>
                      </thead>
                      <tbody>
                      <c:if test="${ not empty orders }">
                      	<c:forEach var="orderDetail" items="${orders}" >
                      	<tr>
                          <td class="p-name">
                              <a href="order_details.html">${orderDetail.order.pname}</a>
                              <br>
                              <small>${orderDetail.order.pkey}</small>
                          </td>
                          <c:if test="${ not empty orderDetail.assignees }">
                          <td class="p-team">
                          <c:forEach var="assignee" items="${orderDetail.assignees}">
                              <a href="#"><img alt="image" width="32" height="32" class="img-circle" src="" avatar="${assignee}"></a>
                          </c:forEach>
                          </td>
                          </c:if>
                          <td class="p-progress">
                              <div class="progress progress-xs">
                                  <div style="width: ${orderDetail.progress};" class="progress-bar progress-bar-success"></div>
                              </div>
                              <small>${orderDetail.progress} Complete </small>
                          </td>
                          <td>
                              <%-- <span class="label label-primary">${orderDetail.openIssues}</span> --%>
                              <span class="badge bg-red">${orderDetail.openIssues}</span>
                          </td>
                          <!-- <td>
                              <a href="order_details.html" class="btn btn-primary btn-xs"><i class="fa fa-folder"></i> View </a>
                              <a href="#" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> Edit </a>
                              <a href="#" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Delete </a>
                          </td> -->
                          <td>
                          	<a href="http://vdelnnap46.ce.hsi.local:9080/browse/${orderDetail.order.pkey}"><i class="fa fa-link"></i> ${orderDetail.order.pkey} </a>
                          	
                          </td>
                      </tr>
                      	</c:forEach>
                      </c:if>
                     
                      
                      </tbody>
                  </table>
                                            <!-- Pagination -->
 <div class="row">                 
	<div class="col-sm-5">
		<div class="dataTables_info" id="example2_info" role="status" aria-live="polite">${totalordersMessage}</div>
	</div>
	<div class="col-sm-7">
		<tag:paginate max="15" offset="${maxResult}" count="${totalorders}"
   				uri="#" onclick ="return Paginationorders(2,5)" next="&raquo;" previous="&laquo;" />
   	</div>
		
	</div>
	</div>
                <%--   <script src="<c:url value="/resources/dist/js/letterAvatar.js"/>"></script>	
		<script src="<c:url value="/resources/dist/js/orders.js"/>"></script>	 --%>