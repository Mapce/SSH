package yeying.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import yeying.common.PModelAndView;
import yeying.entity.User;
import yeying.service.UserService;
import yeying.utils.SecurityUserHolder;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */
@Controller
public class TestController extends  BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/test.htm")
    public String helper(HttpServletRequest request){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getDetails());
    return "/index";
}
    @RequestMapping(value="/welcome.htm")
    public PModelAndView welcome(HttpServletRequest request){
        PModelAndView mv=new PModelAndView("index",VIEW_PATH);
        User user= SecurityUserHolder.getCurrentUser();
        if(user!=null){
            mv.addObject("user",user);
        }
return mv;
    }



}
