package com.hemant.jdbc;
import com.hemant.conf.ReadProperties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


public class DbUtil {
	private Connection connection = null;
	
    public Connection getConnection(ReadProperties conf) {
            try {
                
            	String  dbhost  = conf.getRANGER_DB_HOST()	;
            	String  dbport  = conf.getRANGER_DB_PORT()	;
            	String  dbclass = conf.getRANGER_DB_CLASS()	;
            	String  dbname  = conf.getRANGER_DB_NAME()	;
            	String  dbuser  = conf.getRANGER_DB_USER()	;
            	String  dbpwd   = conf.getRANGER_DB_PWD()	;

                String  dburl   = "jdbc:postgresql://"+dbhost+":"+dbport+"/"+dbname     			;
                
                Class.forName(dbclass);
                connection = DriverManager.getConnection(dburl, dbuser, dbpwd);
                System.out.println(" Successfully conneted to Ranger DB");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println(" Class Error while establishing Ranger DB Connection");
                System.exit(-1);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(" SQL Error while establishing Ranger DB Connection");
                System.exit(-1);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(" Error while establishing Ranger DB Connection");
                System.exit(-1);
            } 
            return connection;        
    }
    

	public boolean addUser(Connection conn, String user){
    	Statement stmt 	= null;
    	ZoneOffset zoneOffset  = ZoneOffset.of("Z"); 
    	LocalDateTime localTime = LocalDateTime.now(zoneOffset);
    	
    	String query	= "insert into x_user("
			    			+ "create_time ," 	// 2016-08-31 20:57:18.372
			    			+ "update_time ," 	// 2016-08-31 20:57:18.372
			    			+ "added_by_id ," 	// 2
			    			+ "upd_by_id," 		// 2
			    			+ "user_name," 		// hedindi
			    			+ "descr ," 		// hedindi - add from RangerADSync
			    			+ "status," 		// 0
			    			+ "is_visible)" 	// 1
			    			+ "values"
			    			+ "("
			    			+ "'" + localTime + "',"
			    			+ "'" + localTime + "',"
			    			+ "2,"  
			    			+ "2,"
			    			+ "'"+ user + "',"
			    			+ "'"+ user + " - add from RangerADSync',"
			    			+ "0,"
			    			+ "1"
			    			+ ") ON CONFLICT (user_name) DO UPDATE SET update_time = " + "'" + localTime + "'";
    	try{
    		stmt = conn.createStatement();
    		stmt.executeUpdate(query);
    		System.out.println("  Sync completed for user : " + user);    		
    		stmt.close();
    	}catch (Exception e){
    		System.out.println("  Issue while adding user "   + user + ".Sync failed for this account");
    		//e.printStackTrace();
    	}    	
    	return true;
    }

	public boolean addGroup(Connection conn,String group){
    	Statement stmt 	= null;
    	ZoneOffset zoneOffset  = ZoneOffset.of("Z"); 
    	LocalDateTime localTime = LocalDateTime.now(zoneOffset);
    	String query	= "insert into x_group ("
    						+ "create_time,"
    						+ "update_time,"
    						+ "added_by_id,"
    						+ "upd_by_id,"
    						+ "group_name,"
    						+ "descr,"
    						+ "status,"
    						+ "group_type,"
    						+ "group_src,"
    						+ "is_visible)"
    						+ "values"
    						+ "("
    						+ "'" + localTime + "',"
			    			+ "'" + localTime + "',"
			    			+ "2,"  
			    			+ "2,"
			    			+ "'"+ group + "',"
			    			+ "'"+ group + " - add from RangerADSync',"
			    			+ "0,"
			    			+ "1,"
			    			+ "0,"
			    			+ "1"			    			
			    			+ ") ON CONFLICT (group_name) DO UPDATE SET update_time = " + "'" + localTime + "'";
    	try{
    		stmt = conn.createStatement();
    		stmt.executeUpdate(query);
    		System.out.println("  Sync completed for group : " + group);
    		stmt.close();
    	}catch (Exception e){
    		System.out.println("Issue while adding " + group + ".Sync failed for this account");
    	}
    	return true;
    }
    public void closeConnection(Connection connection){
    	try{
    		connection.close();
    		System.out.println(" Successfully closed the connection to Ranger DB");
    	} catch (Exception e){
    		e.printStackTrace();
            System.out.println(" Error while closing Ranger DB Connection");
            System.exit(-1);
    	}
    }
}
