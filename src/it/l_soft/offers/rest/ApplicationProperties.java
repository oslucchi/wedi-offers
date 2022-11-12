package it.l_soft.offers.rest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

public class ApplicationProperties {
	// site specific
	private String serverSecure = "";

	// package properties
	private String mailSmtpHost = "";
	private String mailFrom = "";
	private String mailUser = "";
	private String mailPassword = "";
	private String URLFilterFiles = "";
	private String URLFilterFolders = "";
	private String defaultLanguage = "EN";
	private boolean useCoars = true;

	private ServletContext context;
	
	private static ApplicationProperties instance = null;
	
	final Logger log = Logger.getLogger(this.getClass());
	
	public static ApplicationProperties getInstance()
	{
		if (instance == null)
		{
			instance = new ApplicationProperties();
		}
		return(instance);
	}
	
	private ApplicationProperties()
	{
		String variable = "";
		log.trace("ApplicationProperties start");
		Properties properties = new Properties();
    	try 
    	{
    		log.debug("path of abs / '" + ApplicationProperties.class.getResource("/").getPath() + "'");
        	InputStream in = ApplicationProperties.class.getResourceAsStream("/resources/package.properties");
        	if (in == null)
        	{
        		log.error("resource path not found");
        		return;
        	}
        	properties.load(in);
	    	in.close();
		}
    	catch(IOException e) 
    	{
			log.warn("Exception " + e.getMessage(), e);
    		return;
		}
    	
    	serverSecure = properties.getProperty("serverSecure");
    	try
    	{
			;
    	}
    	catch(NumberFormatException e)
    	{
    		log.error("The format for the variable '" + variable + "' is incorrect (" +
    					 properties.getProperty("sessionExpireTime") + ")");
    	}

    	String envConf = System.getProperty("envConf");
    	try 
    	{
    		properties = new Properties();
    		String siteProps = "/resources/site." + (envConf == null ? "dev" : envConf) + ".properties";
    		log.debug("Use " + siteProps);
        	InputStream in = ApplicationProperties.class.getResourceAsStream(siteProps);        	
			properties.load(in);
	    	in.close();
		}
    	catch(IOException e) 
    	{
			log.warn("Exception " + e.getMessage(), e);
    		return;
		}
    	mailSmtpHost = properties.getProperty("mailSmtpHost");
    	mailFrom = properties.getProperty("mailFrom");
    	mailUser = properties.getProperty("mailUser");
    	mailPassword = properties.getProperty("mailPassword");
    	URLFilterFiles = properties.getProperty("URLFilterFiles");
    	URLFilterFolders = properties.getProperty("URLFilterFolders");
    	defaultLanguage = properties.getProperty("defaultLanguage");
    	useCoars = Boolean.parseBoolean(properties.getProperty("useCoars"));

    	try
    	{
    		;
    	}
    	catch(NumberFormatException e)
    	{
    		log.error("The format for the variable '" + variable + "' is incorrect (" +
    					 properties.getProperty("sessionExpireTime") + ")");
    	}		
	}

	public String getMailSmtpHost() {
		return mailSmtpHost;
	}

	public String getMailFrom() {
		return mailFrom;
	}

	public String getMailUser() {
		return mailUser;
	}

	public String getMailPassword() {
		return mailPassword;
	}

	public String getServerSecure() {
		return serverSecure;
	}

	public void setServerSecure(String serverSecure) {
		this.serverSecure = serverSecure;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public void setMailSmtpHost(String mailSmtpHost) {
		this.mailSmtpHost = mailSmtpHost;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public void setMailUser(String mailUser) {
		this.mailUser = mailUser;
	}

	public void setMailPassword(String mailPassword) {
		this.mailPassword = mailPassword;
	}

	public String getDefaultLanguage() {
		return defaultLanguage;
	}

	public void setDefaultLanguage(String defaultLanguage) {
		this.defaultLanguage = defaultLanguage;
	}

	public boolean isUseCoars() {
		return useCoars;
	}

	public void setUseCoars(boolean useCoars) {
		this.useCoars = useCoars;
	}

	public String getURLFilterFiles() {
		return URLFilterFiles;
	}

	public void setURLFilterFiles(String uRLFilterFiles) {
		URLFilterFiles = uRLFilterFiles;
	}

	public String getURLFilterFolders() {
		return URLFilterFolders;
	}

	public void setURLFilterFolders(String uRLFilterFolders) {
		URLFilterFolders = uRLFilterFolders;
	}

}