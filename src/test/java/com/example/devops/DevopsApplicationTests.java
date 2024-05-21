package com.example.devops;

import com.example.devops.service.BoardService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DevopsApplicationTests {

	@Value("${spring.datasource.username}")
	String username;
	@Test
	void contextLoads() {
	}

	@Test
	void dbUserNameIsSa() {
		assertEquals("sa", username);
	}

}
