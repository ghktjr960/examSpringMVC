package controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/* 
 * 웹 요청에 대한 처리가 이루어질 때 예외가 발생하면 해당 예외를 그대로 브라우저에 보여주게 되는 것은 
 * 외부 위협요소에 노출되는 것이기 때문에 보안상에 문제가 발생한다. 
 * 이때 적절한 예외처리를 해주어 발생한 예외가 외부에 노출되지 않고 내부적으로 확인할 수 있도록 하는 것이 중요하다.
 * 
 * 스프링에서는 @ExceptionHandler어노테이션을 제공해 예외처리를 할 수 있도록 지원한다.
 * @ExceptionHandler사용할 때 두 가지에 방법
 *  - 클라이언트에 요청을 처리하는 클래스에서 예외처리를 진행하는 방법
 *  	=> 특정 예외를 처리할 때 사용할 수 있음
 *  - 공통 예외를 처리하는 클래스를 만들어 모든 예외를 공통 메세지로 표현하는 방법
 *  	=> 특정 예외처리에 지정되있지 않은 모든 예외를 공통적으로 처리할 수 있음
 *  
 *  이때 예외처리가 되는 우선 순위는 클래스에 따로 지정된 특정 예외처리가 우선적으로 처리가 되며 
 *  그 외에 예외는 공통 예외 처리로 처리가 된다.
 *  
 *  중요!!!
 *  스프링에서 @ExceptionHandler지원해 예외처리를 할 수 있다고 해서 기존에 사용하던 try~catch를 사용하지 말라는 것은 아니다.
 *  컨트롤러 클래스내에서 발생되는 예외에 대해서 @ExceptionHandler로 지정된 메서드가 처리하도록하는 방법을 사용할 수 있는 것이고
 *  컨트롤러들이 전체적으로 공통으로 발생되는 예외를 처리하고자 한다면 @ControllerAdive를 사용할 수 있는 것이다.
 *  @ControllerAdive를 사용할 때 "@ExceptionHandler(Exception.class)"중 Exception에 다른 예외메세지를 넣으면
 *  해당 예외가 처리된다.
 *  이것을 활용해 공통적으로 발생하는 예외를 처리할 수 있는 것이다.
 *  프로젝트를 설계하면서 예외를 어떻게 처리할 것인지 어떻게 관리할 것인지에 따라 예외 처리 방법이 다양해지는 것이며 그것을 결정하는
 *  것은 개발자에 따라 다 다르기 때문에 정확하게 정해진 것은 없다.
 *  하지만 공통적으로 발생하는 예외를 처리할 수 있는 것을 활용하면 조금 더 편하게 예외를 처리할 수 있으니 알고있는 것이 좋다.
 */

@ControllerAdvice
//@ControllerAdvice("프로젝트 명")
public class CommonExceptionHandler {
	@ExceptionHandler(Exception.class)
	public String handleException() {
		System.out.println("handleException()");
		return "error/commonException";
	}
}
