package com.hs.eai.monitor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Scope("prototype")
public class RestClientService {

	private static final Logger logger = LoggerFactory.getLogger(RestClientService.class);

	private static final String REST_URI_COUNT_ALLE_PROJECTS ="restUriCountProjects";
	private static final String REST_URI_COUNT_ALLE_ORDERS ="restUriCountOrders";
	private static final String REST_URI_COUNT_ALLE_JOBS ="restUriCountJobs";
	private static final String REST_URI_ALLE_PROJECTS_LazyLoad_MAX_RESULT = "restUriAllProjectslazyLoadMaxResult";
	private static final String REST_URI_ALLE_ORDERS_LazyLoad_MAX_RESULT = "restUriAllOrdersLazyLoadMaxResult";
	private static final String REST_URI_ALLE_CATALOGS_LazyLoad_MAX_RESULT = "restUriAllCatalogsLazyLoadMaxResult";
	private static final String REST_URI_ALLE_JOBS_LazyLoad_MAX_RESULT = "restUriAllJobsLazyLoadMaxResult";
	
	
	@Autowired
	private Environment env;

	private String uriBase;

	public String getUriBase() {

		// uriBase = env.getRequiredProperty(uriBase);
		return uriBase;
	}

	public void setUriBase(String uriBase) {
		this.uriBase = uriBase;
	}

	public String readUriFromProperty(final String property) {

		return env.getRequiredProperty(uriBase) + env.getRequiredProperty(property);
	}


	public Integer getLazyLoadMaxResult(String view) {

		Integer maxResult = null;
		
		try {
			switch(view){
			case "orders":
				return maxResult = Integer.parseInt(env.getRequiredProperty(REST_URI_ALLE_ORDERS_LazyLoad_MAX_RESULT));
			case "projects":
				return maxResult = Integer.parseInt(env.getRequiredProperty(REST_URI_ALLE_PROJECTS_LazyLoad_MAX_RESULT));
			case "catalogs":
				return maxResult = Integer.parseInt(env.getRequiredProperty(REST_URI_ALLE_CATALOGS_LazyLoad_MAX_RESULT));
			case "jobs":
				return maxResult = Integer.parseInt(env.getRequiredProperty(REST_URI_ALLE_JOBS_LazyLoad_MAX_RESULT));
			}
		} catch (Exception ex) {
			logger.error("Property for LazyLoad_MAX_RESULT must be an integer");
			ex.printStackTrace();
		}
		return maxResult;
	}
    
	public  Integer countAllProjects() {

		RestTemplate restTemplate = new RestTemplate();
		Integer count = 0;
		String restUriCountAllProjects = readUriFromProperty(REST_URI_COUNT_ALLE_PROJECTS);
		//
		ResponseEntity<Integer> projectsResponse = restTemplate.exchange(restUriCountAllProjects, HttpMethod.GET, null,
				Integer.class);
		count = projectsResponse.getBody();
		return count;
	}
	public  Integer countAllorders() {

		RestTemplate restTemplate = new RestTemplate();
		Integer count = 0;
		String restUriCountAllOrders = readUriFromProperty(REST_URI_COUNT_ALLE_ORDERS);
		//
		ResponseEntity<Integer> ordersResponse = restTemplate.exchange(restUriCountAllOrders, HttpMethod.GET, null,
				Integer.class);
		count = ordersResponse.getBody();
		return count;
	}

	public Integer countAllJobs() {
		
		RestTemplate restTemplate = new RestTemplate();
		Integer count = 0;
		String restUriCountAllJobs = readUriFromProperty(REST_URI_COUNT_ALLE_JOBS);
		//
		ResponseEntity<Integer> jobsResponse = restTemplate.exchange(restUriCountAllJobs, HttpMethod.GET, null,
				Integer.class);
		count = jobsResponse.getBody();
		return count;
	}


}
