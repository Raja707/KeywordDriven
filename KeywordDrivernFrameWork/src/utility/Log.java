package utility;

import org.apache.log4j.Logger;

public class Log {
	private static Logger log = Logger.getLogger(Log.class.getName());
	
	//Log Functions - startTestCase
	public static void startTestCase (String sTestCaseName) {
		log.info("*************************************************************************");
		log.info("*************************************************************************");
		log.info("$$$$$$$$$$$$$$$        "+sTestCaseName+"-Started     $$$$$$$$$$$$$$$$$$$$" );
		log.info("*************************************************************************");
		log.info("*************************************************************************");
		
	}
	
	//Log Functions - endTestCase
	public static void endTestCase (String sTestCaseName) {
		log.info("**************************************************************************");
		log.info("$$$$$$$$$$$$$$$       "+sTestCaseName+"-Ended         $$$$$$$$$$$$$$$$$$$$$");
		log.info("**************************************************************************");
	}
	
	//Log Functions - info
	public static void info (String message ) {
		log.info(message);
	}
	
	//Log Functions - fatal
	public static void fatal (String message) {
		log.fatal(message);
	}
	
	//Log Functions - error
	public static void error (String message) {
		log.error(message);		
	}
	
	//Log Functions - warn
	public static void warn (String message) {
		log.warn(message);
	}
	
	//Log Functions - debug
	public static void debug (String message) {
		log.debug(message);
	}
}
