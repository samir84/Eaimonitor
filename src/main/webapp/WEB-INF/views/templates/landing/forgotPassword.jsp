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
					<div id="notifications" style="display:none"></div>
					<div class="form-top">
						<div class="text-center">
							<span class="fa-stack fa-4x">
  								<i class="fa fa-circle fa-stack-2x" style="color: #D2D6DE"></i>
  								<i class="fa fa-lock fa-stack-1x "></i>
							</span>
							<h3 class="text-center">Forgot Password?</h3>
                          <p>You can reset your password here.</p>
						</div>
						
					</div>
				<c:url value="/user/resetPassword.json" var="resetPasswordUrl"/>
				
     				 <div class="form-group has-feedback">
        				<input id="email" type="text" placeholder="Email" class="form-control" />
        				<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
     				 </div>
					 
					<button type="submit" class="btn btn-primary btn-block btn-flat" onclick="resetPass('${resetPasswordUrl}')">Reset password</button>
				<div class="row">
						<div class="col-md-6">
							<a class="switch-button"  href="login.html">Already have an account?</a>
						</div>
						<div class="col-md-6">
							<a class="switch-button"  href="register.html">Do not have an account?</a>
						</div>
				</div>	 
				
			</div>
</div>
</div>
</div>

<script src="<c:url value="/resources/dist/js/user.js"/>"></script>
