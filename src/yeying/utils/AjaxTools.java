package yeying.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Author maple
 *
 * @date 2018/2/13 0013 13:52
 */
public class AjaxTools {
    /**
     * 单例模式
     */
    private  static AjaxTools instance;
    private AjaxTools(){

    }
    public static AjaxTools getInstance(){
        if(instance==null){
            synchronized (AjaxTools.class){
                if(instance==null){
                    instance=new AjaxTools();
                }
            }
        }
        return instance;
    }
    public PrintWriter getWriter(HttpServletResponse response){
        PrintWriter pw=null;
        try {
           pw=response.getWriter();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return pw;
    }

}
