package com.hs.eai.monitor.user.web.validation;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.validation.Errors;

/*import org.passay.DigitCharacterRule;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.SpecialCharacterRule;
import org.passay.UppercaseCharacterRule;
import org.passay.WhitespaceRule;
*/

import com.google.common.base.Joiner;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

	private Pattern pattern;
	private Matcher matcher;
	private boolean isvalidPassword = false;
	private static final String MUST_CONTAIN_ONE_DIGIT = "(?=.*[0-9]).+";
	private static final String MUST_CONTAIN_ONE_LOWER_CASE = "(?=.*[a-z]).+";
	private static final String MUST_CONTAIN_ONE_UPPER_CASE = "(?=.*[a-z]).+";
	private static final String MUST_CONTAIN_ONE_SPECIAL_CHARACTER = "(?=.*[!@#$%^&*+=?-_()/\"\\.,<>~`;:]).+";
	private static final String MUST_CONTAIN_NO_WHITE_SPACE = "(?=\\S+$).+";

	@Override
	public void initialize(final ValidPassword arg0) {

	}

	@Override
    public boolean isValid(final String password, final ConstraintValidatorContext context) {
    	
		
        Boolean result = validate(password);
        if (result) {
            return true;
        }
        //context.disableDefaultConstraintViolation();
        //context.buildConstraintViolationWithTemplate(Joiner.on("\n").join(validator.getMessages(result))).addConstraintViolation();
        return false;
    }

	public boolean validate(String password){
		pattern = Pattern.compile(MUST_CONTAIN_ONE_DIGIT);
        matcher = pattern.matcher(password);
        if(matcher.matches()){
        	isvalidPassword = true;
        }
        pattern = Pattern.compile(MUST_CONTAIN_ONE_LOWER_CASE);
        matcher = pattern.matcher(password);
        if(matcher.matches()){
        	isvalidPassword = true;
        }
        pattern = Pattern.compile(MUST_CONTAIN_ONE_UPPER_CASE);
        matcher = pattern.matcher(password);
        if(matcher.matches()){
        	isvalidPassword = true;
        }
        pattern = Pattern.compile(MUST_CONTAIN_ONE_SPECIAL_CHARACTER);
        matcher = pattern.matcher(password);
        if(matcher.matches()){
        	isvalidPassword = true;
        }
        pattern = Pattern.compile(MUST_CONTAIN_NO_WHITE_SPACE);
        matcher = pattern.matcher(password);
        if(matcher.matches()){
        	isvalidPassword = true;
        }
        
        return isvalidPassword;
		
	}
	public boolean validate(String password, Errors errors){
		pattern = Pattern.compile(MUST_CONTAIN_ONE_DIGIT);
        matcher = pattern.matcher(password);
        if(matcher.matches()){
        	isvalidPassword = true;
        }else{
        	errors.rejectValue("password", "password.must.one.digit");
        }
        pattern = Pattern.compile(MUST_CONTAIN_ONE_LOWER_CASE);
        matcher = pattern.matcher(password);
        if(matcher.matches()){
        	isvalidPassword = true;
        }else{
        	errors.rejectValue("password", "password.must.one.lower.case");
        }
        pattern = Pattern.compile(MUST_CONTAIN_ONE_UPPER_CASE);
        matcher = pattern.matcher(password);
        if(matcher.matches()){
        	isvalidPassword = true;
        }else{
        	errors.rejectValue("password", "password.must.one.upper.case");
        }
        pattern = Pattern.compile(MUST_CONTAIN_ONE_SPECIAL_CHARACTER);
        matcher = pattern.matcher(password);
        if(matcher.matches()){
        	isvalidPassword = true;
        }else{
        	errors.rejectValue("password", "password.must.one.special.character");
        }
        pattern = Pattern.compile(MUST_CONTAIN_NO_WHITE_SPACE);
        matcher = pattern.matcher(password);
        if(matcher.matches()){
        	isvalidPassword = true;
        }else{
        	errors.rejectValue("password", "password.must.no.white.space");
        }
        
        return isvalidPassword;
		
	}
	



}