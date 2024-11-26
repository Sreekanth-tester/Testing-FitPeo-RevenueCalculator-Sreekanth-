package gluecode;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import pageclasses.FP_PageClass;
import utilities.WebSiteUtility;

public class SharedClass 
{
	public SharedClass sh;
	public RemoteWebDriver driver;
	public  FluentWait<RemoteWebDriver> wait; 
	public WebSiteUtility su;
	public Scenario s;
	public FP_PageClass pobj;
	
	
	//method to be executed before for every "Scenario:" or "Scenario Outline:" iterations
		@Before(order=1)
		public void method1(Scenario s) throws Exception
		{
			//define driver and wait objects with null by default
			driver=null;
			wait=null;
			//Create objects to Utility classes
			su=new WebSiteUtility();
			//Create "Scenario" class object to work for current "Scenario:" or "Scenario Outline:"
			this.s=s;
			s.log("\""+s.getName()+"\" execution started");
		}
		
		//method to be executed after every "Scenario:" or "Scenario Outline:" iteration execution
//		@After
//		public void method2(Scenario s)
//		{
//			this.s=s;
//			s.log("\""+s.getName()+"\" execution completed and result is "+s.getStatus().name());
//		}

}
