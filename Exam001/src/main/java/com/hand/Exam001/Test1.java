package com.hand.Exam001;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Test1 {

	public static Connection getConnection() {
		Connection conn = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/sakila", "root", "111111");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;

	}

	public static void qurey(Connection conn, int id) {

		try {
			String sql = "SELECT city_id,city,country FROM city,country WHERE country.country_id="+id;
			Statement st = conn.createStatement();
			ResultSet rs = null;
			rs = st.executeQuery(sql);
			while (rs.next()) {
				System.out.println("County"+rs.getString("country")+"的城市->");
				System.out.println("城市id"+"|"+ "城市名称");
				System.out.println(rs.getInt("city_id")+"|"+rs.getString("city"));
			}
			// System.out.println("over");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
	
		try {
			Connection conn=Test1.getConnection();
			System.out.println(conn.getAutoCommit());
			System.out.println("请输入CountryID：");
			Scanner scanner=new Scanner(System.in);
			int id=scanner.nextInt();
			qurey(conn, id);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}
}
