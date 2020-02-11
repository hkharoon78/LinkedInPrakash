package Pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import utilities.Common;

public class MyNetworkPage extends Page{



	@FindBy(xpath="//*[@data-control-name='manage_all_invites']/span")
	private WebElement lnkSeeAll;

	@FindBy(xpath="//button[contains(@class,'artdeco-button--secondary')]/span[text()='Accept']")
	private WebElement btnAccept;
	
	@FindBy(xpath="//div[@class='mn-community-summary__entity-info' and text()='Contacts']")
	private WebElement btnContacts;
	
	@FindBy(xpath="//div[text()='People I Follow']")
	private WebElement lnkPplIFollow;

	@FindBy(xpath="//a[@data-control-name='see_followers_list']")
	private WebElement lnkFollowers;
	
	
	
	public MyNetworkPage(WebDriver browser) {
		super(browser);
	}

	public void seeAllInvites() {
		try {
			waitForIsClickable(lnkSeeAll);
			if(isElementPresent(lnkSeeAll)) {		
				clickOn(lnkSeeAll, "See All Link");			
			}

			sleep(6000);
			List<WebElement> btnAccept = browser.findElements(By.xpath("//button[contains(@class,'artdeco-button--secondary')]/span[text()='Accept']"));
			int count =0;
			while(btnAccept.size()>0) {
				count++;
				clickOn(btnAccept.get(0), "See All Link");	
				Common.info("Accepted the invite count : "+count);
				sleep(5000);
				btnAccept = browser.findElements(By.xpath("//button[contains(@class,'artdeco-button--secondary')]/span[text()='Accept']"));
			}
			
			Common.info("All the invitations are accepted successfully");
		}catch(Exception ex) {
			Common.fail("Exception caught while accepting the invites , Exception message is : "+ex.getMessage());
		}

	}
	
	
	
	public void sendInvites() {
		try {
			waitForIsClickable(btnContacts);
			clickOn(btnContacts, "Contacts Button");
			waitForElement(By.xpath("//button/span[text()='Invite']"));
			

			List<WebElement> btnInvite = browser.findElements(By.xpath("//button/span[text()='Invite']"));
			int count =0;
			while(btnInvite.size()>0) {
				count++;
				jsMoveToElement(btnInvite.get(0));
				jsClick(btnInvite.get(0), "Invite Button");	
				Common.info("Sent invite count : "+count);
				sleep(3000);
				btnInvite = browser.findElements(By.xpath("//button/span[text()='Invite']"));
			}
			
			Common.info("All Invitations sent successfully");
		}catch(Exception ex) {
			Common.fail("Exception caught while sending the invites , Exception message is : "+ex.getMessage());
		}

	}
	
	
	
	public void follow() {
		try {
			waitForIsClickable(lnkPplIFollow);
			clickOn(lnkPplIFollow, "People I Follow");
			waitForIsClickable(lnkFollowers);
			clickOn(lnkFollowers, "Followers Link");
			for(int i=0;i<3;i++) {
				ScrollEnd();
				sleep(3000);
				ScrollEnd();
				sleep(3000);
				ScrollFirst();
			}
			String strCriteria = Common.getDataFromFollowers("Followers_search_criteria");
			List<WebElement> weFollowers = browser.findElements(By.xpath("//section[@class='follows-recommendation-card__content']"));
			
			for(int i=0;i<weFollowers.size();i++) {
				try {
					WebElement btnFollowLink = weFollowers.get(i).findElement(By.xpath("./following-sibling::button/*[text()='Follow']"));
					WebElement weDesignation = weFollowers.get(i).findElement(By.xpath(".//*[contains(@class,'follows-recommendation-card__headline')]"));
				    String strDesignation = weDesignation.getText();				    
				    String[] str = strCriteria.split(";");
				    boolean blnFound =false;
				    for(int j=0;j<str.length;j++) {
					    if(strDesignation.contains(str[j])) {
					    	blnFound =true;
					    	btnFollowLink = weFollowers.get(i).findElement(By.xpath("./following-sibling::button/*[text()='Follow']"));
					    	clickOn(btnFollowLink, "Follow Link");
					    	Common.info("Followed successfully : "+(i+1));
					    	break;
					    }
				    }
				    if(!blnFound) {
				    	Common.info("Not followed since this does not match the criteria, Actual designation is: "+strDesignation);
				    }

				}catch(Exception e) {
					Common.info("Already followed for : "+(i+1));
				}
				
				
				weFollowers = browser.findElements(By.xpath("//section[@class='follows-recommendation-card__content']"));
			}
			
		}catch(Exception ex) {
			Common.fail("Exception caught while sending the following , Exception message is : "+ex.getMessage());
		}
	}


}
