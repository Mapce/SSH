package yeying.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Author maple
 *
 * @date 2018/2/3 0003 21:27
 */
@Entity
@Table(name = "user")
public class User implements UserDetails, Serializable {
    @Id
    private long id;
    private String name;//姓名
    private String mail;//邮箱
    private String telenumber; //电话
    private String role; //角色
    private Date birthday; //生日
    private Date addTime; //生日
    private String username; //注册号
    private String password;//密码
    private int sex;
    @Column(name = "enabled", columnDefinition = "BIT", length = 1)
    private boolean able=true;

    /**
     * security框架需要接口，返回该用户的权限
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role != null) {
            List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
            GrantedAuthority au = new SimpleGrantedAuthority(role);
            list.add(au);
            return list;
        }
        return null;

    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    /**
     * 账号是否不过期,true表示不过期
     * @return
     */
    @Override
        public boolean isAccountNonExpired() {
        return true;
    }

    /*
     * 帐号是否不锁定，false则验证不通过
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /*
     * 凭证是否不过期，false则验证不通过
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /*
     * 该帐号是否启用，false则验证不通过
     */
    @Override

    public boolean isEnabled() {
        return able;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAble(boolean able) {
        this.able = able;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public void setTelenumber(String telenumber) {
        this.telenumber = telenumber;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getTelenumber() {
        return telenumber;
    }

    public Date getAddTime() {
        return addTime;
    }

    public boolean isAble() {
        return able;
    }

    public String getRole() {
        return role;
    }

    public Date getBirthday() {
        return birthday;
    }

    public int getSex() {
        return sex;
    }

    public static class Builder {
        private User user;

        public Builder() {
            user = new User();

        }

        public Builder setId(long id) {
            user.id = id;
            return this;
        }

        public Builder setName(String name) {
            user.name = name;
            return this;
        }

        public Builder setMail(String mail) {
            user.mail = mail;
            return this;
        }

        public Builder setTelenumber(String telenumber) {
            user.telenumber = telenumber;
            return this;
        }

        public Builder setRole(String role) {
            user.role = role;
            return this;
        }

        public Builder setBirthday(Date birthday) {
            user.birthday = birthday;
            return this;
        }

        public Builder setUsername(String username) {
            user.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            user.password = password;
            return this;
        }

        public Builder setSex(int sex) {
            user.sex = sex;
            return this;
        }

        public Builder setAddTime(Date addTime) {
            user.addTime = addTime;
            return this;
        }
        public User getUser(){
            return user;
        }

    }
}
