package com.hemant.conf;

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

	 String  RANGER_DB_HOST;
	 String  RANGER_DB_PORT;
	 String  RANGER_DB_CLASS;
	 String  RANGER_DB_NAME;
	 String  RANGER_DB_USER;
	 String  RANGER_DB_PWD;
	 
	 
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
	          this.setRANGER_DB_HOST();
	          this.setRANGER_DB_PORT();
	          this.setRANGER_DB_CLASS();
	          this.setRANGER_DB_NAME();
	          this.setRANGER_DB_USER();
	          this.setRANGER_DB_PWD();
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
		INITIAL_CONTEXT_FACTORY = configProp.getProperty("initial_context_factory", "com.sun.jndi.ldap.ldapctxfactory").toString().trim();
	}

	public String getPROVIDER_URL() {
		return PROVIDER_URL;
	}

	public void setPROVIDER_URL() {
		PROVIDER_URL = configProp.getProperty("provider_url", "ldap://localhost:10389").toString().trim();
	}

	public String getSECURITY_AUTHENTICATION() {
		return SECURITY_AUTHENTICATION;
	}

	public void setSECURITY_AUTHENTICATION() {
		SECURITY_AUTHENTICATION = configProp.getProperty("security_authentication", "simple").toString().trim();
	}

	public String getSECURITY_PRINCIPAL() {
		return SECURITY_PRINCIPAL;
	}

	public void setSECURITY_PRINCIPAL() {
		SECURITY_PRINCIPAL = configProp.getProperty("security_principal", "uid=admin,ou=system").toString().trim();
	}

	public String getSECURITY_CREDENTIALS() {
		return SECURITY_CREDENTIALS;
	}

	public void setSECURITY_CREDENTIALS() {
		SECURITY_CREDENTIALS = configProp.getProperty("security_credentials", "secret").toString().trim();
	}

	public String getSECURITY_PROTOCOL() {
		return SECURITY_PROTOCOL;
	}

	public void setSECURITY_PROTOCOL() {
		SECURITY_PROTOCOL = configProp.getProperty("security_protocol", "simple").toString().trim();
	}

	public String getSEARCH_BASE() {
		return SEARCH_BASE;
	}

	public void setSEARCH_BASE() {
		SEARCH_BASE = configProp.getProperty("search_base", "dc=corpdir,dc=com").toString().trim();
	}

	
	public String getRANGER_DB_HOST() {
		return RANGER_DB_HOST;
	}

	public void setRANGER_DB_HOST() {
		RANGER_DB_HOST = configProp.getProperty("ranger_db_host", "").toString().trim();
	}

	public String getRANGER_DB_PORT() {
		return RANGER_DB_PORT;
	}

	public void setRANGER_DB_PORT() {
		RANGER_DB_PORT = configProp.getProperty("ranger_db_port", "").toString().trim();
	}

	public String getRANGER_DB_CLASS() {
		return RANGER_DB_CLASS;
	}

	public void setRANGER_DB_CLASS() {
		RANGER_DB_CLASS = configProp.getProperty("ranger_db_class", "").toString().trim();
	}

	public String getRANGER_DB_NAME() {
		return RANGER_DB_NAME;
	}

	public void setRANGER_DB_NAME() {
		RANGER_DB_NAME = configProp.getProperty("ranger_db_name", "").toString().trim();
	}

	public String getRANGER_DB_USER() {
		return RANGER_DB_USER;
	}

	public void setRANGER_DB_USER() {
		RANGER_DB_USER = configProp.getProperty("ranger_db_user", "").toString().trim();
	}

	public String getRANGER_DB_PWD() {
		return RANGER_DB_PWD;
	}

	public void setRANGER_DB_PWD() {
		RANGER_DB_PWD = configProp.getProperty("ranger_db_pwd", "").toString().trim();
	}

	
	
	@Override
	public String toString() {
		return "ReadProperties [\n INITIAL_CONTEXT_FACTORY=" + INITIAL_CONTEXT_FACTORY + ",\n PROVIDER_URL=" + PROVIDER_URL
				+ ",\n SECURITY_AUTHENTICATION=" + SECURITY_AUTHENTICATION + ",\n SECURITY_PRINCIPAL=" + SECURITY_PRINCIPAL
				+ ",\n SECURITY_CREDENTIALS=" + SECURITY_CREDENTIALS + ",\n SECURITY_PROTOCOL=" + SECURITY_PROTOCOL
				+ ",\n SEARCH_BASE=" + SEARCH_BASE + ",\n RANGER_DB_HOST=" + RANGER_DB_HOST + ",\n RANGER_DB_PORT="
				+ RANGER_DB_PORT + ",\n RANGER_DB_CLASS=" + RANGER_DB_CLASS + ",\n RANGER_DB_NAME=" + RANGER_DB_NAME
				+ ",\n RANGER_DB_USER=" + RANGER_DB_USER + ",\n RANGER_DB_PWD=" + RANGER_DB_PWD + "\n ]";
	}

 

}
