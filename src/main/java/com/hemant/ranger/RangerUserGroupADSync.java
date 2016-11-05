package com.hemant.ranger;

import com.hemant.ranger.ADVerification_Ranger;



public class RangerUserGroupADSync {
	
	public static void main(String[] args) {

		// TODO Auto-generated method stub
		
		/*---------------------------------------------------------------------------------------------------------------------------------*/
		ReadProperties conf = new ReadProperties();
		System.out.println(conf.toString());
		
		/*---------------------------------------------------------------------------------------------------------------------------------*/
		
		System.out.println("Format accepted : users=[\"user1\",\"user2\"] groups=[\"group1\",\"group2\"] ");
		
		if(args.length == 0 || args.length > 2) {
			System.out.println("User and Groups not specified in required format.\nExiting sync process...!");
			System.exit(-1);
		}

		String userArgs = args[0].replace("[", "").replace("]", "");
		String []users = userArgs.split("=");
		if (users.length > 2 ){
			System.out.println("Invalid Input Format.\nExiting sync process...!");
			System.exit(-1);
		}
				 
		 @SuppressWarnings("unused")
		String checkIfGroupsProvided = null;
		 String groups[] = null;
		 try {
			 checkIfGroupsProvided = args[1];
			 
			 String groupArgs = args[1].replace("[", "").replace("]", "");
				groups = groupArgs.split("=");
				if (groups.length > 2 ){
					System.out.println("Invalid Input Format.\nExiting sync process...!");
					System.exit(-1);
				}
							 
		 } catch (java.lang.ArrayIndexOutOfBoundsException e){
			 System.out.println("No groups were Specified");
		 }
		 	 
		ADVerification_Ranger adRanger = new ADVerification_Ranger();
		 
		 try {
			//adRanger.createADConn(conf);
			
			/*Verify and add users			
			for (String user: users[1].split(",")) {
				if (adRanger.SearchUser(user.toString())){
					System.out.println("User Present : " + user.toString());
				}
				else
				{
					System.out.println("User not Present : " + user.toString());
				}
		      }
			
			//Verify and add groups
			for (String group: groups[1].split(",")) {
				
				if (adRanger.SearchGroup(group.toString())){
					System.out.println("Group Present : " + group.toString());
				}
				else
				{
					System.out.println("Group not Present : " + group.toString());
				}
		      }
			*/
			adRanger.closeADConn();
			 
		}catch(Exception e){}
				

	}

}
