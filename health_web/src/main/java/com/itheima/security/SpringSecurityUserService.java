package com.itheima.security;

import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class SpringSecurityUserService implements UserDetailsService {
    @Autowired
   private UserService userService;
    /**
     * 根据用户名查询数据库返回user对象
     * 将用户名 数据库密码 权限列表授予当前登录用户
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1 根据用户名查询数据库获取用户对象
       User user = userService.findUserByUsername(username);
        //2 如果为空 返回null
        if(user == null){
            return null;
        }
        //3 如果不为空获取密码
        String dbPassword = user.getPassword();
        //4 获取当前用户权限列表 授予当前用户权限
        List<GrantedAuthority> list = new ArrayList<>();
        //循环遍历角色集合 获取角色关键字
        if(!CollectionUtils.isEmpty(user.getRoles())){

            for (Role role : user.getRoles()) {
                 String roleKeyword = role.getKeyword(); //权限关键字 一定要以role_
                list.add(new SimpleGrantedAuthority(roleKeyword));
                //循环遍历权限集合 获取权限关键字
                if(!CollectionUtils.isEmpty(role.getPermissions())){
                    for (Permission permission : role.getPermissions()) {
                        list.add(new SimpleGrantedAuthority(permission.getKeyword()));
                    }
                }
            }

        }

        //返回 用户名 密码 权限集合
        return new org.springframework.security.core.userdetails.User(username,dbPassword,list);
    }
}
