package connect.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.xatu.dbconnect.MysqlDbManager;

public class ConnectMysql {
	
	private static Connection conn= null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	
	public static void testConnectMysql(){
		String sql = "select * from city";
		conn = MysqlDbManager.getConnection();
		try{
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			String name = null;
            String id = null;
            int count = 0;
            while(rs.next()){
               //获取ID这列数据
               id = rs.getString("ID");
               //获取Name这列数据
               name = rs.getString("Name");

               //输出结果
               System.out.println(id + "\t" + name);
               count += 1;
               if(count > 5){
            	   break;
               }
           }
			
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}finally{
			MysqlDbManager.close(conn, ps, rs);
		}
	}
	
	public static void main(String []args){
		testConnectMysql();
	}
	

}
