package com.hrms.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class JDBCReview {

	@Test
	public void review() {
		
		String dbUsername = "syntax_hrm";
		String dbPassword = "syntaxhrm123";
		// jdbc:type driver:host:port/name of the database
		String dbUrl = "jdbc:mysql://18.232.148.34:3306/syntaxhrm_mysql";
		
		try {
			Connection c=DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
			Statement st=c.createStatement();
			ResultSet rs=st.executeQuery("select * from ohrm_location;");
			System.out.println(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
