package com.org.service.news;
import com.org.domain.News;
import com.org.domain.PageBean;

import java.util.List;
import java.util.Map;

/**
 *@program: NewsManager
 *@description: 新闻操作的业务接口
 *@author: ming
 *@create: 2020-05-23 15:27
 */
public interface NewsService {

    /**
     * 查询所有新闻信息
     * @return
     */
    public List<News> findAll();

    /**
     * 添加新闻
     * @param news
     * @return
     */
    boolean add(News news);


    /**
     * 根据id删除新闻
     * @param id
     */
    void deleteNews(String id);

    /**
     * 根据id查找新闻
     * @param id
     * @return
     */
    News findNewsById(String id);

    /**
     * 修改新闻
     * @param news
     */
    void updateUser(News news);

    /**
     * 删除选中的新闻
     * @param ids
     */
    void delSelectedNews(String[] ids);

    /**
     * 分页条件查询
     * @param currentPage
     * @param rows
     * @param condition
     * @return
     */
    PageBean<News> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);


    /**
     * 按类别查找新闻
     * @param kinds
     * @return
     */
    List<News> findKindNews(String kinds);


    /**
     * 记录新闻查看次数
     * @param id
     */
    void readNewsFrequency(String id);

    /**
     * 查找实时新闻（新闻直播间）
     */
    List<News> findRealTimeNews();

    /**
     * 查找实时热搜新闻，依照查看次数
     * @return
     */
    List<News> findHotNews();
}
