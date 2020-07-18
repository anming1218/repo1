package com.org.domain;
/**
 *@program: NewsManager
 *@description: 主题的实体类
 *@author: ming
 *@create: 2020-05-23 15:18
 */
public class Topic {

    private int id;
    private String topicname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getTopicname() {
        return topicname;
    }

    public void setTopicname(String topicname) {
        this.topicname = topicname;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", topicname='" + topicname + '\'' +
                '}';
    }
}
