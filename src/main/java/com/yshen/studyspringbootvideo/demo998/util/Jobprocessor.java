package com.yshen.studyspringbootvideo.demo998.util;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;

/**
 * @author 郑悦恺
 * @Classname Jobprocessor
 * @Description TODO
 * @Date 2020/3/14 21:02
 */

public class Jobprocessor implements PageProcessor {
    //    解析页面
    @Override
    public void process(Page page) {

//        page.putField("div", page.getHtml().css("a.title").all());
//xpath
//        page.putField("排行榜",page.getHtml().xpath("//a[@class ='title']/text()"));

        page.addTargetRequests(page.getHtml().css("a.title").links().all());
//        page.putField("title",page.getHtml().css("title").toString());
//        page.addTargetRequests("");

    }
    //.regex(".*>${1}<.*",1)
    private Site site = Site.me()
            .setCharset("UTF-8")//编码
            .setSleepTime(1)//抓取间隔时间
            .setTimeOut(1000*10)//超时时间
            .setRetrySleepTime(3000)//重试时间
            .setRetryTimes(3)
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3741.400 QQBrowser/10.5.3863.400");//重试次数

    @Override
    public Site getSite() {
        return site;

    }

    public static void main(String[] args) {

        /*方法一 */
//        System.setProperty("selenuim_config","D:\\driver\\config.ini");
        Spider spider = Spider.create(new Jobprocessor())
                .addUrl("https://www.bilibili.com/ranking")
//                .setDownloader(new SeleniumDownloader("D:\\driver\\chromedriver.exe"))
                .thread(5)
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(1000000000)));
        //设置布隆去重过滤器，指定最多1000万条数据进行去重操作
//                .addPipeline(new FilePipeline("D:\\resources"))
//                .run();
        //网址去重
        //默认使用hashSet
//        Scheduler scheduler = spider.getScheduler();

        spider.run();


    }
}
