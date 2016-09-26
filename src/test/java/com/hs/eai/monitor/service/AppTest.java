package com.hs.eai.monitor.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.hs.eai.monitor.project.model.ProjectDetails;
import com.hs.eai.monitor.project.model.ProjectsPlanning;

public class AppTest {

	
	public static void main(String[] args) {
/*		RestTemplate restTemplate = new RestTemplate();
		String uri ="http://localhost:8080/ProjectOverviewWS/api/project/projectDetails/pages?startIndex=0&maxResult=1";
		  ResponseEntity<List<ProjectDetails>> projects =
          restTemplate.exchange(uri,
                     HttpMethod.GET, null, new ParameterizedTypeReference<List<ProjectDetails>>() {
             });
		    
		    System.out.println("objects: "+projects.getBody().get(0).getProject().getPname());*/
		    
		    
		    RestTemplate restTemplate = new RestTemplate();
			 String restUriEaiMonitorAllProjectsPlanning  = "http://localhost:8080/EaiMonitorWS/api/planning/";
			    
			    ResponseEntity<List<ProjectsPlanning>> projectsPlanningResponse =
			            restTemplate.exchange(restUriEaiMonitorAllProjectsPlanning,
			                        HttpMethod.GET, null, new ParameterizedTypeReference<List<ProjectsPlanning>>() {
			                });
			    
			    
			    List<ProjectsPlanning> projectsPlanningList = projectsPlanningResponse.getBody();
			    System.out.println("projectsPlanningList:"+projectsPlanningList.size());
		    
		    
		
	}
}
