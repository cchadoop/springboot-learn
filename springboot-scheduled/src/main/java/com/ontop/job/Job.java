package com.ontop.job;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Job {
	// 占位符方式
	// @Value("${jdbc.url}")
	// private String url;
	// //SpEL表达方式，其中代表xml配置文件中的id值configProperties
	// @Value("#{configProperties['jdbc.username']}")
	
	/*
	 * cron:计划任务
	 * 七个值
	 * 秒 分钟 小时 日 月 星期 年
	 * 
	 * 
	 */

	@Value("${schedule.cron}")
	private String cron;  //  0/1 * * * * ?

	@Scheduled(cron = "${schedule.cron}")
	public void print() {
		System.out.println(cron);
		System.out.println("执行任务的时间为：" + new Date());
	}
}
