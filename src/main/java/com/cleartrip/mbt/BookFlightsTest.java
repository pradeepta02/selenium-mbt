package com.cleartrip.mbt;

import java.util.concurrent.TimeUnit;

import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.java.annotation.GraphWalker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.cleartrip.mbt.core.ElementAction;

/**
 * Sample Test class to demonstrate model based testing (MBT)
 */
@GraphWalker(value = "random(edge_coverage(100))", start = "e_StartBrowser")
public class BookFlightsTest extends ExecutionContext implements IBaseModel {

	private WebDriver driver;

	private ElementAction action;

	@Override
	public void e_StartBrowser() {

		driver = new ChromeDriver();

		// To-Do: Read app specific configurations from project.properties file
		driver.get("https://www.cleartrip.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		action = new ElementAction(driver);
	}

	@Override
	public void e_SearchForFlight() {

		// To-Do: Read object locators from properties file.
		action.clear("NAME,origin");
		action.type("NAME,origin", "BLR");

		// To-Do: In production level code, do not use sleep, use WebDriver
		// explicit waits instead.
		action.sleep(2000);

		action.pressKey("NAME,origin", Keys.ENTER);

		action.clear("NAME,destination");
		action.type("NAME,destination", "BBI");

		action.sleep(2000);

		action.pressKey("NAME,destination", Keys.ENTER);

		action.click("XPATH,//td[@data-month='2']/a[text()='15']");

		action.sleep(2000);

		action.click("ID,SearchBtn");
	}

	@Override
	public void e_SelectFlight() {
		action.click("XPATH,//ul[@class='listView flights']/li[1]//button[@class='booking']");
	}

	@Override
	public void e_ContinueBooking() {
		action.uncheck("ID,insurance_box");
		action.click("XPATH,//input[@value='Continue booking']");
	}

	@Override
	public void e_AddEmailAddress() {
		action.clear("XPATH,//input[@type='email']");
		action.type("XPATH,//input[@type='email']", "cleartrip.test01@gmail.com");
		action.click("ID,LoginContinueBtn_1");
	}

	@Override
	public void e_NavigateToCalendarPage() {
		action.click("XPATH,//a[@class='calendarViewLink']");
	}

	@Override
	public void e_SelectLowestPriceFlight() {
		action.click("XPATH,//td[@class=' weekEnd bestPrice searchedOn'][1]");
	}

	@Override
	public void e_CancelAddMeals() {
		action.click("XPATH,//a[@title='Close window']");
	}

	@Override
	public void e_CancelSelectSeats() {
		action.click("XPATH,//a[@title='Close window']");
	}

	@Override
	public void e_SelectSeats() {
		action.click("XPATH,//div[@class='addonSelectedSeats row']//button[@class='action selectAddonButton']");
	}

	@Override
	public void e_AddMeals() {
		action.click("XPATH,//div[@id='beforeMeals']//button[@class='action selectAddonButton']");
	}

	@Override
	public void v_HomePage() {
		try {
			org.junit.Assert.assertEquals(driver.getTitle(), "Cleartrip - Flights, Hotels, Local, Trains, Packages");
		} catch (AssertionError ae) {
			// To-Do: Print stack trace to a error log file
			ae.printStackTrace();
		}
	}

	@Override
	public void v_ListPage() {
		try {
			org.junit.Assert.assertEquals(driver.getTitle(), "Cleartrip | Bangalore → Bhubaneswar");
		} catch (AssertionError ae) {
			ae.printStackTrace();
		}
	}

	@Override
	public void v_BookingsPage() {
		try {
			org.junit.Assert.assertEquals(driver.getTitle(), "Cleartrip | Book your flight securely in simple steps");
		} catch (AssertionError ae) {
			ae.printStackTrace();
		}
	}

	@Override
	public void v_EmailAddressPage() {
		try {
			org.junit.Assert.assertTrue(driver.findElement(By.id("username")).isDisplayed());
		} catch (AssertionError ae) {
			ae.printStackTrace();
		}
	}

	@Override
	public void v_TravellerDetailsPage() {
		try {
			org.junit.Assert.assertTrue(driver.findElement(By.id("AdultFname1")).isDisplayed());
		} catch (AssertionError ae) {
			ae.printStackTrace();
		}
	}

	@Override
	public void v_AddMealsPage() {
		try {
			org.junit.Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='mealSet BLR-HYD']")).isDisplayed());
		} catch (AssertionError ae) {
			ae.printStackTrace();
		}
	}

	@Override
	public void v_SelectSeatsPage() {
		try {
			org.junit.Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Select your seat']")).isDisplayed());
		} catch (AssertionError ae) {
			ae.printStackTrace();
		}
	}

	@Override
	public void e_NavigateToHomePage() {
		driver.get("https://www.cleartrip.com/");
	}
}
