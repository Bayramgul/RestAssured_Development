package com.hrms.practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hrms.utils.CommonMethods;

public class TesingClass extends CommonMethods{
	private static Logger log = LoggerFactory.getLogger(TesingClass.class);
	
	public static void main(String[] args) {
		log.info("This is a log from application");
		
		testingSomething();
	}

}
