package yeying.controller;

import org.springframework.web.servlet.ModelAndView;
import yeying.common.PModelAndView;
import yeying.entity.User;
import yeying.utils.SecurityUserHolder;

/**
 * Author maple
 *
 * @date 2018/2/13 0013 10:57
 */
public class BaseController {
    public  String BASE_PATH="/";  //基本路径 默认为WEB-INFO/view/
    public  String VIEW_PATH="/";   //view路径 默认为WEB-INFO/view/
    public  String PUBLIC_PATH="/public/";   //public 默认为WEB-INFO/view/global

    /**
     * 如果用户已登录，通过security框架添加当前用户到model
     * @param mv
     */
  public void addUserToModel(ModelAndView mv){
      User user= SecurityUserHolder.getCurrentUser();
      if(user!=null){
          mv.addObject("user",user);
      }else{

      }
  }
}
