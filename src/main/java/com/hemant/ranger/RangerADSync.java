package com.hemant.ranger;

import java.sql.Connection;

import com.hemant.conf.ReadProperties;
import com.hemant.jdbc.DbUtil;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RangerADSync {
	
	public static void main(String[] args) {

		// TODO Auto-generated method stub
		System.out.println("\n|--------------------------------------------------------------------------------------------------------------------|");
		System.out.println("| Tool      : RangerADSync                                                                                           |");
		System.out.println("| Version   : 1.0                                                                                                    |");
		System.out.println("| Date      : 08/11/2016                                                                                             |");		
		System.out.println("|--------------------------------------------------------------------------------------------------------------------|");
		System.out.println("|                                                                                                                    |");
		System.out.println("| Usages : java -jar RangerADSync.jar  usersFile groupsFile                                                          |");
		System.out.println("|                                                                                                                    |");
		System.out.println("|--------------------------------------------------------------------------------------------------------------------|\n");

		if(args.length == 0 || args.length > 2) {
			System.out.println(" User and Groups not specified in required format.\n   Exiting sync process...!");
			System.exit(-1);
		}
		String users_File = args[0].toString().trim();

		BufferedReader users_buf = null;
		BufferedReader groups_buf = null;

		InputStream  users = null;
		InputStream  groups = null;

		try {	    
	    	  users = new FileInputStream(users_File);
	    	  users_buf = new BufferedReader(new InputStreamReader(users));
		}
		catch (Exception e){}
		
		String group_File = null;
		if(args.length == 2){
			group_File = args[1].toString().trim();
			try {
		    	  groups = new FileInputStream(group_File);
		    	  groups_buf = new BufferedReader(new InputStreamReader(groups));
			} catch (Exception e){}
		}
		

		Connection connection = null;
		ReadProperties conf = new ReadProperties();
		
	 	DbUtil db_obj = new DbUtil();
		connection = db_obj.getConnection(conf);
		if(connection == null){
			System.out.println(" Unknown error while establishing connection to Ranger DB");
			System.exit(-1);
		}
	
		ADVerification_Ranger adRanger = new ADVerification_Ranger();
		adRanger.createADConn(conf);
		// Users Section
		try {
			String line = users_buf.readLine();
			while(line != null){ 
				String user = line.replace(" ", "").trim();
				if (user.length() > 0){
					if (adRanger.SearchUser(user)){
						System.out.print(" User      found in SEARCH_BASE      : " + user);
						db_obj.addUser(connection, user);
					}
					else{
						System.out.println(" User not  found in SEARCH_BASE      : " + user);
					}
				}
				line = users_buf.readLine(); 				
			}
			users_buf.close();
		} catch (Exception e){
			System.out.println(" Error while processing users file : " + e);
			e.printStackTrace();
		}

		//Groups Section
		if(group_File !=null){
			try {
				String line = groups_buf.readLine();
				while(line != null){ 
					String group = line.replace(" ", "").trim();
					if (group.length() > 0){
						if (adRanger.SearchGroup(group)){
							System.out.print(" Group     found in SEARCH_BASE      : " + group);
							db_obj.addGroup(connection, group);
							//adRanger.syncMembersofGroup(group);
						}
						else{
							System.out.println(" Group not found in SEARCH_BASE      : " + group);
						}
					}
					line = groups_buf.readLine(); 				
				}
				groups_buf.close();
			} catch (Exception e){
				System.out.println(" Error while processing groups file : " + e);
			}
		}
		
		try {
			adRanger.closeADConn();
		}catch(Exception e){}
		
		try {
			db_obj.closeConnection(connection);
		}catch(Exception e){}
	System.out.println("");
	}
}


