package com.servicedx.source.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


public class EventsSourceStreamApplicationTests {

	@Test
	public void contextLoads() {
		
//		String regex= "\\$[\\{]([^}]*)[\\}]";
//		String text = "${TITLE}";
//		System.out.println(text.replaceAll(regex, "HELLO"));
		
		String regex= "[\\{]([^}]*)[\\}]";
		String text = "{TITLE}";
		System.out.println(text.replaceAll(regex, "HELLO"));
	}

}
