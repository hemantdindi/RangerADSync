package com.hemant.ranger;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import com.hemant.conf.ReadProperties;

import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class ADVerification_Ranger
{
	DirContext ldapContext;
    //Specify the Base for the search
    String searchBase = "";
    
	public void closeADConn()
	{
		try{
			ldapContext.close();
		}catch(Exception e){}
	}
	public void createADConn(ReadProperties conf){
		try{
		      Hashtable<String, String> ldapEnv = new Hashtable<String, String>(11)									;
		      ldapEnv.put(Context.INITIAL_CONTEXT_FACTORY, conf.getINITIAL_CONTEXT_FACTORY().toString().trim())		;
		      ldapEnv.put(Context.PROVIDER_URL,  conf.getPROVIDER_URL().toString().trim())							;
		      ldapEnv.put(Context.SECURITY_AUTHENTICATION, conf.getSECURITY_AUTHENTICATION().toString().trim())		;
		      ldapEnv.put(Context.SECURITY_PRINCIPAL, conf.getSECURITY_PRINCIPAL().toString().trim())				;
		      ldapEnv.put(Context.SECURITY_CREDENTIALS, conf.getSECURITY_PRINCIPAL().toString().trim())				;
		      ldapEnv.put(Context.SECURITY_PROTOCOL, conf.getSECURITY_PROTOCOL().toString().trim())					;
		      searchBase=conf.getSEARCH_BASE().toString().trim()													;
		      
		      ldapContext = new InitialDirContext(ldapEnv);
		      System.out.println("Successfully conneted to AD");
			
		}catch (Exception e)
	    {
		      System.out.println(" Search error: " + e);
		      e.printStackTrace();
		      System.exit(-1);
		    }
		
	}
  
  public boolean SearchUser(String accountName) throws Exception
  {
	  // Create the search controls         
      SearchControls searchCtls = new SearchControls();
      //Specify the attributes to return
      String returnedAtts[]={"sn","givenName", "samAccountName"};
      searchCtls.setReturningAttributes(returnedAtts);
      //Specify the search scope
      searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
      //specify the LDAP search filter
      //String searchFilter = "(&(objectClass=group)(sAMAccountName=" + accountName + "))";      
      String searchFilter = "(&(objectClass=user)(sAMAccountName=" + accountName + "))";

      //initialize counter to total the results
      int totalResults = 0;
      // Search for objects using the filter
      NamingEnumeration<SearchResult> answer = ldapContext.search(searchBase, searchFilter, searchCtls);

      //Loop through the search results
      while (answer.hasMoreElements())
      {
        SearchResult sr = (SearchResult)answer.next();
        totalResults++;
        @SuppressWarnings("unused")
		Attributes attrs = sr.getAttributes();
        //System.out.println("Username : " + attrs.get("samAccountName"));
      }
      if (totalResults == 1)
    	  return true;
      else
    	  return false;
  }
  
  public boolean SearchGroup(String accountName) throws Exception
  {
	  // Create the search controls         
      SearchControls searchCtls = new SearchControls();
      //Specify the attributes to return
      String returnedAtts[]={"sn","givenName", "samAccountName"};
      searchCtls.setReturningAttributes(returnedAtts);
      //Specify the search scope
      searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
      //specify the LDAP search filter
      String searchFilter = "(&(objectClass=group)(sAMAccountName=" + accountName + "))";      
      //String searchFilter = "(&(objectClass=user)(sAMAccountName=" + accountName + "))";
      //Specify the Base for the search
      String searchBase = "";
      //initialize counter to total the results      
      int totalResults = 0;
      // Search for objects using the filter
      NamingEnumeration<SearchResult> answer = ldapContext.search(searchBase, searchFilter, searchCtls);

      //Loop through the search results
      while (answer.hasMoreElements())
      {
        SearchResult sr = (SearchResult)answer.next();
        totalResults++;
        @SuppressWarnings("unused")
		Attributes attrs = sr.getAttributes();
        //System.out.println("Group    : "  + attrs.get("samAccountName"));
      }
      if (totalResults == 1)
    	  return true;
      else
    	  return false;
  }
}