package yeying.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Author maple
 *
 * @date 2018/2/10 0010 20:19
 */
public class UserDetailService implements UserDetailsService{

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        return null;
    }
}
