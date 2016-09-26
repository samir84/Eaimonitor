<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<div  id="orderTableWrapper">
	<div  id="orderTableWrapper">
	         <div>${totalordersMessage }</div>
	         
				<table id ="ordersTable" class="table table-hover p-table">
                      <thead>
                      <tr>
                          <th>REFERENCE ID</th>
                          <th>ORDER STATUS</th>
                          <th>ORGANIZATION</th>
                          <th>BILL TO</th>
                          <th>SHIPP TO</th>
                          <th>NAME</th>
                          <th>ORDER DATE</th>
                      </tr>
                      </thead>
                      <tbody>
                      <c:if test="${ not empty orders }">
                      	<c:forEach var="orderHdr" items="${orders}" >
                      	<tr>
                          <td class="p-refno">
                          
                              <a href="${contextBase}order_details.html?id=${orderHdr.id }&userId={4a5bf71a-7c2c-4c8c-be55-615f71392e1f}">${orderHdr.refno}</a>
                          </td>
                          <td class="p-status">
                              <div>${orderHdr.orderStatus.description}</div>
                          </td>
                          <td class="p-organization">
                              <div>${orderHdr.salesOrganizationId}</div>
                          </td>
                          <td class="p-billto">
                              <div>${orderHdr.billto}</div>
                          </td>
                          <td class="p-shipto">
                              <div>${orderHdr.shipto}</div>
                          </td>
                           <td class="o-name">
                              <div>${orderHdr.name}</div>
                          </td>
                          <td class="p-shipto">
                              <div>${orderHdr.orderDate}</div>
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
		      <c:url value="/orders.html" var="BaseOrdersUrl"/>
      
  <div class="dataTables_paginate paging_simple_numbers" id="example2_paginate">
      
 </div>
 </div>
<!-- jQuery 2.2.0 -->
	<script src="<c:url value="/resources/plugins/jQuery/jQuery-2.2.0.min.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/resources/dist/js/jquery.bootpag.min.js"/>"></script>
  	<script src="<c:url value="/resources/dist/js/orders.js"/>"></script>
<script type="text/javascript">
showPagination(${maxPages});
</script>		
</div>
</div>