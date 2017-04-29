package connect.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.xatu.dbconnect.OracleDbManager;

public class ConnectOracle {
	private static PreparedStatement ps = null;
	private static Connection ct = null;
	private static ResultSet rs = null;
	
	public static void testConnectOracle(){
		String sql = "select * from student";
		ct = OracleDbManager.getConnection();
		
		try{
			ps = ct.prepareStatement(sql);
			rs = ps.executeQuery();
			
			String id = null;
			String name = null;
			while(rs.next()){
				id = rs.getString("ID");
				name = rs.getString("STU_NAME");
				System.out.println(id + "\t" + name);
			}
			
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}finally{
			OracleDbManager.close(ct, ps, rs);
		}
	}
	
	public static void main(String []args){
		testConnectOracle();
	}
}