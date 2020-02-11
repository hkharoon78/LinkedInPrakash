package Pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import utilities.Common;

public class Connections extends Page{


	@FindBy(xpath="//input[contains(@placeholder,'Add a country')]")
	private WebElement txtLocations;

	@FindBy(xpath="//input[contains(@placeholder,'Add an industry')]")
	private WebElement txtIndustries;

	@FindBy(xpath="//input[contains(@placeholder,'Add a school')]")
	private WebElement txtschool;

	@FindBy(xpath="//input[contains(@placeholder,'Add a service category')]")
	private WebElement txtServices;

	@FindBy(xpath="//button[@data-control-name='all_filters_apply']")
	private WebElement btnApply;

	@FindBy(xpath="//button[@data-control-name='all_filters_clear']")
	private WebElement btnClear;

	@FindBy(xpath="//*[@aria-label='Next']")
	private WebElement btnNext;


	@FindBy(xpath="//*[@aria-current='true']")
	private WebElement weCurrentPage;

	@FindBy(xpath="//*[@class='artdeco-pill__text']")
	private WebElement weProfileName;

	@FindBy(xpath="//*[@aria-label='Write a message…']")
	private WebElement txtBody;

	@FindBy(xpath="//*[@data-control-name='send']")
	private WebElement btnSend;

	@FindBy(xpath="//*[@data-control-name='overlay.close_conversation_window']")
	private WebElement btnCloseOverLay;

	@FindBy(xpath="//span[text()='Discard']")
	private WebElement btnDiscard;

	@FindBy(xpath="//*[@data-control-name='overlay.expand_conversation_window']")
	private WebElement btnExpandConverationWindowSaved;

	@FindBy(xpath="//p[contains(@class,'msg-s-event-listitem__body')]")
	private WebElement weSentMessageConnections;


	public Connections(WebDriver browser) {
		super(browser);
	}


	public void selectAndApplyFilters() {
		clickOn(btnClear, "Clear Button");
		selectLocations();
		selectIndustries();
		selectProfileLanguage();
		selectSchools();
		selectServices();
		clickOn(btnApply, "Apply Button");
	}



	public void navigateToExpcetedPageConnections(){
		String strPageNo = Common.getDataFromConnections("Page No");
		ScrollEnd();
		sleep(3000);
		ScrollEnd();
		sleep(3000);
		String strCurrentPage = getText(weCurrentPage);
		List<WebElement> pageNos = browser.findElements(By.xpath("//li[contains(@class,'artdeco-pagination__indicator')]//span[1]"));
		int totalPages = Integer.parseInt(pageNos.get(pageNos.size()-1).getText());
		if(totalPages<=Double.parseDouble(strPageNo)) {
			Common.info("Total page in search results is "+totalPages+", But the expected page number in the excel data sheet is->"+strPageNo);
		}else {
			for(int i=0;i<totalPages;i++){
				if(Double.parseDouble(strPageNo) > Double.parseDouble(strCurrentPage)){
					jsClick(btnNext, "Next Button");
					waitForIsClickable(btnNext);
					ScrollEnd();
					sleep(3000);
					ScrollEnd();
					sleep(3000);
					strCurrentPage = getText(weCurrentPage);
				}else{				
					break;
				}
			}
		}
	}

	public String getMessageBody(String name){
		return Common.getDataFromConnections("Message").replace("%Name%", name);
	}


	public void send(){
		String name = "";
		try{
			name = getText(weProfileName);

			String body = getMessageBody(name);
			enterText(txtBody, "test");
			txtBody.clear();
			enterText(txtBody, body);
			txtBody.clear();
			enterText(txtBody, body);
			//jsEnterText(txtBody,body);
			jsClick(btnSend, "Send Button");

			Common.info("Sent Message to : "+name);

		}catch(Exception ex){
			Common.fail("Unable to send Message to : "+name);
		}

		if(isElementPresent(btnCloseOverLay)){
			jsClick(btnCloseOverLay,"Message CloseOverLay Button");
		}

		if(isElementPresent(btnDiscard)) {
			jsClick(btnDiscard,"Discard Button");
		}
	}



