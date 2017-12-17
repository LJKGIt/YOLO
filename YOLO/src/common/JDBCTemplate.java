package common;

import java.io.FileReader;
import java.sql.*;
import java.util.*;

public class JDBCTemplate {

	
	
	public static Connection getConnection(){
		Connection con=null;
		Properties prop=new Properties();
		
		try {
			String path=JDBCTemplate.class.getResource("./").getPath();
			prop.load(new FileReader(path+"driver.properties"));
			Class.forName(prop.getProperty("driver"));
			con=DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("id"),
					prop.getProperty("pwd"));
			
			
			con.setAutoCommit(false);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return con;
	}
	
	public static void commit(Connection con){
		
		try {
			if(!con.isClosed()&& con!=null){
				con.commit();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection con){
		try {
			if(!con.isClosed() && con!=null){
				con.rollback();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void close(Connection con){
		try {
			if(!con.isClosed() && con!=null){
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt){
		try {
			if(!stmt.isClosed() && stmt!=null){
				stmt.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement pstmt){
		try {
			if(!pstmt.isClosed() && pstmt!=null){
				pstmt.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rset){
		try {
			if(!rset.isClosed() && rset!=null){
				rset.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
