package com.hrms.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class JDBCRecap {

	String dbUsername = "syntax_hrm";
	String dbPassword = "syntaxhrm123";
	String dbUrl = "jdbc:mysql://18.232.148.34:3306/syntaxhrm_mysql";

	@Test
	public void review() {
		try {
			Connection c = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
			Statement st = c.createStatement();
			String sqlQuery = "select e.emp_firstname, e.emp_lastname , l.name as language "
					+ " from hs_hr_employees e join hs_hr_emp_language el " + " on e.emp_number = el.emp_number "
					+ " join ohrm_language l on el.lang_id = l.id";

			ResultSet rs = st.executeQuery(sqlQuery);
			ResultSetMetaData rsData = rs.getMetaData();
			int cols = rsData.getColumnCount();

			// lets store result inside the List of Map

			List<Map<String, String>> list = new ArrayList<>();

			while (rs.next()) {

				Map<String, String> map = new LinkedHashMap<>();

				for (int i = 1; i <= cols; i++) {
					map.put(rsData.getColumnName(i), rs.getObject(i).toString());
				}
				
				list.add(map);
			}
			
			System.out.println(list);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
