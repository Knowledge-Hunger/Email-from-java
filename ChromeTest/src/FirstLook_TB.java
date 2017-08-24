import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.Select;



/**
 * @author Astha Verma
 *
 */
public class FirstLook_TB {
	
	/**
	 * entity First Look - Ticket Buyer
	 * 
	 * @param env - Environment i.e. the URL
	 * @param username - email to login with
	 * @param password - password of that account
	 * @param event_poster - S3 link of the poster of that event
	 * @param event_name - Event name as should appear in Event page
	 * 
	 * @param qty - Tickets Quantity
	 * @param pay_type - Payment Type
	 * 
	 * 
	 * @throws NoSuchElementException - If in case the element being mined is not found on page
	 * @throws IOException - If in case the file is not present/not able to open/corrupt file
	 * @throws NullPointerException - If some vale from sheet comes out to be null
	 * 
	 */
	public void firstLook_TB_Chrome(String excelFilePath, int workbookSheet) throws NoSuchElementException, IOException, NullPointerException{
		
		WebDriver driver=null;
		FileInputStream inputStream=null;
		Workbook workbook=null;
		String cellValue1=null,observation=null,result=null;
		 Cell cell1=null;
		 Row nextRow=null;
		
		//Reading the Input Sheet
		 try {
				inputStream = new FileInputStream(new File(excelFilePath));
		 try {
				workbook = new XSSFWorkbook(inputStream);
			
		        Sheet firstSheet = workbook.getSheetAt(workbookSheet);
		        
		        inputStream.close();

				 //check if the file is open...then close it
				 Runtime.getRuntime().exec("TASKKILL /FI \"WINDOWTITLE eq Input_Data_Sheet - Excel\"");
				 
				 try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        
		        
		        
		        
		         //Getting the column index  by headings
		        
		        Row firstRow = firstSheet.getRow(0);  
		        int colNum[]=new int[11];
		        for (int i = 0 ;i<=firstRow.getPhysicalNumberOfCells();i++){
		            cell1 = firstRow.getCell(i);
		            if(cell1!=null)
		            	cellValue1 = cell1.getStringCellValue();
		            else
		            	cellValue1="<cell is empty>";
		            if ("Environment".equals(cellValue1)){
		            	 colNum[0]=cell1.getColumnIndex();
		            }
		            if ("Username".equals(cellValue1)){
		            	 colNum[1]=cell1.getColumnIndex();
		            }
		            if ("Password".equals(cellValue1)){
		            	 colNum[2]=cell1.getColumnIndex();
		            	 
		            }
		            if ("Poster Image Link (From S3)".equals(cellValue1)){
		            	 colNum[3]=cell1.getColumnIndex();
		            	 
		            }
		            if ("Event Name".equals(cellValue1)){
		            	 colNum[4]=cell1.getColumnIndex();
		            	 
		            }
		            if ("Ticket Type".equals(cellValue1)){
		            	 colNum[5]=cell1.getColumnIndex();
		            	 
		            }
		            if ("Quantity of Tickets".equals(cellValue1)){
		            	 colNum[6]=cell1.getColumnIndex();
		            	 
		            }
		            if ("Mode of Payment".equals(cellValue1)){
		            	 colNum[7]=cell1.getColumnIndex();
		            	 
		            }
		            if ("User Account Type - Chrome".equals(cellValue1)){
		            	 colNum[8]=cell1.getColumnIndex();
		            	 
		            }
		            if ("Observation - Chrome".equals(cellValue1)){
		            	 colNum[9]=cell1.getColumnIndex();
		            	 
		            }
		            if ("Result - Chrome".equals(cellValue1)){
		            	 colNum[10]=cell1.getColumnIndex();
		            	 
		            }
		            
		           }
		        
		       
		        
		        
		        
		        Iterator<Row> iterator = firstSheet.iterator();
		        if(iterator.hasNext())
		        	iterator.next();
		        else
		        	System.out.println("The excel is empty..!!");
		 
		 while (iterator.hasNext()){
			 System.out.println("\n-----------------------------------------------------------------------------\n");
			 System.out.println("\nin...."+iterator.toString());
	         String env="",username="",password="",event_poster="",event_name="",type="",qty="",pay_type="",acc_type="";
			 try{
	        	nextRow= iterator.next();
	        	env=nextRow.getCell(colNum[0]).getStringCellValue();
	        	username=nextRow.getCell(colNum[1]).getStringCellValue();
	        	password=nextRow.getCell(colNum[2]).getStringCellValue();
	        	event_poster=nextRow.getCell(colNum[3]).getStringCellValue();
	        	event_name=nextRow.getCell(colNum[4]).getStringCellValue();
	        	type=nextRow.getCell(colNum[5]).getStringCellValue();
	        	qty=nextRow.getCell(colNum[6]).getStringCellValue();
	        	pay_type=nextRow.getCell(colNum[7]).getStringCellValue();
//	        	double qty_temp=nextRow.getCell(colNum[6]).getNumericCellValue();
//	        	qty=Double.toString(qty_temp);
//	        	double pay_type_temp=nextRow.getCell(colNum[7]).getNumericCellValue();
//	        	pay_type=Double.toString(pay_type_temp);
	        	acc_type=nextRow.getCell(colNum[8]).getStringCellValue();
	        	observation=nextRow.getCell(colNum[9]).getStringCellValue();
	        	
			 }catch (NullPointerException npe){
				 //npe.printStackTrace();
				 
			 }
	        	
	            Iterator<Cell> cellIterator = nextRow.cellIterator();
	            observation=null;

		
		
		//sign in with existing account
		 try {
				System.setProperty("webdriver.chrome.driver", "C:\\Softwares\\jars\\chromedriver_win32\\chromedriver.exe");//static
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
				
				driver = new ChromeDriver(capabilities);
//				
				driver.manage().window().setPosition(new Point(-2000, 0));
				driver.get(env);
				
				
				try {
					Thread.sleep(2000);
					//If subscribe Option comes up on the screen
					if(driver.findElement(By.id("ematic_closeExitIntentOverlay_1_xl_1_0")).isDisplayed())
						driver.findElement(By.id("ematic_closeExitIntentOverlay_1_xl_1_0")).click();
					//--------If subscribe Option comes up on the screen----------//
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
				WebElement ele=driver.findElement(By.linkText("SIGN IN"));
				ele.click();
				WebElement emailid=driver.findElement(By.id("exampleInputEmail1"));
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				//If subscribe Option comes up on the screen
				if(driver.findElement(By.id("ematic_closeExitIntentOverlay_1_xl_1_0")).isDisplayed())
					driver.findElement(By.id("ematic_closeExitIntentOverlay_1_xl_1_0")).click();
				//--------If subscribe Option comes up on the screen----------//
				
				
				emailid.click();
				
				emailid.sendKeys(username);
				WebElement pass=driver.findElement(By.id("exampleInputPassword1"));
				pass.sendKeys(password);
				WebElement submitButton=driver.findElement(By.id("submitButton1"));
				submitButton.click();
				try {
					Thread.sleep(6000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//signed in -- now check whether the user is EO or TB:
				WebElement user=driver.findElement(By.className("dropbtn2"));
				if(user.isDisplayed())
					System.out.println("Signed In Successfully..!!");
				List<WebElement> forms = driver.findElements(By.xpath("//*[@id=\"rightSideWrapper\"]/div[1]/div[1]/ul/li[2]/div/div/a"));
				int count = forms.size();
				//System.out.println("Size of acc_type:"+count);
				if(count==3){
					System.out.println("Account Type : TB.");
					acc_type="TB";
				}
				else if(count==4)
					{System.out.println("Account Type : EO.");
					acc_type="EO";
					try {
						Thread.sleep(6000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					WebElement homepage=driver.findElement(By.xpath("//img[contains(@src,'https://s3-ap-southeast-1.amazonaws.com/homepage.ticketmelon.com/logo3x.png')]"));
					Actions actions = new Actions(driver);
				    actions.moveToElement(homepage).click().perform();
					//homepage.click();
				
					}
				
				//select an event
				//find the correct event
				if(event_poster.contains("poster-default")){
				//driver.findElement(By.xpath("//a[@href='/event/"+event_name+"']")).click();
				String temp_evt_name=event_name.toLowerCase();
				String temp_evt_name3=temp_evt_name.replace(' ', '-');
				String temp_evt_name2=temp_evt_name3.replaceAll("[\\+\\.\\^:,%$#@!&\"']*","");
				List<WebElement> event_name_tile=driver.findElements(By.xpath("//div[@class = 'border-box']/a"));
				for (WebElement option : event_name_tile) {

					String link=option.getAttribute("href");
					System.out.println("link :"+link);
					if(link.contains(temp_evt_name2)){
						driver.get(link);
						break;
					}
				}
				System.out.println("lowercase of event name:"+temp_evt_name2);
				}
				else
				{
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					WebElement event=driver.findElement(By.xpath("//img[contains(@src,'"+event_poster+"')]"));
					event.click();
				}
				
				
//				System.out.println("href :"+event_name_tile);
//				WebElement event=driver.findElement(By.xpath("//img[contains(@src,'"+event_poster+"')]"));
//				event.click();
				
				WebElement eventPage=driver.findElement(By.tagName("h3"));
				System.out.println("Event Name on Page :"+eventPage.getText());
				if(eventPage.getText().equalsIgnoreCase(event_name))
					System.out.println("On right event Page..!!");
				else
					{System.out.println("On wrong event Page..!!");
					observation="Coudn't find the Event Page..!!";
					result="Fail";

					cell1=nextRow.createCell(colNum[8]);
					if(cell1!=null)
						cell1.setCellValue(acc_type);
					else
						System.out.println("Actual Report column not present!!");
					cell1=nextRow.createCell(colNum[9]);
					if(cell1!=null)
						cell1.setCellValue(observation);
					else
						System.out.println("Actual Report column not present!!");
					cell1=nextRow.createCell(colNum[10]);
					if(cell1!=null)
						cell1.setCellValue(result);
					else
						System.out.println("Actual Report column not present!!");
					 FileOutputStream outputStream = new FileOutputStream(new File(excelFilePath));
					 workbook.write(outputStream);
					 outputStream.close();
					 driver.close();
					 
						continue;
					}
				
				//select a ticket type
				WebElement tkt_qty=driver.findElement(By.name(type)); // hard coded for now as 2290 is the Id of this ticket_type in DB.//static
//				Select dropdown= new Select(tkt_qty);
//				dropdown.selectByValue(qty);
				String temp_type=type.substring(9);
				char temp_type2=temp_type.charAt(0);
				String temp_type3="input[value='"+temp_type2+","+temp_type.substring(1,4)+", 1']";
				System.out.println("tkt_type: "+temp_type3);
				//WebElement tkt_type=driver.findElement(By.cssSelector(temp_type3));//static
				//System.out.println("Price of selected Ticket Type :"+tkt_type.getAttribute("innerText"));
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				List<WebElement> options = tkt_qty.findElements(By.tagName("option"));
				for (WebElement option : options) {

					if(qty.equals(option.getText()))
						option.click();
						
				}
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//pay for it and checkout
				WebElement buy=driver.findElement(By.id("checkoutBtn"));
				try{
					buy.click();
				
				
				
				
				
				//On Checkout Page
				WebElement pay_meth;//=driver.findElement(By.name("payment_method"));
				if(pay_type.equals("1"))
					{
					pay_meth=driver.findElement(By.xpath("//input[@name='payment_method' and @value='0']"));
					System.out.println("Payment Mode : CARD ");
					}
				else
					{
					pay_meth=driver.findElement(By.xpath("//input[@name='payment_method' and @value='1']"));
					System.out.println("Payment Mode : CASH ");
					}
				
				pay_meth.click();
				
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(pay_type.equals("1"))	//CARD PAYMENTS
					{
					driver.findElement(By.id("xcardnumber")).sendKeys("4111111111111111");
					driver.findElement(By.id("xcvv")).sendKeys("123");
					driver.findElement(By.id("xname")).sendKeys("SampleCard");
					driver.findElement(By.id("xcardmonth")).sendKeys("12");
					driver.findElement(By.id("xcardyear")).sendKeys("2020");
					
					}
				else
					{
					
					 WebElement oCheckBox = driver.findElement(By.cssSelector("input[value='1']"));	//CASH PAYMENTS
					 
					 Actions actions = new Actions(driver);

					 actions.moveToElement(oCheckBox).click().perform();
					 
					
					
					//have to close the pop-up of cash payments warning
					String label = "Close";
					driver.findElement(By.xpath("//button[contains(.,'" + label + "')]")).click();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					Select parent = new Select(driver.findElement(By.id("parent_selection")));
					parent.selectByIndex(2);
					Select child = new Select(driver.findElement(By.id("child_selection")));
					child.selectByIndex(2);
					}
					//driver.findElement(By.id("parent_selection")).findElements(By.tagName("option")).;
					//driver.findElement(By.id("child_selection")).findElement(By.tagName("option"));
					driver.findElement(By.id("agree_terms")).click();
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//System.out.println("Buy Tickets : "+driver.findElement(By.id("PConfirmBuyTicket")).getText());
					try{
//					if(driver.findElement(By.id("XConfirmBuyTicket")).isDisplayed() || driver.findElement(By.id("PConfirmBuyTicket")).isDisplayed())
//						driver.findElement(By.id("XConfirmBuyTicket")).click();
//					else if(driver.findElement(By.id("PConfirmBuyTicket")).isDisplayed())
//						driver.findElement(By.id("PConfirmBuyTicket")).click();
						driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();
					}catch(WebDriverException e){
						System.out.println("Didn't get the Checkout Button");
						observation="Didn't get the Checkout Button";
						result="Fail";
					}
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(driver.getPageSource().contains("1-2-3 Service is the One-Stop-Service payment collection service provider.You can pay by cash or via direct debit at all the available 1-2-3 payment channels. "))
					{
						System.out.println("Cash Payment couldn't be completed because : 123 Service Session Timed Out");
						observation="Cash Payment couldn't be completed because : 123 Service Session Timed Out";
						if(env.contains("uat"))
							result="Pass";
						else
							result="Fail";
					}
					if(driver.getCurrentUrl().contains("https://secure.123.co.th/PaymentMerchant/PaymentSlip/Generic")){
						System.out.println("123 Service Asking for Phone number");
						driver.findElement(By.id("mobileNumberArea")).sendKeys("333333");
						driver.findElement(By.id("nextButton")).click();
						try {
							Thread.sleep(4000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(driver.findElement(By.className("code")).isDisplayed())
							System.out.println("Got the Payment Code.\nFirstLook went fine.");
							observation="Got the Payment Code.\nFirstLook went fine.";
							result="Pass";
					}
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(driver.getCurrentUrl().equalsIgnoreCase(env+"/event/checkout"))
					{
						System.out.println("Card Payment couldn't be completed because : Card Details entered are wrong OR a field is still empty..!!");
						observation="Card Payment couldn't be completed because : Card Details entered are wrong OR a field is still empty..!!";
						result="Fail";
					}
					else if(driver.getCurrentUrl().equalsIgnoreCase("https://demoacs.2c2p.com/2C2PACS/pav/pa.aspx")){
						System.out.println("Asking for 2C2P OTP in UAT.\nFirstLook went fine.");
						observation="Asking for 2C2P OTP in UAT.\nFirstLook went fine.";
						result="Pass";
					}
					else if(driver.getPageSource().contains("Payment failed")){
						driver.findElement(By.className("close")).click();
						System.out.println("Got an error from the Bank.");
						observation="Got an error from the Bank.";
						result="Fail";
					}
					else if(driver.getPageSource().contains("DISCLAIMER")){
						driver.findElement(By.className("close")).click();
						System.out.println("Ticket Booked...!! On Thankyou Page Now..");
						observation="Ticket Booked...!! On Thankyou Page Now..";
						if(driver.findElement(By.className("forcanvas")).isDisplayed()==true){
							System.out.println("Got the QR Code.\nFirstLook went fine.");
							observation="Got the QR Code.\nFirstLook went fine.";
							result="Pass";
						}
						else{
							System.out.println("Didn't get the QR Code");
							observation="Didn't get the QR Code";
							result="Fail";
					}
					}
					
					
					
				
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				}catch(UnhandledAlertException f){
					
					 Alert alert = driver.switchTo().alert();
				        String alertText = alert.getText();
				        observation=alertText;
						result="Fail";
				        System.out.println("Alert data: " + alertText);
				        alert.accept();
				        observation+=" on "+driver.getCurrentUrl();
				        
				}
				catch(Exception e){
					e.printStackTrace();
					System.out.println("May be Not enough seats left for this ticket type..!!");
					observation="May be Not enough seats left for this ticket type..!!";
					result="Fail";
					
				}
				
				cell1=nextRow.createCell(colNum[8]);
				if(cell1!=null)
					cell1.setCellValue(acc_type);
				else
					System.out.println("Actual Report column not present!!");
				cell1=nextRow.createCell(colNum[9]);
				if(cell1!=null)
					cell1.setCellValue(observation);
				else
					System.out.println("Actual Report column not present!!");
				cell1=nextRow.createCell(colNum[10]);
				if(cell1!=null)
					cell1.setCellValue(result);
				else
					System.out.println("Actual Report column not present!!");
				
				
		 }catch(TimeoutException to){
			 StringWriter errors = new StringWriter();
				to.printStackTrace(new PrintWriter(errors));
				System.out.println(errors.toString());
				observation="Timed Out...Please see logs!!";
				result="Fail";
				cell1=nextRow.createCell(colNum[8]);
				if(cell1!=null)
					cell1.setCellValue(acc_type);
				else
					System.out.println("Actual Report column not present!!");
				cell1=nextRow.createCell(colNum[9]);
				if(cell1!=null)
					cell1.setCellValue(observation);
				else
					System.out.println("Actual Report column not present!!");
				cell1=nextRow.createCell(colNum[10]);
				if(cell1!=null)
					cell1.setCellValue(result);
				else
					System.out.println("Actual Report column not present!!");
				
		 }
		 catch(Exception e){
			 StringWriter errors = new StringWriter();
				e.printStackTrace(new PrintWriter(errors));
				System.out.println(errors.toString());
				//driver.close();
			}//script catch
		 
		 
		 FileOutputStream outputStream = new FileOutputStream(new File(excelFilePath));
		 workbook.write(outputStream);
		 outputStream.close();

		 driver.close();
		 }//while
		 
		 
		 	
		 	
		 }//workbook try 
		 catch (IOException e1) {
				// TODO Auto-generated catch block
	        	//workbook.close();
				e1.printStackTrace();
				 driver.close();
			}
		 
 
		 
		 
		 workbook.close();
		 }//inputstream try 
	        catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
	        	
				e2.printStackTrace();
				 driver.close();
			}
		

		
		
	}

	public void firstLook_TB_FF(String excelFilePath, int workbookSheet)throws NoSuchElementException, IOException, NullPointerException {



		
		WebDriver driver=null;
		FileInputStream inputStream=null;
		Workbook workbook=null;
		String cellValue1=null,observation=null,result=null;
		 Cell cell1=null;
		 Row nextRow=null;
		
		//Reading the Input Sheet
		 try {
				inputStream = new FileInputStream(new File(excelFilePath));
		 try {
				workbook = new XSSFWorkbook(inputStream);
			
		        Sheet firstSheet = workbook.getSheetAt(workbookSheet);
		        
		        inputStream.close();

				 //check if the file is open...then close it
				 Runtime.getRuntime().exec("TASKKILL /FI \"WINDOWTITLE eq Input_Data_Sheet - Excel\"");
				 
				 try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        
		        
		        
		        
		         //Getting the column index  by headings
		        
		        Row firstRow = firstSheet.getRow(0);  
		        int colNum[]=new int[11];
		        for (int i = 0 ;i<=firstRow.getPhysicalNumberOfCells();i++){
		            cell1 = firstRow.getCell(i);
		            if(cell1!=null)
		            	cellValue1 = cell1.getStringCellValue();
		            else
		            	cellValue1="<cell is empty>";
		            if ("Environment".equals(cellValue1)){
		            	 colNum[0]=cell1.getColumnIndex();
		            }
		            if ("Username".equals(cellValue1)){
		            	 colNum[1]=cell1.getColumnIndex();
		            }
		            if ("Password".equals(cellValue1)){
		            	 colNum[2]=cell1.getColumnIndex();
		            	 
		            }
		            if ("Poster Image Link (From S3)".equals(cellValue1)){
		            	 colNum[3]=cell1.getColumnIndex();
		            	 
		            }
		            if ("Event Name".equals(cellValue1)){
		            	 colNum[4]=cell1.getColumnIndex();
		            	 
		            }
		            if ("Ticket Type".equals(cellValue1)){
		            	 colNum[5]=cell1.getColumnIndex();
		            	 
		            }
		            if ("Quantity of Tickets".equals(cellValue1)){
		            	 colNum[6]=cell1.getColumnIndex();
		            	 
		            }
		            if ("Mode of Payment".equals(cellValue1)){
		            	 colNum[7]=cell1.getColumnIndex();
		            	 
		            }
		            if ("User Account Type - FF".equals(cellValue1)){
		            	 colNum[8]=cell1.getColumnIndex();
		            	 
		            }
		            if ("Observation - FF".equals(cellValue1)){
		            	 colNum[9]=cell1.getColumnIndex();
		            	 
		            }
		            if ("Result - FF".equals(cellValue1)){
		            	 colNum[10]=cell1.getColumnIndex();
		            	 
		            }
		            
		           }
		        
		       
		        
		        
		        
		        Iterator<Row> iterator = firstSheet.iterator();
		        if(iterator.hasNext())
		        	iterator.next();
		        else
		        	System.out.println("The excel is empty..!!");
		 
		 while (iterator.hasNext()){
			 System.out.print("\n-----------------------------------------------------------------------------\n");
			 System.out.println("\nin...."+iterator.toString());
	         String env="",username="",password="",event_poster="",event_name="",type="",qty="",pay_type="",acc_type="";
			 try{
	        	nextRow= iterator.next();
	        	env=nextRow.getCell(colNum[0]).getStringCellValue();
	        	username=nextRow.getCell(colNum[1]).getStringCellValue();
	        	password=nextRow.getCell(colNum[2]).getStringCellValue();
	        	event_poster=nextRow.getCell(colNum[3]).getStringCellValue();
	        	event_name=nextRow.getCell(colNum[4]).getStringCellValue();
	        	type=nextRow.getCell(colNum[5]).getStringCellValue();
	        	qty=nextRow.getCell(colNum[6]).getStringCellValue();
	        	pay_type=nextRow.getCell(colNum[7]).getStringCellValue();
//	        	double qty_temp=nextRow.getCell(colNum[6]).getNumericCellValue();
//	        	qty=Double.toString(qty_temp);
//	        	double pay_type_temp=nextRow.getCell(colNum[7]).getNumericCellValue();
//	        	pay_type=Double.toString(pay_type_temp);
	        	acc_type=nextRow.getCell(colNum[8]).getStringCellValue();
	        	observation=nextRow.getCell(colNum[9]).getStringCellValue();
	        	
			 }catch (NullPointerException npe){
				 //npe.printStackTrace();
				 
			 }
	        	
	            Iterator<Cell> cellIterator = nextRow.cellIterator();
	            observation=null;

		
		
		//sign in with existing account
		 try {
				
			 System.setProperty("webdriver.gecko.driver","C:\\Softwares\\jars\\geckodriver-v0.17.0-win32\\geckodriver.exe");
				DesiredCapabilities capabilities_ff = DesiredCapabilities.firefox();
				capabilities_ff.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
				capabilities_ff.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				FirefoxProfile profile = new FirefoxProfile();
				profile.setAcceptUntrustedCertificates(true);
				capabilities_ff.setCapability(FirefoxDriver.PROFILE, profile);
				
				// this is the important line - i.e. don't use Marionette
				//capabilities_ff.setCapability(FirefoxDriver.MARIONETTE, false);

				driver= new FirefoxDriver(capabilities_ff);
				driver.manage().window().setPosition(new Point(-2000, 0));
//				
				
				driver.get(env);
				
				
				try {
					Thread.sleep(2000);
					//If subscribe Option comes up on the screen
					if(driver.findElement(By.id("ematic_closeExitIntentOverlay_1_xl_1_0")).isDisplayed())
						driver.findElement(By.id("ematic_closeExitIntentOverlay_1_xl_1_0")).click();
					//--------If subscribe Option comes up on the screen----------//
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
				WebElement ele=driver.findElement(By.linkText("SIGN IN"));
				ele.click();
				WebElement emailid=driver.findElement(By.id("exampleInputEmail1"));
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				//If subscribe Option comes up on the screen
				if(driver.findElement(By.id("ematic_closeExitIntentOverlay_1_xl_1_0")).isDisplayed())
					driver.findElement(By.id("ematic_closeExitIntentOverlay_1_xl_1_0")).click();
				//--------If subscribe Option comes up on the screen----------//
				
				
				emailid.click();
				
				emailid.sendKeys(username);
				WebElement pass=driver.findElement(By.id("exampleInputPassword1"));
				pass.sendKeys(password);
				WebElement submitButton=driver.findElement(By.id("submitButton1"));
				submitButton.click();
				try {
					Thread.sleep(6000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//signed in -- now check whether the user is EO or TB:
				WebElement user=driver.findElement(By.className("dropbtn2"));
				if(user.isDisplayed())
					System.out.println("Signed In Successfully..!!");
				List<WebElement> forms = driver.findElements(By.xpath("//*[@id=\"rightSideWrapper\"]/div[1]/div[1]/ul/li[2]/div/div/a"));
				int count = forms.size();
				//System.out.println("Size of acc_type:"+count);
				if(count==3){
					System.out.println("Account Type : TB.");
					acc_type="TB";
				}
				else if(count==4)
					{System.out.println("Account Type : EO.");
					acc_type="EO";
					try {
						Thread.sleep(6000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					WebElement homepage=driver.findElement(By.xpath("//img[contains(@src,'https://s3-ap-southeast-1.amazonaws.com/homepage.ticketmelon.com/logo3x.png')]"));
					Actions actions = new Actions(driver);
				    actions.moveToElement(homepage).click().perform();
					//homepage.click();
				
					}
				
				//select an event
				//find the correct event
				if(event_poster.contains("poster-default")){
				//driver.findElement(By.xpath("//a[@href='/event/"+event_name+"']")).click();
				String temp_evt_name=event_name.toLowerCase();
				String temp_evt_name3=temp_evt_name.replace(' ', '-');
				String temp_evt_name2=temp_evt_name3.replaceAll("[\\+\\.\\^:,%$#@!&\"']*","");
				List<WebElement> event_name_tile=driver.findElements(By.xpath("//div[@class = 'border-box']/a"));
				for (WebElement option : event_name_tile) {

					String link=option.getAttribute("href");
					System.out.println("link :"+link);
					if(link.contains(temp_evt_name2)){
						driver.get(link);
						break;
					}
				}
				System.out.println("lowercase of event name:"+temp_evt_name2);
				}
				else
				{
					try {
						Thread.sleep(6000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					WebElement event=driver.findElement(By.xpath("//img[contains(@src,'"+event_poster+"')]"));
					event.click();
				}
				
				
//				System.out.println("href :"+event_name_tile);
//				WebElement event=driver.findElement(By.xpath("//img[contains(@src,'"+event_poster+"')]"));
//				event.click();
				
				WebElement eventPage=driver.findElement(By.tagName("h3"));
				System.out.println("Event Name on Page :"+eventPage.getText());
				if(eventPage.getText().equalsIgnoreCase(event_name))
					System.out.println("On right event Page..!!");
				else
					{System.out.println("On wrong event Page..!!");
					observation="Coudn't find the Event Page..!!";
					result="Fail";

					cell1=nextRow.createCell(colNum[8]);
					if(cell1!=null)
						cell1.setCellValue(acc_type);
					else
						System.out.println("Actual Report column not present!!");
					cell1=nextRow.createCell(colNum[9]);
					if(cell1!=null)
						cell1.setCellValue(observation);
					else
						System.out.println("Actual Report column not present!!");
					cell1=nextRow.createCell(colNum[10]);
					if(cell1!=null)
						cell1.setCellValue(result);
					else
						System.out.println("Actual Report column not present!!");
					 FileOutputStream outputStream = new FileOutputStream(new File(excelFilePath));
					 workbook.write(outputStream);
					 outputStream.close();
					 driver.close();
					 
						continue;
					}
				
				//select a ticket type
				WebElement tkt_qty=driver.findElement(By.name(type)); // hard coded for now as 2290 is the Id of this ticket_type in DB.//static
//				Select dropdown= new Select(tkt_qty);
//				dropdown.selectByValue(qty);
				String temp_type=type.substring(9);
				char temp_type2=temp_type.charAt(0);
				String temp_type3="input[value='"+temp_type2+","+temp_type.substring(1,4)+", 1']";
				System.out.println("tkt_type: "+temp_type3);
				//WebElement tkt_type=driver.findElement(By.cssSelector(temp_type3));//static
				//System.out.println("Price of selected Ticket Type :"+tkt_type.getAttribute("innerText"));
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				List<WebElement> options = tkt_qty.findElements(By.tagName("option"));
				for (WebElement option : options) {

					if(qty.equals(option.getText()))
						option.click();
						
				}
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//pay for it and checkout
				WebElement buy=driver.findElement(By.id("checkoutBtn"));
				try{
					
					buy.click();
				
				//On Checkout Page
				WebElement pay_meth;//=driver.findElement(By.name("payment_method"));
				if(pay_type.equals("1"))
					{
					pay_meth=driver.findElement(By.xpath("//input[@name='payment_method' and @value='0']"));
					System.out.println("Payment Mode : CARD ");
					}
				else
					{
					pay_meth=driver.findElement(By.xpath("//input[@name='payment_method' and @value='1']"));
					System.out.println("Payment Mode : CASH ");
					}
				
				pay_meth.click();
				
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(pay_type.equals("1"))	//CARD PAYMENTS
					{
					driver.findElement(By.id("xcardnumber")).sendKeys("4111111111111111");
					driver.findElement(By.id("xcvv")).sendKeys("123");
					driver.findElement(By.id("xname")).sendKeys("SampleCard");
					driver.findElement(By.id("xcardmonth")).sendKeys("12");
					driver.findElement(By.id("xcardyear")).sendKeys("2020");
					
					}
				else
					{
					
					 WebElement oCheckBox = driver.findElement(By.cssSelector("input[value='1']"));	//CASH PAYMENTS
					 
					 Actions actions = new Actions(driver);

					 actions.moveToElement(oCheckBox).click().perform();
					 
					
					
					//have to close the pop-up of cash payments warning
					String label = "Close";
					driver.findElement(By.xpath("//button[contains(.,'" + label + "')]")).click();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					Select parent = new Select(driver.findElement(By.id("parent_selection")));
					parent.selectByIndex(2);
					Select child = new Select(driver.findElement(By.id("child_selection")));
					child.selectByIndex(2);
					}
					//driver.findElement(By.id("parent_selection")).findElements(By.tagName("option")).;
					//driver.findElement(By.id("child_selection")).findElement(By.tagName("option"));
					driver.findElement(By.id("agree_terms")).click();
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//System.out.println("Buy Tickets : "+driver.findElement(By.id("PConfirmBuyTicket")).getText());
					try{
//					if(driver.findElement(By.id("XConfirmBuyTicket")).isDisplayed() || driver.findElement(By.id("PConfirmBuyTicket")).isDisplayed())
//						driver.findElement(By.id("XConfirmBuyTicket")).click();
//					else if(driver.findElement(By.id("PConfirmBuyTicket")).isDisplayed())
//						driver.findElement(By.id("PConfirmBuyTicket")).click();
						driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();
					}catch(WebDriverException e){
						System.out.println("Didn't get the Checkout Button");
						observation="Didn't get the Checkout Button";
						result="Fail";
					}
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(driver.getPageSource().contains("1-2-3 Service is the One-Stop-Service payment collection service provider.You can pay by cash or via direct debit at all the available 1-2-3 payment channels. "))
					{
						System.out.println("Cash Payment couldn't be completed because : 123 Service Session Timed Out");
						observation="Cash Payment couldn't be completed because : 123 Service Session Timed Out";
						if(env.contains("uat"))
							result="Pass";
						else
							result="Fail";
					}
					if(driver.getCurrentUrl().contains("https://secure.123.co.th/PaymentMerchant/PaymentSlip/Generic")){
						System.out.println("123 Service Asking for Phone number");
						driver.findElement(By.id("mobileNumberArea")).sendKeys("333333");
						driver.findElement(By.id("nextButton")).click();
						try {
							Thread.sleep(4000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(driver.findElement(By.className("code")).isDisplayed())
							System.out.println("Got the Payment Code.\nFirstLook went fine.");
							observation="Got the Payment Code.\nFirstLook went fine.";
							result="Pass";
					}
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(driver.getCurrentUrl().equalsIgnoreCase(env+"/event/checkout"))
					{
						System.out.println("Card Payment couldn't be completed because : Card Details entered are wrong OR a field is still empty..!!");
						observation="Card Payment couldn't be completed because : Card Details entered are wrong OR a field is still empty..!!";
						result="Fail";
					}
					else if(driver.getCurrentUrl().equalsIgnoreCase("https://demoacs.2c2p.com/2C2PACS/pav/pa.aspx")){
						System.out.println("Asking for 2C2P OTP in UAT.\nFirstLook went fine.");
						observation="Asking for 2C2P OTP in UAT.\nFirstLook went fine.";
						result="Pass";
					}
					else if(driver.getPageSource().contains("Payment failed")){
						driver.findElement(By.className("close")).click();
						System.out.println("Got an error from the Bank.");
						observation="Got an error from the Bank.";
						result="Fail";
					}
					else if(driver.getPageSource().contains("DISCLAIMER")){
						driver.findElement(By.className("close")).click();
						System.out.println("Ticket Booked...!! On Thankyou Page Now..");
						observation="Ticket Booked...!! On Thankyou Page Now..";
						if(driver.findElement(By.className("forcanvas")).isDisplayed()==true){
							System.out.println("Got the QR Code.\nFirstLook went fine.");
							observation="Got the QR Code.\nFirstLook went fine.";
							result="Pass";
						}
						else{
							System.out.println("Didn't get the QR Code");
							observation="Didn't get the QR Code";
							result="Fail";
					}
					}
					
					
					
				
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				}catch(UnhandledAlertException f){
					
					 Alert alert = driver.switchTo().alert();
				        String alertText = alert.getText();
				        observation=alertText;
						result="Fail";
				        System.out.println("Alert data: " + alertText);
				        alert.accept();
				        observation+=" on "+driver.getCurrentUrl();
				        
				}
				catch(NoSuchElementException element){
					System.out.println("Coudln't find some element..!!: "+element.getMessage());
					observation="Coudln't find some element..!!See Logs.";
					result="Fail";
				}
				catch(Exception e){
					e.printStackTrace();
					System.out.println("May be Not enough seats left for this ticket type..!!");
					observation="May be Not enough seats left for this ticket type..!!";
					result="Fail";
					
				}
				
				cell1=nextRow.createCell(colNum[8]);
				if(cell1!=null)
					cell1.setCellValue(acc_type);
				else
					System.out.println("Actual Report column not present!!");
				cell1=nextRow.createCell(colNum[9]);
				if(cell1!=null)
					cell1.setCellValue(observation);
				else
					System.out.println("Actual Report column not present!!");
				cell1=nextRow.createCell(colNum[10]);
				if(cell1!=null)
					cell1.setCellValue(result);
				else
					System.out.println("Actual Report column not present!!");
				
				
		 }catch(TimeoutException to){
			 StringWriter errors = new StringWriter();
				to.printStackTrace(new PrintWriter(errors));
				System.out.println(errors.toString());
				observation="Timed Out...Please see logs!!";
				result="Fail";
				cell1=nextRow.createCell(colNum[8]);
				if(cell1!=null)
					cell1.setCellValue(acc_type);
				else
					System.out.println("Actual Report column not present!!");
				cell1=nextRow.createCell(colNum[9]);
				if(cell1!=null)
					cell1.setCellValue(observation);
				else
					System.out.println("Actual Report column not present!!");
				cell1=nextRow.createCell(colNum[10]);
				if(cell1!=null)
					cell1.setCellValue(result);
				else
					System.out.println("Actual Report column not present!!");
				
		 }
		 catch(Exception e){
			 StringWriter errors = new StringWriter();
				e.printStackTrace(new PrintWriter(errors));
				System.out.println(errors.toString());
				//driver.close();
			}//script catch
		 
		 
		 FileOutputStream outputStream = new FileOutputStream(new File(excelFilePath));
		 workbook.write(outputStream);
		 outputStream.close();

		 driver.close();
		
		 }//while
		 
		 
		 	
		 	
		 }//workbook try 
		 catch (IOException e1) {
				// TODO Auto-generated catch block
	        	//workbook.close();
				e1.printStackTrace();
				 driver.close();
			}
		 
 
		 
		 
		 workbook.close();
		 }//inputstream try 
	        catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
	        	
				e2.printStackTrace();
				 driver.close();
			}
		

		
		
	
		
	}

//	public void firstLook_TB_Safari(String excelFilePath, int workbookSheet)throws NoSuchElementException, IOException, NullPointerException {
//		
//
//
//
//
//		
//		WebDriver driver=null;
//		FileInputStream inputStream=null;
//		Workbook workbook=null;
//		String cellValue1=null,observation=null,result=null;
//		 Cell cell1=null;
//		 Row nextRow=null;
//		
//		//Reading the Input Sheet
//		 try {
//				inputStream = new FileInputStream(new File(excelFilePath));
//		 try {
//				workbook = new XSSFWorkbook(inputStream);
//			
//		        Sheet firstSheet = workbook.getSheetAt(workbookSheet);
//		        
//		        inputStream.close();
//
//				 //check if the file is open...then close it
//				 Runtime.getRuntime().exec("TASKKILL /FI \"WINDOWTITLE eq Input_Data_Sheet - Excel\"");
//				 
//				 try {
//						Thread.sleep(4000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//		        
//		        
//		        
//		        
//		         //Getting the column index  by headings
//		        
//		        Row firstRow = firstSheet.getRow(0);  
//		        int colNum[]=new int[11];
//		        for (int i = 0 ;i<=firstRow.getPhysicalNumberOfCells();i++){
//		            cell1 = firstRow.getCell(i);
//		            if(cell1!=null)
//		            	cellValue1 = cell1.getStringCellValue();
//		            else
//		            	cellValue1="<cell is empty>";
//		            if ("Environment".equals(cellValue1)){
//		            	 colNum[0]=cell1.getColumnIndex();
//		            }
//		            if ("Username".equals(cellValue1)){
//		            	 colNum[1]=cell1.getColumnIndex();
//		            }
//		            if ("Password".equals(cellValue1)){
//		            	 colNum[2]=cell1.getColumnIndex();
//		            	 
//		            }
//		            if ("Poster Image Link (From S3)".equals(cellValue1)){
//		            	 colNum[3]=cell1.getColumnIndex();
//		            	 
//		            }
//		            if ("Event Name".equals(cellValue1)){
//		            	 colNum[4]=cell1.getColumnIndex();
//		            	 
//		            }
//		            if ("Ticket Type".equals(cellValue1)){
//		            	 colNum[5]=cell1.getColumnIndex();
//		            	 
//		            }
//		            if ("Quantity of Tickets".equals(cellValue1)){
//		            	 colNum[6]=cell1.getColumnIndex();
//		            	 
//		            }
//		            if ("Mode of Payment".equals(cellValue1)){
//		            	 colNum[7]=cell1.getColumnIndex();
//		            	 
//		            }
//		            if ("User Account Type - FF".equals(cellValue1)){
//		            	 colNum[8]=cell1.getColumnIndex();
//		            	 
//		            }
//		            if ("Observation - FF".equals(cellValue1)){
//		            	 colNum[9]=cell1.getColumnIndex();
//		            	 
//		            }
//		            if ("Result - FF".equals(cellValue1)){
//		            	 colNum[10]=cell1.getColumnIndex();
//		            	 
//		            }
//		            
//		           }
//		        
//		       
//		        
//		        
//		        
//		        Iterator<Row> iterator = firstSheet.iterator();
//		        if(iterator.hasNext())
//		        	iterator.next();
//		        else
//		        	System.out.println("The excel is empty..!!");
//		 
//		 while (iterator.hasNext()){
//			 System.out.print("\n-----------------------------------------------------------------------------\n");
//			 System.out.println("\nin...."+iterator.toString());
//	         String env="",username="",password="",event_poster="",event_name="",type="",qty="",pay_type="",acc_type="";
//			 try{
//	        	nextRow= iterator.next();
//	        	env=nextRow.getCell(colNum[0]).getStringCellValue();
//	        	username=nextRow.getCell(colNum[1]).getStringCellValue();
//	        	password=nextRow.getCell(colNum[2]).getStringCellValue();
//	        	event_poster=nextRow.getCell(colNum[3]).getStringCellValue();
//	        	event_name=nextRow.getCell(colNum[4]).getStringCellValue();
//	        	type=nextRow.getCell(colNum[5]).getStringCellValue();
//	        	qty=nextRow.getCell(colNum[6]).getStringCellValue();
//	        	pay_type=nextRow.getCell(colNum[7]).getStringCellValue();
////	        	double qty_temp=nextRow.getCell(colNum[6]).getNumericCellValue();
////	        	qty=Double.toString(qty_temp);
////	        	double pay_type_temp=nextRow.getCell(colNum[7]).getNumericCellValue();
////	        	pay_type=Double.toString(pay_type_temp);
//	        	acc_type=nextRow.getCell(colNum[8]).getStringCellValue();
//	        	observation=nextRow.getCell(colNum[9]).getStringCellValue();
//	        	
//			 }catch (NullPointerException npe){
//				 //npe.printStackTrace();
//				 
//			 }
//	        	
//	            Iterator<Cell> cellIterator = nextRow.cellIterator();
//	            observation=null;
//
//		
//		
//		//sign in with existing account
//		 try {
//				
//			// System.setProperty("webdriver.gecko.driver","C:\\Softwares\\jars\\geckodriver-v0.17.0-win32\\geckodriver.exe");
//
//			 	//String osType = "MAC";
//			 	//SafariOptions optionssaf = new SafariOptions();
//			 	
//				driver= new SafariDriver();	
//				
//				driver.get(env);
//				
//				try {
//					Thread.sleep(2000);
//					//If subscribe Option comes up on the screen
//					if(driver.findElement(By.id("ematic_closeExitIntentOverlay_1_xl_1_0")).isDisplayed())
//						driver.findElement(By.id("ematic_closeExitIntentOverlay_1_xl_1_0")).click();
//					//--------If subscribe Option comes up on the screen----------//
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					//e.printStackTrace();
//				}
//				WebElement ele=driver.findElement(By.linkText("SIGN IN"));
//				ele.click();
//				WebElement emailid=driver.findElement(By.id("exampleInputEmail1"));
//				try {
//					Thread.sleep(4000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//				
//				//If subscribe Option comes up on the screen
//				if(driver.findElement(By.id("ematic_closeExitIntentOverlay_1_xl_1_0")).isDisplayed())
//					driver.findElement(By.id("ematic_closeExitIntentOverlay_1_xl_1_0")).click();
//				//--------If subscribe Option comes up on the screen----------//
//				
//				
//				emailid.click();
//				
//				emailid.sendKeys(username);
//				WebElement pass=driver.findElement(By.id("exampleInputPassword1"));
//				pass.sendKeys(password);
//				WebElement submitButton=driver.findElement(By.id("submitButton1"));
//				submitButton.click();
//				try {
//					Thread.sleep(6000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//				//signed in -- now check whether the user is EO or TB:
//				WebElement user=driver.findElement(By.className("dropbtn2"));
//				if(user.isDisplayed())
//					System.out.println("Signed In Successfully..!!");
//				List<WebElement> forms = driver.findElements(By.xpath("//*[@id=\"rightSideWrapper\"]/div[1]/div[1]/ul/li[2]/div/div/a"));
//				int count = forms.size();
//				//System.out.println("Size of acc_type:"+count);
//				if(count==3){
//					System.out.println("Account Type : TB.");
//					acc_type="TB";
//				}
//				else if(count==4)
//					{System.out.println("Account Type : EO.");
//					acc_type="EO";
//					try {
//						Thread.sleep(6000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					WebElement homepage=driver.findElement(By.xpath("//img[contains(@src,'https://s3-ap-southeast-1.amazonaws.com/homepage.ticketmelon.com/logo3x.png')]"));
//					Actions actions = new Actions(driver);
//				    actions.moveToElement(homepage).click().perform();
//					//homepage.click();
//				
//					}
//				
//				//select an event
//				//find the correct event
//				if(event_poster.contains("poster-default")){
//				//driver.findElement(By.xpath("//a[@href='/event/"+event_name+"']")).click();
//				String temp_evt_name=event_name.toLowerCase();
//				String temp_evt_name3=temp_evt_name.replace(' ', '-');
//				String temp_evt_name2=temp_evt_name3.replaceAll("[\\+\\.\\^:,%$#@!&\"']*","");
//				List<WebElement> event_name_tile=driver.findElements(By.xpath("//div[@class = 'border-box']/a"));
//				for (WebElement option : event_name_tile) {
//
//					String link=option.getAttribute("href");
//					System.out.println("link :"+link);
//					if(link.contains(temp_evt_name2)){
//						driver.get(link);
//						break;
//					}
//				}
//				System.out.println("lowercase of event name:"+temp_evt_name2);
//				}
//				else
//				{
//					try {
//						Thread.sleep(6000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					WebElement event=driver.findElement(By.xpath("//img[contains(@src,'"+event_poster+"')]"));
//					event.click();
//				}
//				
//				
////				System.out.println("href :"+event_name_tile);
////				WebElement event=driver.findElement(By.xpath("//img[contains(@src,'"+event_poster+"')]"));
////				event.click();
//				
//				WebElement eventPage=driver.findElement(By.tagName("h3"));
//				System.out.println("Event Name on Page :"+eventPage.getText());
//				if(eventPage.getText().equalsIgnoreCase(event_name))
//					System.out.println("On right event Page..!!");
//				else
//					{System.out.println("On wrong event Page..!!");
//					observation="Coudn't find the Event Page..!!";
//					result="Fail";
//
//					cell1=nextRow.createCell(colNum[8]);
//					if(cell1!=null)
//						cell1.setCellValue(acc_type);
//					else
//						System.out.println("Actual Report column not present!!");
//					cell1=nextRow.createCell(colNum[9]);
//					if(cell1!=null)
//						cell1.setCellValue(observation);
//					else
//						System.out.println("Actual Report column not present!!");
//					cell1=nextRow.createCell(colNum[10]);
//					if(cell1!=null)
//						cell1.setCellValue(result);
//					else
//						System.out.println("Actual Report column not present!!");
//					 FileOutputStream outputStream = new FileOutputStream(new File(excelFilePath));
//					 workbook.write(outputStream);
//					 outputStream.close();
//					 
//					 
//						continue;
//					}
//				
//				//select a ticket type
//				WebElement tkt_qty=driver.findElement(By.name(type)); // hard coded for now as 2290 is the Id of this ticket_type in DB.//static
////				Select dropdown= new Select(tkt_qty);
////				dropdown.selectByValue(qty);
//				String temp_type=type.substring(9);
//				char temp_type2=temp_type.charAt(0);
//				String temp_type3="input[value='"+temp_type2+","+temp_type.substring(1,4)+", 1']";
//				System.out.println("tkt_type: "+temp_type3);
//				//WebElement tkt_type=driver.findElement(By.cssSelector(temp_type3));//static
//				//System.out.println("Price of selected Ticket Type :"+tkt_type.getAttribute("innerText"));
//				try {
//					Thread.sleep(3000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				List<WebElement> options = tkt_qty.findElements(By.tagName("option"));
//				for (WebElement option : options) {
//
//					if(qty.equals(option.getText()))
//						option.click();
//						
//				}
//				try {
//					Thread.sleep(4000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				//pay for it and checkout
//				WebElement buy=driver.findElement(By.id("checkoutBtn"));
//				try{
//					
//					buy.click();
//				
//				//On Checkout Page
//				WebElement pay_meth;//=driver.findElement(By.name("payment_method"));
//				if(pay_type.equals("1"))
//					{
//					pay_meth=driver.findElement(By.xpath("//input[@name='payment_method' and @value='0']"));
//					System.out.println("Payment Mode : CARD ");
//					}
//				else
//					{
//					pay_meth=driver.findElement(By.xpath("//input[@name='payment_method' and @value='1']"));
//					System.out.println("Payment Mode : CASH ");
//					}
//				
//				pay_meth.click();
//				
//				try {
//					Thread.sleep(2000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				if(pay_type.equals("1"))	//CARD PAYMENTS
//					{
//					driver.findElement(By.id("xcardnumber")).sendKeys("4111111111111111");
//					driver.findElement(By.id("xcvv")).sendKeys("123");
//					driver.findElement(By.id("xname")).sendKeys("SampleCard");
//					driver.findElement(By.id("xcardmonth")).sendKeys("12");
//					driver.findElement(By.id("xcardyear")).sendKeys("2020");
//					
//					}
//				else
//					{
//					
//					 WebElement oCheckBox = driver.findElement(By.cssSelector("input[value='1']"));	//CASH PAYMENTS
//					 
//					 Actions actions = new Actions(driver);
//
//					 actions.moveToElement(oCheckBox).click().perform();
//					 
//					
//					
//					//have to close the pop-up of cash payments warning
//					String label = "Close";
//					driver.findElement(By.xpath("//button[contains(.,'" + label + "')]")).click();
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					
//					Select parent = new Select(driver.findElement(By.id("parent_selection")));
//					parent.selectByIndex(2);
//					Select child = new Select(driver.findElement(By.id("child_selection")));
//					child.selectByIndex(2);
//					}
//					//driver.findElement(By.id("parent_selection")).findElements(By.tagName("option")).;
//					//driver.findElement(By.id("child_selection")).findElement(By.tagName("option"));
//					driver.findElement(By.id("agree_terms")).click();
//					try {
//						Thread.sleep(4000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					//System.out.println("Buy Tickets : "+driver.findElement(By.id("PConfirmBuyTicket")).getText());
//					try{
////					if(driver.findElement(By.id("XConfirmBuyTicket")).isDisplayed() || driver.findElement(By.id("PConfirmBuyTicket")).isDisplayed())
////						driver.findElement(By.id("XConfirmBuyTicket")).click();
////					else if(driver.findElement(By.id("PConfirmBuyTicket")).isDisplayed())
////						driver.findElement(By.id("PConfirmBuyTicket")).click();
//						driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();
//					}catch(WebDriverException e){
//						System.out.println("Didn't get the Checkout Button");
//						observation="Didn't get the Checkout Button";
//						result="Fail";
//					}
//					try {
//						Thread.sleep(4000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					
//					if(driver.getPageSource().contains("1-2-3 Service is the One-Stop-Service payment collection service provider.You can pay by cash or via direct debit at all the available 1-2-3 payment channels. "))
//					{
//						System.out.println("Cash Payment couldn't be completed because : 123 Service Session Timed Out");
//						observation="Cash Payment couldn't be completed because : 123 Service Session Timed Out";
//						if(env.contains("uat"))
//							result="Pass";
//						else
//							result="Fail";
//					}
//					if(driver.getCurrentUrl().contains("https://secure.123.co.th/PaymentMerchant/PaymentSlip/Generic")){
//						System.out.println("123 Service Asking for Phone number");
//						driver.findElement(By.id("mobileNumberArea")).sendKeys("333333");
//						driver.findElement(By.id("nextButton")).click();
//						try {
//							Thread.sleep(4000);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//						if(driver.findElement(By.className("code")).isDisplayed())
//							System.out.println("Got the Payment Code.\nFirstLook went fine.");
//							observation="Got the Payment Code.\nFirstLook went fine.";
//							result="Pass";
//					}
//					try {
//						Thread.sleep(2000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					if(driver.getCurrentUrl().equalsIgnoreCase(env+"/event/checkout"))
//					{
//						System.out.println("Card Payment couldn't be completed because : Card Details entered are wrong OR a field is still empty..!!");
//						observation="Card Payment couldn't be completed because : Card Details entered are wrong OR a field is still empty..!!";
//						result="Fail";
//					}
//					else if(driver.getCurrentUrl().equalsIgnoreCase("https://demoacs.2c2p.com/2C2PACS/pav/pa.aspx")){
//						System.out.println("Asking for 2C2P OTP in UAT.\nFirstLook went fine.");
//						observation="Asking for 2C2P OTP in UAT.\nFirstLook went fine.";
//						result="Pass";
//					}
//					else if(driver.getPageSource().contains("Payment failed")){
//						driver.findElement(By.className("close")).click();
//						System.out.println("Got an error from the Bank.");
//						observation="Got an error from the Bank.";
//						result="Fail";
//					}
//					else if(driver.getPageSource().contains("DISCLAIMER")){
//						driver.findElement(By.className("close")).click();
//						System.out.println("Ticket Booked...!! On Thankyou Page Now..");
//						observation="Ticket Booked...!! On Thankyou Page Now..";
//						if(driver.findElement(By.className("forcanvas")).isDisplayed()==true){
//							System.out.println("Got the QR Code.\nFirstLook went fine.");
//							observation="Got the QR Code.\nFirstLook went fine.";
//							result="Pass";
//						}
//						else{
//							System.out.println("Didn't get the QR Code");
//							observation="Didn't get the QR Code";
//							result="Fail";
//					}
//					}
//					
//					
//					
//				
//				try {
//					Thread.sleep(4000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//				}catch(UnhandledAlertException f){
//					
//					 Alert alert = driver.switchTo().alert();
//				        String alertText = alert.getText();
//				        observation=alertText;
//						result="Fail";
//				        System.out.println("Alert data: " + alertText);
//				        alert.accept();
//				        observation+=" on "+driver.getCurrentUrl();
//				        
//				}
//				catch(NoSuchElementException element){
//					System.out.println("Coudln't find some element..!!: "+element.getMessage());
//					observation="Coudln't find some element..!!See Logs.";
//					result="Fail";
//				}
//				catch(Exception e){
//					e.printStackTrace();
//					System.out.println("May be Not enough seats left for this ticket type..!!");
//					observation="May be Not enough seats left for this ticket type..!!";
//					result="Fail";
//					
//				}
//				
//				cell1=nextRow.createCell(colNum[8]);
//				if(cell1!=null)
//					cell1.setCellValue(acc_type);
//				else
//					System.out.println("Actual Report column not present!!");
//				cell1=nextRow.createCell(colNum[9]);
//				if(cell1!=null)
//					cell1.setCellValue(observation);
//				else
//					System.out.println("Actual Report column not present!!");
//				cell1=nextRow.createCell(colNum[10]);
//				if(cell1!=null)
//					cell1.setCellValue(result);
//				else
//					System.out.println("Actual Report column not present!!");
//				
//				
//		 }catch(TimeoutException to){
//			 StringWriter errors = new StringWriter();
//				to.printStackTrace(new PrintWriter(errors));
//				System.out.println(errors.toString());
//				observation="Timed Out...Please see logs!!";
//				result="Fail";
//				cell1=nextRow.createCell(colNum[8]);
//				if(cell1!=null)
//					cell1.setCellValue(acc_type);
//				else
//					System.out.println("Actual Report column not present!!");
//				cell1=nextRow.createCell(colNum[9]);
//				if(cell1!=null)
//					cell1.setCellValue(observation);
//				else
//					System.out.println("Actual Report column not present!!");
//				cell1=nextRow.createCell(colNum[10]);
//				if(cell1!=null)
//					cell1.setCellValue(result);
//				else
//					System.out.println("Actual Report column not present!!");
//				
//		 }
//		 catch(Exception e){
//			 StringWriter errors = new StringWriter();
//				e.printStackTrace(new PrintWriter(errors));
//				System.out.println(errors.toString());
//				//driver.close();
//			}//script catch
//		 
//		 
//		 FileOutputStream outputStream = new FileOutputStream(new File(excelFilePath));
//		 workbook.write(outputStream);
//		 outputStream.close();
//
//		//driver.close();
//		 }//while
//		 
//		 
//		 	
//		 	
//		 }//workbook try 
//		 catch (IOException e1) {
//				// TODO Auto-generated catch block
//	        	//workbook.close();
//				e1.printStackTrace();
//			}
//		 
// 
//		 
//		 
//		 workbook.close();
//		 }//inputstream try 
//	        catch (FileNotFoundException e2) {
//				// TODO Auto-generated catch block
//	        	
//				e2.printStackTrace();
//			}
//		
//
//		
//		
//	
//		
//	
//		
//		
//		
//	}

}
