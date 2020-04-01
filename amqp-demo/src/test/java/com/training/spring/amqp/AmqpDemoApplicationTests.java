package com.training.spring.amqp;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
class AmqpDemoApplicationTests {

	@Autowired
	private RabbitTemplate rabbitTemplate;


	@Test
	public void test() {
		Map<String, Object> map = new HashMap<>();
		map.put("msg", "this is the first test msg");
		map.put("data", Arrays.asList("data1","data2","data3"));
		rabbitTemplate.convertAndSend("exchange.test.direct", "demo.test.news", map);
	}
	@Test
	void contextLoads() {
	}

}
