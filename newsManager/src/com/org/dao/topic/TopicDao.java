package com.org.dao.topic;
import com.org.domain.Topic;

import java.util.List;
import java.util.Map;

/**
 *@program: NewsManager
 *@description: 主题操作的DAO
 *@author: ming
 *@create: 2020-05-23 15:29
 */
public interface TopicDao {


    /**
     * 增加主题
     * @param topic
     * @return
     */
    boolean add(Topic topic);

    /**
     * 查找全部主题
     * @return
     */
    List<Topic> findAll();

    /**
     * 根据id删除主题
     * @param id
     */
    void deleteTopic(int id);

    /**
     * 根据id查找主题
     * @param id
     * @return
     */
    Topic findTopicById(int id);

    /**
     * 更新主题
     * @param topic
     */
    void updateTopic(Topic topic);

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
    List<Topic> findByPage(int start, int rows, Map<String, String[]> condition);
}
