package com.didispace.domain.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="com.didispace.blog",locations={"classpath:application-dev.properties","classpath:application-prod.properties"})
public class TestProperties {
//	@Value("${com.didispace.blog.name}")	
	private String name;
//	@Value("${com.didispace.blog.name}")
	private String name1;
	@Value("${com.dd}")
	private long l;
	@Value("${com.didispace.blog.title}")
	private String title;
	@Profile("dev")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Profile("prod")
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getL() {
		return l;
	}
	public void setL(long l) {
		this.l = l;
	}


}
