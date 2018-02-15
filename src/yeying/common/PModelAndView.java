package yeying.common;

import org.springframework.web.servlet.ModelAndView;

/**
 * Author maple
 *
 * @date 2018/2/13 0013 10:51
 */
public class PModelAndView extends ModelAndView {


     public PModelAndView(String url,String type){
          super(type+url);
     }
     public PModelAndView(String url){
          super(url);
     }
}
