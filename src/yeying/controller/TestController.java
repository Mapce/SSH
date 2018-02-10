package yeying.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import yeying.entity.User;
import yeying.service.UserService;
import yeying.utils.EntityWrapper;
import yeying.utils.Page;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */
@Controller
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/test.htm")
    public String helper(HttpServletRequest request){
        EntityWrapper entityWrapper=new EntityWrapper(User.class);
        entityWrapper.addAnd("sex","1","=");
        entityWrapper.addOr("sex","0","=");
        System.out.println(entityWrapper.getHql());
        System.out.println(entityWrapper.getDeleteHql());
        System.out.println(entityWrapper.getCountHql());
        Page<User> page=userService.queryPage(entityWrapper,1,5);
    return "111.jsp";
}



}
