package com.hs.eai.monitor.project.controller;

import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.hs.eai.monitor.common.GenericResponse;
import com.hs.eai.monitor.model.ErrorMessage;
import com.hs.eai.monitor.model.ValidationResponse;
import com.hs.eai.monitor.project.model.WeeklyReturn;
import com.hs.eai.monitor.service.RestClientService;

import com.hs.eai.monitor.project.model.AccesActivity;
import com.hs.eai.monitor.project.model.AccessProject;
import com.hs.eai.monitor.project.model.dto.WeeklyReturnDto;

@Controller
public class TimeSheetsController {

	private static final Logger logger = LoggerFactory.getLogger(TimeSheetsController.class);

	private static final String REST_URI_TIMESHEET_BASE = "restUriTimeSheetsBase";
	private static final String REST_URI_TIMESHEET_WEEKLY_RETURN_BY_WINDOWS_USER_ID = "restUriTimeSheetsWeeklyReturnByWindowsUserId";
	private static final String REST_URI_TIMESHEET_ALL_PROJECTS = "restUriTimeSheetsAllProjects";
	private static final String REST_URI_TIMESHEET_ALL_ACTIVITIES = "restUriTimeSheetsAllActivities";
	private static final String REST_URI_TIMESHEET_ADD_WEEKLY_RETURN_BY_WINDOWS_USER_ID = "restUriTimeSheetsAddByWindowsUserId";
	private static final String REST_URI_TIMESHEET_UPDATE_WEEKLY_RETURN_BY_WINDOWS_USER_ID = "restUriTimeSheetsUpdateByWindowsUserId";
	private static final String REST_URI_TIMESHEET_DELETE_WEEKLY_RETURN_BY_WINDOWS_USER_ID = "restUriTimeSheetsDeleteByWindowsUserId";
	private static final String FULL_CALLENDAR_DATE_FORMAT = "yyyy-MM-dd";
	private static final String WORKLOG_WS_DATE_FORMAT = "dd-MM-yyyy";
	@Autowired
	RestClientService restClientService;

	private ObjectMapper mapper;

	@PostConstruct
	public void init() {
		restClientService.setUriBase(REST_URI_TIMESHEET_BASE);
		mapper = new ObjectMapper();

	}

	@RequestMapping(value = "/timesheets", method = RequestMethod.GET)
	public String showTimeSheets(Model model, Principal principal) {

		WeeklyReturn weeklyReturn = new WeeklyReturn();

		List<AccessProject> listAccesProjects = listAccessProject();
		List<AccesActivity> listAccesActivities = listAccesActivity();

		model.addAttribute("listAccesProjects", listAccesProjects);
		model.addAttribute("listAccesActivities", listAccesActivities);
		model.addAttribute("weeklyReturn", weeklyReturn);

		return "timesheets";
	}

