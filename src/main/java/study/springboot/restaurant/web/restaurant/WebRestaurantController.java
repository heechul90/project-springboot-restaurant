package study.springboot.restaurant.web.restaurant;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pages")
public class WebRestaurantController {

    @GetMapping(value = "/main")
    public String main() {
        return "main";
    }
}
