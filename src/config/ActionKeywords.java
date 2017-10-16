package config;

import static executionEngine.DriverScript.OR;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.client.ClientProtocolException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import utility.DialogWindow;
import utility.Log;
import utility.WebServiceApiResponse;
import executionEngine.DriverScript;

public class ActionKeywords {
	
		public static WebDriver driver;
		private static boolean foundFolder = false;
		private static boolean foundFile = false;
		// Optional, if not specified, WebDriver will search your path for chromedriver.
		  
			
	public static void openBrowser(String object,String data){		
		Log.info("Opening Browser");
		try{
			
			if(data.equals("Mozilla")){
				driver=new FirefoxDriver();
				Log.info("Mozilla browser started");				
				}
			else if(data.equals("IE")){
				//Dummy Code, Implement you own code
				try {
					
				String current = new java.io.File( "." ).getCanonicalPath();
		        System.out.println("Current dir:"+current);
		        System.out.println("Current ie dir:"+findDirectory(new File(current),"IEDriverServer_x64_3.5.1","IEDriverServer.exe"));
				  System.setProperty("webdriver.ie.driver", findDirectory(new File(current),"IEDriverServer_x64_3.5.1","IEDriverServer.exe"));
				  System.setProperty("webdriver.ie.bin", findDirectory(new File("C:\\Program Files\\Internet Explorer"),"Internet Explorer","iexplore.exe"));
				  DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
				  dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);  //If IE fail to work, please remove this and remove enable protected mode for all the 4 zones from Internet options
				 
				driver=new InternetExplorerDriver(dc);
				//((driver.)driver).executeScript(“window.resizeTo(1024, 768);”);
				
				Log.info("IE browser started");
				} catch (Exception e) {
					e.getMessage();// TODO: handle exception
				}
				}
			else if(data.equals("Chrome")){
				//Dummy Code, Implement you own code
				// Optional, if not specified, WebDriver will search your path for chromedriver.
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--start-maximized");
				String current = new java.io.File( "." ).getCanonicalPath();
		        System.out.println("Current dir:"+current);
				  System.setProperty("webdriver.chrome.driver", findDirectory(new File(current),"chromedriver","chromedriver.exe"));
				driver=new ChromeDriver(options);
				Log.info("Chrome browser started");
				}
			
			int implicitWaitTime=(10);
			driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
			//driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
			driver.manage().timeouts().setScriptTimeout(1, TimeUnit.SECONDS);
			
		}catch (Exception e){
			Log.info("Not able to open the Browser --- " + e.getMessage());
			DriverScript.bResult = false;
		}
	}
	
	private static String findDirectory(File parentDirectory, String dirName, String filename) {
        String openFile=null;
        File[] files = parentDirectory.listFiles();
        ArrayList<File> files2 = new ArrayList<File>();
        for (File file : files) {
            if (file.isFile()) {
                continue;
            }
            if (file.getName().equals(dirName)) {
                foundFolder = true;
                files2.addAll( Arrays.asList(file.listFiles()));
                break;
            }
        }
        
        for (File file : files2) {
            
            if (file.getName().equals(filename)) {
            	foundFile = true;
            	openFile = file.getPath();
                break;
            }
        }
        if(foundFile)
        {
        	return openFile;
        }else{
        	return null;
        }
    }
	
	public static void navigate(String object, String data){
		try{
			Log.info("Navigating to URL");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(Constants.URL);
			
		}catch(Exception e){
			Log.info("Not able to navigate --- " + e.getMessage());
			DriverScript.bResult = false;
			}
		}
	public static void webServiceApi(String object, String data){
		try {
			
			 driver.navigate().to(data);	
			 WebServiceApiResponse weatherApiResponse=new WebServiceApiResponse();
			 String ExpectedString=weatherApiResponse.GetResponse(data);
			 //Assert.assertTrue(webElement.getText().equals(ExpectedString));
		}catch (ClientProtocolException cpe)
		{
			Log.info("Web Service failed --- " + cpe.getMessage());
			DriverScript.bResult = false;
		} catch (IOException ioe) {
			Log.info("Not able to navigate --- " + ioe.getMessage());
			DriverScript.bResult = false;
		} 
		
	 
	}

	public static void httpAuthentication (String object, String data){
		try{
			Log.info("Http Authentication "+data);
			(new Thread(new DialogWindow(Constants.httpAuthentication))).start();
			//driver.get(Constants.URL);
		}catch(Exception e){
			Log.info("Not able to do Http Authentication --- " + e.getMessage());
			DriverScript.bResult = false;
			}
		}
	
	public static void click(String object, String data){
		try{
			Log.info("Clicking on Webelement "+ object +" "+data);
			if(OR.getProperty(object).contains("{item_name}"))
				driver.findElement(By.xpath(OR.getProperty(object).replace("{item_name}", data))).click();
			else
				driver.findElement(By.xpath(OR.getProperty(object))).click();
		 }catch(Exception e){
 			Log.error("Not able to click --- " + e.getMessage());
 			DriverScript.bResult = false;
         	}
		}
	
	public static void input(String object, String data){
		try{
			if(data.equals("randomCellNumber"))
			{
				data = generateCellNumber();
			}
			Log.info("Entering the text in " + object);
			driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
		 }catch(Exception e){
			 Log.error("Not able to Enter UserName --- " + e.getMessage());
			 DriverScript.bResult = false;
		 	}
		}
	public static String generateCellNumber()
	{
		String []cellNumberPre = {"0711", "076", "079",	"082",
								  "08500", "08505", "0710", "0719",
								  "073"	,"075",	"077", "0741",
								  "0742", "0743", "0744"};
		Random random = new Random();
		int index = random.nextInt(cellNumberPre.length);
		System.out.println(cellNumberPre[index]);
		String randomNumbers = RandomStringUtils.randomNumeric(7);
		String phNo = cellNumberPre[index]+randomNumbers;
		
		return phNo.substring(0, 10);
	}

	public static void waitFor(String object, String data) throws Exception{
		try{
			Log.info("Wait for 10 seconds");
			Thread.sleep(5000);
		 }catch(Exception e){
			 Log.error("Not able to Wait --- " + e.getMessage());
			 DriverScript.bResult = false;
         	}
		}

	public static void closeBrowser(String object, String data){
		try{
			Log.info("Closing the browser");
			driver.quit();
		 }catch(Exception e){
			 Log.error("Not able to Close the Browser --- " + e.getMessage());
			 DriverScript.bResult = false;
         	}
		}

	}