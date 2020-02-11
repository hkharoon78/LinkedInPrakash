package Pages;


import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Common;






/**
 * Base class for all the pages.
 *
 */
public class Page {
	protected WebDriver browser;	

	/**
	 * Constructor for Page class 
	 * @param browser
	 * @param report
	 */
	protected Page(WebDriver browser) {
		this.browser=browser;		
		PageFactory.initElements(browser, this);

	}


	/**
	 * Check if the element is present in the page
	 * @param element WebElement need to check
	 * @return True/False
	 */
	protected boolean isElementPresent(WebElement element){
		try{
			new WebDriverWait(browser, 2).until(ExpectedConditions
					.elementToBeClickable(element));
			if(element.isDisplayed()){
				return true;
			}else{
				return false;
			}
		}catch(Exception ex){
			return false;		
		}
	}

	

	/**
	 * Method to click on a link(WebElement link)
	 * @param : WebElement
	 * @param : Element Name
	 */
	protected void jsClick(WebElement we,String elemName) {		
		try{			
			((JavascriptExecutor) browser).executeScript("arguments[0].click();",we);	
		}catch (RuntimeException ex) {
			Common.fail("Uanble to click on Element-"+ elemName +", Exception is->"+ex.getMessage());
		} 
	}

	public void jsMoveToElement(WebElement we) {
		try {
		((JavascriptExecutor) browser).executeScript("arguments[0].scrollIntoView(true);", we);
		}catch (Exception ex) {
			
		} 
	}
	/**
	 * Check if the element is present in the page
	 * @param Element locator of type By
	 * @return True/False
	 */
	public boolean isElementPresent(By by){
		try{
			new WebDriverWait(browser, 2).until(ExpectedConditions
					.elementToBeClickable(by));
			if(browser.findElement(by).isDisplayed()){
				return true;
			}else{
				return false;
			}
		}catch(Exception ex){
			return false;		
		}
	}


	/***
	 * Method to check for an alert for 20 seconds
	 * @param       : Element Name
	 * @return      : 
	 * Modified By  :  
	 ***/

	public boolean isAlertPresent(){
		try{
			WebDriverWait wait = new WebDriverWait(browser, 20);
			wait.until(ExpectedConditions.alertIsPresent());
			return true;
		}catch(Exception e){			
			return false;
		}
	}

	/**
	 * Method to wait for element to load in the page
	 * @param WebElement
	 */
	protected Boolean waitForIsClickable(WebElement we) {
		try {
			new WebDriverWait(browser, 60).until(ExpectedConditions
					.elementToBeClickable(we));			
			if(isElementPresent(we)){
				return true;
			}else{
				Common.fail("Element is not visible after waiting for 60 Seconds");
				return false;
			}			
		} catch (Exception ex) {
			Common.fail("Element is not visible after waiting for 60 Seconds, : "+ex.getMessage());			
			return false;
		}    	
	}
	
	/**
	 * Method to wait for element to load in the page
	 * @param WebElement
	 */
	protected void waitForElement(By by) {
		try {
			new WebDriverWait(browser, 60).until(ExpectedConditions
					.presenceOfElementLocated(by));				
		} catch (Exception ex) {
			Common.fail("Element is not visible after waiting for 60 Seconds, : "+ex.getMessage());			
		}    	
	}
	
	public String getText(WebElement we) {
		try {
			return we.getText();
		} catch (Exception ex) {
			Common.fail("Unable to get the text : "+ex.getMessage());			
			return "";
		}  
	}
	
	public void ScrollEnd() {
		JavascriptExecutor js = (JavascriptExecutor) browser;
        //This will scroll the web page till end.		
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	public void ScrollFirst() {
		JavascriptExecutor js = (JavascriptExecutor) browser;
        //This will scroll the web page till end.		
        js.executeScript("window.scrollTo(0, 0)");
	}



	/***
	 * Method to click on a link(WebElement button)
	 * @param : WebElement
	 * @param : Element Name
	 ***/
	public void clickOn(WebElement we,String elemName) {		
		try{
			waitForIsClickable(we);
			String strProp = we.toString();
			if (isElementPresent(we)){
				we.click();				
			}else{
				Common.fail("Unable to click on Element "+elemName+", Element with following property is not displayed->"+strProp);
			}
		}catch (Exception ex) {
			Common.fail("Uanble to click on Element-"+ elemName +", Exception is->"+ex.getMessage());
		} 
	}

	/***
	 * Method to enter text in a textbox
	 * @param : WebElement - Textbox
	 * @param : Text to be entered
	 * @return :
	 ***/
	public boolean enterText(WebElement we,String text){
		try{
			waitForIsClickable(we);
			if(isElementPresent(we)){
				we.clear();
				we.sendKeys(text);
				return true;
			}else{
				Common.fail("Element is not displayed, Unable to enter text->"+ text);
				return false;
			}
		}
		catch (RuntimeException ex) {			
			Common.fail("Unable to enter text in the text field->"+ text);
			return false;
		} 
	}
	
	/***
	 * Method to enter text in a textbox
	 * @param : WebElement - Textbox
	 * @param : Text to be entered
	 * @return :
	 ***/
	public boolean jsEnterText(WebElement we,String text){
		try{
			waitForIsClickable(we);
			if(isElementPresent(we)){
				JavascriptExecutor js = (JavascriptExecutor)browser;				 
				//js.executeScript("arguments[0].setAttribute('value', '" + text +"');", we);
				js.executeScript("arguments[0].value = arguments[1];", we,text);
				
				
				sleep(5000);
				return true;
			}else{
				Common.fail("Element is not displayed, Unable to enter text->"+ text);
				sleep(5000);
				return false;
			}
			
			
			}
		catch (RuntimeException ex) {			
			Common.fail("Unable to enter text in the text field->"+ text);
			return false;
		} 
	}

	/***
	 * Method to switch to child window
	 * @param : parentWindow
	 ***/
	public void navigateToNewWindow() {
		boolean blnNavigate=false;
		String parentWindow = browser.getWindowHandle();
		try{				
			Set<String> handles = browser.getWindowHandles();			
			for (String windowHandle : handles) {					
				String strActWindow = browser.switchTo().window(windowHandle).getWindowHandle();
				if(!strActWindow.equals(parentWindow)){
					blnNavigate = true;
					browser.manage().window().maximize();				
					break;
				}					
			}
			if(!blnNavigate){
				Common.fail("Unable to Navigate to the new page");
			}
		}catch(Exception ex){
			Common.fail("Unable to Navigate to the new page");
		}
	}
	
	public boolean isDisabled(WebElement elem) {
		if(elem.isEnabled()) {
			return false;
		}else {
			return true;
		}
	}
	
	public void sleep(int milisec) {
		try {
			Thread.sleep(milisec);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
