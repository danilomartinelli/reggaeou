package br.reaggeou.seleinium;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class UserTest {
	private static WebDriver driver;

	@BeforeClass
	public static void setupChrome() {
		System.setProperty("webdriver.chrome.driver",
				"/Users/daniloleonemartinelli/chromedriver");
		driver = new ChromeDriver();
	}
	
	@Test
	public void emptyFieldsTest() {
		driver.get("http://localhost:8080/Reaggeou/index.jsp");

		WebElement ctg1Element = driver.findElement(By.xpath("/html/body/div/div[3]/div/form/div[1]/div[3]/div[1]/div/label"));
		ctg1Element.click();
		
		WebElement ctg2Element = driver.findElement(By.xpath("/html/body/div/div[3]/div/form/div[1]/div[3]/div[2]/div/label"));
		ctg2Element.click();

		WebElement onSubmitElement = driver.findElement(By.xpath("/html/body/div/div[3]/div/form/div[2]/div/button"));
		onSubmitElement.click();

		String content = driver.getPageSource();

		Assert.assertTrue(content.contains("Os campos estão vazios"));

	}
	
	@Test
	public void emptyEmailFieldTest() {
		driver.get("http://localhost:8080/Reaggeou/index.jsp");

		WebElement ctg1Element = driver.findElement(By.xpath("/html/body/div/div[3]/div/form/div[1]/div[3]/div[1]/div/label"));
		ctg1Element.click();
		
		WebElement ctg2Element = driver.findElement(By.xpath("/html/body/div/div[3]/div/form/div[1]/div[3]/div[2]/div/label"));
		ctg2Element.click();
		
		WebElement telElement = driver.findElement(By.xpath("//*[@id=\"tel\"]"));
		telElement.sendKeys("71993037767");

		WebElement onSubmitElement = driver.findElement(By.xpath("/html/body/div/div[3]/div/form/div[2]/div/button"));
		onSubmitElement.click();

		String content = driver.getPageSource();

		Assert.assertTrue(content.contains("O campo email está vazio"));

	}
	
	@Test
	public void emptyTelFieldTest() {
		driver.get("http://localhost:8080/Reaggeou/index.jsp");

		WebElement ctg1Element = driver.findElement(By.xpath("/html/body/div/div[3]/div/form/div[1]/div[3]/div[1]/div/label"));
		ctg1Element.click();
		
		WebElement ctg2Element = driver.findElement(By.xpath("/html/body/div/div[3]/div/form/div[1]/div[3]/div[2]/div/label"));
		ctg2Element.click();
		
		WebElement emailElement = driver.findElement(By.xpath("//*[@id=\"email\"]"));
		emailElement.sendKeys("danilo@email.com");

		WebElement onSubmitElement = driver.findElement(By.xpath("/html/body/div/div[3]/div/form/div[2]/div/button"));
		onSubmitElement.click();

		String content = driver.getPageSource();

		Assert.assertTrue(content.contains("O campo telefone está vazio"));

	}

	@AfterClass
	public static void finish() {
		driver.quit();
	}


}
