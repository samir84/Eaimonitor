package com.hs.eai.monitor.files.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.hs.eai.monitor.common.GenericResponse;
import com.hs.eai.monitor.files.model.FileMeta;
import com.hs.eai.monitor.model.ErrorMessage;
import com.hs.eai.monitor.model.ValidationResponse;
import com.hs.eai.monitor.project.model.WeeklyReturn;
import com.hs.eai.monitor.service.RestClientService;

/**
 * Handles requests for the application file upload requests
 */
@Controller
public class FileUploadController {

	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	private static final String REST_URI_EAI_MONITOR_BASE = "restUriEaiMonitorWSBase";
	private static final String REST_URI_IMPORT_PROJECT_PLANNING = "restUriEaiMonitorWSImportProjectsPlanning";
	private static final String PROJECT_OVERVIEW_IMPORT_FILE_TYPE="projectOverview";
	@Autowired
	RestClientService restClientService;
	@PostConstruct
	public void init() {
		restClientService.setUriBase(REST_URI_EAI_MONITOR_BASE);

	}
	
	/**
	 * Upload single file using Spring Controller
	 */
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.GET)
	public String getUploadFile(){
		return "uploadFile";
	}
	@RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.GET)
	public String getUploadMultipleFile(){
		return "uploadMultipleFile";
	}
	@RequestMapping(value = ("/uploadFile"),headers=("content-type=multipart/*"), method = RequestMethod.POST)
	public @ResponseBody GenericResponse uploadFileHandler(
			@RequestParam("file") MultipartFile file , Principal principal) throws IOException {

		String username = principal.getName();
		ValidationResponse res = new ValidationResponse();

		GenericResponse genericResp = null;
		
		try{
			RestTemplate restTemplate = new RestTemplate();
			String restUriImportProjectPlanning = restClientService
					.readUriFromProperty(REST_URI_IMPORT_PROJECT_PLANNING);

			FileMeta fileMeta = new FileMeta();
			fileMeta.setContent(file.getBytes());
			fileMeta.setFilename(file.getOriginalFilename());
			fileMeta.setFileType(PROJECT_OVERVIEW_IMPORT_FILE_TYPE);
			fileMeta.setOwner(username);
			
			HttpEntity<FileMeta> request = new HttpEntity<>(fileMeta);

			ResponseEntity<GenericResponse> response = restTemplate.exchange(restUriImportProjectPlanning,
					HttpMethod.POST, request, GenericResponse.class);
			
			genericResp = (GenericResponse) response.getBody();
			
			System.out.println("genericResp:"+genericResp);
			
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
		}catch(Exception ex){
			ex.printStackTrace();
			res.setStatus("ERROR");
			res.setMessage(ex.getMessage());
			res.setObject(ex);
		}
			
			
			
		
		return genericResp;
		
	}

	/**
	 * Upload multiple file using Spring Controller
	 */
	@RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST)
	public @ResponseBody
	String uploadMultipleFileHandler(
			@RequestParam("file") MultipartFile[] files) {

		/*if (files.length != names.length)
			return "Mandatory information missing";
			*/

		String message = "";
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			String name = file.getName();
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				//String rootPath = System.getProperty("catalina.home");
				
				String rootPath ="C:\\test";
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location="
						+ serverFile.getAbsolutePath());

				message = message + "You successfully uploaded file=" + name
						+ "";
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		return message;
	}
}
