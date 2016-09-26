
 var urlFeed = "/eaimonitor/timeSheets.json";

  initializeTimeSheets(urlFeed);
  

  $("#newTimeEntryButton").on('click', function(event) {
	  enableFormInputs();
  });
// deleteWeeklyReturn
  
  $('#deleteWeeklyReturn').on('click', function(event) {

	  var $weeklyReturnForm = $( "#addWeeklyReturnForm" );
		var urlAddWk = $weeklyReturnForm.attr('action');
		
		var url = urlAddWk.replace("addWeeklyReturn","deleteWeeklyReturn");

		    var project = $("#deleteWeeklyReturnModal").attr("project");
			var activity = $("#deleteWeeklyReturnModal").attr("activity");
			var date = $("#deleteWeeklyReturnModal").attr("date");
			var hours = $("#deleteWeeklyReturnModal").attr("hours");
			var description = $("#deleteWeeklyReturnModal").attr("description");
		$.ajax({
			type: "POST",
			 url: url,
			 data: {
					project: project,
					activity: activity,
					date: date,
					hours: hours,
					description:description,
		        },
			         success: function(response){
			        	 $("#Notifications h4").css("display","block");
			        	 $("#Notifications h4").html(response.message);
			        	 clearForm();
			        	 $('#deleteWeeklyReturnModal').modal('hide');
			        	 showNotifications();
			        	 refetchEvents();
			         }
		});
	});
  
 
  var selected = [];
  $('#deleteSelectedWeeklyReturns').on('click', function(event) {
	  
	   
	  for (i=0 ; i < selected.length ; i++){
		  var calEvent = selected[i];
		  $.ajax({
				type: "POST",
				 url: $( "#addWeeklyReturnForm" ).attr('action').replace("addWeeklyReturn","deleteWeeklyReturn"),
				data: {
					project: calEvent.project.id,
					activity: calEvent.activity.id,
					date: calEvent.date,
					hours: calEvent.hours,
					description:calEvent.description,
		        },
				         success: function(response){
				        	//$('#calendar').fullCalendar('refetchEvents');
				        	 
				         }
			});
	  }
	  showNotifications();
	  refetchEvents();
	  //
	});	

  
$( "#addWeeklyReturnForm" ).on( "submit", function( event ) {
	
	event.preventDefault();
		  $.ajax({
			 type: "POST",
			 url: $( this ).attr('action'),
			 data: $( this ).serialize(),
			         success: function(response){

			         if (response.status.toString() == "FAIL") {
			        	 
			        	 var htmlNotification = "<div class=\"alert alert-danger alert-dismissible\">"+
								"<button aria-hidden=\"true\" data-dismiss=\"alert\" class=\"close\" type=\"button\"><i class=\"fa fa-close\"></i></button>"
								+"<h4><i class=\"icon fa fa-ban\"></i> Alert!</h4>"
								+"<ul>";
			        	 
							for (var i = 0; i < response.errorMessageList.length; i++) {
								var item = response.errorMessageList[i];
								htmlNotification +="<li>"+item.message+"</li>";

							}
							htmlNotification +="</ul>";
							$("#formNotifications").html(htmlNotification);
						}else if (response.status.toString() == "CONFLICT"){
							
							var htmlNotification = response.message;
							
							$("#formNotifications").css("display","block");
							var conflictMsg = "<div class=\"alert alert-warning alert-dismissible\">";
							conflictMsg+="<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\"><i class=\"fa fa-close\"></i></button>";
							conflictMsg+="<h4><i class=\"icon fa fa-warning\"></i>"+response.status+"!</h4>";
							conflictMsg+=response.message+"</div>";
			                
			              
							$("#formNotifications").html(conflictMsg);
							$('select[name=project]').val(response.object.project);
							$('select[name=project]').attr("disabled",true);
							$('select[name=activity]').val(response.object.activity);
							$('select[name=activity]').attr("disabled",true);
							$("#myDatepicker").datepicker("setDate", response.object.date);
							$("#myDatepicker").attr("disabled",true);
							$('input[name=hours]').val(response.object.hours);
							$('input[name=hours]').attr("disabled",true);
							$('textarea[name=description]').val(response.object.description);
							$('textarea[name=description]').attr("disabled",true);
							
							//var modalFooter ="<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Close</button>";
							//modalFooter+="<button id=\"editWeeklyReturn\" type=\"submit\" class=\"btn btn-warning\">Edit</button>";
							//modalFooter+="<button id=\"deleteWeeklyReturn\"  class=\"btn btn-delete\">Delete</button>";
							
							//$("#weeklyReturnFooter").html(modalFooter);
						
			         }else {
							
							$("#Notifications h4").css("display","block");
				        	 $("#Notifications h4").html(response.message);
				        	// 
				        	 $('#addWeeklyReturnModal').modal('hide');
				        	 $('#calendar').fullCalendar('refetchEvents');
				        	 showNotifications();
				        	 clearForm();
						}
			         $("callout-warning h4").html(response);
			        $( this ).modal('hide'); 
			         },
			 error: function(response){
			 console.log("failure:"+response.status);
			 }
			        
			       });
		  
		});
