package com.org.service.topic;
import com.org.domain.PageBean;
import com.org.domain.Topic;

import java.util.List;
import java.util.Map;

/**
 *@program: NewsManager
 *@description: 主题操作的业务接口
 *@author: ming
 *@create: 2020-05-23 15:27
 */
public interface TopicService {

    /**
     * 查询所有主题信息
     * @return
     */
    public List<Topic> findAll();

    /**
     * 添加新主题
     * @param topic
     * @return
     */
    boolean add(Topic topic);

    /**
     * 删除主题
     * @param id
     */
    void deleteTopic(String id);

    /**
     * 根据id查找主题
     * @param id
     * @return
     */
    Topic findTopicById(String id);

    /**
     * 修改主题
     */
    void updateTopic(Topic topic);

    /**
     * 删除选中主题
     * @param ids
     */
    void delSelectedTopic(String[] ids);

    /**
     * 分页条件查询
     * @param currentPage
     * @param rows
     * @param condition
     * @return
     */
    PageBean<Topic> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);
}
