<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 


<div class="container">
<div class="row">
<div class="col-sm-4 col-sm-offset-4 form-box">	
					<div class="login-logo">
						<a href="">
							<b>EAI</b>Monitor</a>
					</div>
					<div class="form-bottom login-box-body">
					<div class="form-top">
						<div class="form-top-right">
							<i class="fa fa-pencil"></i>
						</div>
						<div class="form-top-left">
							<h3>Sign up now</h3>
							<p>Fill in the form below to get instant access:</p>
						</div>
						
					</div>
				<p class="form-bottom login-box-body"/>
				<c:url value="/register.html" var="RegisterUrl"/>
				<form:form action="${RegisterUrl}" method="post" commandName="userForm" role="form">
					<div class="form-group has-feedback">
        				<form:input type="text" path="username" placeholder="User Name" class="form-control" />
        				<span class="glyphicon glyphicon-user form-control-feedback"></span>
     				 </div>
     				 <div class="form-group has-feedback">
        				<form:input type="text" path="email" placeholder="Email" class="form-control"></form:input>
        				<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
     				 </div>
					 <div class="form-group has-feedback">
        				<form:input type="password" path="password" placeholder="Password" class="form-control"></form:input>
        				<span class="glyphicon glyphicon-lock form-control-feedback"></span>
     				 </div>
     				 <div class="form-group has-feedback">
        				<input type="password" placeholder="Retype password" class="form-control">
        				<span class="glyphicon glyphicon-log-in form-control-feedback"></span>
      				</div>
      				<div id="agree" class="checkbox">
						<div class="checkbox icheck">
            				<label class="">
              					<div class="icheckbox_square-blue" style="position: relative;" aria-checked="false" aria-disabled="false"><input type="checkbox" style="position: absolute; top: -20%; left: -20%; display: block; width: 140%; height: 140%; margin: 0px; padding: 0px; background: rgb(255, 255, 255) none repeat scroll 0% 0%; border: 0px none; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: -20%; left: -20%; display: block; width: 140%; height: 140%; margin: 0px; padding: 0px; background: rgb(255, 255, 255) none repeat scroll 0% 0%; border: 0px none; opacity: 0;"></ins></div> I agree to the <a href="#">terms</a>
            				</label>
          				</div>
					</div>
					<button type="submit" class="btn btn-primary btn-block btn-flat">Sign Up</button>
				</form:form>
				<div class="row">
						<div class="text-right col-md-6">
								<a class="switch-button" title="Recover password" href="login.html">Already have an account?</a>
						</div>
				</div>	 
				
			</div>
</div>
</div>
</div>
<!-- <script th:inline="javascript">
/*<![CDATA[*/
var serverContext = "/";

$(document).ready(function () {
	$('form').submit(function(event) {
		register(event);
	});
	
	$("password").keyup(function(){
		if($("#password").val() != $("#matchPassword").val()){
	        $("#globalError").show().html(<spring:message code="PasswordMatches.user" />);
	    }else{
	    	$("#globalError").html("").hide();
	    }
	});
	
	options = {
		    common: {minChar:8},
		    ui: {
		    	showVerdictsInsideProgressBar:true,
		    	showErrors:true,
		    	errorMessages:{
		    		  wordLength: <spring:message code="label.login" /> ,
		    		  wordNotEmail: <spring:message code="error.wordNotEmail" />,
		    		  wordSequences:<spring:message code="error.wordSequences" /> ,
		    		  wordLowercase: <spring:message code="error.wordLowercase" />,
		    		  wordUppercase: <spring:message code="error.wordUppercase" />,
		    	      wordOneNumber: <spring:message code="error.wordOneNumber" />,
		    		  wordOneSpecialChar:<spring:message code="error.wordOneSpecialChar" />'
		    		}
		    	}
		};
	 $('#password').pwstrength(options);
});

function register(event){
	event.preventDefault();
    $(".alert").html("").hide();
    $(".error-list").html("");
    if($("#password").val() != $("#matchPassword").val()){
    	$("#globalError").show().html(<spring:message code="PasswordMatches.user" />);
    	return;
    }
    var formData= $('form').serialize();
    $.post(serverContext + "user/registration",formData ,function(data){
        if(data.message == "success"){
            window.location.href = serverContext + "successRegister.html";
        }
        
    })
    .fail(function(data) {
        if(data.responseJSON.error.indexOf("MailError") > -1)
        {
            window.location.href = serverContext + "emailError.html";
        }
        else if(data.responseJSON.error == "UserAlreadyExist"){
            $("#emailError").show().html(data.responseJSON.message);
        }
        else if(data.responseJSON.error.indexOf("InternalError") > -1){
            window.location.href = serverContext + "login?message=" + data.responseJSON.message;
        }
        else{
        	var errors = $.parseJSON(data.responseJSON.message);
            $.each( errors, function( index,item ){
                $("#"+item.field+"Error").show().html(item.defaultMessage);
            });
            errors = $.parseJSON(data.responseJSON.error);
            $.each( errors, function( index,item ){
                $("#globalError").show().append(item.defaultMessage+"<br/>");
            });
        }
    });
}

</script> -->