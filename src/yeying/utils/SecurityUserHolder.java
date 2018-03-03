package yeying.utils;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import yeying.entity.User;

/**
 * Author maple
 *
 * @date 2018/2/14 0014 15:25
 */
public class SecurityUserHolder {

    public static User getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            return null;
        }
        Object obj = context.getAuthentication().getPrincipal();
        if (obj instanceof User) {
            User user = (User) obj;
            return user;
        } else {
            return null;
        }

    }
}
