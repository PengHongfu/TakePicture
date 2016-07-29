package com.peng.news_client;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Peng on 2016/7/29.
 */
public class XmlParaseUtils {
    //解析的业务方法

    public  static List<News> parseXml(InputStream inputStream) throws Exception{
        List<News> newslists =null;
        News news = null;

        //获取xml的解析器
        XmlPullParser parser = Xml.newPullParser();
        //设置解析器要解析的内容
        parser.setInput(inputStream,"utf-8");
        //获取解析的事件类型
        int type = parser.getEventType();
        //不停的向下解析
        while (type!=XmlPullParser.END_DOCUMENT){
            //具体判断一下解析的是开始节点还是结束节点
            switch (type){
                case XmlPullParser.START_TAG://解析开始节点
                    //具体判断从那个开始标签
                    if("channel".equals(parser.getName())){
                        newslists = new ArrayList<News>();
                    }else if("item".equals(parser.getName())){
                        news = new News();
                    }else if("title".equals(parser.getName())){
                        news.setTitle(parser.nextText());
                    }else if("description".equals(parser.getName())){
                        news.setDescription(parser.nextText());
                    }else if("image".equals(parser.getName())){
                        news.setImage(parser.nextText());
                    }else if("type".equals(parser.getName())){
                        news.setType(parser.nextText());
                    }else if("comment".equals(parser.getName())){
                        news.setComment(parser.nextText());
                    }

                    break;
                case XmlPullParser.END_TAG:
                    if("item".equals(parser.getName())){
                        //把javabean添加到集合
                        newslists.add(news);
                    }

                    break;
            }
            //不停的向下解析
            type = parser.next();
        }
        return newslists;
    }

}
