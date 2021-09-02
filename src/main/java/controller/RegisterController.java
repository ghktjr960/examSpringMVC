package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spring.AlreadyExistingMemberException;
import spring.MemberRegisterService;
import spring.RegisterRequest;

@Controller
@RequestMapping(value="/register")
public class RegisterController {
	public RegisterController() {
		System.out.println("RegisterController()");
	}
	@RequestMapping(value="/step1", method=RequestMethod.GET)
	public String handleStep1() {
		System.out.println("handleStep1()");
		return "register/step1";
	}
	
	/*
	 *  step1에서 약관에 동의여부를 가져오는 방법은 두 가지가 있다.
	 *	1. HttpServletRequest를 직접 이용하여 getParameter()메서드를 호출하여 값을 사용할 수 있다.
	 *  2. @RequestParam 어노테이션을 사용하는 방법
	 *   - value : 타입 String : Http요청 파라미터의 이름을 지정
	 *   - required : 타입 Boolean : 필수여부 지정(default => true, 값이 없을 경우 예외 발생)
	 *   - defaultValue : 타입 String : 요청 파라미터의 기본 값 지정
	 *  
	 *  1. HttpServletRequest사용
	 *  @RequestMapping(value="/register/step2", method=RequestMethod.POST)
	 *  public String handleStep2(HttpServletRequest req) {
	 *  	String agreeParam = req.getParameter("agree");
	 *  	if(agreeParam == null || !agreeParam.equals("true")) {
	 *  		return "register/step1";
	 *  	}
	 *  	return "register/step2";
	 *  }
	 *  
	 */
	
	// 2. @RequsetParam 사용
	// @RequestMapping(value="/step2", method=RequestMethod.POST)
	@PostMapping("step2")
	public String handleStep2(@RequestParam(value="agree", defaultValue="false")Boolean agree, Model model) {
		System.out.println("handleStep2().POST");
		if(!agree) {
			return "redirect:/register/step1";
		}
		model.addAttribute("formData", new RegisterRequest());
		return "register/step2";
	}
	
	// GET요청 시 발생하는 오류페이지를 없애기 위해 작성
	// @RequestMapping(value="/step2", method=RequestMethod.GET)
	@GetMapping("step2")
	public String handleStep2() {
		System.out.println("handleStep2().GET");
		return "redirect:/register/step1";
	}
	
	private MemberRegisterService memberRegisterService;
	
	public void setMemberRegisterService(MemberRegisterService memberRegisterService) {
		this.memberRegisterService = memberRegisterService;
	}
	
	/* 
	 * @ModelAttribute("formData") jsp로 넘겨줄 커맨드 객체의 이름을 지정해주는 작업
	 * 커맨드 객체는 지정된 jsp까지는 값을 넘겨준다.
	 * 커맨드 객체가 지정된 jsp에 전달될 때 기본적인 이름은 RegisterRequest처럼 클래스명에 앞글자만 소문자만 바뀐게
	 * jsp에서 사용될 기본 디폴트 이름이 된다. 그 이름을 @ModelAttribute("formData")으로 바꿔줄 수 있는거다
	 */
	@RequestMapping(value="/step3", method=RequestMethod.POST)
	public String handlerStep3(@ModelAttribute("formData")RegisterRequest regReq, Errors errors) {
		new RegisterRequestValidator().validate(regReq, errors);
		if(errors.hasErrors()) {
			return "register/step2";
		}
		try {
			memberRegisterService.regist(regReq);
			return "register/step3";
		} catch (AlreadyExistingMemberException e) {
			errors.rejectValue("email", "duplicate");
			return "register/step2";
		}
	}
	
	
}
