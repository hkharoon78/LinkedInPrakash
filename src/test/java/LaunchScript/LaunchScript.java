	package LaunchScript;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

import utilities.Common;

public class LaunchScript extends Common  {

	public static void main(String[] args) {
		start();
		TestNG testng = new TestNG();
		List<String> suites = new ArrayList<String>();
		suites.add("/home/haroon/Documents/gopi/v5/source code/LinkedInPrakashv5/LinkedInPrakash/testng.xml");			
		testng.setTestSuites(suites);
		testng.run();
	}

}
