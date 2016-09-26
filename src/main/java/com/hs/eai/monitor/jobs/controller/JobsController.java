package com.hs.eai.monitor.jobs.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.hs.eai.monitor.jobs.dto.JobDto;
import com.hs.eai.monitor.jobs.model.XrefJobPartStatusJob;
import com.hs.eai.monitor.service.AppUtilsService;
import com.hs.eai.monitor.service.RestClientService;


@Controller
public class JobsController {

	private static final Logger logger = LoggerFactory.getLogger(JobsController.class);

	private static final String REST_URI_JOBS_BASE = "restUriJobsBase";
	private static final String REST_URI_ALLE_JOBS = "restUriAllJobs";
	private static final String REST_URI_job_DETAIL = "restUriJobDetail";
	private static final String REST_URI_job_FULL_TEXT_SEARCH = "restUriJobFullTextSearch";
	private static final String REST_URI_ALLE_JOBS_LazyLoad ="restUriAllJobsLazyLoad";
	private static final String REST_URI_ALLE_JOBS_FULL_TEXT_SEARCH ="restUriAllJobsFullTextSearchWildcard";
	//private static final String REST_URI_CUSTOMER_SYNC_BY_USER_ID ="restUriCutomerSyncByUserId";
	private static final String REST_URI_JOBS_DATE_TIME_PATTERN ="dd-MM-yyyy HH:mm:ss";//add this later to property
	
	@Autowired
    private Environment env;
	@Autowired
	RestClientService restClientService;
	@Autowired
	AppUtilsService appUtilsService;
	
	@PostConstruct
	public void init() {
		restClientService.setUriBase(REST_URI_JOBS_BASE);
		
		
	}
	
	/**
	 * 
	 * @param show all jobs
	 * @param jobId
	 * @return
	 */
	
	@RequestMapping(value = "/jobs", method = RequestMethod.GET)
	public String showJobs(Model model, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "maxResult", required = false) Integer maxResult , HttpServletRequest request) {
		
		RestTemplate restTemplate = new RestTemplate();

		if(page == null){
			page = 1;
		}
		if(maxResult == null){
			maxResult = restClientService.getLazyLoadMaxResult("jobs");
		}
		Map<String, Integer> params = new HashMap<String, Integer>();
	    params.put("startIndex", page);
	    params.put("maxResult", maxResult);
	    System.out.println("START INDEX:"+page);
	    System.out.println("MAX RESULT:"+maxResult);
	    String restUriAlljobsDetailsazyLoad  = restClientService.readUriFromProperty(REST_URI_ALLE_JOBS_LazyLoad);
	    System.out.println("restUriAlljobsDetailsazyLoad:"+restUriAlljobsDetailsazyLoad);
	    
	    
	    List<JobDto> jobs = null ;
	    try{
	    	 ResponseEntity<JobDto[]> jobsResponse =
	 	            restTemplate.exchange(restUriAlljobsDetailsazyLoad,
	 	                        HttpMethod.GET, null, new ParameterizedTypeReference<JobDto[]>() {
	 	                },params);
	    	jobs = new ArrayList<JobDto>(Arrays.asList(jobsResponse.getBody()));
	    	  //jobs = (Collection<JobDto>) new ObjectMapper().readValues((JsonParser) jobsResponse.getBody(), new TypeReference<Collection<JobDto>>() { });
	    	// List<JobDto> consultants = Arrays.asList(
	    			// new ObjectMapper().readValue(jobsResponse.getBody().toString(), JobDto[].class);
	    }catch(Exception ex){
	    	ex.printStackTrace();
	    }
	   
	    Integer totaljobs = restClientService.countAllJobs ();
	    
	    PagedListHolder<JobDto> pagedListHolder = new PagedListHolder<>(jobs);
	    pagedListHolder.setPageSize(maxResult);
		
	    model.addAttribute("maxPages", appUtilsService.getMaxPages(totaljobs, maxResult));
	    
	    if(page==null || page < 1 || page > pagedListHolder.getPageCount()){
	    	page = 1;
	    } if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
            pagedListHolder.setPage(0);
            model.addAttribute("users", pagedListHolder.getPageList());
        }
        else if(page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page-1);
            model.addAttribute("jobs",pagedListHolder.getPageList());
        }
	    model.addAttribute("totaljobs", totaljobs);
	    model.addAttribute("maxResult", maxResult);
	    model.addAttribute("dateTimePattern",REST_URI_JOBS_DATE_TIME_PATTERN);//change later to property based
	    //
	    int current = page + 1;
	    int begin = Math.max(1, current - 5);
	    int end = Math.min(begin + 10, appUtilsService.getMaxPages(totaljobs, maxResult));

	    model.addAttribute("deploymentLog", page);
	    model.addAttribute("beginIndex", begin);
	    model.addAttribute("endIndex", end);
	    model.addAttribute("currentIndex", current);
	    
	    //
	    StringBuilder totalProjMsg = new StringBuilder("Showing ");
	    totalProjMsg.append(page).append(" to ").append(maxResult).append(" of ").append(totaljobs).append(" entries");
	    model.addAttribute("totaljobsMessage",totalProjMsg.toString());
	    
			return "jobs";
	}
	@RequestMapping(value = "/job_details.html", method = RequestMethod.GET)
	public String showjobDetails(Model model, @RequestParam(value = "id", required = true) Integer jobId
			) {

		logger.debug("Showing job detail page..");
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Integer> params = new HashMap<String, Integer>();
	    params.put("jobId", jobId);
	    model.addAttribute("dateTimePattern",REST_URI_JOBS_DATE_TIME_PATTERN);//change later to property based
	    String restUrijobDetail = restClientService.readUriFromProperty(REST_URI_job_DETAIL);
	   
	    ResponseEntity<List<XrefJobPartStatusJob>> jobDetailsResponse =
	            restTemplate.exchange(restUrijobDetail,
	                        HttpMethod.GET, null, new ParameterizedTypeReference<List<XrefJobPartStatusJob>>() {
	                },params);
	    List<XrefJobPartStatusJob> jobDetails = jobDetailsResponse.getBody();
	    for(XrefJobPartStatusJob xref:jobDetails){
	    	
	    	System.out.println("recordcount:"+xref.getRecordcount());
	    }
		model.addAttribute("jobDetails", jobDetails);
	
		return "jobdetails";
	}
}
