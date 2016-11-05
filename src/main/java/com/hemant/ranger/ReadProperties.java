package com.hemant.ranger;

import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {
	
	 private final Properties configProp = new Properties();
	 
	 String INITIAL_CONTEXT_FACTORY;
	 String PROVIDER_URL;				
	 String SECURITY_AUTHENTICATION;	
	 String SECURITY_PRINCIPAL;		
	 String SECURITY_CREDENTIALS;		
	 String SECURITY_PROTOCOL;
	 String SEARCH_BASE;
	 
	   public ReadProperties()
	   {
	      //Private constructor to restrict new instances
	      InputStream in = this.getClass().getClassLoader().getResourceAsStream("app.properties");
	      try {
	          configProp.load(in);
	          this.setINITIAL_CONTEXT_FACTORY();
	          this.setPROVIDER_URL();
	          this.setSEARCH_BASE();
	          this.setSECURITY_AUTHENTICATION();
	          this.setSECURITY_CREDENTIALS();
	          this.setSECURITY_PRINCIPAL();
	          this.setSECURITY_PROTOCOL();
	      } catch (Exception e) {
	    	  System.out.println("Please check the Properties...!");
	          e.printStackTrace();
	          System.exit(-1);
	      }
	   }

	public String getINITIAL_CONTEXT_FACTORY() {
		return INITIAL_CONTEXT_FACTORY;
	}

	public void setINITIAL_CONTEXT_FACTORY() {
		INITIAL_CONTEXT_FACTORY = configProp.getProperty("initial_context_factory", "com.sun.jndi.ldap.ldapctxfactory");
	}

	public String getPROVIDER_URL() {
		return PROVIDER_URL;
	}

	public void setPROVIDER_URL() {
		PROVIDER_URL = configProp.getProperty("provider_url", "ldap://localhost:10389");
	}

	public String getSECURITY_AUTHENTICATION() {
		return SECURITY_AUTHENTICATION;
	}

	public void setSECURITY_AUTHENTICATION() {
		SECURITY_AUTHENTICATION = configProp.getProperty("security_authentication", "simple");
	}

	public String getSECURITY_PRINCIPAL() {
		return SECURITY_PRINCIPAL;
	}

	public void setSECURITY_PRINCIPAL() {
		SECURITY_PRINCIPAL = configProp.getProperty("security_principal", "uid=admin,ou=system");
	}

	public String getSECURITY_CREDENTIALS() {
		return SECURITY_CREDENTIALS;
	}

	public void setSECURITY_CREDENTIALS() {
		SECURITY_CREDENTIALS = configProp.getProperty("security_credentials", "secret");
	}

	public String getSECURITY_PROTOCOL() {
		return SECURITY_PROTOCOL;
	}

	public void setSECURITY_PROTOCOL() {
		SECURITY_PROTOCOL = configProp.getProperty("security_protocol", "simple");
	}

	public String getSEARCH_BASE() {
		return SEARCH_BASE;
	}

	public void setSEARCH_BASE() {
		SEARCH_BASE = configProp.getProperty("search_base", "dc=corpdir,dc=com");
	}

	@Override
	public String toString() {
		return "ReadProperties [\n INITIAL_CONTEXT_FACTORY=" + INITIAL_CONTEXT_FACTORY + ",\n PROVIDER_URL=" + PROVIDER_URL
				+ ",\n SECURITY_AUTHENTICATION=" + SECURITY_AUTHENTICATION + ",\n SECURITY_PRINCIPAL=" + SECURITY_PRINCIPAL
				+ ",\n SECURITY_CREDENTIALS=" + SECURITY_CREDENTIALS + ",\n SECURITY_PROTOCOL=" + SECURITY_PROTOCOL
				+ ",\n SEARCH_BASE=" + SEARCH_BASE + "\n]";
	}
	
	   

}
