package com.hs.eai.monitor.project.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.hs.eai.monitor.project.model.ProjectsPlanning;
import com.hs.eai.monitor.service.AppUtilsService;
import com.hs.eai.monitor.service.RestClientService;

@Controller
public class ProjectPlanningController {

	private static final Logger logger = LoggerFactory.getLogger(ProjectPlanningController.class);

	private static final String REST_URI_EAI_MONITOR_BASE = "restUriEaiMonitorWSBase";
	private static final String REST_URI_ALL_PROJECT_PLANNING = "restUriEaiMonitorWSAllProjectsPlanning";
	private static final String REST_URI_ALL_PROJECT_PLANNING_BY_ASSIGNEE ="restUriEaiMonitorAllProjectsPlanningByAssignee";
	private static final String REST_URI_ALL_PROJECT_PLANNING_BY_ASSIGNEES ="restUriEaiMonitorAllProjectsPlanningByAssignees";

	private static final String REST_URI_ALL_PROJECT_PLANNING_BY_WEEK_NUMBER ="restUriEaiMonitorAllProjectsPlanningByWeekNumber";
	private static final String REST_URI_ALL_PROJECT_PLANNING_BY_ASSIGNEE_AND_WEEK_NUMBER ="restUriEaiMonitorAllProjectsPlanningByAssigneeAndWeekNumber";
	
	@Autowired
	RestClientService restClientService;
	@Autowired
	AppUtilsService appUtilsService;
	ObjectMapper mapper;
	
	@PostConstruct
	public void init() {
		restClientService.setUriBase(REST_URI_EAI_MONITOR_BASE);
		 mapper = new ObjectMapper();
		
	}
	
	//projectsPlanning
		@RequestMapping(value = "/planning", method = RequestMethod.GET)
		public String showProjectPlanning(Model model){
			
			try{
				RestTemplate restTemplate = new RestTemplate();
				 String restUriEaiMonitorAllProjectsPlanning  = restClientService.readUriFromProperty(REST_URI_ALL_PROJECT_PLANNING);
				    
				    ResponseEntity<List<ProjectsPlanning>> projectsPlanningResponse =
				            restTemplate.exchange(restUriEaiMonitorAllProjectsPlanning,
				                        HttpMethod.GET, null, new ParameterizedTypeReference<List<ProjectsPlanning>>() {
				                });
				    
				    List<ProjectsPlanning> projectsPlanningList = projectsPlanningResponse.getBody();
				    ProjectsPlanning projectsPlanning = new ProjectsPlanning();
					model.addAttribute("projectsPlanningList", projectsPlanningList);
					model.addAttribute("projectsPlanning", projectsPlanning);
			}catch(Exception ex){
				
			}
			
			
			return "projectsPlanning";
		}
		@RequestMapping(value = "/planning.json", method = RequestMethod.GET)
		@ResponseBody
		public String getProjectPlanning(@RequestParam (required=false) String anything){
			
			
			String projectPlanningJson = null;
			
			try {
				RestTemplate restTemplate = new RestTemplate();
				 String restUriEaiMonitorAllProjectsPlanning  = restClientService.readUriFromProperty(REST_URI_ALL_PROJECT_PLANNING);
				    
				    ResponseEntity<List<ProjectsPlanning>> projectsPlanningResponse =
				            restTemplate.exchange(restUriEaiMonitorAllProjectsPlanning,
				                        HttpMethod.GET, null, new ParameterizedTypeReference<List<ProjectsPlanning>>() {
				                });
				    
				projectPlanningJson = mapper.writeValueAsString(projectsPlanningResponse.getBody());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return projectPlanningJson;
		}
}
