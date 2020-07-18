package com.org.service.news.impl;
import com.org.dao.news.NewsDao;
import com.org.dao.news.impl.NewsDaoImpl;
import com.org.domain.News;
import com.org.domain.PageBean;
import com.org.service.news.NewsService;

import java.util.List;
import java.util.Map;

/**
 *@program: NewsManager
 *@description:
 *@author: ming
 *@create: 2020-05-23 15:28
 */
public class NewsServiceImpl implements NewsService {
    private NewsDao dao = new NewsDaoImpl();

    /**
     * 查询所有新闻信息
     * @return
     */
    @Override
    public List<News> findAll() {
        return dao.findAll();
    }

    /**
     * 添加新闻
     * @param news
     * @return
     */
    @Override
    public boolean add(News news) {
        return dao.add(news);
    }

    /**
     * 删除新闻
     * @param id
     */
    @Override
    public void deleteNews(String id) {
        dao.deleteNews(Integer.parseInt(id));
    }

    /**
     * 根据id查找新闻
     * @param id
     * @return
     */
    @Override
    public News findNewsById(String id) {
        return dao.findNewsById(Integer.parseInt(id));
    }

    /**
     * 修改新闻
     * @param news
     */
    @Override
    public void updateUser(News news) {
        dao.updateNews(news);
    }

    /**
     * 删除选中的新闻
     * @param ids
     */
    @Override
    public void delSelectedNews(String[] ids) {
        if (ids != null && ids.length > 0) {
            //遍历数组
            for (String id : ids) {
                //调用dao删除
                dao.deleteNews(Integer.parseInt(id));
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
    public PageBean<News> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        if (currentPage <= 0) {
            currentPage = 1;
        }
        //1.创建空的PageBean对象
        PageBean<News> pb = new PageBean<News>();
        //2.设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        //3.调用dao查询总记录数
        int totalCount = dao.findTotalCount(condition);
        pb.setTotalCount(totalCount);
        //4.调用dao查询List集合
        //计算开始的记录索引
        int start = (currentPage - 1) * rows;
        List<News> list = dao.findByPage(start, rows, condition);
        pb.setList(list);

        //5.计算总页码
        int totalPage = (totalCount % rows) == 0 ? totalCount / rows : (totalCount / rows) + 1;
        pb.setTotalPage(totalPage);


        return pb;

    }

    /**
     * 按类别查找新闻
     * @param kinds
     * @return
     */
    @Override
    public List<News> findKindNews(String kinds) {
        return dao.findKindNews(kinds);
    }

    /**
     * 记录新闻查看次数
     * @param id
     */
    @Override
    public void readNewsFrequency(String id) {
        dao.readNewsFrequency(Integer.parseInt(id));
    }

    /**
     * 查找实时新闻（新闻直播间）
     */
    @Override
    public List<News> findRealTimeNews() {
        return dao.findRealTimeNews();
    }

    /**
     * 查找实时热搜新闻，依照查看次数
     * @return
     */
    @Override
    public List<News> findHotNews() {
        return dao.findHotNews();
    }
}
