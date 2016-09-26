function resetPass(resetPasswordUrl){
	//var resetPasswordUrl ="/resetPasswordUrl";
	
    var email = $("#email").val();
    $.ajax({
		 type: "POST",
		 url: resetPasswordUrl,
		 data: "email="+email,
		 success: function(response){
			 if(response.status.toString() == "SUCCES"){
        	 var htmlNotification = "<div class=\"callout callout-success alert-dismissible\">";
        	 htmlNotification+="<h4><i class=\"icon fa fa-check\"></i> Alert!</h4>";
        	 htmlNotification+= response.message;
        	 htmlNotification+="</div>";
        	 
        	 $("#notifications").css("display","block");
        	 $("#notifications").html(htmlNotification);
        	 
		 }else if(response.status.toString() == "FAIL"){
			 var htmlNotification = "<div class=\"callout callout-warning alert-dismissible\">";
        	 htmlNotification+="<h4><i class=\"icon fa fa-warning\"></i> Alert!</h4>";
        	 htmlNotification+= response.message;
        	 htmlNotification+="</div>";
        	 
        	 $("#notifications").css("display","block");
        	 $("#notifications").html(htmlNotification);
		 }
		 },
		 error: function(response){
			 console.log("failure:"+response.message);
		 }
		 
    });

}
$('#changePasswordForm').on( "submit", function( event ){
    savePass(event);
});

$("#password").keyup(function(){
	
    if($("#password").val() != $("#matchPassword").val()){
     $("#formNotifications").css("display","block");
   	 $("#formNotifications").html("Password does not match!");
    }else{
    	$("#formNotifications").hide();
    }
});


function savePass(event){
event.preventDefault();

if($("#password").val() != $("#matchPassword").val()){
	$("#formNotifications").css("display","block");
  	 $("#formNotifications").html("Password does not match!");
    return;
}
var urlSavePassword = $( '#changePasswordForm' ).attr('action').replace("html","json");
urlSavePassword  = urlSavePassword.replace("changePassword","savePassword");
$.ajax({
	 type: "POST",
	 url: urlSavePassword,
	 data: $( '#changePasswordForm' ).serialize(),
	         success: function(response){
	        	 console.log("succes:"+response.status);
	        	 if(response.status.toString() == "SUCCES"){
	            	 var htmlNotification = "<div class=\"callout callout-success alert-dismissible\">";
	            	 htmlNotification+="<h4><i class=\"icon fa fa-check\"></i> Alert!</h4>";
	            	 htmlNotification+= response.message;
	            	 htmlNotification+="</div>";
	            	 
	            	 $("#formNotifications").css("display","block");
	            	 $("#formNotifications").html(htmlNotification);
	            	 
	    		 }
	        	 if(response.status.toString() == "FAIL"){
	        		 
	        		 var htmlNotification = "<div class=\"alert alert-danger alert-dismissible\">"+
						"<button aria-hidden=\"true\" data-dismiss=\"alert\" class=\"close\" type=\"button\"><i class=\"fa fa-close\"></i></button>"
						+"<h4><i class=\"icon fa fa-ban\"></i> Alert!</h4>"
						+"<ul>";
	        		 for (var i = 0; i < response.errorMessageList.length; i++) {
							var item = response.errorMessageList[i];
							htmlNotification +="<li>"+item.message+"</li>";

						}
	        		 htmlNotification +="</ul>";
	        		 $("#formNotifications").css("display","block");
						$("#formNotifications").html(htmlNotification);
				        
	        	 }
	         },
			 error: function(response){
				 console.log("failure:"+response.status);
				 
				 }
				       });

}