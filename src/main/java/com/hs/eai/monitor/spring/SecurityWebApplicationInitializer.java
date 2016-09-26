package com.hs.eai.monitor.spring;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebApplicationInitializer  extends AbstractSecurityWebApplicationInitializer{

	 public SecurityWebApplicationInitializer() {
	        super(SecurityConfig.class);
	    }
}
