package controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.AuthInfo;
import spring.AuthService;
import spring.IdPasswordNotMatchingException;

@Controller
@RequestMapping("/login")
public class LoginController {
	private AuthService authService;
	
	public AuthService getAuthService() {
		return authService;
	}

	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}

	@RequestMapping(method=RequestMethod.GET)
	public String form(LoginCommand loginCommand) {
		return "login/loginForm";
	}
	
	@PostMapping
	public String submit(LoginCommand loginCommand, Errors errors, HttpSession session) {
		new LoginCommandValidator().validate(loginCommand, errors);
		if(errors.hasErrors()) {
			return "login/loginForm";
		}
		try {
			AuthInfo authInfo = authService.authenticate(loginCommand.getEmail(), loginCommand.getPassword());
			System.out.println("authInfo 내용 확인");
			System.out.println("authEmail : " + authInfo.getEmail());
			System.out.println("authName : " + authInfo.getName());
			System.out.println("authId : " + authInfo.getId());
			session.setAttribute("authInfo", authInfo);
			return "login/loginSuccess";
		} catch (IdPasswordNotMatchingException e) {
			errors.reject("IdPasswordMatching");
			return "login/loginForm";
		}
	}
	
}
