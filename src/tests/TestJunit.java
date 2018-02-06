package tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;  
import org.junit.runners.Suite;  
import org.junit.runner.notification.Failure;

public class TestJunit {

	@RunWith(Suite.class)  
	@Suite.SuiteClasses({ CalculationsTest.class, ConnectTest.class })  
	public class TestSuite  
	{  
	      
	}
	
	public static void main(String[] args) {
	   Result result = JUnitCore.runClasses(TestSuite.class);
			
	   if (result.wasSuccessful())
		   System.out.println("Test successful!");
	   else
		   for (Failure failure : result.getFailures()) {
			   System.out.println(failure.toString());
		   }
	}
	
}
