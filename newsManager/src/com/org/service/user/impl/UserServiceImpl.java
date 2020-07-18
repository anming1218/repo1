package com.org.service.user.impl;
import com.org.dao.user.UserDao;
import com.org.dao.user.impl.UserDaoImpl;
import com.org.domain.PageBean;
import com.org.domain.User;
import com.org.service.user.UserService;
import com.org.util.MD5Utils;

import java.util.List;
import java.util.Map;

/**
 *@program: NewsManager
 *@description:
 *@author: ming
 *@create: 2020-05-23 10:27
 */
public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();

    /**
     * 添加管理员
     * @param user
     * @return
     */
    @Override
    public boolean add(User user) {
        return dao.add(user);
    }

    /**
     * 查询所有用户信息
     * @return
     */
    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    /**
     * 登录方法
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        return dao.findUserByUsernameAndPassword(user.getUsername(), MD5Utils.md5(user.getPassword()));
    }

    /**
     * 根据id删除用户
     * @param id
     */
    @Override
    public void deleteUser(String id) {
        dao.deleteUser(Integer.parseInt(id));
    }

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Override
    public User findUserById(String id) {
        return dao.findUserById(Integer.parseInt(id));
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @Override
    public void updateUser(User user) {
         dao.updateUser(user);
    }

    /**
     * 删除选中用户
     * @param ids
     */
    @Override
    public void delSelectUser(String[] ids) {

        if (ids != null && ids.length > 0) {
            //1.遍历数组
            for (String id : ids) {
                //调用dao删除
                dao.deleteUser(Integer.parseInt(id));
            }
        }
    }

    /**
     * 分页条件查询
     * @param _currentPage
     * @param _rows
     * @param condition
     * @return
     */
    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        if (currentPage <= 0) {
            currentPage = 1;
        }
        //1.创建空的PageBean对象
        PageBean<User> pb = new PageBean<User>();
        //2.设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        //3.调用dao查询总记录数
        int totalCount = dao.findTotalCount(condition);
        pb.setTotalCount(totalCount);
        //4.调用dao查询List集合
        //计算开始的记录索引
        int start = (currentPage - 1) * rows;
        List<User> list = dao.findByPage(start, rows, condition);
        pb.setList(list);

        //5.计算总页码
        int totalPage = (totalCount % rows) == 0 ? totalCount / rows : (totalCount / rows) + 1;
        pb.setTotalPage(totalPage);


        return pb;
    }
}
