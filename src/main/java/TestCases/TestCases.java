package TestCases;

import org.testng.annotations.Test;

import Pages.Connections;
import Pages.LinkedInApplication;
import Pages.LoginPage;
import Pages.MyNetworkPage;
import Pages.SalesNavigatorPage;
import utilities.Common;

public class TestCases {
	
	//h2[text()='Saved Searches']

	@Test
	public void SavedSearcher() {
		if(Common.checkValidity()) {
			LinkedInApplication LinkedInApplication = new LinkedInApplication();
			LoginPage loginPage = LinkedInApplication.openLinkedInApplication();
			loginPage.loginToApplication();
			SalesNavigatorPage salesNavigatorPage = loginPage.clickOnSalesNavigator();
			salesNavigatorPage.selectSavedSearches();
			salesNavigatorPage.sendMessages();
		}
	}
	
	@Test
	public void SavedLeads() {
		if(Common.checkValidity()) {
			LinkedInApplication LinkedInApplication = new LinkedInApplication();
			LoginPage loginPage = LinkedInApplication.openLinkedInApplication();
			loginPage.loginToApplication();
			 SalesNavigatorPage salesNavigatorPage = loginPage.clickOnSalesNavigator();
			 salesNavigatorPage.selectLeadLists();
			 salesNavigatorPage.sendMessagesSavedLeads();
			 
		}
	}
	
	@Test
	public void Recommendedleads() {
		if(Common.checkValidity()) {
			LinkedInApplication LinkedInApplication = new LinkedInApplication();
			LoginPage loginPage = LinkedInApplication.openLinkedInApplication();
			loginPage.loginToApplication();
			 SalesNavigatorPage salesNavigatorPage = loginPage.clickOnSalesNavigator();
			 salesNavigatorPage.selectRecommendedLeads();
			 salesNavigatorPage.sendMessagesRecLeads();
		}
	}
	
	@Test
	public void Connections() {
		if(Common.checkValidity()) {
			LinkedInApplication LinkedInApplication = new LinkedInApplication();
			LoginPage loginPage = LinkedInApplication.openLinkedInApplication();
			loginPage.loginToApplication();
			Connections Connections =  loginPage.NavigateToConnnections();
			Connections.selectAndApplyFilters();
			Connections.sendMessagesRecLeads();
			 
		}
	}
	
	@Test
	public void InvitationsAccepts() {
	
		if(Common.checkValidity()) {
			LinkedInApplication LinkedInApplication = new LinkedInApplication();
			LoginPage loginPage = LinkedInApplication.openLinkedInApplication();
			loginPage.loginToApplication();
			MyNetworkPage MyNetworkPage = loginPage.NavigateToMyNetworkPage();
			MyNetworkPage.seeAllInvites();
			
		}
	}
	
	
	@Test
	public void You_may_know() {
	
		if(Common.checkValidity()) {
			LinkedInApplication LinkedInApplication = new LinkedInApplication();
			LoginPage loginPage = LinkedInApplication.openLinkedInApplication();
			loginPage.loginToApplication();
			MyNetworkPage MyNetworkPage = loginPage.NavigateToMyNetworkPage();
			
			MyNetworkPage.sendInvites();
		}
	}
	
	
	@Test
	public void Followers() {
	
		if(Common.checkValidity()) {
			LinkedInApplication LinkedInApplication = new LinkedInApplication();
			LoginPage loginPage = LinkedInApplication.openLinkedInApplication();
			loginPage.loginToApplication();
			MyNetworkPage MyNetworkPage = loginPage.NavigateToMyNetworkPage();
			
			MyNetworkPage.follow();
		}
	}
	
	
	@Test
	public void Scan_profiles() {
		if(Common.checkValidity()) {
			LinkedInApplication LinkedInApplication = new LinkedInApplication();
			LoginPage loginPage = LinkedInApplication.openLinkedInApplication();
			loginPage.loginToApplication();
			SalesNavigatorPage salesNavigatorPage = loginPage.clickOnSalesNavigator();
			salesNavigatorPage.selectSavedSearches();
			salesNavigatorPage.viewProfiles();
		}
	}
}
