package com.housee.app.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.housee.app.dao.CommonDaoImpl;
import com.housee.app.model.Admin;
import com.housee.app.service.AdminServiceImpl;

public class AdminTest {

	
	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/com/housee/app/test/testApplicationContext.xml");
		AdminServiceImpl adminService = (AdminServiceImpl)applicationContext.getBean("adminService");
		Admin admin = new Admin();
		admin.setUsername("admin");
		admin.setPassword("password");
		adminService.saveAdmin(admin);
	}
}
