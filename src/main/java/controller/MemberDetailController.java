package controller;

import org.springframework.beans.TypeMismatchException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.Member;
import spring.MemberDao;
import spring.MemberNotFoundException;

@Controller
public class MemberDetailController {
	private MemberDao memberDao;

	public MemberDao getMemberDao() {
		return memberDao;
	}

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	@RequestMapping("/member/detail/{id}")
	public String detail(@PathVariable("id")Long memId, Model model) {
		/*
		 * /member/detail/{id} 에서 {id}는 경로변수라고 하며 이 중괄호에 들어가는 값은
		 * @PathVariable에 경로변수 이름(현재 클래스에서는 id)으로 전달된다.
		 * 즉, @RequestMapping("/member/detail/{id}")의 {id}는
		 * @PathVariable("id")의 ("id")와 같다
		 */
		Member member = memberDao.selectById(memId);
		if(member == null) {
			throw new MemberNotFoundException();
		}
		model.addAttribute("member", member);
		return "member/memberDetail";
	}
	
	@ExceptionHandler(TypeMismatchException.class)
	public String hendleTypeMismatchException() {
		return "member/invalidId";
	}
	
//	@ExceptionHandler(MemberNotFoundException.class)
//	public String handlerMemberNotFoundException() {
//		return "member/noMember";
//	}
	
	
}
