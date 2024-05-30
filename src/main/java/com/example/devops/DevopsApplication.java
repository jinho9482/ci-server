package com.example.devops;

import com.example.devops.controller.BoardController;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.Annotation;


@SpringBootApplication
public class DevopsApplication {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DevopsApplication.class, args);
		BoardController controller = context.getBean(BoardController.class);
		System.out.println(controller);
	}

}
