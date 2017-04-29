package com.xatu.importdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.xatu.dbconnect.MysqlDbManager;

/**
 * 将oracle数据导入mysql
 * @author raymchen
 *
 */

public class Oracle2Mysql {
	private static PreparedStatement ps = null;
	private static Connection ct = null;
//	private static ResultSet rs = null;
	
public static void doit(){
		
        File file = new File("E:\\student.txt");  
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
                
                String sql = "insert into student values('" + Integer.parseInt(str[0]) + "', '" + str[1] + "', '"+ Integer.parseInt(str[2])+"', '"+Integer.parseInt(str[3])+"', '"+str[4]+"')";
                ct = MysqlDbManager.getConnection();
                ps = ct.prepareStatement(sql);
                ps.execute(); //mysql insert 
            }
            in.close();
        }catch(IOException e){
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	MysqlDbManager.close(ct, ps);
        }
        
    }
    public static void main(String[] args) {
        doit();
    }
	
	
}
