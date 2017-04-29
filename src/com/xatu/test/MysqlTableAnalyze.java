package com.xatu.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.xatu.dbconnect.MysqlDbManager;

public class MysqlTableAnalyze {
	
	private static PreparedStatement ps = null;
	private static Connection ct = null;
	private static ResultSet rs = null;

	public static void readMysqlTable(){
//		String sql = "select column_name,column_type,column_key,column_comment from information_schema.COLUMNS"
//				+ " where table_name = 'student' and table_schema = 'data_migration'";
		String sql = "show columns from student";
		try{
			ct = MysqlDbManager.getConnection();
			ps = ct.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				System.out.println(rs.getString(1) +" "+ rs.getString(2)+ " " + rs.getShort(3) + " " + rs.getString(4));
			}
			
		} catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	MysqlDbManager.close(ct,ps, rs);
        }
	}
	
	public static void main(String []args){
		readMysqlTable();
	}
}
