package com.hs.eai.monitor.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.hs.eai.monitor.jobs.model.ProjectIssueStatistics;
import com.hs.eai.monitor.project.model.ProjectDetails;
import com.hs.eai.monitor.project.model.ProjectStatistics;
import com.hs.eai.monitor.service.AppUtilsService;
import com.hs.eai.monitor.service.RestClientService;

@Controller("/projects")
public class ProjectController {

	private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

	private static final String REST_URI_PROJECT_BASE = "restUriProjectBase";
	private static final String REST_URI_PROJECT_DETAILS = "restUriProjectDetails";
	private static final String REST_URI_ALLE_PROJECT_LazyLoad ="restUriAllProjectsLazyLoad";
	private static final String REST_URI_ALLE_PROJECT_FULL_TEXT_SEARCH ="restUriAllProjectsDetailsfullTextProjectSearchWildcard";
	private static final String REST_URI_JOBS_DATE_TIME_PATTERN ="dd-MM-yyyy HH:mm:ss";//add this later to property
	private static final String REST_URI_PROJECT_STATISTICS_TIME_ESTIMATED_SPENT ="restUriProjectStatisticsTimeoriginalestimatedAndTimespent";
	private static final String REST_URI_PROJECT_STATISTICS_TIME_ESTIMATED_SPENT_BY_PROJECT_AND_YEAR ="restUriProjectStatisticsTimeoriginalestimatedAndTimespentByProjectAndYear";
	
	@Autowired
	RestClientService restClientService;
	@Autowired
	AppUtilsService appUtilsService;
	ObjectMapper mapper;
	
	@PostConstruct
	public void init() {
		restClientService.setUriBase(REST_URI_PROJECT_BASE);
		 mapper = new ObjectMapper();
		
	}
	
