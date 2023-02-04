package com.iris.techtask;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateVacancy {

	@Test
	public void createVacancy() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
		// Initializing chrome driver.
		final WebDriver driver = new ChromeDriver();

		// For synchronization, adding implicit wait for 10 seconds on all the elements
		// on web page.
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Open the application url in the browser.
		driver.get("https://uat.networxrecruitment.net/");

		// Maximize the window
		driver.manage().window().maximize();

		// Clear the email address field
		driver.findElement(By.id("emailaddress")).clear();

		// Enter email id
		driver.findElement(By.id("emailaddress")).sendKeys("candidate27@networxtesting.com");

		// Enter password
		driver.findElement(By.id("password")).sendKeys("CandidateTask020223!");

		// Click SignIn button
		driver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();

		// Click 'Create Vacancy' button
		driver.findElement(By.xpath("//span[contains(text(),'Create Vacancy')]")).click();

		// Validate whether the current focus is on 'Create Vacancy' tab
		WebElement createVacancyiframe = new WebDriverWait(driver, Duration.ofSeconds(50))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h4[text()='Create Vacancy']")));
		Assert.assertEquals(createVacancyiframe.getText(), "Create Vacancy");

		// Select new vacancy
		driver.findElement(By.xpath("//h4[text()='Create Vacancy']")).getText();

		// Expand 'New Vacancy' drop down
		driver.findElement(By.xpath("(//span[@class='fancytree-expander'])[1]")).click();
		Thread.sleep(5000);

		// Select required option.
		driver.findElement(
				By.xpath("//span[contains(text(),'### NOT IN USE ### Application method is application form')]"))
				.click();
		// select structure level
		driver.findElement(By.xpath("(//span[contains(text(),'Test Structure')])[1]")).click();

		// Click 'Create Vacancy' button
		driver.findElement(By.id("CreateVacancyButton")).click();

		// Fill vacancy details
		WebElement vacancyDetails = new WebDriverWait(driver, Duration.ofSeconds(50))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='stageHeader1']")));
		Assert.assertEquals(vacancyDetails.getText(), "Vacancy Details");
		Thread.sleep(50);
		driver.switchTo().frame(driver.findElement(By.id("frame-4")));

		// Select Company.
		Select companyDropdown = new Select(driver.findElement(By.xpath("//table//select[@id='inp37270202']")));

		List<WebElement> companyOptions = companyDropdown.getOptions();
		companyOptions.remove(0);
		// Creating an ArrayList for all companies
		ArrayList<String> companies = new ArrayList<String>();
		for (WebElement company : companyOptions) {
			companies.add(company.getText());
		}
		for (String companyName : companies) {
			// selecting element by it's visible text
			if (companyName.equals("Torus")) {
				companyDropdown.selectByVisibleText(companyName);
			}
			break;
		}

		// Enter number of vacancies
		driver.findElement(By.id("inp37270198")).sendKeys("10");
		Select locationListDropdown = new Select(driver.findElement(By.id("inp37270179")));
		List<WebElement> locationOptions = locationListDropdown.getOptions();
		locationOptions.remove(0);

		// Creating an ArrayList for all location names and select required location
		ArrayList<String> locations = new ArrayList<String>();
		for (WebElement location : locationOptions) {
			locations.add(location.getText());
		}
		for (String locationName : locations) {
			// selecting element by it's visible text
			if (locationName.equals("Bank Park House")) {
				locationListDropdown.selectByVisibleText(locationName);
				break;
			}
		}

		// Select required job type from drop down
		Select jobTypeDropdown = new Select(driver.findElement(By.id("inp37270180")));
		List<WebElement> jobTypeOptions = jobTypeDropdown.getOptions();
		jobTypeOptions.remove(0);
		// Creating an ArrayList for all manufacturer names
		ArrayList<String> jobTypes = new ArrayList<String>();
		for (WebElement job : jobTypeOptions) {
			jobTypes.add(job.getText());
		}
		for (String jobType : jobTypes) {
			// selecting element by it's visible text
			if (jobType.equals("Full Time")) {
				jobTypeDropdown.selectByVisibleText(jobType);
				break;
			}
		}

		// is DBS check required? Yes
		driver.findElement(By.id("inpOption74835394")).click();

		// Select Status from drop down
		Select statusDropdown = new Select(driver.findElement(By.id("inp37270183")));
		List<WebElement> statusOptions = statusDropdown.getOptions();
		statusOptions.remove(0);
		// Creating an ArrayList for all statuses
		ArrayList<String> statusList = new ArrayList<String>();
		for (WebElement job : statusOptions) {
			statusList.add(job.getText());
		}
		for (String status : statusList) {
			// selecting element by it's visible text
			if (status.equals("Permanent")) {
				statusDropdown.selectByVisibleText(status);
				break;
			}
		}
		driver.findElement(By.id("inp37270221")).sendKeys("IRIS");
		driver.findElement(By.id("inp37270222")).sendKeys("fixed");
		driver.findElement(By.id("inp37270223")).sendKeys("020223");
		driver.switchTo().defaultContent();
		for (int i = 0; i < 4; i++) {
			driver.findElement(By.xpath("//button[@id='NextButton']")).click();
			Thread.sleep(10);
		}

		driver.switchTo().frame(driver.findElement(By.id("frame-11")));

		// Add document
		driver.findElement(By.xpath("//button[@title='Add Document']")).click();
		String filePath = "C:/Users/hp/Documents/Candidate Task 27.docx";

		// Upload document
		driver.findElement(By.xpath("//span/input[@id='FileUpload']")).sendKeys(filePath);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[contains(text(),'Upload')]")).click();
		Thread.sleep(5000);
		driver.switchTo().defaultContent();
		// Click on next button
		driver.findElement(By.xpath("//button[text()='Next']")).click();
		Thread.sleep(1000);
		driver.switchTo().frame(driver.findElement(By.id("frame-13")));

		// Select salary band
		Select salaryBandDropdown = new Select(driver.findElement(By.xpath("//select[@id='selSalaryBands']")));
		List<WebElement> salaryBandOptions = salaryBandDropdown.getOptions();
		salaryBandOptions.remove(0);
		driver.findElement(By.id("selSalaryBands")).click();
		driver.findElement(By.xpath("//input[@id='chkSalaryBand13016']")).click();

		// Input post code
		driver.findElement(By.id("txtPostcode")).sendKeys("HX2 9DL");
		driver.findElement(By.xpath("//button[text()='Search']")).click();

		// Select the 'Discipline'
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("BusyBox")));
		WebElement checkbox = driver.findElement(By.cssSelector(
				"#DisciplinesTree > ul.ui-fancytree.fancytree-container.fancytree-plain > li:nth-child(1) > span > span.fancytree-checkbox"));
		checkbox.click();
		driver.switchTo().defaultContent();

		// Click 'Next'
		driver.findElement(By.xpath("//button[text()='Next']")).click();
		// Click 'Publish Later'
		driver.findElement(By.xpath("//button[text()='Publish Later']")).click();
		
	}
}
