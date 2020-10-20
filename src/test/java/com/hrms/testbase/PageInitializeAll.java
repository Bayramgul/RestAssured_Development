package com.hrms.testbase;

import com.hrms.pages.AddEmployeePageElements;
import com.hrms.pages.DashboardPageElement;
import com.hrms.pages.LeaveListPageElements;
import com.hrms.pages.LoginPageElements;
import com.hrms.pages.PersonaDetailsPageElements;
import com.hrms.pages.ReportsPageElements;

public class PageInitializeAll extends BaseClass {
	protected static DashboardPageElement dash;
	protected static LoginPageElements login;
	protected static AddEmployeePageElements add;
	protected static LeaveListPageElements leave;
	protected static PersonaDetailsPageElements person;
	protected static ReportsPageElements report;

	public static void initializeAll() {
		login = LoginPageElements.getInstance();
		dash = DashboardPageElement.getInstance();
		add = AddEmployeePageElements.getInstance();
		leave = LeaveListPageElements.getInstance();
		person = PersonaDetailsPageElements.getInstance();
		report= ReportsPageElements.getInstance();

	}
}