	public boolean verifySendingForFirstTimeConnections(){

		try{
			browser.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);		
			if(weSentMessageConnections.isDisplayed()){
				//browser.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
				return false;

			}else{
				//browser.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
				return true;
			}

		}catch(Exception ex){
			//browser.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			return true;
		}


	}


	public void sendMessagesRecLeads() {
		try {
			List<WebElement> weSearchResults = browser.findElements(By.xpath("//*[contains(@class,'message-anywhere-button')]"));
			ScrollEnd();
			sleep(3000);
			ScrollFirst();
			if(isElementPresent(btnNext)) {

				navigateToExpcetedPageConnections();

				waitForIsClickable(btnNext);

				int strCurrentPage = Integer.parseInt(getText(weCurrentPage));
				List<WebElement> pageNos = browser.findElements(By.xpath("//li[contains(@class,'artdeco-pagination__indicator')]//span[1]"));
				int totalPages = Integer.parseInt(pageNos.get(pageNos.size()-1).getText());

				while(strCurrentPage<=totalPages){				

					Common.setPageNumConnections(strCurrentPage+1);
					Common.info("Currently in the Page Number : "+strCurrentPage);

					ScrollEnd();
					sleep(3000);
					ScrollEnd();
					sleep(3000);
					ScrollFirst();
					weSearchResults = browser.findElements(By.xpath("//*[contains(@class,'message-anywhere-button')]"));
					for(int i=0;i<weSearchResults.size();i++) {						
						weSearchResults = browser.findElements(By.xpath("//*[contains(@class,'message-anywhere-button')]"));
						weSearchResults.get(i).click();
						waitForIsClickable(btnCloseOverLay);
						waitForIsClickable(btnExpandConverationWindowSaved);

						clickOn(btnExpandConverationWindowSaved, "Expand Coversation");
						sleep(2000);
						if(verifySendingForFirstTimeConnections()){
							send();				
						}else {
							if(isElementPresent(btnCloseOverLay)){
								jsClick(btnCloseOverLay,"Message CloseOverLay Button");
							}
						}
					}
					clickOn(btnNext, "Next Button");
					waitForIsClickable(btnNext);
					strCurrentPage = Integer.parseInt(getText(weCurrentPage));
					pageNos = browser.findElements(By.xpath("//li[contains(@class,'artdeco-pagination__indicator')]//span[1]"));
					totalPages = Integer.parseInt(pageNos.get(pageNos.size()-1).getText());					
				}

			}else{
				weSearchResults = browser.findElements(By.xpath("//*[contains(@class,'message-anywhere-button')]"));
				if(weSearchResults.size()>0) {
					ScrollEnd();
					sleep(3000);
					ScrollEnd();
					sleep(3000);
					ScrollFirst();
					weSearchResults = browser.findElements(By.xpath("//*[contains(@class,'message-anywhere-button')]"));
					for(int i=0;i<weSearchResults.size();i++) {						
						weSearchResults = browser.findElements(By.xpath("//*[contains(@class,'message-anywhere-button')]"));
						weSearchResults.get(i).click();
						waitForIsClickable(btnCloseOverLay);
						waitForIsClickable(btnExpandConverationWindowSaved);

						clickOn(btnExpandConverationWindowSaved, "Expand Coversation");
						sleep(2000);
						if(verifySendingForFirstTimeConnections()){
							send();				
						}else {
							if(isElementPresent(btnCloseOverLay)){
								jsClick(btnCloseOverLay,"Message CloseOverLay Button");
							}
						}				
					}
				}else {
					Common.fail("Search Results are not displayed");
				}
			}
		} catch (Exception e) {
			Common.fail("Exception occured : "+e.getMessage());
		}
	}

	public void selectLocations() {
		//Filter select Locations
		String arrLoc = Common.getDataFromConnections("Locations");
		if(!arrLoc.trim().equals("")) {
			String[] loc = arrLoc.split(";");
			for(int i=0;i<loc.length;i++){
				List<WebElement> weLocations = browser.findElements(By.xpath("//div[@id='locations-facet-values']//li[@class='search-facet__value ']"));
				boolean blnFound = false;
				for(int j=0;j<weLocations.size();j++) {
					weLocations = browser.findElements(By.xpath("//div[@id='locations-facet-values']//li[@class='search-facet__value ']"));
					String actLoc = weLocations.get(j).getText();
					if(actLoc.trim().equalsIgnoreCase(loc[i].trim())) {
						clickOn(weLocations.get(j), loc[i]);
						blnFound = true;
						//break;
					}

				}
				if(!blnFound) {
					try {
						enterText(txtLocations, loc[i]);
						sleep(2000);
						List<WebElement> weLocationsPopped = browser.findElements(By.xpath("//div[@role='listbox']"));
						weLocationsPopped.get(0).click();				
						sleep(3000);
					} catch (Exception e) {
						Common.fail("Unable to select value from dropdown "+loc[i]);
					}
					//
				}
			}
		}
	}


	public void selectIndustries() {
		//Filter select Locations
		String arrLoc = Common.getDataFromConnections("Industries");
		if(!arrLoc.trim().equals("")) {
			String[] loc = arrLoc.split(";");
			for(int i=0;i<loc.length;i++){
				List<WebElement> weLocations = browser.findElements(By.xpath("//div[@id='industries-facet-values']//li[@class='search-facet__value ']"));
				boolean blnFound = false;
				for(int j=0;j<weLocations.size();j++) {
					weLocations = browser.findElements(By.xpath("//div[@id='industries-facet-values']//li[@class='search-facet__value ']"));
					String actLoc = weLocations.get(j).getText();
					if(actLoc.trim().equalsIgnoreCase(loc[i].trim())) {
						clickOn(weLocations.get(j), loc[i]);
						blnFound = true;
						//break;
					}

				}
				try {
					if(!blnFound) {
						try {
							enterText(txtIndustries, loc[i]);
							sleep(2000);
							List<WebElement> weLocationsPopped = browser.findElements(By.xpath("//div[@role='listbox']"));
							weLocationsPopped.get(0).click();				
							sleep(3000);
						} catch (Exception e) {
							Common.fail("Unable to select value from dropdown "+loc[i]);
						}
						//
					}
				} catch (Exception e) {

				}
			}
		}
	}




	public void selectProfileLanguage() {
		//Filter select Locations
		String arrLoc = Common.getDataFromConnections("Profile language");
		if(!arrLoc.trim().equals("")) {
			String[] loc = arrLoc.split(";");
			for(int i=0;i<loc.length;i++){
				List<WebElement> weLocations = browser.findElements(By.xpath("//div[@id='profile-language-facet-values']//li[@class='search-facet__value ']"));
				boolean blnFound = false;
				for(int j=0;j<weLocations.size();j++) {
					weLocations = browser.findElements(By.xpath("//div[@id='profile-language-facet-values']//li[@class='search-facet__value ']"));
					String actLoc = weLocations.get(j).getText();
					if(actLoc.trim().equalsIgnoreCase(loc[i].trim())) {
						clickOn(weLocations.get(j), loc[i]);
						blnFound = true;
						//break;
					}

				}
			}
		}
	}



	public void selectSchools() {
		//Filter select Locations
		String arrLoc = Common.getDataFromConnections("Schools");
		if(!arrLoc.trim().equals("")) {
			String[] loc = arrLoc.split(";");
			for(int i=0;i<loc.length;i++){
				List<WebElement> weLocations = browser.findElements(By.xpath("//div[@id='schools-facet-values']//li[@class='search-facet__value ']"));
				boolean blnFound = false;
				for(int j=0;j<weLocations.size();j++) {
					weLocations = browser.findElements(By.xpath("//div[@id='schools-facet-values']//li[@class='search-facet__value ']"));
					String actLoc = weLocations.get(j).getText();
					if(actLoc.trim().equalsIgnoreCase(loc[i].trim())) {
						clickOn(weLocations.get(j), loc[i]);
						blnFound = true;
						//break;
					}

				}

				try {
					if(!blnFound) {
						try {
							enterText(txtschool, loc[i]);
							sleep(2000);
							List<WebElement> weLocationsPopped = browser.findElements(By.xpath("//div[@role='listbox']"));
							weLocationsPopped.get(0).click();				
							sleep(3000);
						} catch (Exception e) {
							Common.fail("Unable to select value from dropdown "+loc[i]);
						}
						//
					}
				} catch (Exception e) {

				}
			}
		}
	}




	public void selectServices() {
		//Filter select Locations
		String arrLoc = Common.getDataFromConnections("Services");
		if(!arrLoc.trim().equals("")) {
			String[] loc = arrLoc.split(";");
			for(int i=0;i<loc.length;i++){
				List<WebElement> weLocations = browser.findElements(By.xpath("//div[@id='services-facet-values']//li[@class='search-facet__value ']"));
				boolean blnFound = false;
				for(int j=0;j<weLocations.size();j++) {
					weLocations = browser.findElements(By.xpath("//div[@id='services-facet-values']//li[@class='search-facet__value ']"));
					String actLoc = weLocations.get(j).getText();
					if(actLoc.trim().equalsIgnoreCase(loc[i].trim())) {
						clickOn(weLocations.get(j), loc[i]);
						blnFound = true;
						//break;
					}

				}

				try {
					if(!blnFound) {
						try {
							enterText(txtServices, loc[i]);
							sleep(2000);
							List<WebElement> weLocationsPopped = browser.findElements(By.xpath("//div[@role='listbox']"));
							weLocationsPopped.get(0).click();				
							sleep(3000);
						} catch (Exception e) {
							Common.fail("Unable to select value from dropdown "+loc[i]);
						}
						//
					}
				} catch (Exception e) {

				}
			}
		}
	}

}
