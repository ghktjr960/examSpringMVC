package survey;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/survey")
public class SurveyController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String form(Model model) {
		System.out.println("survey : GET요청 => form() ");
		
		List<Question> questions = createQuestions();
		model.addAttribute("questions", questions);
		return "survey/surveyForm";
	}
	
	private List<Question> createQuestions(){
		Question q1 = new Question("당신의 역할은?", Arrays.asList("서버","프론트","풀스택"));
		Question q2 = new Question("주로 사용하는 개발도구는 무엇입니까?", Arrays.asList("Eclipse","Intellij","Sublime"));
		Question q3 = new Question("하고 싶은 말?");
		return Arrays.asList(q1, q2, q3);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String submit(@ModelAttribute("ansData")AnsweredData data) {
		System.out.println("survey : POST요청 => submit() ");
		return "survey/submitted";
	}
	
}
