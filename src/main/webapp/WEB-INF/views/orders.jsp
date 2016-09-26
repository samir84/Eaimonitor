<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
From Which Page : <%=request.getAttribute("hello")%><br>
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<c:url value="/" var="contextBase" />
		<section class="content-header">
      		<h1>
        		Dashboard
        		<small>Orders</small>
     		 </h1>
      		<ol class="breadcrumb">
        		<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        		<li class="active"><i class="fa fa-cubes"></i>Orders Overview</li>
      		</ol>
    	</section>
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-header">
							<header class="panel-heading">
                      			All Orders List
                      			<span class="pull-right">
                          			<button type="button" id="loading-btn" class="btn btn-warning btn-xs" onclick="refreshorderPage()"><i class="fa fa-refresh"></i> Refresh</button>
                          			<a href="#" class=" btn btn-success btn-xs"> Create New order</a>
                      			</span>
                  			</header>
						</div>
						<div class="box-body">
						
                             <div class="row">
  
<!--  <div class="col-sm-6">
		<div id="example1_length" class="dataTables_length">
		
		<label>Show 
		
		<select name="example1_length" class="form-control input-sm" onchange="orderRecordselected()" id="orderRecordsOptions">
			<option value="10">10</option>
			<option value="25">25</option>
			<option value="50">50</option>
			<option value="100">100</option>
		</select>
		</div>  
	</div> -->

	<c:url value="/orders.html/search" var="SearchordersUrl"/>
	<div class="col-sm-6">
		<!--  search Form -->
		<div class="dataTables_filter" id="example1_filter">
			<div class="input-group input-group-sm">
					<input id="searchordersText" name="searchTerm" type="text" placeholder="Search" class="form-control pull-right"  />
						<div class="input-group-btn">
							<button class="btn btn-default" type="submit" onclick="orderFullTextSearch()"><i class="fa fa-search"></i></button>
						</div>
			</div>
			</div>
		<!-- Einde search form -->
		</div>
	</div>
	
	         <div  id="orderTableWrapper">
	         
	         
				<table id ="ordersTable" class="table table-hover p-table  table-bordered table-striped">
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
		<!--  <div class="dataTables_info" id="example2_info" role="status" >${totalordersMessage}</div>-->
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
<!-- <script type="text/javascript">
showPagination(${maxPages});
</script>		 -->
</div>
</div>
<script>
  $(function () {
    $("#ordersTable").DataTable();
   /* $('#ordersTable').DataTable({
      "paging": true,
      "lengthChange": false,
      "searching": false,
      "ordering": true,
      "info": true,
      "autoWidth": false
    });*/
  });
</script>
<!-- Ein Pagination -->
						</div>
					</div>
				</div>
			</div>
              </section>
			</div>
		<script src="<c:url value="/resources/dist/js/orders.js"/>"></script>	