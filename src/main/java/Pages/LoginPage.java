package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.Common;

public class LoginPage extends Page {

	
	
	@FindBy(xpath="//*[@class='nav__button-secondary']")
	private WebElement btnNavSignIn;
	
	@FindBy(xpath="//*[@id='username']")
	private WebElement txtUsername;
	
	@FindBy(xpath="//*[@id='password']")
	private WebElement txtPassword;
	
	@FindBy(xpath="//*[@aria-label='Sign in']")
	private WebElement btnSignIn;
	
	@FindBy(xpath="//*[@data-control-name='nav.sales_navigator']")
	private WebElement lnkSalesNavigator;
	
	@FindBy(id="mynetwork-tab-icon")
	private WebElement lnkMyNetwork;
	
	@FindBy(xpath="//*[@role='presentation']//a[@data-control-name='connections']")
	private WebElement lnkConnections;
	
	@FindBy(xpath="//a[@data-control-name='search_with_filters']")
	private WebElement lnkSearchWithFilters;
	
	@FindBy(xpath="//button[@data-control-name='all_filters']")
	private WebElement btnAllFilters;
	
	
	
	
	
	public LoginPage(WebDriver browser) {
		super(browser);
	}

	
	public void loginToApplication() {
		clickOn(btnNavSignIn, "NavigateSignIn Button");
		enterText(txtUsername, Common.getUserName());
		enterText(txtPassword, Common.getPassword());
		clickOn(btnSignIn, "Sign In Button");
	}
	
	public SalesNavigatorPage clickOnSalesNavigator() {
		clickOn(lnkSalesNavigator, "Sales Navigator Link");
		navigateToNewWindow();
		return new SalesNavigatorPage(browser);
	}
	
	public Connections NavigateToConnnections() {
		clickOn(lnkMyNetwork, "My Network Link");
		clickOn(lnkConnections, "Coonections Link");
		clickOn(lnkSearchWithFilters, "Search with filters");
		clickOn(btnAllFilters, "All filters");
		return new Connections(browser);
	}
	
	public MyNetworkPage NavigateToMyNetworkPage() {
		clickOn(lnkMyNetwork, "My Network Link");
		return new MyNetworkPage(browser);
	}
}
