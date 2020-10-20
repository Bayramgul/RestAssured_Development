package com.hrms.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hrms.testbase.BaseClass;

public class LeaveListPageElements {
	
	private static LeaveListPageElements leaveListInstance;
	
	private LeaveListPageElements() {
		PageFactory.initElements(BaseClass.driver, this);
	}
	
	public static LeaveListPageElements getInstance() {
		if (leaveListInstance == null)
			leaveListInstance = new LeaveListPageElements();
		return leaveListInstance;
	}
	

	@FindBy(css="a.toggle.tiptip")
	public WebElement leaveListLbl;
	

	
}
