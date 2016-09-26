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
        		<small>Jobs</small>
     		 </h1>
      		<ol class="breadcrumb">
        		<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        		<li class="active"><i class="fa fa-cubes"></i>Jobs Overview</li>
      		</ol>
    	</section>
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-header">
							<header class="panel-heading">
                      			All Jobs List
                      			<span class="pull-right">
                          			<button type="button" id="loading-btn" class="btn btn-warning btn-xs" onclick="refreshjobPage()"><i class="fa fa-refresh"></i> Refresh</button>
                      			</span>
                  			</header>
						</div>
						<div class="box-body">
						
                             <div class="row">
                             <div class="col-sm-6">
     
 
</div>
	<%-- <div class="col-sm-6">
		<div id="example1_length" class="dataTables_length">
		
		<label>Show 
		<form:select path="jobRecordsOptions" onchange="jobRecordselected()" class="form-control input-sm" aria-controls="example1" id="jobRecordsOptions" >
   			<form:options items="${jobRecordsOptions}" />
		</form:select> entries</label>
		</div> 
	</div> --%>
	<c:url value="/jobs.html/search" var="SearchjobsUrl"/>
	<div class="col-sm-6">
		<!--  search Form -->
		<div class="dataTables_filter" id="example1_filter">
			<div class="input-group input-group-sm">
					<input id="searchjobsText" name="searchTerm" type="text" placeholder="Search" class="form-control pull-right"  />
						<div class="input-group-btn">
							<button class="btn btn-default" type="submit" onclick="jobFullTextSearch()"><i class="fa fa-search"></i></button>
						</div>
			</div>
			</div>
		<!-- Einde search form -->
		</div>
	</div>
	         <div  id="jobTableWrapper">
	         <div>${totaljobsMessage }</div>
	         
				<table id ="jobsTable" class="table table-hover p-table">
                      <thead>
                      <tr>
                      	  <th>JOB ID</th>
                      	  <th>DATETIME STARTED</th>
                          <th>JOB REFERENCE</th>
                          <th>COUNTRY CODE</th>
                          <th>JOB STATUS</th>
                          <th>JOB TYPE</th>
                          <th>REMARK</th>
                      </tr>
                      </thead>
                      <tbody>
                      <c:if test="${ not empty jobs }">
                      	<c:forEach var="jobDto" items="${jobs}" >
                      	<tr>
                          <td class="p-jobid">
                              <a href="${contextBase}job_details.html?id=${jobDto.job.id }">${jobDto.job.id}</a>
                          </td>
                           <td class="p-startDate">
                              <div><fmt:formatDate pattern="${dateTimePattern}" value="${jobDto.job.timestamp}" /></div>
                          </td>
                          <td class="p-refno">
                              <div>${jobDto.job.reference}</div>
                          </td>
                          <td class="p-contry">
                              <div>${jobDto.job.jobType.countryId}</div>
                          </td>
                          <td class="p-status">
                              <div>${jobDto.job.jobStatus.description}</div>
                          </td>
                         <%--  <td class="p-organization">
                              <div>${job.salesOrganizationId}</div>
                          </td> --%>
                          <td class="p-jobType">
                              <div>${jobDto.job.jobType.code}</div>
                          </td>
                          <td class="p-remark">
                              <div>${jobDto.remark}</div>
                          </td>
                      </tr>
                      	</c:forEach>
                      	</c:if>
                     
                      
                      </tbody>
                  </table>
                          <!-- Pagination -->
<div class="row">                 
	<div class="col-sm-5">
		<div class="dataTables_info" id="example2_info" role="status" aria-live="polite">${totaljobsMessage}</div>
	</div>
	<div class="col-sm-7">
		      <c:url value="/jobs.html" var="BasejobsUrl"/>
      
  <div class="dataTables_paginate paging_simple_numbers" id="example2_paginate">
      
  <%--     <ul class="pagination">

    <c:url value="/jobs.html" var="prev">
        <c:param name="page" value="${page-1}"/>
    </c:url>
    <c:if test="${page > 1}">
      <li class="paginate_button previous disabled" id="example2_previous">
			<a href="<c:out value="${prev}" />" class="pn prev">Previous</a>
	</li>
    </c:if>
	
    <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
        <c:choose>
            <c:when test="${page == i.index}">
                <span>${i.index}</span>
            </c:when>
            <c:otherwise>
                <c:url value="/jobs.html" var="url">
                    <c:param name="page" value="${i.index}"/>
                </c:url>
                	<li class="paginate_button ">
						<a href="<c:out value="${url}" />">${i.index}</a>
					</li>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    
    <c:url value="/jobs.html" var="next">
        <c:param name="page" value="${page + 1}"/>
    </c:url>
    <c:if test="${page + 1 <= maxPages}">
       <li class="paginate_button next" id="example2_next">
		<a href="<c:out value="${next}" />" >Next</a>
	</li>
    </c:if>
    
</ul> --%>
<c:url value="/jobs.html" var="firstUrl">
	<c:param name="page" value="${page-1}"/>
</c:url>
<c:url value="/jobs.html" var="lastUrl">
	<c:param name="page" value="${maxPages}"/>
</c:url>
<c:url value="/jobs.html" var="prevUrl">
	<c:param name="page" value="${page - 1}"/>
</c:url>
<c:url value="/jobs.html" var="nextUrl">
	<c:param name="page" value="${page + 1}"/>
</c:url>


<%-- <ul class="pagination">
        <c:choose>
            <c:when test="${currentIndex == 1}">
                <li class="disabled"><a href="#">&lt;&lt;</a></li>
                <li class="disabled"><a href="#">&lt;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${firstUrl}">&lt;&lt;</a></li>
                <li><a href="${prevUrl}">&lt;</a></li>
            </c:otherwise>
        </c:choose>
        <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
            <c:url var="pageUrl" value="/pages/${i}" />
            <c:choose>
                <c:when test="${i == currentIndex}">
                    <li class="active"><a href="${pageUrl}"><c:out value="${i}" /></a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:choose>
            <c:when test="${currentIndex == maxPages}">
                <li class="disabled"><a href="#">&gt;</a></li>
                <li class="disabled"><a href="#">&gt;&gt;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${nextUrl}">&gt;</a></li>
                <li><a href="${lastUrl}">&gt;&gt;</a></li>
            </c:otherwise>
        </c:choose>
    </ul> --%>
    <p class="demo demo4_bottom"></p>
<p class="well demo content4">
                            Dynamic content here.
</p>
</div>
   	</div>
		
</div>
</div>
<!-- jQuery 2.2.0 -->
	<script src="<c:url value="/resources/plugins/jQuery/jQuery-2.2.0.min.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/resources/dist/js/jquery.bootpag.min.js"/>"></script>
  	<script src="<c:url value="/resources/dist/js/jobs.js"/>"></script>
<script type="text/javascript">
showPagination(${maxPages});
</script>
<!-- Ein Pagination -->
						</div>
					</div>
				</div>
			</div>
              </section>
			</div>
		