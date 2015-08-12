package com.hand.Exam002;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Test2 {

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
			String sql = "select t1.first_name ,t2.rental_date,t3.film_id,t4.title from customer AS t1 ,rental AS t2,"
					+ " inventory AS t3 ,film AS t4 WHERE "
					+ "t1.customer_id = t2.customer_id AND t1.customer_id = "
					+ id
					+ " "
					+ "AND t2.inventory_id = t3.inventory_id AND t3.film_id = t4.film_id ORDER BY t2.rental_date desc;";
			Statement st = conn.createStatement();
			ResultSet rs = null;
			rs = st.executeQuery(sql);
			while (rs.next()) {
				System.out.println(rs.getString("t1.first_name") + "租用的Film->");
				System.out.println("FilmID" + "|" + "租用名称|租用时间");
				System.out.println(rs.getInt("t3.film_id") + "|"+rs.getString("t4.title")+"|"
						+ rs.getString("t2.rental_date"));
			}
			// System.out.println("over");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		try {
			Connection conn = Test2.getConnection();
			System.out.println(conn.getAutoCommit());
			System.out.println("请输入CustomerID：");
			Scanner scanner = new Scanner(System.in);
			int id = scanner.nextInt();
			qurey(conn, id);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
