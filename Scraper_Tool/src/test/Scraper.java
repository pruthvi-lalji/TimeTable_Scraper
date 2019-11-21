package test;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class Scraper {
	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C://Selenium//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		//driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.navigate().to("https://teaching.brunel.ac.uk/SWS-1920/login.aspx");
		JFrame frame = new JFrame("InputDialog Example #1");
		String iD = JOptionPane.showInputDialog(frame, "Enter your ID");
		driver.findElement(By.id("tUserName")).sendKeys(iD);
		
		Box box = Box.createHorizontalBox();

		JLabel jl = new JLabel("Password: ");
		box.add(jl);

		JPasswordField jpf = new JPasswordField(24);
		box.add(jpf);

		int button = JOptionPane.showConfirmDialog(null, box, "Enter your password", JOptionPane.OK_CANCEL_OPTION);

		if (button == JOptionPane.OK_OPTION) {
		    char[] input = jpf.getPassword();
		  String pass = String.copyValueOf(input);
	
		
		
		driver.findElement(By.id("tPassword")).sendKeys(pass);
		driver.findElement(By.id("bLogin")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"LinkBtn_pos\"]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"dlFilter\"]/option[4]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"dlObject\"]/option[72]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"lbWeeks\"]/option[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"lbWeeks\"]/option[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"lbDays\"]/option[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"dlType\"]/option[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"bGetTimetable\"]")).click();
		Thread.sleep(1000);
		
		
		for(int i=1; i< 7;i++) {
			String table = driver.findElement(By.xpath("/html/body/table["+ i +"]/tbody")).getText(); 	
			System.out.println("Text in cell: " + table);
		}
		
		//driver.findElement(By.id("bNextWeek")).click();
			
//		String table = driver.findElement(By.xpath("/html/body/table[2]/tbody")).getText(); 
//		/* List<WebElement> rows = table.findElements(By.tagName("tr")); */
//		System.out.println("Text in cell: " + table);
	
		Thread.sleep(10000);
		driver.close();
	}
		
		
		
	}
	
	
		
}
