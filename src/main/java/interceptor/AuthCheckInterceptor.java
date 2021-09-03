package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthCheckInterceptor extends HandlerInterceptorAdapter{
	
	/*
	 * 인터셉터!!!
	 * 비밀번호 변경과 같은 작업들은 로그인 작업이 필수적으로 진행이 된 다음에 이어지는 동작들이다.
	 * 이러한 동작은 각 컨트롤러마다 세션을 사용하여 로그인 여부에 대한 작업이 진행되야 하는데
	 * 컨트롤러마다 각각의 검사를 진행하는 것은 코드중복이 많아지게 되며 효율적인 코드진행과 거리가 떨어진다.
	 * 그래서 사용되는 것이 인터셉터 이다.
	 * 스프링에서도 인터셉터를 지원하기위해 HandleInterceptor 인터페이스를 제공한다.
	 * 인터셉터는 어떠한 동작이 이루어지게 전에 낚아채와 인터셉터에서 먼저 동작을 이룰 수 있게 한다.
	 */
	
	@Override
	public boolean preHandle(
		HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		/*
		 * Session 메서드
		 * 1. getSession(), getSession(true)
		 * 	- HttpSession이 존재하면 현재 HttpSession을 반환하고 존재하지 않으면 새로운 세션을 생성한다.
		 * 2. getSession(false)
		 * 	- HttpSession이 존재하면 현재 HttpSession을 반환하고 존재하지 않으면 생성하지 않고 그냥 null을 반환한다.
		 */
		
		HttpSession session = request.getSession(false);
		if(session != null) {
			Object authInfo = session.getAttribute("authInfo");
			if(authInfo != null) {
				return true;
			}
		}
		response.sendRedirect(request.getContextPath() + "/login");
		return false;
	}
	
}
