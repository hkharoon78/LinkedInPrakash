package utilities;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Common {

	public static final String ExcelPath = "/home/haroon/Documents/gopi/v5/source code/LinkedInPrakashv5/LinkedInPrakash/src/main/java/utilities/data.xlsx";
	public static Logger logger;

	public static void  start() {
		PropertiesFile.properties();
		createLogFile();
	}

	public static String getBrowser() {
		try {
			ExcelData excelData = new ExcelData(ExcelPath,"Saved Searcher");
			return excelData.getData("Browser");
		} catch (Exception e) {
			return "";
		}
	}

	public static String getUrl() {
		try {
			ExcelData excelData = new ExcelData(ExcelPath,"Tool_Subscription");
			return excelData.getData("URL");
		} catch (Exception e) {
			return "";
		}
	}

	public static String getUserName() {
		try {
			ExcelData excelData = new ExcelData(ExcelPath,"Tool_Subscription");
			return excelData.getData("USER ID");
		} catch (Exception e) {
			return "";
		}
	}

	public static String getValidity() {
		try {
			ExcelData excelData = new ExcelData(ExcelPath,"Tool_Subscription");
			return excelData.getData("Validity");
		} catch (Exception e) {
			return "";
		}
	}

	public static String getPassword() {
		try {
			ExcelData excelData = new ExcelData(ExcelPath,"Modules");			
			return excelData.getData(2, 2);
		} catch (Exception e) {
			return "";
		}
	}

	public static boolean checkValidity() {
		try {
			String strValidityDate = getValidity();
			Date dateValidityDate = new Date(strValidityDate);
			Date currentDate = new Date();			
			int y = dateValidityDate.compareTo(currentDate);
			if(y>=0) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	public static void writeToLogFile(String type, String message)
	{		
		String t = type.toUpperCase();
		if (t.equalsIgnoreCase("DEBUG"))
		{
			Common.logger.debug(message);
		}
		if (t.equalsIgnoreCase("PASS"))
		{
			Common.logger.info(message);
		}
		else if (t.equalsIgnoreCase("INFO"))
		{
			Common.logger.info(message);
		}
		else if (t.equalsIgnoreCase("WARN"))
		{
			Common.logger.warn(message);
		}
		else if (t.equalsIgnoreCase("ERROR"))
		{
			Common.logger.error(message);
		}
		else if (t.equalsIgnoreCase("FATAL"))
		{
			Common.logger.fatal(message);
		}
		else {
			Common.logger.warn(message);
		}
	}
	
	public static void info(String msg) {
		writeToLogFile("INFO",msg);
	}
	
	public static void fail(String msg) {
		writeToLogFile("ERROR",msg);
	}


	public static void createLogFile()
	{
		Properties props = new Properties();
		String propsFileName = "C:\\Apps\\log4j.properties";
		String path;

		try
		{
			java.io.FileInputStream configStream = new java.io.FileInputStream(propsFileName);
			props.load(configStream);

			path = "C:\\Apps\\temp\\LogFile.log";


			props.setProperty("log4j.appender.FA.File", path);
			java.io.FileOutputStream output = new java.io.FileOutputStream(propsFileName);
			props.store(output, "Output Directory updated : " + path);


			output.close();
			configStream.close();


			org.apache.log4j.PropertyConfigurator.configure(propsFileName);

			Common.logger = org.apache.log4j.Logger.getLogger(path);
			Common.writeToLogFile("INFO", "Startup activites...");

			Common.writeToLogFile("INFO", "Log File creation successful : LogFile.log");
		}catch (IOException ex){
			System.out.println("There was an error creating the log file");
		}
	}
	
	public static String getDataFromSalesSearches(String colName) {
		ExcelData excelData = new ExcelData(ExcelPath, "Saved Searcher");
		return excelData.getData(colName);
	}
	
	public static String getDataFromRecLeads(String colName) {
		ExcelData excelData = new ExcelData(ExcelPath, "Recommended leads");
		return excelData.getData(colName);
	}
	
	public static String getDataFromConnections(String colName) {
		ExcelData excelData = new ExcelData(ExcelPath, "Connections");
		return excelData.getData(colName);
	}
	
	public static void setPageNumConnections(int pageNum){
		ExcelData excelData = new ExcelData(ExcelPath, "Connections");
		excelData.setData(2, "Page No", pageNum);
	}
	
	public static String getDataFromSalesLeads(String colName) {
		ExcelData excelData = new ExcelData(ExcelPath, "Saved Leads");
		return excelData.getData(colName);
	}
	
	public static String getDataFromFollowers(String colName) {
		ExcelData excelData = new ExcelData(ExcelPath, "Followers");
		return excelData.getData(colName);
	}
	
	public static void setPageNum(int pageNum){
		ExcelData excelData = new ExcelData(ExcelPath, "Saved Searcher");
		excelData.setData(2, "Page No", pageNum);
	}
	
	
	
	public static void setPageNumRecLeads(int pageNum){
		ExcelData excelData = new ExcelData(ExcelPath, "Recommended leads");
		excelData.setData(2, "Page No", pageNum);
	}
	
	public static void setPageNumSaveLeads(int pageNum){
		ExcelData excelData = new ExcelData(ExcelPath, "Saved Leads");
		excelData.setData(2, "Page No", pageNum);
	}
	
	
	
	
	public static String getDataFromScanProfiles(String colName) {
		ExcelData excelData = new ExcelData(ExcelPath, "Scan_profiles");
		return excelData.getData(colName);
	}
	
	public static void setPageNumScanProfiles(int pageNum){
		ExcelData excelData = new ExcelData(ExcelPath, "Scan_profiles");
		excelData.setData(2, "Page No", pageNum);
	}
	
	
	
	
}
