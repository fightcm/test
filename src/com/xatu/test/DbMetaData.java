package com.xatu.test;

//import com.xatu.dbconnect.MysqlDbManager;
import com.xatu.dbconnect.OracleDbManager;

import java.sql.Connection;
//import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * oracle ±Ì«®“∆≤‚ ‘
 * @author raymchen
 *
 */

public class DbMetaData {
//	private static PreparedStatement mps = null;
//	private static Connection mct = null;
//	private static ResultSet mrs = null;
	
	private static PreparedStatement ps = null;
	private static Connection ct = null;
	private static ResultSet rs = null;
	
	public static ResultSet test(){
		
		String sql = "select  COLUMN_NAME,COLUMN_TYPE, COLUMN_LENGTH,NULLABLE,data_default "
				+ "from  user_tab_cols  where table_name=upper('student')";
		
		try{
			ct = OracleDbManager.getConnection();
//			mct = MysqlDbManager.getConnection();
			
			ps = ct.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				System.out.println(rs.getString(1) +" "+ rs.getString(2)+ " " + rs.getShort(3) + " " + rs.getString(4) + " " + rs.getString(5));
			}
			
			//productMysql(rs, "student");
			
		}catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	OracleDbManager.close(ct, ps, rs);
        }
		return rs;
		
	}
	
	public static void productMysql(ResultSet rs, String tableName){
		StringBuffer mysql = new StringBuffer();
		mysql.append("CREATE TABLE ").append(tableName.toUpperCase()).append("(");
		try {
			while(rs.next()){
				//System.out.println(rs.getString(1) +" "+ rs.getString(2)+ " " + rs.getShort(3) + " " + rs.getString(4) + " " + rs.getString(5));
				mysql.append(rs.getString(1)+" "+rs.getString(2) + "(" + rs.getString(3) + ") " );
				if(rs.getString(4).equals("N")){
					mysql.append("NOT NULL ");
				}
				mysql.append("DEFAULT " + rs.getString(5) + ",");
			}
			mysql.append(")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(mysql.toString());
	}
	
	public static void read(){
		
		String sql = "select  COLUMN_NAME,DATA_TYPE,DATA_LENGTH,NULLABLE,data_default "
				+ "from  user_tab_cols  where table_name=upper('student')";
		
		try{
			ct = OracleDbManager.getConnection();
//			mct = MysqlDbManager.getConnection();
			
			ps = ct.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				System.out.println(rs.getString(1) +" "+ rs.getString(2)+ " " + rs.getShort(3) + " " + rs.getString(4) + " " + rs.getString(5));
			}
			
			
		}catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
        	
            e.printStackTrace();
        } finally {
        	OracleDbManager.close(ct, ps, rs);
        }
		
	}
	
	public static void main(String []args){
		read();
	}
}
