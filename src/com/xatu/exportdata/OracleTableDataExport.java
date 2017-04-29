package com.xatu.exportdata;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.xatu.dbconnect.*;

public class OracleTableDataExport {

	private static PreparedStatement ps = null;
	private static Connection ct = null;
	private static ResultSet rs = null;
	
	public static void exportOracleData(){
		String sql;
  
        try {
        	sql = "select * from student";
            ct = OracleDbManager.getConnection();
       
            ps = ct.prepareStatement(sql);
            
            rs = ps.executeQuery();
             
            FileOutputStream fos = new FileOutputStream("E:\\oracle.txt",true);
            //fos.write(rs.getInt(1));
            PrintStream p = new PrintStream(fos);
            while (rs.next()) {
                System.out.println(rs.getString("Id") +","+rs.getString("STU_NAME") +"," +rs.getString("AGE") +","+rs.getString("ADDRESS"));// 入如果返回的是int类型可以用getInt()
             
                String sb = rs.getString("ID") +","+rs.getString("STU_NAME") +"," +rs.getString("AGE") +","+rs.getString("ADDRESS");
                //FileOutputStream fos = new FileOutputStream("E:\\ok.txt",true);
                //fos.write(rs.getInt(1));
                //PrintStream p = new PrintStream(fos);
                p.println(sb);
            }
          
            p.close();
            fos.flush();
             
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	OracleDbManager.close(ct, ps, rs);
        }
	}
	
	public static void main(String[] args) {
        exportOracleData();
    }
}