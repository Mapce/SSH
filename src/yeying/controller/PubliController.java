package yeying.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import yeying.common.PModelAndView;
import yeying.entity.User;
import yeying.utils.SecurityUserHolder;

/**
 * Author maple
 *页面公共部分Controller 比如top.controller
 * @date 2018/2/13 0013 10:42
 */
@Controller
@RequestMapping(value = "/public")
public class PubliController extends  BaseController{
    /**
     * 获取公共顶页
     */
    @RequestMapping(value = "/topMenu.htm")
    public ModelAndView TopMenu(){
        PModelAndView mv=new PModelAndView("topMenu",PUBLIC_PATH);

        addUserToModel(mv);
        return mv;
    }
}
