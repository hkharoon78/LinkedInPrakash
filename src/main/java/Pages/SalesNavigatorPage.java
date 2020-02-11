package Pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import utilities.Common;

public class SalesNavigatorPage extends Page{

	@FindBy(xpath="//h2[text()='Saved Searches']")
	private WebElement weSavedSearches;

	@FindBy(xpath="//*[@class='search-results__pagination-previous-button']")
	private WebElement btnPrevious;

	@FindBy(xpath="//span[text()='Previous']")
	private WebElement btnPreviousSavedLeads;

	@FindBy(xpath="//*[@class='search-results__pagination-next-button']")
	private WebElement btnNext;

	@FindBy(xpath="//span[text()='Next']")
	private WebElement btnNextSavedLeads;

	@FindBy(xpath="//button[@data-control-name='overlay.close_overlay']")
	private WebElement btnCloseOverLay;

	@FindBy(xpath="//a[@data-control-name='view_profile']//artdeco-entity-lockup-title/span[1]")
	private WebElement weProfileName;

	@FindBy(xpath="//li[contains(@aria-label,'Current page')]")
	private WebElement weCurrentPage;

	@FindBy(xpath="//span[@aria-current='true']")
	private WebElement weCurrentPageSavedLeads;

	@FindBy(xpath="//button[contains(@aria-label,'Maximize compose form')]/preceding-sibling::span[1]")
	private WebElement weProfileType;

	@FindBy(xpath="//input[contains(@id,'compose-form-subject')]")
	private WebElement txtSubject;

	@FindBy(xpath="//textarea[contains(@id,'compose-form-text')]")
	private WebElement txtBody;

	@FindBy(xpath="//span[text()='Send']")
	private WebElement btnSend;

	@FindBy(xpath="//h2[text()='Lists']")
	private WebElement lnkLists;

	@FindBy(xpath="//a[text()='Lead Lists']")
	private WebElement lnkLeadLists;

	@FindBy(xpath="//div[contains(@class,'recommendation-list__footer')]/a[contains(@href,'leads')]")
	private WebElement lnkRecLeads;


	@FindBy(xpath="//section[@class='message-overlay__conversation full-width flex flex-column full-height overflow-y-hidden ember-view']//div[@class='relative']")
	private WebElement weSentMessage;

	@FindBy(xpath="//p[contains(@class,'msg-s-event-listitem__body')]")
	private WebElement weSentMessageConnections;

	@FindBy(xpath="//*[@data-control-name='overlay.expand_conversation_window']")
	private WebElement btnExpandConverationWindowSaved;




	public SalesNavigatorPage(WebDriver browser) {
		super(browser);
	}

	public void selectLeadLists() {
		clickOn(lnkLists,"Lists");
		waitForIsClickable(lnkLeadLists);		
		clickOn(lnkLeadLists,"Lead Lists");
		String strLeadListName = Common.getDataFromSalesLeads("Lead List Name").trim(); 
		waitForElement(By.xpath("//td//div[text()='"+strLeadListName+"']"));
		WebElement weLeadListName = browser.findElement(By.xpath("//td//div[text()='"+strLeadListName+"']"));
		clickOn(weLeadListName, strLeadListName);
		waitForIsClickable(btnNextSavedLeads);
	}

	public void selectRecommendedLeads() {
		clickOn(lnkRecLeads, "See All Recommended Leads");
	}

	public void selectSavedSearches() {
		String strSavedSearches = Common.getDataFromSalesSearches("Saved searcher text").trim(); 
		clickOn(weSavedSearches, "Saved Searches");		
		WebElement we = browser.findElement(By.xpath("//ul[@class='saved-searches-list']/li/a/div/div[text()='"+strSavedSearches+"']"));
		jsClick(we, "Saved Search List");
	}