	/**
	 * 
	 * @param show all projects
	 * @param projectId
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String showProjects(Model model, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "maxResult", required = false) Integer maxResult) {
		
		RestTemplate restTemplate = new RestTemplate();
		if(page == null){
			page = 1;
		}if(maxResult == null){
			maxResult = restClientService.getLazyLoadMaxResult("projects");
		}
		Map<String, Integer> params = new HashMap<String, Integer>();
	    params.put("startIndex", page);
	    params.put("maxResult", maxResult);
	    
	    String restUriAllProjectsDetailsazyLoad  = restClientService.readUriFromProperty(REST_URI_ALLE_PROJECT_LazyLoad);
	    
	    ResponseEntity<List<ProjectDetails>> projectsResponse =
	            restTemplate.exchange(restUriAllProjectsDetailsazyLoad,
	                        HttpMethod.GET, null, new ParameterizedTypeReference<List<ProjectDetails>>() {
	                },params);


	    List<ProjectDetails> projects = projectsResponse.getBody();
	    Integer totalProjects = restClientService.countAllProjects ();
	    
	    PagedListHolder<ProjectDetails> pagedListHolder = new PagedListHolder<>(projects);
	    pagedListHolder.setPageSize(maxResult);
		
	    model.addAttribute("maxPages", appUtilsService.getMaxPages(totalProjects, maxResult));
	 
	    model.addAttribute("totalProjects", totalProjects);
	    model.addAttribute("maxResult", maxResult);
	    StringBuilder totalProjMsg = new StringBuilder("Showing ");
	    totalProjMsg.append(page).append(" to ").append(maxResult).append(" of ").append(totalProjects).append(" entries");
	    model.addAttribute("totalProjectsMessage",totalProjMsg.toString());
	    model.addAttribute("projects",projects);
	    
			return "projects";
	}
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String loadProjects(Model model, @RequestParam(value = "startIndex", required = true) Integer startIndex,
			@RequestParam(value = "maxResult", required = true) Integer maxResult) {
		
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Integer> params = new HashMap<String, Integer>();
	    params.put("startIndex", startIndex);
	    params.put("maxResult", maxResult);
	    
	    String restUriAllProjectsDetailsazyLoad  = restClientService.readUriFromProperty(REST_URI_ALLE_PROJECT_LazyLoad);
	    
	    ResponseEntity<List<ProjectDetails>> projectsResponse =
	            restTemplate.exchange(restUriAllProjectsDetailsazyLoad,
	                        HttpMethod.GET, null, new ParameterizedTypeReference<List<ProjectDetails>>() {
	                },params);


	    List<ProjectDetails> projects = projectsResponse.getBody();
	    
	    Integer totalProjects = restClientService.countAllProjects ();
	    model.addAttribute("totalProjects", totalProjects);
	    model.addAttribute("maxResult", maxResult);
	    StringBuilder totalProjMsg = new StringBuilder("Showing ");
	    totalProjMsg.append(startIndex).append(" to ").append(maxResult).append(" of ").append(totalProjects).append(" entries");
	    model.addAttribute("totalProjectsMessage",totalProjMsg.toString());
	    model.addAttribute("projects",projects);
	    
			return "projectsTable";
	}
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String loadProjects(Model model, @RequestParam(value = "q", required = true) String searchTerm) {
		
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("searchTerm", searchTerm);
	    
	    String restUriAllProjectsDetailsfullTextProjectSearchWildcard  = restClientService.readUriFromProperty(REST_URI_ALLE_PROJECT_FULL_TEXT_SEARCH);
	   
	    ResponseEntity<List<ProjectDetails>> projectsResponse =
	            restTemplate.exchange(restUriAllProjectsDetailsfullTextProjectSearchWildcard,
	                        HttpMethod.GET, null, new ParameterizedTypeReference<List<ProjectDetails>>() {
	                },params);


	    List<ProjectDetails> projects = projectsResponse.getBody();
	    Integer AllProjects = restClientService.countAllProjects ();
	    model.addAttribute("totalProjects", AllProjects);
	    model.addAttribute("projects",projects);
	    
			return "projectsTable";
	}
	

	
	@RequestMapping(value = "/project_details", method = RequestMethod.GET)
	public String showProjectDetails(Model model, @RequestParam(value = "id", required = true) Integer projectId) {

		logger.debug("Showing Project details page..");
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Integer> params = new HashMap<String, Integer>();
	    params.put("projectId", projectId);
	    
	    String restUriProjectDetails = restClientService.readUriFromProperty(REST_URI_PROJECT_DETAILS);
	    model.addAttribute("dateTimePattern",REST_URI_JOBS_DATE_TIME_PATTERN);//change later to property based
		ProjectDetails projectdetails = restTemplate.getForObject(restUriProjectDetails, ProjectDetails.class, params);
		ProjectStatistics projectStatistics = new ProjectStatistics(projectdetails);
		
		Float inprogressIssues = appUtilsService.calculatePercentage(projectdetails.getInprogressIssues(), projectdetails.getTotalIssues());
		Float percentResolved =  appUtilsService.calculatePercentage(projectdetails.getResolvedIssues(), projectdetails.getTotalIssues());
		Float percentReopenIssues =  appUtilsService.calculatePercentage(projectdetails.getReopenIssues(), projectdetails.getTotalIssues());
		Float percentOpenIssues = appUtilsService.calculatePercentage(projectdetails.getOpenIssues(), projectdetails.getTotalIssues());
		Float percentClosed = appUtilsService.calculatePercentage(projectdetails.getClosedIssues(), projectdetails.getTotalIssues());
		
		projectStatistics.setPercentClosed(appUtilsService.toPercentage(percentClosed));
		projectStatistics.setPercentResolved(appUtilsService.toPercentage(percentResolved));
		projectStatistics.setPercentReopenIssues(appUtilsService.toPercentage(percentReopenIssues));
		projectStatistics.setPercentOpenIssues(appUtilsService.toPercentage(percentOpenIssues));
		projectStatistics.setInprogressIssues(appUtilsService.toPercentage(inprogressIssues));
		
		//statistics
		Map <String,Object> map = new HashMap<String,Object>();
		map.put("projectId", projectId);
		map.put("year", "2016");//2015-11-01 00:00:00.000/2015-11-30 11:59:59.999
		String restUriProjectStatisticsTimeoriginalestimatedAndTimespentByProjectAndYear  = restClientService.readUriFromProperty(REST_URI_PROJECT_STATISTICS_TIME_ESTIMATED_SPENT_BY_PROJECT_AND_YEAR);
		   
	    ResponseEntity<List<ProjectIssueStatistics>> projectsDetailsStatisticsResp =
	            restTemplate.exchange(restUriProjectStatisticsTimeoriginalestimatedAndTimespentByProjectAndYear,
	                        HttpMethod.GET, null, new ParameterizedTypeReference<List<ProjectIssueStatistics>>() {
	                },map);

	    model.addAttribute("timeEstimatedAndTimespentStatistics", projectsDetailsStatisticsResp.getBody());
		//
	    String projectStatisticsJson = "";
		try {
			projectStatisticsJson = mapper.writeValueAsString(projectsDetailsStatisticsResp.getBody());
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("list", projectStatisticsJson);
		model.addAttribute("projectdetails", projectdetails);
		model.addAttribute("projectStatistics", projectStatistics);
		
		return "projectdetails";
	}
	
	


}
