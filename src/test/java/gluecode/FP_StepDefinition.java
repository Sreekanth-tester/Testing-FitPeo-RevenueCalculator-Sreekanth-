package gluecode;




import org.openqa.selenium.firefox.FirefoxDriver;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageclasses.FP_PageClass;
import utilities.PropertiesFileUtility;



public class FP_StepDefinition
{
	
public SharedClass sh;
	
	//constructor method
	public FP_StepDefinition(SharedClass sh)
	{
		this.sh=sh;
	}
	
	@Given("launch website using url")
	public void launch_website_using_url() throws Exception 
	{

	   WebDriverManager.firefoxdriver().setup();
	   sh.driver=new FirefoxDriver();
	   sh.wait=sh.su.defineWait(sh.driver);
  	   sh.pobj = new FP_PageClass(sh.driver,sh.wait);
	   sh.driver.get("https://www.fitpeo.com/");
	   sh.driver.manage().window().maximize();
	}

	@When("Navigate to Revenue Calculator page")
	public void navigate_to_revenue_calculator_page() throws Exception 
	{
		sh.pobj.navigate_to_revenue_calculator();
		Thread.sleep(2000);
		sh.pobj.DisplayScreenshot("switched into revenue_calculator page");
		sh.driver.executeScript("window.scrollBy(0,500);");
    	sh.pobj.DisplayScreenshot("Visibility of Slider");

	}

	@Then("User performs slider action and verify the values")
	public void user_performs_slider_action_and_verify_the_values() throws Exception {
		Thread.sleep(5000);
		sh.pobj.moveSlider(-328, 0);
		sh.pobj.moveSlider(123, 0);
		boolean b=sh.pobj.validateSliderAndTextBoxValue();
		if(b)
		{
			System.out.println("Validation of slider value and textbox value test case passed");
		}
		else
		{
			System.out.println("Validation of slider value and textbox value test case failed");
		}
	}

	@Then("User performs textbox action and verify the values")
	public void user_performs_textbox_action_and_verify_the_values() throws Exception {
		String temp_present_value = PropertiesFileUtility.getValueInPropertiesFile("presentvalue");
		String temp_textbox_value = PropertiesFileUtility.getValueInPropertiesFile("textvalue");
		int present_value =Integer.parseInt(temp_present_value);
		int textbox_value=Integer.parseInt(temp_textbox_value);
		sh.pobj.filltextbox(-(present_value));
		Thread.sleep(2000);
		sh.pobj.filltextbox(textbox_value);
		boolean b=sh.pobj.validateSliderAndTextBoxValue();
		if(b)
		{
			System.out.println("Validation of textbox value and slider value test case passed");
		}
		else
		{
			System.out.println("Validation of textbox value and slider value test case failed");
		}

	}
 
	@Then("Select the mentioned checkboxes")
	public void select_the_mentioned_checkboxes() throws Exception
	{
		sh.pobj.selectCheckBoxes(1);
	    sh.pobj.selectCheckBoxes(2);
	    sh.pobj.selectCheckBoxes(3);
	    Thread.sleep(2000);
	    sh.pobj.DisplayScreenshot("Selected required checkboxes");

	}

	@Then("Find the Total Recurring Reimbursement for all Patients Per month")
	public void find_the_total_recurring_reimbursement_for_all_patients_per_month() throws Exception
	{
		String Reccuring_Value= sh.pobj.getRecurringReimbursement();
	    System.out.println("Total Recurring Reimbursement for all Patients Per Month: "+Reccuring_Value);
	    Thread.sleep(2000);
		sh.driver.quit();
	}
	
}