	public void navigateToExpcetedPage(){
		String strPageNo = Common.getDataFromSalesSearches("Page No");
		ScrollEnd();
		sleep(3000);
		ScrollEnd();
		sleep(3000);
		String strCurrentPage = getText(weCurrentPage);
		List<WebElement> pageNos = browser.findElements(By.xpath("//button[contains(@aria-label,'Navigate')]"));
		int totalPages = Integer.parseInt(pageNos.get(pageNos.size()-1).getText());
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

	public void navigateToExpcetedPageRecLeads(){
		String strPageNo = Common.getDataFromRecLeads("Page No");
		ScrollEnd();
		sleep(3000);
		ScrollEnd();
		sleep(3000);
		String strCurrentPage = getText(weCurrentPage);
		List<WebElement> pageNos = browser.findElements(By.xpath("//ol[@class='search-results__pagination-list']/li"));
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

	public void navigateToExpcetedPageSavedLeads(){
		String strPageNo = Common.getDataFromSalesLeads("Page No");
		ScrollEnd();
		sleep(3000);
		ScrollEnd();
		sleep(3000);
		String strCurrentPage = getText(weCurrentPageSavedLeads);
		List<WebElement> pageNos = browser.findElements(By.xpath("//li[contains(@class,'artdeco-pagination__indicator')]//span[1]"));
		int totalPages = Integer.parseInt(pageNos.get(pageNos.size()-1).getText());
		if(totalPages<=Double.parseDouble(strPageNo)) {
			Common.info("Total page in search results is "+totalPages+", But the expected page number in the excel data sheet is->"+strPageNo);
		}else {
			for(int i=0;i<totalPages;i++){
				if(Double.parseDouble(strPageNo) > Double.parseDouble(strCurrentPage)){
					jsClick(btnNextSavedLeads, "Next Button");
					waitForIsClickable(btnNextSavedLeads);
					ScrollEnd();
					sleep(3000);
					ScrollEnd();
					sleep(3000);
					strCurrentPage = getText(weCurrentPageSavedLeads);
				}else{
					break;
				}
			}
		}

	}


	public void navigateToExpcetedPagescanProfiles(){
		String strPageNo = Common.getDataFromScanProfiles("Page No");
		ScrollEnd();
		sleep(3000);
		ScrollEnd();
		sleep(3000);
		String strCurrentPage = getText(weCurrentPage);
		List<WebElement> pageNos = browser.findElements(By.xpath("//button[contains(@aria-label,'Navigate')]"));
		int totalPages = Integer.parseInt(pageNos.get(pageNos.size()-1).getText());
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


	public String getSubject(String name){
		return Common.getDataFromSalesSearches("Subject").replace("%Name%", name);
	}

	public String getSubjectSaveLeads(String name){
		return Common.getDataFromSalesLeads("Subject").replace("%Name%", name);
	}

	public String getSubjectRecLeads(String name){
		return Common.getDataFromRecLeads("Subject").replace("%Name%", name);
	}

	public String getMessageBody(String name){
		return Common.getDataFromSalesSearches("Message").replace("%Name%", name);
	}


	public String getMessageBodySaveLeads(String name){
		return Common.getDataFromSalesLeads("Message").replace("%Name%", name);
	}

	public String getMessageBodyRecLeads(String name){
		return Common.getDataFromRecLeads("Message").replace("%Name%", name);
	}


	public void VerifyAndSendMessage(){
		try{
			if(getText(weProfileType).contains("Open Profile")){		
				if(Common.getDataFromSalesSearches("Open profile").trim().equalsIgnoreCase("Yes")){
					send();
				}
			}else if(getText(weProfileType).contains("remaining credits")){
				if(Common.getDataFromSalesSearches("InMail").trim().equalsIgnoreCase("Yes")){
					send();

				}
			}
		}catch(Exception ex){
			Common.fail("Unable to send mail to : "+getText(weProfileName));
		}
		if(isElementPresent(btnCloseOverLay)){
			jsClick(btnCloseOverLay,"Message CloseOverLay Button");
		}
	}

	public void VerifyAndSendMessageRecLeads(){
		try{
			if(getText(weProfileType).contains("Open Profile")){		
				if(Common.getDataFromRecLeads("Open profile").trim().equalsIgnoreCase("Yes")){
					sendRecLeads();
				}
			}else if(getText(weProfileType).contains("remaining credits")){
				if(Common.getDataFromRecLeads("InMail").trim().equalsIgnoreCase("Yes")){
					sendRecLeads();

				}
			}
		}catch(Exception ex){
			Common.fail("Unable to send mail to : "+getText(weProfileName));
		}
		if(isElementPresent(btnCloseOverLay)){
			jsClick(btnCloseOverLay,"Message CloseOverLay Button");
		}
	}

	public void SendMessageFirstConnection(){
		String name = "";
		try{
			name = getText(weProfileName);

			String body = getMessageBody(name);

			enterText(txtBody, body);

			jsClick(btnSend, "Send Button");

			Common.info("Sent Message to : "+name);

		}catch(Exception ex){
			Common.fail("Unable to send Message to : "+name);
		}
		if(isElementPresent(btnCloseOverLay)){
			jsClick(btnCloseOverLay,"Message CloseOverLay Button");
		}
	}

	public void SendMessageFirstConnectionRecLeads(){
		String name = "";
		try{
			name = getText(weProfileName);

			String body = getMessageBody(name);

			jsEnterText(txtBody, body);

			jsClick(btnSend, "Send Button");

			Common.info("Sent Message to : "+name);

		}catch(Exception ex){
			Common.fail("Unable to send Message to : "+name);
		}
		if(isElementPresent(btnCloseOverLay)){
			jsClick(btnCloseOverLay,"Message CloseOverLay Button");
		}
	}

	public void send(){
		String name = "";
		try{
			name = getText(weProfileName);
			String sub = getSubject(name);
			String body = getMessageBody(name);

			enterText(txtSubject, sub);
			enterText(txtBody, body);

			jsClick(btnSend, "Send Button");

			Common.info("Sent Message to : "+name);

		}catch(Exception ex){
			Common.fail("Unable to send Message to : "+name);
		}

		if(isElementPresent(btnCloseOverLay)){
			jsClick(btnCloseOverLay,"Message CloseOverLay Button");
		}
	}


	public void sendRecLeads(){
		String name = "";
		try{
			name = getText(weProfileName);
			String sub = getSubjectRecLeads(name);
			String body = getMessageBodyRecLeads(name);

			enterText(txtSubject, sub);
			enterText(txtBody, body);

			jsClick(btnSend, "Send Button");

			Common.info("Sent Message to : "+name);

		}catch(Exception ex){
			Common.fail("Unable to send Message to : "+name);
		}

		if(isElementPresent(btnCloseOverLay)){
			jsClick(btnCloseOverLay,"Message CloseOverLay Button");
		}
	}


	public void sendMessagesRecLeads() {
		try {
			if(waitForIsClickable(btnNext)) {


				List<WebElement> wes = browser.findElements(By.xpath("//*[@class='search-spotlights__tablist native-scroll ember-view']/artdeco-spotlight-tab/span[2]"));
				String type = Common.getDataFromRecLeads("Category | ResultsText"); 
				for(WebElement we : wes){					
					if(getText(we).toLowerCase().contains(type.toLowerCase())){
						jsClick(we, "Search Category");
						break;
					}
				}

				navigateToExpcetedPageRecLeads();

				waitForIsClickable(btnNext);

				int strCurrentPage = Integer.parseInt(getText(weCurrentPage));
				List<WebElement> pageNos = browser.findElements(By.xpath("//ol[@class='search-results__pagination-list']/li"));
				int totalPages = Integer.parseInt(pageNos.get(pageNos.size()-1).getText());

				while(strCurrentPage<=totalPages){				

					Common.setPageNumRecLeads(strCurrentPage+1);
					Common.info("Currently in the Page Number : "+strCurrentPage);

					ScrollEnd();
					sleep(3000);
					ScrollEnd();
					sleep(3000);
					ScrollFirst();
					List<WebElement> weSearchResults = browser.findElements(By.xpath("//*[@class='search-results__result-list']//li//article/section[1]/div[2]/ul/li/div/div[2]/button"));
					for(int i=0;i<weSearchResults.size();i++) {
						List<WebElement> weConnection = browser.findElements(By.xpath("//*[@class='search-results__result-list']//li//article/section[1]/div/dl/dd/ul/li[@class='mr1']/span[1]"));
						Actions actions = new Actions(browser);
						actions.moveToElement(weConnection.get(i));
						String strConnection = weConnection.get(i).getText();
						String str1stConnection = Common.getDataFromRecLeads("1 St Connection").trim();
						if(strConnection.contains("1st")) {
							if(str1stConnection.equalsIgnoreCase("Yes")) {
								List<WebElement> weMore = browser.findElements(By.xpath("//*[@class='search-results__result-list']//li//article/section[1]/div[2]/ul/li/div/div[2]/button"));
								jsClick(weMore.get(i),"More Link");					
								List<WebElement> weMsg = browser.findElements(By.xpath("//*[@class='search-results__result-list']//li//article/section[1]/div[2]/ul/li/div/div[2]//button[text()='Message']"));
								jsClick(weMsg.get(i),"Message Link");

								sleep(2000);

								waitForIsClickable(btnCloseOverLay);
								if(verifySendingForFirstTime()){
									SendMessageFirstConnectionRecLeads();
								}else{
									jsClick(btnCloseOverLay,"Message CloseOverLay Button");
								}

							}
						}else {							
							boolean blnOpenProfile = true;
							try {
								WebElement we = weConnection.get(i).findElement(By.xpath("./parent::li/following-sibling::li//linkedin-logo"));
								blnOpenProfile = true;;
							}catch(Exception e) {
								blnOpenProfile = false;
							}
							if(blnOpenProfile) {
								List<WebElement> weMore = browser.findElements(By.xpath("//*[@class='search-results__result-list']//li//article/section[1]/div[2]/ul/li/div/div[2]/button"));
								jsClick(weMore.get(i),"More Link");				
								List<WebElement> weMsg = browser.findElements(By.xpath("//*[@class='search-results__result-list']//li//article/section[1]/div[2]/ul/li/div/div[2]//button[text()='Message']"));
								//weMsg.click();
								jsClick(weMsg.get(i),"Message Link");

								waitForIsClickable(btnCloseOverLay);
								sleep(2000);

								Common.info("Clicked on User :"+getText(weProfileName));
								if(verifySendingForFirstTime()){
									VerifyAndSendMessageRecLeads();
								}else{
									jsClick(btnCloseOverLay,"Message CloseOverLay Button");
								}
							}else {
								String strInMail = Common.getDataFromSalesSearches("InMail").trim();
								if(strInMail.equalsIgnoreCase("Yes")) {
									List<WebElement> weMore = browser.findElements(By.xpath("//*[@class='search-results__result-list']//li//article/section[1]/div[2]/ul/li/div/div[2]/button"));
									jsClick(weMore.get(i),"More Link");				
									List<WebElement> weMsg = browser.findElements(By.xpath("//*[@class='search-results__result-list']//li//article/section[1]/div[2]/ul/li/div/div[2]//button[text()='Message']"));
									//weMsg.click();
									jsClick(weMsg.get(i),"Message Link");

									waitForIsClickable(btnCloseOverLay);
									sleep(2000);

									Common.info("Clicked on User :"+getText(weProfileName));
									if(verifySendingForFirstTime()){
										VerifyAndSendMessageRecLeads();
									}else{
										jsClick(btnCloseOverLay,"Message CloseOverLay Button");
									}
								}
							}
						}
						weSearchResults = browser.findElements(By.xpath("//*[@class='search-results__result-list']//li//article/section[1]/div[2]/ul/li/div/div[2]/button"));
					}
					clickOn(btnNext, "Next Button");
					waitForIsClickable(btnNext);
					strCurrentPage = Integer.parseInt(getText(weCurrentPage));
					pageNos = browser.findElements(By.xpath("//button[contains(@aria-label,'Navigate')]"));
					totalPages = Integer.parseInt(pageNos.get(pageNos.size()-1).getText());					
				}
			}else{
				Common.fail("Search Results are not displayed");
			}
		} catch (Exception e) {
			Common.fail("Exception occured : "+e.getMessage());
		}
	}


	public void sendMessagesSavedLeads() {
		try {
			if(waitForIsClickable(btnNextSavedLeads)) {
				List<WebElement> wes = browser.findElements(By.xpath("//*[@class='search-spotlights__tablist native-scroll ember-view']/artdeco-spotlight-tab/span[2]"));
				for(WebElement we : wes){
					String type = Common.getDataFromSalesLeads("Category | ResultsIndex"); 
					if(getText(we).toLowerCase().contains(type.toLowerCase())){
						jsClick(we, "Search Category");
					}
				}

				navigateToExpcetedPageSavedLeads();

				waitForIsClickable(btnNextSavedLeads);

				int strCurrentPage = Integer.parseInt(getText(weCurrentPageSavedLeads));
				List<WebElement> pageNos = browser.findElements(By.xpath("//li[contains(@class,'artdeco-pagination__indicator')]//span[1]"));
				int totalPages = Integer.parseInt(pageNos.get(pageNos.size()-1).getText());

				while(strCurrentPage<=totalPages){	
					Common.setPageNumSaveLeads(strCurrentPage+1);
					Common.info("Currently in the Page Number : "+strCurrentPage);

					ScrollEnd();
					sleep(3000);
					ScrollEnd();
					sleep(3000);
					ScrollFirst();
					List<WebElement> weSearchResults = browser.findElements(By.xpath("//*[@aria-label='Opens dropdown menu options']"));
					for(int i=0;i<weSearchResults.size();i++) {
						List<WebElement> weMore = browser.findElements(By.xpath("//*[@aria-label='Opens dropdown menu options']"));
						jsClick(weMore.get(i),"More Link");					
						sleep(2000);
						List<WebElement> weMsg1 = browser.findElements(By.xpath("//button[text()='Message']"));

						jsClick(weMsg1.get(0),"Message Link");

						waitForElement(By.xpath("//a[@data-control-name='view_profile']//artdeco-entity-lockup-title/span[1]"));
						sleep(1000);
						String name = getText(weProfileName);
						String sub = getSubjectSaveLeads(name);
						String body = getMessageBodySaveLeads(name);

						if(isElementPresent(txtSubject)) {
							enterText(txtSubject, sub);
						}
						if(isElementPresent(txtBody)) {
							enterText(txtBody, body);
						}

						jsClick(btnSend, "Send Button");

						Common.info("Sent Message to : "+name);
						weSearchResults = browser.findElements(By.xpath("//*[@aria-label='Opens dropdown menu options']"));
					}	
					clickOn(btnNextSavedLeads, "Next Button");
					waitForIsClickable(btnNextSavedLeads);
					strCurrentPage = Integer.parseInt(getText(weCurrentPageSavedLeads));
					pageNos = browser.findElements(By.xpath("//li[contains(@class,'artdeco-pagination__indicator')]//span[1]"));
					totalPages = Integer.parseInt(pageNos.get(pageNos.size()-1).getText());	
				}				
			}else{
				Common.fail("Search Results are not displayed");
			}
		} catch (Exception e) {
			Common.fail("Exception occured : "+e.getMessage());
		}
	}

	public void sendMessages() {
		try {
			if(waitForIsClickable(btnNext)) {


				List<WebElement> wes = browser.findElements(By.xpath("//*[@class='search-spotlights__tablist native-scroll ember-view']/artdeco-spotlight-tab/span[2]"));
				for(WebElement we : wes){
					String type = Common.getDataFromSalesSearches("Category | ResultsText"); 
					if(getText(we).toLowerCase().contains(type.toLowerCase())){
						jsClick(we, "Search Category");
					}
				}

				navigateToExpcetedPage();

				waitForIsClickable(btnNext);

				int strCurrentPage = Integer.parseInt(getText(weCurrentPage));
				List<WebElement> pageNos = browser.findElements(By.xpath("//button[contains(@aria-label,'Navigate')]"));
				int totalPages = Integer.parseInt(pageNos.get(pageNos.size()-1).getText());

				while(strCurrentPage<=totalPages){				

					Common.setPageNum(strCurrentPage+1);
					Common.info("Currently in the Page Number : "+strCurrentPage);

					ScrollEnd();
					sleep(3000);
					ScrollEnd();
					sleep(3000);
					ScrollFirst();
					List<WebElement> weSearchResults = browser.findElements(By.xpath("//*[@class='search-results__result-list']//li//article/section[1]/div[2]/ul/li/div/div[2]/button"));
					for(int i=0;i<weSearchResults.size();i++) {
						List<WebElement> weConnection = browser.findElements(By.xpath("//*[@class='search-results__result-list']//li//article/section[1]/div/dl/dd/ul/li[@class='mr1']/span[1]"));
						Actions actions = new Actions(browser);
						actions.moveToElement(weConnection.get(i));
						String strConnection = weConnection.get(i).getText();
						String str1stConnection = Common.getDataFromSalesSearches("1 St Connection").trim();
						if(strConnection.contains("1st")) {
							if(str1stConnection.equalsIgnoreCase("Yes")) {
								List<WebElement> weMore = browser.findElements(By.xpath("//*[@class='search-results__result-list']//li//article/section[1]/div[2]/ul/li/div/div[2]/button"));
								jsClick(weMore.get(i),"More Link");					
								List<WebElement> weMsg = browser.findElements(By.xpath("//*[@class='search-results__result-list']//li//article/section[1]/div[2]/ul/li/div/div[2]//button[text()='Message']"));
								jsClick(weMsg.get(i),"Message Link");

								sleep(2000);

								waitForIsClickable(btnCloseOverLay);
								if(verifySendingForFirstTime()){
									SendMessageFirstConnection();
								}else{
									jsClick(btnCloseOverLay,"Message CloseOverLay Button");
								}

							}
						}else {							
							boolean blnOpenProfile = true;
							try {
								WebElement we = weConnection.get(i).findElement(By.xpath("./parent::li/following-sibling::li//linkedin-logo"));
								blnOpenProfile = true;;
							}catch(Exception e) {
								blnOpenProfile = false;
							}
							if(blnOpenProfile) {
								List<WebElement> weMore = browser.findElements(By.xpath("//*[@class='search-results__result-list']//li//article/section[1]/div[2]/ul/li/div/div[2]/button"));
								jsClick(weMore.get(i),"More Link");				
								List<WebElement> weMsg = browser.findElements(By.xpath("//*[@class='search-results__result-list']//li//article/section[1]/div[2]/ul/li/div/div[2]//button[text()='Message']"));
								//weMsg.click();
								jsClick(weMsg.get(i),"Message Link");

								waitForIsClickable(btnCloseOverLay);
								sleep(2000);

								Common.info("Clicked on User :"+getText(weProfileName));
								if(verifySendingForFirstTime()){
									VerifyAndSendMessage();
								}else{
									jsClick(btnCloseOverLay,"Message CloseOverLay Button");
								}
							}else {
								String strInMail = Common.getDataFromSalesSearches("InMail").trim();
								if(strInMail.equalsIgnoreCase("Yes")) {
									List<WebElement> weMore = browser.findElements(By.xpath("//*[@class='search-results__result-list']//li//article/section[1]/div[2]/ul/li/div/div[2]/button"));
									jsClick(weMore.get(i),"More Link");				
									List<WebElement> weMsg = browser.findElements(By.xpath("//*[@class='search-results__result-list']//li//article/section[1]/div[2]/ul/li/div/div[2]//button[text()='Message']"));
									//weMsg.click();
									jsClick(weMsg.get(i),"Message Link");

									waitForIsClickable(btnCloseOverLay);
									sleep(2000);

									Common.info("Clicked on User :"+getText(weProfileName));
									if(verifySendingForFirstTime()){
										VerifyAndSendMessage();
									}else{
										jsClick(btnCloseOverLay,"Message CloseOverLay Button");
									}
								}
							}
						}
						weSearchResults = browser.findElements(By.xpath("//*[@class='search-results__result-list']//li//article/section[1]/div[2]/ul/li/div/div[2]/button"));
					}
					clickOn(btnNext, "Next Button");
					waitForIsClickable(btnNext);
					strCurrentPage = Integer.parseInt(getText(weCurrentPage));
					pageNos = browser.findElements(By.xpath("//button[contains(@aria-label,'Navigate')]"));
					totalPages = Integer.parseInt(pageNos.get(pageNos.size()-1).getText());					
				}
			}else{
				Common.fail("Search Results are not displayed");
			}
		} catch (Exception e) {
			Common.fail("Exception occured : "+e.getMessage());
		}
	}
	//connection
	//*[@class='search-results__result-list']/li//article/section[1]/div/dl/dd/ul/li/span[1]

	// dot dot dot
	//*[@class='search-results__result-list']/li//article/section[1]/div[2]/ul/li/div/div[2]/button

	//Message


	public boolean verifySendingForFirstTime(){

		try{
			browser.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);		
			if(weSentMessage.isDisplayed()){
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



	public void sendMessagesOld() {
		try {
			if(waitForIsClickable(btnNext)) {
				isDisabled(btnNext);
				isDisabled(btnPrevious);
				System.out.println("test");
			}else{
				Common.fail("Search Results are not displayed");
			}
			ScrollEnd();
			sleep(3000);
			ScrollFirst();
			List<WebElement> weSearchResults = browser.findElements(By.xpath("//*[@class='search-results__result-list']//li//article/section[1]/div[2]/ul/li/div/div[2]/button"));
			for(int i=1;i<=weSearchResults.size();i++) {
				WebElement weConnection = browser.findElement(By.xpath("//*[@class='search-results__result-list']//li["+i+"]//article/section[1]/div/dl/dd/ul/li[@class='mr1']/span[1]"));
				Actions actions = new Actions(browser);
				actions.moveToElement(weConnection);
				String strConnection = weConnection.getText();
				String str1stConnection = Common.getDataFromSalesSearches("1 St Connection").trim();
				if(strConnection.contains("1st")) {
					if(str1stConnection.equalsIgnoreCase("Yes")) {
						WebElement weMore = browser.findElement(By.xpath("//*[@class='search-results__result-list']//li["+i+"]//article/section[1]/div[2]/ul/li/div/div[2]/button"));
						jsClick(weMore,"More Link");					
						WebElement weMsg = browser.findElement(By.xpath("//*[@class='search-results__result-list']//li["+i+"]//article/section[1]/div[2]/ul/li/div/div[2]//button[text()='Message']"));
						jsClick(weMsg,"Message Link");

						sleep(2000);

						waitForIsClickable(btnCloseOverLay);

						jsClick(btnCloseOverLay,"Message CloseOverLay Button");
					}
				}else {
					WebElement weMore = browser.findElement(By.xpath("//*[@class='search-results__result-list']//li["+i+"]//article/section[1]/div[2]/ul/li/div/div[2]/button"));
					jsClick(weMore,"More Link");				
					WebElement weMsg = browser.findElement(By.xpath("//*[@class='search-results__result-list']//li["+i+"]//article/section[1]/div[2]/ul/li/div/div[2]//button[text()='Message']"));
					//weMsg.click();
					jsClick(weMsg,"Message Link");

					waitForIsClickable(btnCloseOverLay);
					sleep(2000);

					Common.info("Clicked on User :"+getText(weProfileName));

					jsClick(btnCloseOverLay,"Message CloseOverLay Button");
				}
				weSearchResults = browser.findElements(By.xpath("//*[@class='search-results__result-list']//li//article/section[1]/div[2]/ul/li/div/div[2]/button"));

			}
		} catch (Exception e) {
			Common.fail("Exception occured : "+e.getMessage());
		}


	}

	public void waitTillResultsBecome25() {
		try {
			List<WebElement> weSearchResults = browser.findElements(By.xpath("//*[@class='search-results__result-list']//li//article/section[1]/div/dl/dt/a"));
			ScrollFirst();
			for(int i=0;i<25;i++) {
				weSearchResults = browser.findElements(By.xpath("//*[@class='search-results__result-list']//li//article/section[1]/div/dl/dt/a"));
				jsMoveToElement(weSearchResults.get(i));
			}	
		}catch(Exception ex) {

		}
	}


	public void viewProfiles() {
		try {
			if(waitForIsClickable(btnNext)) {


				List<WebElement> wes = browser.findElements(By.xpath("//*[@class='search-spotlights__tablist native-scroll ember-view']/artdeco-spotlight-tab/span[2]"));
				for(WebElement we : wes){
					String type = Common.getDataFromScanProfiles("Category | ResultsText"); 
					if(getText(we).toLowerCase().contains(type.toLowerCase())){
						jsClick(we, "Search Category");
					}
				}

				navigateToExpcetedPagescanProfiles();

				waitForIsClickable(btnNext);

				int strCurrentPage = Integer.parseInt(getText(weCurrentPage));
				List<WebElement> pageNos = browser.findElements(By.xpath("//button[contains(@aria-label,'Navigate')]"));
				int totalPages = Integer.parseInt(pageNos.get(pageNos.size()-1).getText());

				while(strCurrentPage<=totalPages){				

					Common.setPageNumScanProfiles(strCurrentPage+1);
					Common.info("Currently in the Page Number : "+strCurrentPage);

					waitTillResultsBecome25();
					//ScrollFirst();
					List<WebElement> weSearchResults = browser.findElements(By.xpath("//*[@class='search-results__result-list']//li//article/section[1]/div/dl/dt/a"));
					for(int i=0;i<weSearchResults.size();i++) {
						weSearchResults = browser.findElements(By.xpath("//*[@class='search-results__result-list']//li//article/section[1]/div/dl/dt/a"));
						Actions actions = new Actions(browser);
						actions.moveToElement(weSearchResults.get(i));
						String strName = weSearchResults.get(i).getText();						
						jsClick(weSearchResults.get(i),strName );
						Common.info("Clicked on  "+(i+1)+ " : " +strName);
						waitForElement(By.xpath("//span[contains(@class,'profile-topcard-person-entity__name')]"));
						sleep(2000);
						browser.navigate().back();
						waitForElement(By.xpath("//*[@class='search-results__result-list']//li//article/section[1]/div/dl/dt/a"));
						waitTillResultsBecome25();					
						weSearchResults = browser.findElements(By.xpath("//*[@class='search-results__result-list']//li//article/section[1]/div/dl/dt/a"));
					}
					clickOn(btnNext, "Next Button");
					waitForIsClickable(btnNext);
					strCurrentPage = Integer.parseInt(getText(weCurrentPage));
					pageNos = browser.findElements(By.xpath("//button[contains(@aria-label,'Navigate')]"));
					totalPages = Integer.parseInt(pageNos.get(pageNos.size()-1).getText());					
				}
			}else{
				Common.fail("Search Results are not displayed");
			}
		} catch (Exception e) {
			Common.fail("Exception occured : "+e.getMessage());
		}
	}


}
