package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    //hello 라는 url로 오면 이 controller가 호출된다.
    //컨트롤러에서 model에 데이터를 싣어서 view에 넘길 수 있다.
    //return은 화면 이름이다. (hello만 해도 html이 자동으로 붙는다)
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }
}
