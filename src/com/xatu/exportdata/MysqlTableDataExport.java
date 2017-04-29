package com.xatu.exportdata;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.xatu.dbconnect.MysqlDbManager;

public class MysqlTableDataExport {

	private static PreparedStatement ps = null;
	private static Connection ct = null;
	private static ResultSet rs = null;
	
	public static void exportMysqlData(){
		String sql;
		
        try {
        	sql = "select * from student";
            ct = MysqlDbManager.getConnection();
       
            ps = ct.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            FileOutputStream fos = new FileOutputStream("E:\\student.txt",true);
            PrintStream p = new PrintStream(fos);
            
            while (rs.next()) {
                System.out.println(rs.getInt("id") +","// 入如果返回的是int类型可以用getInt()
                					+rs.getString("name") +"," 
                					+rs.getString("gender") +"," 
                					+rs.getString("age") + "," 
                					+rs.getString("address"));
             
                String sb = rs.getInt("id") +","
                			+rs.getString("name") +"," 
                			+rs.getString("gender") +"," 
                			+rs.getString("age") + "," 
                			+rs.getString("address");
                p.println(sb);
            }
          
            p.close();
            fos.flush();
             
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	MysqlDbManager.close(ct, ps, rs);
        }
	}
	
	public static void main(String []args){
		exportMysqlData();
	}
}
