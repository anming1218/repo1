package com.org.service.topic.impl;
import com.org.dao.topic.TopicDao;
import com.org.dao.topic.impl.TopicDaoImpl;
import com.org.domain.PageBean;
import com.org.domain.Topic;
import com.org.service.topic.TopicService;

import java.util.List;
import java.util.Map;

/**
 *@program: NewsManager
 *@description:
 *@author: ming
 *@create: 2020-05-23 15:28
 */
public class TopicServiceImpl implements TopicService {
    private TopicDao dao = new TopicDaoImpl();


    /**
     * 查询所有主题信息
     * @return
     */
    @Override
    public List<Topic> findAll() {
        return dao.findAll();
    }

    /**
     * 添加主题
     * @param topic
     * @return
     */
    @Override
    public boolean add(Topic topic) {
        return dao.add(topic);
    }

    /**
     * 删除主题
     * @param id
     */
    @Override
    public void deleteTopic(String id) {
        dao.deleteTopic(Integer.parseInt(id));
    }

    /**
     * 根据id查找主题
     * @param id
     * @return
     */
    @Override
    public Topic findTopicById(String id) {
        return dao.findTopicById(Integer.parseInt(id));
    }

    /**
     * 修改主题
     */
    @Override
    public void updateTopic(Topic topic) {
        dao.updateTopic(topic);
    }

    /**
     * 删除选中主题
     * @param ids
     */
    @Override
    public void delSelectedTopic(String[] ids) {
        if (ids != null && ids.length > 0) {
            //遍历数组
            for (String id : ids) {
                //调用dao删除
                dao.deleteTopic(Integer.parseInt(id));
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
    public PageBean<Topic> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        if(currentPage <=0) {
            currentPage = 1;
        }
        //1.创建空的PageBean对象
        PageBean<Topic> pb = new PageBean<Topic>();
        //2.设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        //3.调用dao查询总记录数
        int totalCount = dao.findTotalCount(condition);
        pb.setTotalCount(totalCount);
        //4.调用dao查询List集合
        //计算开始的记录索引
        int start = (currentPage - 1) * rows;
        List<Topic> list = dao.findByPage(start,rows,condition);
        pb.setList(list);

        //5.计算总页码
        int totalPage = (totalCount % rows)  == 0 ? totalCount/rows : (totalCount/rows) + 1;
        pb.setTotalPage(totalPage);


        return pb;
    }
}
