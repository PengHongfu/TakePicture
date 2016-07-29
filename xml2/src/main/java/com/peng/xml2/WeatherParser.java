package com.peng.xml2;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Peng on 2016/7/26.
 * 服务器以流的形式把数据返回
 */
public class WeatherParser {
    public static List<Channel> parserXml(InputStream inputStream) throws Exception {
        //申明集合对象
        List<Channel> weatherLists = null;
        Channel channel = null;

        //回去Xmlpullparser 解析的实例

        XmlPullParser parser = Xml.newPullParser();

        //设置XmlPullParser 的参数
        parser.setInput(inputStream,"utf-8");

        //获取事件类型
        int type = parser.getEventType();
        while (type != XmlPullParser.END_DOCUMENT) {

            switch (type) {
                case XmlPullParser.START_TAG://解析开始标签
                    //具体判断一下,解析到那个节点
                    if ("weather".equals(parser.getName())) {
                        //创建一个集合对象
                        weatherLists = new ArrayList<Channel>();

                    } else if ("channel".equals(parser.getName())) {
                        //创建Channel对象
                        channel = new Channel();
                        //获取id值
                        String id = parser.getAttributeValue(0);
                        channel.setId(id);

                    } else if ("city".equals(parser.getName())) {
                        //创建Channel对象
                        //获取id值
                        String city = parser.nextText();
                        channel.setCity(city);

                    }
                    else if ("temp".equals(parser.getName())) {
                        //创建Channel对象
                        //获取id值
                        String temp = parser.nextText();
                        channel.setTemp(temp);

                    }
                    else if ("wind".equals(parser.getName())) {
                        //创建Channel对象
                        //获取id值
                        String wind = parser.nextText();
                        channel.setWind(wind);

                    }else if ("pm250".equals(parser.getName())) {
                        //创建Channel对象
                        //获取id值
                        String pm250 = parser.nextText();
                        channel.setPm250(pm250);
                    }

                    break;
                case XmlPullParser.END_TAG://解析结束标签
                    //判断结束标签
                    if("channel".equals(parser.getName())){
                        //把javabean对象存到集合中
                        weatherLists.add(channel);
                    }
                    break;
            }
            //不停向下解析
            type = parser.next();
        }
        return weatherLists;
    }
}
