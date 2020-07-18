package com.org.dao.news.impl;
import com.org.dao.news.NewsDao;
import com.org.domain.News;
import com.org.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

/**
 *@program: NewsManager
 *@description:
 *@author: ming
 *@create: 2020-05-23 15:31
 */
public class NewsDaoImpl implements NewsDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 查找全部新闻
     * @return
     */
    @Override
    public List<News> findAll() {

        //使用JDBC进行数据库操纵
        //1.定义sql
        String sql = "select * from news";
        List<News> news = template.query(sql, new BeanPropertyRowMapper<News>(News.class));

        return news;
    }

    /**
     * 根据id删除新闻
     * @param id
     */
    @Override
    public void deleteNews(int id) {
        //定义sql
        String sql = "delete from news where id=?";

        //运行sql
        template.update(sql, id);
    }

    /**
     * 根据id查找新闻
     * @param id
     * @return
     */
    @Override
    public News findNewsById(int id) {
        //定义sql
        String sql = "select * from news where id = ?";
        //执行sql
        return template.queryForObject(sql, new BeanPropertyRowMapper<News>(News.class), id);
    }

    /**
     * 增加新闻
     * @param news
     * @return
     */
    @Override
    public boolean add(News news) {
        //将修改时间和修改人定义为空
        String str = "无";
        ////使用JDBC操作数据库
        //1.定义sql
        String sql = "insert into news values(null,?,?,?,?,?,?,?,?,?,0)";
        //2.执行sql
        template.update(sql, news.getTopic(), news.getTitle(), news.getAuthor(), news.getSummary(), news.getContent(), news.getCreattime(), news.getCreatby(),str,str);
        return true;
    }

    /**
     *修改新闻
     */
    @Override
    public void updateNews(News news) {

        String sql = "update news set topic = ?, title = ?, author = ?, summary = ?, creattime = ?, creatby = ?, modifytime = ?, modifyby = ? where id = ?";
        template.update(sql, news.getTopic(), news.getTitle(), news.getAuthor(), news.getSummary(), news.getCreattime(), news.getCreatby(), news.getModifytime(), news.getModifyby(), news.getId());

    }

    /**
     * 查询总记录数
     * @param condition
     * @return
     */
    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        //1.定义模板初始化sql
        String sql = "select count(*) from news where 1 = 1 ";
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
    public List<News> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from news  where 1 = 1 ";

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

        return template.query(sql, new BeanPropertyRowMapper<News>(News.class), params.toArray());
    }

    /**
     * 按类别查找新闻
     * @param kinds
     * @return
     */
    @Override
    public List<News> findKindNews(String kinds) {
        //使用JDBC进行数据库操纵
        //1.定义sql
        String sql = "select id,title from news where 1=1 and topic= ? order by id desc limit 0,4";
        List<News> kindsnews = template.query(sql, new BeanPropertyRowMapper<News>(News.class), kinds);

        return kindsnews;
    }

    /**
     * 记录新闻查看次数
     * @param id
     */
    @Override
    public void readNewsFrequency(int id) {

        String sql = "update news set frequency = frequency + 1 where id = ?";

        template.update(sql, id);
    }

    /**
     * 查找实时新闻（新闻直播间）
     * @return
     */
    @Override
    public List<News> findRealTimeNews() {

        //使用JDBC进行数据库操纵
        //1.定义sql
        String sql = "select id,title from news where 1=1  order by creattime desc limit 0,15";

        List<News> realNews = template.query(sql, new BeanPropertyRowMapper<News>(News.class));

        return realNews;
    }

    /**
     * 查找实时热搜新闻，依照查看次数
     * @return
     */
    @Override
    public List<News> findHotNews() {

        //使用JDBC进行数据库操纵
        //1.定义sql
        String sql = "select id,title from news where 1=1 order by frequency desc limit 0,10";

        List<News> hotNews = template.query(sql, new BeanPropertyRowMapper<News>(News.class));

        return hotNews;
    }
}