$("#select-project").select2();
$("#select-activity").select2();
$("#select-reportingActivity").select2();

//Date picker
$( "#myDatepicker" ).datepicker({
	autoclose: true,
	  format: "yyyy-mm-dd"
	});
//Timepicker
$(".timepicker").timepicker({
  showInputs: false,
  use24hours: false,
  showMeridian: false,
  maxHour: 10,
});

function initializeTimeSheets(urlFeed) {

   $('#calendar').fullCalendar({
   	eventClick: function(calEvent, jsEvent, view) {
   		var $clickedEvent = $(jsEvent.target);
   		$('input:checked').each(function() {

  	      selected.push(calEvent);
  	  });
   		if($clickedEvent.attr("id")=="trashWeeklyReturn"){
   			$("#deleteWeeklyReturnModal").attr("project",calEvent.project.id);
   			$("#deleteWeeklyReturnModal").attr("activity",calEvent.activity.id);
   			$("#deleteWeeklyReturnModal").attr("date",calEvent.date);
   			$("#deleteWeeklyReturnModal").attr("hours",calEvent.hours);
   			$("#deleteWeeklyReturnModal").attr("description",calEvent.description);

   		}else if($clickedEvent.attr("id")=="editWeeklyReturn"){
   			editWeeklyReturn(calEvent);
   		}else{
   			//console.log("somthing else");
   		}
   		

       },

       header: {
           left: 'prev,next today',
           center: 'title',
           right: 'timesheetsDay,timesheetsWeek'
         },
         defaultView: 'timesheetsDay',
   	   editable: false,
         buttonText: {
           today: 'today',
           week: 'week',
           day: 'day'
         },
     eventSources: [
                    {
                        url: urlFeed,
                        type: 'GET',
                        data: {
                        //	fromDate:  moment(start).format('DD-MM-YYYY'),
                        //	toDate:  moment(end).format('DD-MM-YYYY')
                        },
                        error: function() {
                            alert('there was an error while fetching events!');
                        },

                    }

                ],
     editable: false,
     droppable: false // this allows things to be dropped onto the calendar !!!
 
   });
 
   var start = $('#calendar').fullCalendar('getView').start;
   var startDate = moment(start).format('MMMM D , YYYY');
   $(".timeEntryDate").html(startDate);
   
 }
function clearForm(){
	$('select[name=project]').val("");
	$('select[name=activity]').val("");
	$("#myDatepicker").val("");
	$('input[name=hours]').val("");
	$('textarea[name=description]').val(""); 
}
function enableFormInputs(){

	$('select[name=project]').attr("disabled",false);
	$('select[name=activity]').attr("disabled",false);
	$("#myDatepicker").attr("disabled",false);
	$('input[name=hours]').attr("disabled",false);
	$('textarea[name=description]').attr("disabled",false);  
}
function showNotifications(){

   $(".callout").alert();
   $(".callout").fadeTo(2000, 500).slideUp(500, function(){
	   	$(".callout").slideUp(500);
    });   

}

function refetchEvents(){
	 $('#calendar').fullCalendar( 'refetchEvents' )
}
function editWeeklyReturn(calEvent){
	$('select[name=project]').val(calEvent.project.id).change();
	$('select[name=activity]').val(calEvent.activity.id).change();
	$("#myDatepicker").datepicker("setDate", calEvent.date);
	$('input[name=hours]').val(calEvent.hours);
	$('textarea[name=description]').val(calEvent.description);
	$('#addWeeklyReturnModal')
	$('#addWeeklyReturnModal').modal('show'); 
	var updateUrl = $( "#addWeeklyReturnForm" ).attr('action').replace("addWeeklyReturn","updateWeeklyReturn");
	$( "#addWeeklyReturnForm" ).attr("action",updateUrl);
}


