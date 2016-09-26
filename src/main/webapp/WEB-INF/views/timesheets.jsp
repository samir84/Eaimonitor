<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 

<div class="content-wrapper">
<style>
.datepicker{z-index:1151 !important;}
</style>

<c:url value="/" var="contextBase" />
	<section class="content-header"><h1>Dashboard<small>TimeSheets</small></h1>
      		<ol class="breadcrumb"><li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li><li class="active"><i class="fa fa-cubes"></i>Time Managment</li></ol>
    </section>
	<section class="content">
	    <div id="Notifications" style="display:none">
	    	<div class="callout callout-success">
                <h4></h4>
                <p></p>
              </div>
	    </div>
		<div class="row">
		<div class="col-xs-12">
			<div class="box box-primary">
			     <div class="box-header with-border">
					<i class="fa fa-calendar"></i>
					<h3 class="box-title">Timesheets</h3>
					<div class="box-tools pull-right">
            			<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
            			<button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-remove"></i></button>
          			</div>
          	 	</div>
            <div class="box-body no-padding">
               
        	   
              <!-- THE CALENDAR -->
              <div id="calendar"></div>
            </div>
            <!-- /.box-body -->
			</div>
		</div>

        </div>
        

<c:url value="/timesheets/addWeeklyReturn" var="addWeeklyReturnUrl"/>
<form:form id="addWeeklyReturnForm" method="POST" action="${addWeeklyReturnUrl}" commandName="weeklyReturn" role="form" class="form-horizontal">
  <!-- Modal -->
  <div class="modal fade" id="addWeeklyReturnModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">New Time Entry</h4>
          <span class="timeEntryDate"></span>
        </div>
        <div class="modal-body">
        	<!-- <div id="formNotifications">
        	<div class="callout callout-warning">
                <h4></h4>

                <p>This is a yellow callout.</p>
              </div>
			</div> -->
	    	
    		<div class="modal-body">
    			<div class="form-group" id="project">
					<label for="select-project">Project:</label>
					<form:select path="project"  id="select-project"  data-placeholder="Select a project..." class="form-control select2" style="width:100%">
						<form:option value="" label="Select a project..."/>
						<c:forEach items="${listAccesProjects}" var="accessProject">
							<form:option value="${accessProject.id}" label="${accessProject.projectName}"/>
						</c:forEach>
   						
					</form:select>
					
					<form:errors path="project" cssClass="error" />
				</div>
				<div class="form-group" id="activity">
					<label for="select-activity">Activity:</label>
					<form:select path="activity"  id="select-activity"   data-placeholder="Select an activity..." class="form-control select2" style="width:100%">
						<form:option value="" label="Select an activity..."/>
						<c:forEach items="${listAccesActivities}" var="accessActivity">
							<form:option value="${accessActivity.id}" label="${accessActivity.activityName}"/>
						</c:forEach>
					</form:select>
					<form:errors path="activity" cssClass="error" />
				</div>
				<div class="form-group" id="date">
                <label>Date:</label>

                <div class="input-group date">
                  <div class="input-group-addon">
                    <i class="fa fa-calendar"></i>
                  </div>
                  <form:input path="date" type="text" class="form-control pull-right" id="myDatepicker" />
                  <form:errors path="date" cssClass="error" />
                </div>
                <!-- /.input group -->
              </div>
              <!-- time Picker -->
              <div class="bootstrap-timepicker">
                <div class="form-group" id="hours">
                  <label>Hours:</label>

                  <div class="input-group">
                    <form:input path="hours" type="number"  placeholder="0:00" class="form-control" />
					<form:errors path="hours" cssClass="error" />
                    <div class="input-group-addon">
                      <i class="fa fa-clock-o"></i>
                    </div>
                  </div>
                  <!-- /.input group -->
                </div>
                <!-- /.form group -->
              </div>
              
              <!-- textarea -->
                <div class="form-group">
                  <label>Description</label>
                  <form:textarea path="description" class="form-control" rows="3" placeholder="Enter ..."></form:textarea>
                  <form:errors path="description" cssClass="error" />
                </div>
        
        <div id="weeklyReturnFooter" class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          <button id="saveWeeklyReturn" class="btn btn-success">Save</button>
          <!-- <button id="deleteWeeklyReturn"  class="btn btn-delete" style="display:none">Delete</button> -->
        </div>
        </div>
        
        </div>
       
      </div>
      
    </div>
  </div>
  </form:form>
  <div class="modal fade" id="deleteWeeklyReturnModal" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
      <div class="modal-dialog">
    <div class="modal-content">
          <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
        <h4 class="modal-title custom_align" id="Heading">Delete this entry</h4>
      </div>
          <div class="modal-body">
       
       <div class="alert alert-danger"><span class="glyphicon glyphicon-warning-sign"></span> Are you sure you want to delete this Record?</div>
       
      </div>
        <div class="modal-footer ">
        <button id="deleteWeeklyReturn" type="button" class="btn btn-success"><span class="glyphicon glyphicon-ok-sign"></span> Yes</button>
        <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> No</button>
      </div>
        </div>
    <!-- /.modal-content --> 
  </div>
    <!-- /.modal-dialog --> 
    </div>
	</section>

</div>
