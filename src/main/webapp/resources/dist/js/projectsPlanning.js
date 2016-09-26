

 var projectPlanningTable =  $('#projectPlanningTable').DataTable({
     "paging": true,
     "lengthChange": false,
     "searching": true,
     "ordering": true,
     "info": true,
     "autoWidth": false,
     "ajax": {
	     "url": "planning.json",
	     "type": "GET",
	     "dataSrc": ''
	   },"columns": [
	                 { "data": "jiraIssue" },
	                 { "data": "projectName" },
	                 { "data": "activity" },
	                 { "data": "assignee" },
	                 { "data": "description" },
	                 { "data": "remark" },
	                 { "data": "week" },
	                 { "data": "excpectedHours" }]
     //'sDom': ''
   });
 //DatepickerFrom
 $("#DatepickerFrom").datepicker({
	  autoclose: true,
	  format: "yyyy-mm-dd",
     showWeek: true
 });
 $('#DatepickerFrom').datepicker().on('changeDate', function (event) {
	    //$('#date-daily').change();
     projectPlanningTable
     .columns(7)
      .search( ''+$.datepicker.iso8601Week(event.date)+'')
      .draw();
	});

 $('#projectPlanningTable_filter').on( 'keyup', function () {
	 projectPlanningTable.search( $(this).val() ).draw();
	} );

 /**
  * upload process
  * 
  */
 //import

 $( 'input[type="file"]' ).change(function() {
	  //var file = this[0].files;
	  var files = $('input[type="file"]')[0].files;

	 createFileUploadPresentation(files);
});
 $( "#planningFileUpload" ).submit(function( event ) {
	 
	  event.preventDefault();
	  var url= $( this ).attr('action');
	  processUpload(url);
	});

 function processUpload(url)
 {
	 var oMyForm = new FormData();
	 var progressbar = $('.progress-bar');
	 oMyForm.append("file", file.files[0]);
       $.ajax({
    	   
    	   dataType : 'json',
           url : url,
           data : oMyForm,
           type : "POST",
           enctype: 'multipart/form-data',
           processData: false, 
           contentType:false,
           
           xhr: function () {
               var xhr = new window.XMLHttpRequest();
               //Download progress
               xhr.addEventListener("progress", function (evt) {
                   if (evt.lengthComputable) {
                       var percentComplete = evt.loaded / evt.total;
                       var percent = Math.round(percentComplete * 100) + "%";
                       progressbar.css("width",percent);
                   }
               }, false);
               return xhr;
           },
           beforeSend: function () {
              // $('#loading').show();
        	   progressbar.css("width","0%");
           },
           complete: function () {
              // $("#loading").hide();
        	   progressbar.css("width","100%");
           },
           success : function(response) {

        	   if (response.message.toString() == "SUCCES"){
                   console.log("SUCCESS...");
                   $('#projectPlanningTable').DataTable().ajax.reload();
                
                  /* var table = $('#projectPlanningTable').DataTable( {
                	   ajax: "/planning.json"
                	   } );
                	   setInterval( function () {
                	   table.ajax.reload( null, false );
                	   }, 3000 );
                	*/
                   
                   /*$('#projectPlanningTable').dataTable( {
                	   paging: false,
                	    searching: false,
                	   "ajax": {
                	     "url": "planning.json",
                	     "type": "POST"
                	   }
                	 } );*/
               } else if (response.message.toString() == "FAIL"){
                   console.log("FAIL...");
               }
           },
           error : function(response){
               console.log("response:"+response.message);
               //oMyForm
               
           }
       });
 }
 
 function createFileUploadPresentation(files){
	 
	 var table =$('<table class="table table-striped" role="presentation"><tbody class="files">');
	 
	 for (var i = 0, file; file = files[i]; i++) {
	
		 var iSize = (file.size / 1024); 
		 iSize = (Math.round(iSize * 100) / 100)
		 
	 var row = $('\
			 	  <tr class="template-upload fade in">\
			 		<td><span class="preview"></span></td>\
			 	<td>\
         	<p class="name">'+file.name+'</p>\
          </td>\
          <td>\
          	<p class="size">'+iSize+'&nbsp;KB</p>\
          	<div aria-valuenow="0" aria-valuemax="100" aria-valuemin="0" id="progressbar" role="progressbar" class="progress progress-striped active">\
          	   <div style="width:0%;" class="progress-bar progress-bar-success"></div>\
          	</div>\
           </td>\
           <td>\
             <button class="btn btn-primary start" type="submit">\
                 <i class="glyphicon glyphicon-upload"></i>\
                 <span>Start</span>\
             </button>\
           <button class="btn btn-warning cancel" type="reset">\
                 <i class="glyphicon glyphicon-ban-circle"></i>\
                 <span>Cancel</span>\
             </button>\
          	<button class="btn btn-danger delete" type="button">\
             <i class="glyphicon glyphicon-trash"></i>\
             <span>Delete</span>\
         </button>\
           </td>\
           </tr>');
	 		
	 	table.append(row);
	 }
	 table.append('</tbody></table>');
	 $("#notifications").html(table);
 }
  