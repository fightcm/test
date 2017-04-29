package com.xatu.importdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.xatu.dbconnect.OracleDbManager;

/**
 * 导入 oracle.txt
 *  将mysql数据导入oracle
 * @author raymchen
 *
 */

public class Mysql2Oracle {
	
	private static PreparedStatement ps = null;
	private static Connection ct = null;
	private static ResultSet rs = null;
	
	public static void doit(){
		
        File file = new File("E:\\oracle.txt");  
        try{
            BufferedReader in =
                new BufferedReader(
                new FileReader(file));
            String s;
            while ( (s = in.readLine()) != null) {
            	//int count = s.split(",").length;
                String[] str = s.split(",");
                if(str == null || str.length < 2){
                    continue; //丢弃不完整的数据
                }
                System.out.println(str[0] + "    " + str[1]);
                
                String sql = "insert into student(id, stu_name, gender, age, address) values('" + Integer.parseInt(str[0]) + "', '" + str[1] + "', '"+ Integer.parseInt(str[2])+"', '"+Integer.parseInt(str[3])+"', '"+str[4]+"')";
                ct = OracleDbManager.getConnection();
                ps = ct.prepareStatement(sql);
                rs = ps.executeQuery();
            }
            in.close();
        }catch(IOException e){
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	OracleDbManager.close(ct, ps, rs);
        }
        
    }
    public static void main(String[] args) {
        doit();
    }
}
