package yeying.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import yeying.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author maple
 *
 * @date 2018/2/2 0002 21:51
 * UserDetailsService:spring-security框架过滤器要使用的接口，调用该接口的loadUserByUsername方法
 * 可以获取到一个实现了UserDetails类的User实体对象。
 */
@Repository
public class UserDao extends  BaseDao<User> implements UserDetailsService{

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//根据用户名获取User
        List<User> users=this.queryByHql("select obj from User as obj where obj.username=?",username);
        User user=null;
        String role="";
        if(users.size()>0){
           user=users.get(0);
           return user;
        }else{
            return new User();
        }
    }
}
