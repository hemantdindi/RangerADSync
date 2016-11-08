package com.hemant.ranger;

import java.sql.Connection;

import com.hemant.conf.ReadProperties;
import com.hemant.jdbc.DbUtil;

public class RangerADSync {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		// TODO Auto-generated method stub
		System.out.println("\n|--------------------------------------------------------------------------------------------------------------------|");
		System.out.println("| Tool      : RangerADSync                                                                                           |");
		System.out.println("| Version   : 1.0                                                                                                    |");
		System.out.println("| Date      : 08/11/2016                                                                                             |");
		System.out.println("| Author    : Hemant Dindi                                                                                           |");
		System.out.println("|--------------------------------------------------------------------------------------------------------------------|");
		System.out.println("|                                    domain1                                                                         |");
		System.out.println("| Usage : java -jar RangerADSync.jar domain2   users=[\"user1\",\"user2\"] groups=[\"group1\",\"group2\"]            |");
		System.out.println("|                                    domain3                                                                         |");
		System.out.println("|--------------------------------------------------------------------------------------------------------------------|\n");

		if(args.length == 0 || args.length > 3) {
			System.out.println(" User and Groups not specified in required format.\nExiting sync process...!");
			System.exit(-1);
		}
		String domain  = args[0].toString().trim();
					
		String userArgs = args[1].replace("[", "").replace("]", "");
		String []users = userArgs.split("=");
		if (users.length > 2 ){
			System.out.println(" Invalid User Input Format.\nExiting sync process...!");
			System.exit(-1);
		}
				 
		String checkIfGroupsProvided = null;
		String groups[] = null;
		boolean _group = true;
		try {
			 checkIfGroupsProvided = args[1];
			 
			 String groupArgs = args[2].replace("[", "").replace("]", "");
				groups = groupArgs.split("=");
				if (groups.length > 2 ){
					System.out.println(" Invalid Group Input Format.\nExiting sync process...!");
					System.exit(-1);
				}
							 
		 } catch (java.lang.ArrayIndexOutOfBoundsException e){
			 _group = false;
			 System.out.println(" No groups were Specified");
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
		 
		 try {		
			for (String user: users[1].split(",")) {
				if (adRanger.SearchUser(user.toString())){
					db_obj.addUser(connection, user);
				}
				else
				{
					System.out.println(" User not  Present in SEARCH_BASE      : " + user.toString());
				}
		      }
		
			//Verify and add groups
			if (_group){
				for (String group: groups[1].split(",")) {
					if (adRanger.SearchGroup(group.toString())){
						db_obj.addGroup(connection, group);
					}
					else
					{
						System.out.println(" Group not Present in SEARCH_BASE      : " + group.toString());
					}
			      }
			}
			adRanger.closeADConn();
			db_obj.closeConnection(connection);
		}catch(Exception e){
			System.out.println(" Please check the input format. Search error: " + e + "\n");
		      	e.printStackTrace();
		      	System.exit(-1);
		}
				

	}

}
