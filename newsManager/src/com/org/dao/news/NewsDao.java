package com.org.dao.news;
import com.org.domain.News;

import java.util.List;
import java.util.Map;

/**
 *@program: NewsManager
 *@description: 新闻操作的DAO
 *@author: ming
 *@create: 2020-05-23 15:29
 */
public interface NewsDao {


    /**
     * 添加新闻
     * @param news
     * @return
     */
    boolean add(News news);

    /**
     * 查找全部新闻
     * @return
     */
    List<News> findAll();

    /**
     * 根据id删除新闻
     * @param id
     */
    void deleteNews(int id);

    /**
     * 根据id查找新闻
     * @param id
     * @return
     */
    News findNewsById(int id);

    /**
     * 更新新闻
     * @param news
     */
    void updateNews(News news);


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
    List<News> findByPage(int start, int rows, Map<String, String[]> condition);

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
    void readNewsFrequency(int id);

    /**
     * 查找实时新闻（新闻直播间）
     * @return
     */
    List<News> findRealTimeNews();

    /**
     * 查找实时热搜新闻，依照查看次数
     * @return
     */
    List<News> findHotNews();
}
