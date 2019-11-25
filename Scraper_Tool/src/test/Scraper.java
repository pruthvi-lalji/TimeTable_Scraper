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
	
	public static boolean weekChecker;
	public static int weekNum;
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
		
		

		do {
			String weekName = driver.findElement(By.xpath("/html/body/table[1]/tbody/tr[2]/td/table/tbody/tr/td[3]/span[2]")).getText();
			weekNum = Integer.parseInt(weekName);
			System.out.println(weekNum);
			loopMethod(driver);	
		}
		while(weekNum < 52);
			System.out.println("No More Data");
			driver.close();
			System.exit(0);		
	}
		
		
		
	}
	public static void loopMethod(WebDriver driver) throws InterruptedException {
		for(int i=1; i< 7;i++) {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
			Boolean isWholePresent = driver.findElements(By.className("columnTitles")).size() > 0;
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			if(isWholePresent) {
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
				Boolean isPresent = driver.findElements(By.xpath("/html/body/table["+i+"]/tbody/tr[1]")).size() > 0;
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
				//System.out.println(isPresent);
				if(isPresent) {
					String table = driver.findElement(By.xpath("/html/body/table["+ i +"]/tbody")).getText(); 	
					System.out.println("Text in cell: " + table);
				}
				//If there is nothing on the day then moves on to the next day
				continue;
			}
			else {
				//If there is nothing on this Week then the system doesn't check the whole page and moves on to the next week.
				break;
			}
		}

	driver.findElement(By.xpath("//*[@id=\"bNextWeek\"]")).click();
	Thread.sleep(1000);
	}
	
	
		
}
