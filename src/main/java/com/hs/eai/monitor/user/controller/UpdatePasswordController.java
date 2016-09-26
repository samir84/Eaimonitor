package com.hs.eai.monitor.user.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.hs.eai.monitor.common.GenericResponse;
import com.hs.eai.monitor.model.ErrorMessage;
import com.hs.eai.monitor.model.ValidationResponse;
import com.hs.eai.monitor.user.dto.PasswordDto;
import com.hs.eai.monitor.user.model.User;
import com.hs.eai.monitor.user.service.UserService;
import com.hs.eai.monitor.user.web.error.InvalidOldPasswordException;

@Controller
public class UpdatePasswordController {

	@Autowired
	UserService userService;
	@Autowired
	private MessageSource messages;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private SimpleMailMessage preConfiguredMessage;
	@Autowired
	private Environment env;
	
	@RequestMapping(value = "/user/forgotPassword", method = RequestMethod.GET)
	public String forgotPassword() {

		return "forgotPassword";
	}
	

	// Reset password
	@RequestMapping(value = "/user/resetPassword.json", method = RequestMethod.POST)
	@ResponseBody
	public GenericResponse resetPassword(final HttpServletRequest request,
			@RequestParam("email") final String email) {

		GenericResponse genericResp = new GenericResponse();
		try {
			 User user = userService.findByEmail(email);
			if (user == null) {
				genericResp.setMessage("No user found with email: "+email);
				genericResp.setStatus("FAIL");
				genericResp.setObject(null);
			}else{
				final String token = UUID.randomUUID().toString();
				userService.createPasswordResetTokenForUser(user, token);
				mailSender.send(constructResetTokenEmail(getAppUrl(request), request.getLocale(), token, user));
				genericResp.setMessage(messages.getMessage("message.resetPasswordEmail", null, request.getLocale()));
				genericResp.setStatus("SUCCES");
			}
			
		} catch (Exception ex) {
			genericResp.setMessage("Exception:"+ex.getMessage());
			genericResp.setStatus("ERROR");
			ex.printStackTrace();
		}

		return genericResp;
	}
	
	@RequestMapping(value = "/user/resetPassword", method = RequestMethod.GET)
    public String showChangePasswordPage(final Locale locale, final Model model, @RequestParam("id")  long id, @RequestParam("token") final String token) {
        final String result = userService.validatePasswordResetToken(id, token);
        if (result != null) {
            model.addAttribute("message", messages.getMessage("auth.message." + result, null, locale));
            return "redirect:/login?lang=" + locale.getLanguage();
        }
        PasswordDto passwordDto = new PasswordDto();
		model.addAttribute("passwordDto", passwordDto);
        return "redirect:/user/changePassword.html?id="+id+"&lang=" + locale.getLanguage();
    }
	@RequestMapping(value = "/user/changePassword", method = RequestMethod.GET)
	public String updatePassword(Model model) {
		PasswordDto passwordDto = new PasswordDto();
		model.addAttribute("passwordDto", passwordDto);
		return "changePassword";
	}
	@RequestMapping(value = "/user/savePassword", method = RequestMethod.POST)
    @PreAuthorize("hasRole('READ_PRIVILEGE')")
    @ResponseBody
    public ValidationResponse savePassword(final Locale locale,@RequestParam(value="lnag", required=false) String localeLang,@RequestParam("id") Integer id, @Valid @ModelAttribute("passwordDto") PasswordDto passwordDto , BindingResult result)
	{
		
        ValidationResponse validationResp = new ValidationResponse();
    	if (!result.hasErrors()){
    		User user = userService.findById(id);

            userService.changeUserPassword(user, passwordDto.getNewPassword());
            validationResp.setStatus("SUCCES");
            validationResp.setMessage(messages.getMessage("message.resetPasswordSuc", null, locale));
    	}else{
    		validationResp.setStatus("FAIL");
			List<FieldError> allErrors = result.getFieldErrors();
			List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
			for (FieldError objectError : allErrors) {
				errorMesages.add(new ErrorMessage(objectError.getField(),
						objectError.getField() + "  " + objectError.getDefaultMessage()));
			}
			validationResp.setErrorMessageList(errorMesages);
    	}
    	 
        return validationResp;
        
        //
    }

    // change user password
    @RequestMapping(value = "/user/changePassword", method = RequestMethod.POST)
    @PreAuthorize("hasRole('READ_PRIVILEGE')")
    @ResponseBody
    public ValidationResponse changeUserPassword(final Locale locale,@RequestParam(value="lnag", required=false) String localeLang,@RequestParam("id") Integer id, @Valid @ModelAttribute("passwordDto") PasswordDto passwordDto , BindingResult result) {
        
    	ValidationResponse validationResp = new ValidationResponse();
    	if (!result.hasErrors()){
    		User user = userService.findById(id);
            if (!userService.checkIfValidOldPassword(user, passwordDto.getOldPassword())) {
                throw new InvalidOldPasswordException();
            }
            userService.changeUserPassword(user, passwordDto.getNewPassword());
            validationResp.setStatus("SUCCES");
            validationResp.setMessage(messages.getMessage("message.updatePasswordSuc", null, locale));
    	}else{
    		validationResp.setStatus("FAIL");
			List<FieldError> allErrors = result.getFieldErrors();
			List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
			for (FieldError objectError : allErrors) {
				errorMesages.add(new ErrorMessage(objectError.getField(),
						objectError.getField() + "  " + objectError.getDefaultMessage()));
			}
			validationResp.setErrorMessageList(errorMesages);
    	}
    	 
        return validationResp;
    }
    
	private SimpleMailMessage constructResetTokenEmail(final String contextPath, final Locale locale,
			final String token, final User user) {
		final String url = contextPath + "/user/resetPassword?id=" + user.getId() + "&token=" + token;
		final String message = messages.getMessage("message.resetPassword", null, locale);
		return constructEmail("Reset Password", message + " \r\n" + url, user);
	}

	private SimpleMailMessage constructEmail(String subject, String body, User user) {
		SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
		mailMessage.setSubject(subject);
		mailMessage.setText(body);
		mailMessage.setTo(user.getEmail());
		return mailMessage;
	}

	private String getAppUrl(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}
}
