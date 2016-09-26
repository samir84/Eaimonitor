<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="content-wrapper">
<section class="content-header">
      		<h1>
        		Dashboard
        		<small>Order Detail</small>
     		 </h1>
      		<ol class="breadcrumb">
        		<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        		<li class="active"><i class="fa fa-cubes"></i>Orders Overview</li>
        		<li class="active"><i class="fa fa-cubes"></i>Order Details</li>
      		</ol>
</section>
<section class="invoice">

     
      <!-- title row -->
      <div class="row">
       
      </div>
      <!-- info row -->
      
      <div class="row invoice-info">
       
      </div>

      <div class="divider"></div>

      <!-- Table row -->
      <div class="row">
        <div class="col-xs-12 table-responsive">
          <table class="table table-striped">
            <thead>
            <tr>
              <th>Job id</th>
              <th>job reference</th>
              <th>Job status</th>
              <th>job type code</th>
              <th>timestamp</th>
              <th>job part type id</th>
              <th>job part description</th>
              <th>job part status</th>
              <th>recordcount</th>
            </tr>
            </thead>
            <c:if test="${ not empty jobDetails }">
            <tbody>
            <c:forEach var="jobDetail" items="${jobDetails}" >
            <tr>
              <td>${jobDetail.job.id}</td>
              <td>${jobDetail.job.reference}</td>
              <td>${jobDetail.job.jobStatus.description}</td>
              <td>${jobDetail.job.jobType.code}</td>
              
              <td><fmt:formatDate pattern="${dateTimePattern}" value="${jobDetail.job.timestamp}" /></td>
              <td>${jobDetail.jobPart.id}</td>
              <td>${jobDetail.jobPart.description}</td>
              <td>${jobDetail.jobStatus.description}</td>
              
              <td>${jobDetail.recordcount}</td>
              
            </tr>
            </c:forEach>
            </tbody>
            </c:if>
          </table>
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
</div>