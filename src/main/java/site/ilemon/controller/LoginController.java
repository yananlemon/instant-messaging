package site.ilemon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.ilemon.controller.req.LoginReq;
import site.ilemon.controller.res.JsonResult;

/**
 * <p></p>
 *
 * @author Andy.Yan
 */
@RequestMapping
@Controller
public class LoginController {

    @GetMapping
    public String redirectLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(LoginReq loginReq, Model modelAttribute) {
        if (loginReq == null) {
            return "login";
        }
        if (loginReq.getUsername() != null && !loginReq.getUsername().equals("") &&
                loginReq.getUsername() != null && !loginReq.getUsername().equals("")) {
            modelAttribute.addAttribute("username", loginReq.getUsername());
            return "index";
        }
        return "login";
    }
}
