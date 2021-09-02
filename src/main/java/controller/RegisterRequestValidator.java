package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import spring.RegisterRequest;

public class RegisterRequestValidator implements Validator{
	
	private static final String emailRegExp = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	/*
	 *  이메일에 대한 형식(Pattern)을 지정해준다.
	 *  검색 키워드 : 자바 정규 표현식
	 */
	
	
	private Pattern pattern;
	
	public RegisterRequestValidator() {
		System.out.println("RegisterRequestValidator()");
		pattern = Pattern.compile(emailRegExp);
	}
	
	@Override
	public boolean supports(Class<?> arg0) {
		System.out.println("supports(Class<?> arg0)");
		return RegisterRequestValidator.class.isAssignableFrom(arg0);
		/*
		 * 스프링 MVC에서 전달받은 객체를 자동으로 검증할 경우 올바르게 구현해야 함
		 * 현재 실습에서는 사용하지 않은ㅁ
		 */
	}	
	
	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("validate(Object target, Errors errors)");
		// target : 검사 대상 객체
		// errors : 검사 결과 에러코드를 저장하는 객체
		RegisterRequest regReq = (RegisterRequest) target;
		if(regReq.getEmail() == null || regReq.getEmail().trim().isEmpty()) {
			errors.rejectValue("email", "required");
		} else {
			Matcher matcher = pattern.matcher(regReq.getEmail());
			if(!matcher.matches()) {
				errors.rejectValue("email", "bad");
			}
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		ValidationUtils.rejectIfEmpty(errors, "password", "required");
		ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "required");
		if(!regReq.getPassword().isEmpty()) {
			if(!regReq.isPasswordEqualsToConfirmPassword()) {
				errors.rejectValue("confirmPassword", "nomatch");
			}
		}
	}
}
