package com.org.dao.topic.impl;
import com.org.dao.topic.TopicDao;
import com.org.domain.Topic;
import com.org.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *@program: NewsManager
 *@description:
 *@author: ming
 *@create: 2020-05-23 15:31
 */
public class TopicDaoImpl implements TopicDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 查找全部主题
     * @return
     */
    @Override
    public List<Topic> findAll() {

        //使用JDBC操作数据库
        //1.定义sql
        String sql = "select * from topic";
        //2.使用sql
        List<Topic> topics = template.query(sql, new BeanPropertyRowMapper<Topic>(Topic.class));

        return topics;
    }

    /**
     * 根据id删除主题
     * @param id
     */
    @Override
    public void deleteTopic(int id) {
        //定义sql
        String sql = "delete from topic where id=?";

        //运行sql
        template.update(sql, id);
    }

    /**
     * 增加主题
     * @param topic
     * @return
     */
    @Override
    public boolean add(Topic topic) {
        //使用JDBC操作数据库
        //1.定义sql
        String sql = "insert into topic values(null,?)";
        //2.执行sql
        template.update(sql, topic.getTopicname());
        return true;
    }

    /**
     * 根据id查找主题
     * @return
     */
    @Override
    public Topic findTopicById(int id) {
        //定义sql
        String sql = "select * from topic where id = ?";
        //执行sql
        return template.queryForObject(sql, new BeanPropertyRowMapper<Topic>(Topic.class), id);
    }

    /**
     * 修改主题
     */
    @Override
    public void updateTopic(Topic topic) {
        String sql = "update topic set topicname = ? where id = ?";
        template.update(sql, topic.getTopicname(), topic.getId());
    }

    /**
     * 查询总记录数
     * @param condition
     * @return
     */
    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        //1.定义模板初始化sql
        String sql = "select count(*) from topic where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        //2.遍历map
        Set<String> keySet = condition.keySet();
        //定义参数的集合
        List<Object> params = new ArrayList<Object>();
        for (String key : keySet) {

            //排除分页条件参数
            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }

            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if (value != null && !"".equals(value)) {
                //有值
                sb.append(" and " + key + " like ? ");
                //？条件的值
                params.add("%" + value + "%");
            }
        }
        System.out.println(sb.toString());
        System.out.println(params);

        return template.queryForObject(sb.toString(), Integer.class, params.toArray());
    }

    /**
     * 分页查询每页记录
     * @param start
     * @param rows
     * @param condition
     * @return
     */
    @Override
    public List<Topic> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from topic  where 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);
        //2.遍历map
        Set<String> keySet = condition.keySet();
        //定义参数的集合
        List<Object> params = new ArrayList<Object>();
        for (String key : keySet) {

            //排除分页条件参数
            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }

            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if (value != null && !"".equals(value)) {
                //有值
                sb.append(" and " + key + " like ? ");
                params.add("%" + value + "%");//？条件的值
            }
        }

        //添加分页查询
        sb.append(" limit ?,? ");
        //添加分页查询参数值
        params.add(start);
        params.add(rows);
        sql = sb.toString();
        System.out.println(sql);
        System.out.println(params);

        return template.query(sql, new BeanPropertyRowMapper<Topic>(Topic.class), params.toArray());

    }
}
