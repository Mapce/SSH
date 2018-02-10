package yeying.service;

import org.springframework.stereotype.Service;
import yeying.dao.UserDao;
import yeying.entity.User;

/**
 * Author maple
 *
 * @date 2018/2/2 0002 22:15
 */
@Service
public class UserService extends  BaseService<UserDao,User>{
    public String test(){
 dao.getByIdT();
 return "1";
    }
}
