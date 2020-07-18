package com.org.service.user;
import com.org.domain.PageBean;
import com.org.domain.User;

import java.util.List;
import java.util.Map;

/**
 *@program: NewsManager
 *@description: 用户管理的业务接口
 *@author: ming
 *@create: 2020-05-23 10:26
 */
public interface UserService {

    /**
     * 添加管理员
     * @param user
     * @return
     */
    boolean add(User user);

    /**
     * 查询所有用户信息
     * @return
     */
    public List<User> findAll();

    /**
     * 登录方法
     * @param user
     * @return
     */
    User login(User user);


    /**
     * 根据id删除用户
     * @param id
     */
    void deleteUser(String id);


    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User findUserById(String id);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    void updateUser(User user);

    /**
     * 删除选中用户
     * @param ids
     */
    void delSelectUser(String[] ids);

    /**
     * 分页条件查询
     * @param currentPage
     * @param rows
     * @param condition
     * @return
     */
    PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);
}
