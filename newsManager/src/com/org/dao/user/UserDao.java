package com.org.dao.user;
import com.org.domain.User;

import java.util.List;
import java.util.Map;

/**
 *@program: NewsManager
 *@description: 用户操作的DAO
 *@author: ming
 *@create: 2020-05-23 10:27
 */
public interface UserDao {

    User findUserByUsernameAndPassword(String username, String password);

    List<User> findAll();

    void deleteUser(int id);

    boolean add(User user);

    User findUserById(int id);

    void updateUser(User user);

    /**
     * 查询总记录数
     * @param condition
     * @return
     */
    int findTotalCount(Map<String, String[]> condition);

    /**
     * 分页查询每页记录
     * @param start
     * @param rows
     * @param condition
     * @return
     */
    List<User> findByPage(int start, int rows, Map<String, String[]> condition);
}
