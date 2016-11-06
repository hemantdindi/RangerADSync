package com.hemant.jdbc;
import com.hemant.conf.ReadProperties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
                System.out.println("Successfully conneted to Ranger DB");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Class Error while establishing Ranger DB Connection");
                System.exit(-1);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("SQL Error while establishing Ranger DB Connection");
                System.exit(-1);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error while establishing Ranger DB Connection");
                System.exit(-1);
            } 
            return connection;        
    }
    
    public void closeConnection(Connection connection){
    	try{
    		connection.close();
    	} catch (Exception e){
    		e.printStackTrace();
            System.out.println("Error while closing Ranger DB Connection");
            System.exit(-1);
    	}
    }
}
