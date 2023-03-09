package KTCTC;

import org.apache.log4j.Logger;

import com.uiFramework.KTCTC.helper.frame.FrameHelper;
import com.uiFramework.KTCTC.helper.logger.LoggerHelper;

public class SampleClass {
	private Logger log = LoggerHelper.getLogger(SampleClass.class);
	
	
	public static void main(String[] args) {
		
		Logger log = LoggerHelper.getLogger(SampleClass.class);
		
		Logger logg = LoggerHelper.getLogger(SampleClass.class);
		
	}
	
	
	public void addNewUser()
	{
		log.info("Adding new user in application");
		 
		
		
		log.info("User added successfully...");
	}
}
