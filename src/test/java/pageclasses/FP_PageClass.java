package pageclasses;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;



public class FP_PageClass 
{
	private RemoteWebDriver driver;
	FluentWait<RemoteWebDriver> wait;
	private Actions act;
	
	//elements locators as properties
	@FindBy(xpath="//div[text()='Revenue Calculator']")
	private WebElement rc;
	//rail
	@FindBy(xpath="//span[contains(@class,'MuiSlider-rail')]")
	private WebElement mainslider;	
	
	//@FindBy(xpath="//span[contains(@class,'MuiSlider-thumb')]")
	//private WebElement slider;	

	@FindBy(xpath="//span[contains(@class,'MuiSlider-root')]/child::span[3]/input")
	private WebElement slider;
	
	@FindBy(xpath="//span[contains(@class,'MuiSlider-thumb')]/child::input")
	private WebElement slider_value;	
	//main
	@FindBy(xpath="//span[contains(@class,'MuiSlider-root')]")
	private WebElement subslider;	
	
	@FindBy(xpath="//input[contains(@class,'MuiInputBase')]")
	private WebElement textbox;
	
	
	@FindBy(xpath="//div[@class='MuiBox-root css-1eynrej'][1]/child::label/span[1]")
	private WebElement cb1;
	
	@FindBy(xpath="//div[@class='MuiBox-root css-1eynrej'][2]/child::label/span[1]")
	private WebElement cb2;
	
	@FindBy(xpath="//div[@class='MuiBox-root css-1eynrej'][3]/child::label/span[1]")
	private WebElement cb3;
	
	@FindBy(xpath="//header[contains(@class,'MuiPaper-root MuiPaper')]/div/p[4][contains(text(),'Total Recurring Reimbursement')]/p")
	private WebElement rrvalue;
	
	public  FP_PageClass(RemoteWebDriver driver,FluentWait<RemoteWebDriver> wait)
	{
		this.driver=driver;
		this.wait=wait;
		PageFactory.initElements(driver, this);
		act=new Actions(driver);
	}
	
	
	public void navigate_to_revenue_calculator()
	{
		wait.until(ExpectedConditions.elementToBeClickable(rc)).click();
	}
		
	public int getSliderWidth()
	{
		int width=slider.getSize().getWidth();
		return(width);
	}
	
	public int getTextBoxRange()
	{
		String value =textbox.getAttribute("max");
		int range=Integer.parseInt(value);
		return(range);
	}
	
	public void moveSlider(int x, int y)
	{
		act.dragAndDropBy(slider,x,y).perform(); 		
	}
	
	public void filltextbox(int x) throws InterruptedException
	{
		driver.navigate().refresh();
		driver.executeScript("arguments[0].click();", textbox);
		driver.executeScript("arguments[0].value='"+x+"';",textbox);
		textbox.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
	}
	
	public boolean validateSliderAndTextBoxValue() throws InterruptedException
	{

		String SV=slider_value.getAttribute("aria-valuenow");
		System.out.println(SV+" : is the Total Individual Patient/Month collected by using slider");
		String TV =textbox.getAttribute("value");
		System.out.println(TV+" : is the Total Individual Patient/Month collected by using textbox");
		if(SV.equalsIgnoreCase(TV))
		{
			System.out.println("Total Individual Patient/Month value is same in slider and textbox");
			return(true);
		}
		else
		{
			System.out.println("Total Individual Patient/Month value is not same in slider and textbox");
			System.out.println("Test case failed");
			return(false);
		}	
		}

	
	
	public void selectCheckBoxes(int  index) throws InterruptedException
	{
		
		 WebElement ele = null;
		 if(index==1)
		 {
			 ele=cb1;
		 }
		 else if(index==2)
		 {
			 ele=cb2;
		 }
		 else if (index==3)
		 {
			 ele = cb3;
		 }
		 else
		 {
			 System.out.println("wrong index");
			 System.exit(0);
		 }
			 
		 if(!ele.isSelected())
		 {
			driver.executeScript("arguments[0].click();",ele);
			 //ele.click();
			Thread.sleep(2000);
		 }
	}
	
	public String getRecurringReimbursement()
	{
		rrvalue.getText();
		String Recurringvalue=(String)driver.executeScript("return(arguments[0].textContent);", rrvalue);
		return(Recurringvalue);
	}
	
	public String DisplayScreenshot(String name) throws IOException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-YYYY-hh-mm-ss");
		Date dt = new Date();
		String fn=sdf.format(dt)+".png";
		File src = driver.getScreenshotAs(OutputType.FILE);
		File dst = new File("target/"+name+fn);
		FileHandler.copy(src, dst);
		return(dst.getAbsolutePath());			
	}
}
