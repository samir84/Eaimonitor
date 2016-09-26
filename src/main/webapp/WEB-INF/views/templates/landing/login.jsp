<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

		<div class="container">
			<div class="row">
				<div class="col-sm-4 col-sm-offset-4 form-box">	
					<div class="login-logo">
						<a href="/">
							<b>EAI</b>Monitor</a>
					</div>
					<div class="form-bottom login-box-body">
						<img id="profile-img" class="profile-img-card" src="<c:url value="/resources/dist/img/avatar_2x.png" /> ">

							
							 <c:if test="${param.error != null}">
							 
	            				<div class="login-box-msg">
	                				 Failed to login.
	                					<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
	                   						Reason:<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
	                					</c:if>
	            				</div>
	            				
	        				</c:if>
							<c:url value="/login.html" var="loginProcessingUrl"/>
							<form action="${loginProcessingUrl}" method="post">
								<div class="form-group has-feedback">
									<input type="text" class="form-control" name="username" placeholder="User Name">
										<span class="glyphicon glyphicon-user form-control-feedback"/>
									</div>
									<div class="form-group has-feedback">
										<input type="password" class="form-control" name="password" placeholder="Password">
											<span class="glyphicon glyphicon-lock form-control-feedback"/>
										</div>
										<div id="remember" class="checkbox">
											<label>
												<input type="checkbox" id="loginCheckbox1" class="custom-checkbox" name="remember-me"> Remember me
												</label>
											</div>
											<button type="submit" class="btn btn-primary btn-block btn-flat">Login</button>

										</form>
										<div class="row">
											<div class="text-right col-md-6">
												<a class="switch-button"  href="register.html">Do not have an account?</a>
											</div>
											<div class="text-right col-md-6">
												<a title="Recover password" class="switch-button" href="user/forgotPassword.html">Forgot your password?</a>
											</div>
										</div>
									</div>
								</div>

							</div>
						</div>