	@RequestMapping(value = "/timesheets/addWeeklyReturn", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse addWeeklyReturn(
			@Valid @ModelAttribute("weeklyReturn") WeeklyReturn weeklyReturn, BindingResult result, Model model,
			Principal principal) {

		logger.info("POST: /timesheets/addWeeklyReturn ");
		String username = principal.getName();
		ValidationResponse res = new ValidationResponse();

		GenericResponse genericResp = null;

		if (!result.hasErrors()) {

			try {

				RestTemplate restTemplate = new RestTemplate();
				String restUriAddTimeSheetsByWindowUserId = restClientService
						.readUriFromProperty(REST_URI_TIMESHEET_ADD_WEEKLY_RETURN_BY_WINDOWS_USER_ID);

				HttpEntity<WeeklyReturn> request = new HttpEntity<>(weeklyReturn);

				ResponseEntity<GenericResponse> response = restTemplate.exchange(restUriAddTimeSheetsByWindowUserId,
						HttpMethod.POST, request, GenericResponse.class, username);
				
				genericResp = (GenericResponse) response.getBody();

				
				if (genericResp.getStatus().equals(HttpStatus.OK.toString())) {
					res.setStatus("SUCCESS");
					res.setMessage(genericResp.getMessage());
					res.setObject(genericResp.getObject());

				} else if (genericResp.getStatus().equals(HttpStatus.CONFLICT.toString())) {
					res.setStatus("CONFLICT");
					res.setMessage(genericResp.getMessage());
					res.setObject(genericResp.getObject());
				} else {
					res.setStatus("ERROR");
					res.setMessage(genericResp.getMessage());
					res.setObject(genericResp.getObject());
				}

			} catch (Exception ex) {
				logger.info(ex.getMessage());
				logger.error(ex.getMessage());
				res.setStatus("INTERNALERROR");
				res.setMessage(genericResp.getMessage());
				res.setObject(genericResp.getObject());
				ex.printStackTrace();
			}
		} else {
			res.setStatus("FAIL");
			List<FieldError> allErrors = result.getFieldErrors();
			List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
			for (FieldError objectError : allErrors) {
				errorMesages.add(new ErrorMessage(objectError.getField(),
						objectError.getField() + "  " + objectError.getDefaultMessage()));
			}
			res.setErrorMessageList(errorMesages);

		}

		return res;
	}

	@RequestMapping(value = "/timesheets/deleteWeeklyReturn", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse deleteWeeklyReturn(
			@Valid @ModelAttribute("weeklyReturn") WeeklyReturn weeklyReturn, BindingResult result, Model model,
			Principal principal) {

		String username = principal.getName();
		ValidationResponse res = new ValidationResponse();

		GenericResponse genericResp = null;

		if (!result.hasErrors()) {

			try {
				RestTemplate restTemplate = new RestTemplate();
				String restUriDeleteTimeSheetsByWindowUserId = restClientService
						.readUriFromProperty(REST_URI_TIMESHEET_DELETE_WEEKLY_RETURN_BY_WINDOWS_USER_ID);

				HttpEntity<WeeklyReturn> request = new HttpEntity<>(weeklyReturn);

				ResponseEntity<GenericResponse> response = restTemplate.exchange(restUriDeleteTimeSheetsByWindowUserId,
						HttpMethod.POST, request, GenericResponse.class, username);
				
				genericResp = (GenericResponse) response.getBody();
				
				if (genericResp.getStatus().equals(HttpStatus.OK.toString())) {
					res.setStatus("SUCCESS");
					res.setMessage(genericResp.getMessage());
					res.setObject(genericResp.getObject());

				} else if (genericResp.getStatus().equals(HttpStatus.CONFLICT.toString())) {
					res.setStatus("CONFLICT");
					res.setMessage(genericResp.getMessage());
					res.setObject(genericResp.getObject());
				} else {
					res.setStatus("ERROR");
					res.setMessage(genericResp.getMessage());
					res.setObject(genericResp.getObject());
				}
				
			} catch (Exception ex) {
				logger.info(ex.getMessage());
				logger.error(ex.getMessage());
				res.setStatus("INTERNALERROR");
				res.setMessage(genericResp.getMessage());
				res.setObject(genericResp.getObject());
				ex.printStackTrace();
			}

		} else {
			res.setStatus("FAIL");
			List<FieldError> allErrors = result.getFieldErrors();
			List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
			for (FieldError objectError : allErrors) {
				errorMesages.add(new ErrorMessage(objectError.getField(),
						objectError.getField() + "  " + objectError.getDefaultMessage()));
			}
			res.setErrorMessageList(errorMesages);

		}

		return res;
	}
	@RequestMapping(value = "/timesheets/updateWeeklyReturn", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse updateWeeklyReturn(
			@Valid @ModelAttribute("weeklyReturn") WeeklyReturn weeklyReturn, BindingResult result, Model model,
			Principal principal) {

		String username = principal.getName();
		ValidationResponse res = new ValidationResponse();

		GenericResponse genericResp = null;

		if (!result.hasErrors()) {

			try {
				RestTemplate restTemplate = new RestTemplate();
				String restUriTimeSheetsUpdateByWindowsUserId = restClientService
						.readUriFromProperty(REST_URI_TIMESHEET_UPDATE_WEEKLY_RETURN_BY_WINDOWS_USER_ID);

				HttpEntity<WeeklyReturn> request = new HttpEntity<>(weeklyReturn);

				ResponseEntity<GenericResponse> response = restTemplate.exchange(restUriTimeSheetsUpdateByWindowsUserId,
						HttpMethod.POST, request, GenericResponse.class, username);
				
				genericResp = (GenericResponse) response.getBody();
				System.out.println("update response update:"+response.getBody());
				if (genericResp.getStatus().equals(HttpStatus.OK.toString())) {
					res.setStatus("SUCCESS");
					res.setMessage(genericResp.getMessage());
					res.setObject(genericResp.getObject());

				} else if (genericResp.getStatus().equals(HttpStatus.CONFLICT.toString())) {
					res.setStatus("CONFLICT");
					res.setMessage(genericResp.getMessage());
					res.setObject(genericResp.getObject());
				} else {
					res.setStatus("ERROR");
					res.setMessage(genericResp.getMessage());
					res.setObject(genericResp.getObject());
				}
				
			} catch (Exception ex) {
				logger.info(ex.getMessage());
				logger.error(ex.getMessage());
				res.setStatus("INTERNALERROR");
				//res.setMessage(genericResp.getMessage());
				//res.setObject(genericResp.getObject());
				ex.printStackTrace();
			}

		} else {
			res.setStatus("FAIL");
			List<FieldError> allErrors = result.getFieldErrors();
			List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
			for (FieldError objectError : allErrors) {
				errorMesages.add(new ErrorMessage(objectError.getField(),
						objectError.getField() + "  " + objectError.getDefaultMessage()));
			}
			res.setErrorMessageList(errorMesages);

		}

		return res;
	}
	@RequestMapping(value = "/timeSheets.json", method = RequestMethod.GET)
	@ResponseBody
	public String getCalendarData(@RequestParam(value = "start", required = true) String fromDate,
			@RequestParam(value = "end", required = true) String toDate) {

		fromDate = changeFullCallendarFormat(fromDate);
		toDate = changeFullCallendarFormat(toDate);
		logger.info("get timesheetsJosn");
		String restUriWeeklyReturnByWsUserId = restClientService
				.readUriFromProperty(REST_URI_TIMESHEET_WEEKLY_RETURN_BY_WINDOWS_USER_ID);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		logger.info("get timesheetsJosn for " + username);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);
		params.put("fromDate", fromDate);
		params.put("toDate", toDate);
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<List<WeeklyReturnDto>> timeSheetsResponse = restTemplate.exchange(restUriWeeklyReturnByWsUserId,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<WeeklyReturnDto>>() {
				}, params);
		String calendarJson = "";
		try {
			calendarJson = mapper.writeValueAsString(timeSheetsResponse.getBody());
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("calendarJson" + calendarJson);
		return calendarJson;
	}

	@RequestMapping(value = "/accesProjects.json", method = RequestMethod.GET)
	@ResponseBody
	public String loadProjectsFromAcces() {

		String projectsJson = null;
		logger.info("get Projects as json from acces db.");
		
		try {
			String restUriTimeSheetsAllProjecs = restClientService.readUriFromProperty(REST_URI_TIMESHEET_ALL_PROJECTS);

			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<List<AccessProject>> timeSheetsResponse = restTemplate.exchange(restUriTimeSheetsAllProjecs,
					HttpMethod.GET, null, new ParameterizedTypeReference<List<AccessProject>>() {
					});
			projectsJson = mapper.writeValueAsString(timeSheetsResponse.getBody());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return projectsJson;
	}

	public List<AccessProject> listAccessProject() {

		logger.debug("Get list of Projects from Acces DB");
		List<AccessProject> accesProjects = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			String restUriTimeSheetsAllProjecs = restClientService.readUriFromProperty(REST_URI_TIMESHEET_ALL_PROJECTS);
			ResponseEntity<List<AccessProject>> timeSheetsResponse = restTemplate.exchange(restUriTimeSheetsAllProjecs,
					HttpMethod.GET, null, new ParameterizedTypeReference<List<AccessProject>>() {
					});
			accesProjects = timeSheetsResponse.getBody();
		} catch (Exception ex) {
			logger.error("error occurs when trying to get list of Projects from Acces DB, error {}", ex.getMessage());
		}
		return accesProjects;
	}

	public List<AccesActivity> listAccesActivity() {

		logger.debug("Get list of Activities from Acces DB");
		List<AccesActivity> accesActivities = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			String restUriTimeSheetsAllActivities = restClientService
					.readUriFromProperty(REST_URI_TIMESHEET_ALL_ACTIVITIES);
			ResponseEntity<List<AccesActivity>> timeSheetsResponse = restTemplate.exchange(
					restUriTimeSheetsAllActivities, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<AccesActivity>>() {
					});
			accesActivities = timeSheetsResponse.getBody();
		} catch (Exception ex) {
			logger.error("error occurs when trying to get list of Activities from Acces DB, error {}", ex.getMessage());
		}
		return accesActivities;
	}

	public String changeFullCallendarFormat(String dateStr) {

		DateFormat fromFormat = new SimpleDateFormat(FULL_CALLENDAR_DATE_FORMAT);// fullcalendat
																					// format
		DateFormat toFormat = new SimpleDateFormat(WORKLOG_WS_DATE_FORMAT);
		Date date = null;
		try {

			fromFormat.setLenient(false);

			toFormat.setLenient(false);
			date = fromFormat.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toFormat.format(date);
	}

}
