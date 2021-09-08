package com.itheima.security;

import com.itheima.pojo.User;
import org.aspectj.weaver.ast.Var;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义认证授权类
 * 认证：账号密码从数据库查询
 */
@Component
public class MySpringSecurityService implements UserDetailsService {
     //模拟从数据库查询
    public static Map<String, User> map=new HashMap<String,User>();

    static {
         User user1 = new User();
         user1.setUsername("admin");
         user1.setPassword("admin");

         User user2 = new User();
         user2.setUsername("zhangsan");
         user2.setPassword("admin");

         map.put(user1.getUsername(),user1);
         map.put(user2.getUsername(),user2);
    }

    /**
     * 根据用户名 查询用户对象 返回给框架
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1根据用户名查询数据库
        User user = map.get(username);

        //2 用户不存在则返回null
        if(user==null){
            return null;
        }

        //3 将数据库查询出来的密码 用户名 授予的权限列表
        String dbPassword="{noop}"+user.getPassword();
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        list.add(new SimpleGrantedAuthority("add"));
        return new org.springframework.security.core.userdetails.User(username,dbPassword,list);
    }
}
