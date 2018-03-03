package yeying.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import yeying.common.PModelAndView;
import yeying.common.Role;
import yeying.entity.User;
import yeying.service.UserService;
import yeying.utils.AjaxTools;
import yeying.utils.EntityWrapper;
import yeying.utils.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.parser.Entity;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

/**
 * Author maple
 *
 * @date 2018/2/13 0013 12:10
 */
@Controller
public class LoginController extends  BaseController{
@Autowired
private UserService userService;

    /**
     * 跳转页面
     * @param request
     * @return
     */
    @RequestMapping("register.htm")
    public ModelAndView register(HttpServletRequest request){
        PModelAndView pModelAndView=new PModelAndView("register",VIEW_PATH);
        return pModelAndView;
    }
    /**
     * 用户注册表单提交
     */
    @RequestMapping("register_form.htm")
    public String registerUser(HttpServletRequest request,
                                     String username,String password,
                                     String mail,String name){
if(!checkRegisterIsExist(name,mail)) {
    //生成id
    long id = Math.abs(UUID.randomUUID().getLeastSignificantBits());
    boolean isExist = true;
    while (isExist) {
        if (userService.queryById(id) == null) {
            isExist = false;
        } else {
            id = Math.abs(UUID.randomUUID().getLeastSignificantBits());
        }
    }
    //创建用户
    User user = new User.Builder().setName(name).setMail(mail).setPassword(password)
            .setRole(Role.ROLE_USER).setUsername(username).setId(id).getUser();
    //保存
    userService.save(user);
    return "redirect:/welcome.htm";
}else{
    return "redirect:/register.htm";
}
    }

    /**
     * 注册页面 validate检查username是否重复
     * @param request
     * @param response
     * @param username
     */
    @RequestMapping("register_remoteUsername.htm")
    public void register_remoteUsername(HttpServletRequest request, HttpServletResponse response,String username){
        PrintWriter pw= AjaxTools.getInstance().getWriter(response);
        if(!StringUtils.isEmpty(username)){
            EntityWrapper entityWrapper=new EntityWrapper(User.class);
            entityWrapper.addAnd("username",username,"=");
          List<User> users=userService.query(entityWrapper);
          if(users!=null && users.size()>0){
              pw.print(false);
              pw.close();
              return ;
          }else{
              pw.print(true);
          }
        }

    }

    /**
     * 检查注册账户传入的值是否合法
     * @return
     */
 private boolean checkRegisterIsExist(String name,String mail){
     EntityWrapper entityWrapper=new EntityWrapper(User.class);
         entityWrapper.addAnd("name",name,"=");
     entityWrapper.addOr("mail",mail,"=");
     List<User> list=userService.query(entityWrapper);
     if(list!=null && list.size()>0){
         return true; //存在重复
     }else{
         return false;
     }
 }
}
