package com.xatu.dbconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public class MysqlDbManager {

	/**
	 * �Զ������ݿ����ӷ���
	 * @return
	 */
	public static Connection getConnection(){
		
		try {
			Class.forName(Driver.class.getName());//��������
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		Connection conn = null;
		String url = "jdbc:mysql://127.0.0.1:3306/data_migration?useSSL=true";
		
		try {
			conn = DriverManager.getConnection(url,"root","raymchen");//��������
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
		
	}
	/**
	 * ��Բ�Ĺر�
	 * @param conn
	 * @param ps
	 * @param rs
	 */
	public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {

		try {
			rs.close();//�ر�rs
			ps.close();//�ر�ps
			conn.close();//�ر�conn
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	/**
	 * �����ɾ�ĵĹر�
	 * @param conn
	 * @param ps
	 */
	public static void close(Connection conn, PreparedStatement ps) {

		try {
			ps.close();//�ر�ps
			conn.close();//�ر�conn
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}