import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * 
 */

/**
 * @author Astha Verma
 *
 */
public class SignUpTest {
	
	
	/**
	 * entity Sign Up
	 * @param env - Environment i.e. the URL
	 * @param excelFilePath - The path where the Input_Data_Sheet.xlsx is present
	 * @param workbookSheet - The sheet that has data for this method
	 * 
	 * 
	 * @throws IOException - If in case the file is not present/not able to open/corrupt file
	 * @throws NullPointerException - If some vale from sheet comes out to be null
	 */
	
	public void signUpTest(String env,String excelFilePath, int workbookSheet)  throws IOException, NullPointerException {
		WebDriver driver=null;
		FileInputStream inputStream=null;
		Workbook workbook=null;
		String cellValue1=null,observation=null;
		 Cell cell1=null;
		 Row nextRow=null;
		 boolean firstPopUp=false;
		 
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
		        int colNum[]=new int[10];
		        for (int i = 0 ;i<=firstRow.getPhysicalNumberOfCells();i++){
		            cell1 = firstRow.getCell(i);
		            if(cell1!=null)
		            	cellValue1 = cell1.getStringCellValue();
		            else
		            	cellValue1="<cell is empty>";
		            if ("Email Id".equals(cellValue1)){
		            	 colNum[0]=cell1.getColumnIndex();
		            }
		            if ("Password".equals(cellValue1)){
		            	 colNum[1]=cell1.getColumnIndex();
		            }
		            if ("First Name".equals(cellValue1)){
		            	 colNum[2]=cell1.getColumnIndex();
		            	 
		            }
		            if ("Last Name".equals(cellValue1)){
		            	 colNum[3]=cell1.getColumnIndex();
		            	 
		            }
		            if ("Phone Number".equals(cellValue1)){
		            	 colNum[4]=cell1.getColumnIndex();
		            	 
		            }
		            if ("Gender".equals(cellValue1)){
		            	 colNum[5]=cell1.getColumnIndex();
		            	 
		            }
		            if ("Date".equals(cellValue1)){
		            	 colNum[6]=cell1.getColumnIndex();
		            	 
		            }
		            if ("Month".equals(cellValue1)){
		            	 colNum[7]=cell1.getColumnIndex();
		            	 
		            }
		            if ("Year".equals(cellValue1)){
		            	 colNum[8]=cell1.getColumnIndex();
		            	 
		            }
		            if ("Actual Report".equals(cellValue1)){
		            	 colNum[9]=cell1.getColumnIndex();
		            	 
		            }
		           }
		        
		       
		        
		        
		        
		        Iterator<Row> iterator = firstSheet.iterator();
		        if(iterator.hasNext())
		        	iterator.next();
		        else
		        	System.out.println("The excel is empty..!!");
		 
		 while (iterator.hasNext()){
			 System.out.println("in...."+iterator.toString());
	         String email="",pass="",first="",last="",phone="",gender="",date="",month="",year="";
			 try{
	        	nextRow= iterator.next();
	        	email=nextRow.getCell(colNum[0]).getStringCellValue();
	        	pass=nextRow.getCell(colNum[1]).getStringCellValue();
	        	first=nextRow.getCell(colNum[2]).getStringCellValue();
	        	last=nextRow.getCell(colNum[3]).getStringCellValue();
	        	phone=nextRow.getCell(colNum[4]).getStringCellValue();
	        	gender=nextRow.getCell(colNum[5]).getStringCellValue();
	        	date=nextRow.getCell(colNum[6]).getStringCellValue();
	        	month=nextRow.getCell(colNum[7]).getStringCellValue();
	        	year=nextRow.getCell(colNum[8]).getStringCellValue();
	        	
			 }catch (NullPointerException npe){
				 npe.printStackTrace();
				 
			 }
	        	
	            Iterator<Cell> cellIterator = nextRow.cellIterator();
	            observation=null;

	            
	            
	            
		 
		 try {
				System.setProperty("webdriver.chrome.driver", "C:\\Softwares\\jars\\chromedriver_win32\\chromedriver.exe");
				driver = new ChromeDriver();
				driver.get(env);
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//If subscribe Option comes up on the screen
				if(driver.findElement(By.id("ematic_closeExitIntentOverlay_1_xl_1_0")).isDisplayed())
					driver.findElement(By.id("ematic_closeExitIntentOverlay_1_xl_1_0")).click();
				//--------If subscribe Option comes up on the screen----------//
				
				
				
				WebElement ele=driver.findElement(By.linkText("SIGN IN"));
				ele.click();

				WebElement createacc=driver.findElement(By.cssSelector("a.Register_New:nth-child(3)"));
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				
				
				createacc.click();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				WebElement emailid=driver.findElement(By.id("email"));
				emailid.sendKeys(email);
				WebElement password=driver.findElement(By.id("password"));
				password.sendKeys(pass);
				WebElement password2=driver.findElement(By.id("password_confirmation"));
				password2.sendKeys(pass);
				WebElement continueButton=driver.findElement(By.id("nextregister"));
				continueButton.click();
				
				firstPopUp=false;
				
				
				boolean erroremail;
				try {
					 WebElement ee=driver.findElement(By.id("error-email"));
					 if(ee.getText().contains("Incorrect email: please try again."))
						 {erroremail = true;
						 firstPopUp=true;
						 System.out.println("got incorrect email....");
						 }
					 else
						 erroremail=false;
				   
				} catch (org.openqa.selenium.NoSuchElementException e) {
					erroremail = false;
					firstPopUp=true;//stuck at first pop up
					System.out.println("got stuck in first pop up");
				}
				if(erroremail==true)
					observation="Incorrect email: please try again";
				
				System.out.println("firstPopUp1 :"+firstPopUp);
				
				
				
				boolean errorpass=false;
				try {
				   WebElement ee=driver.findElement(By.id("error-pw"));
					 if(ee.getText().equals("The password must be at least 6 characters."))
						 {errorpass = true;
						 firstPopUp=true;
						 System.out.println("got incorrect password....");
						 }
					 else
						 errorpass=false;
				} catch (org.openqa.selenium.NoSuchElementException e) {
					errorpass = false;
					firstPopUp=true;
					System.out.println("got stuck in first pop up...password thingy");
				}
				if(errorpass==true)
					observation="The password must be at least 6 characters";
				
				
				
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("firstPopUp2 :"+firstPopUp);
				if(firstPopUp==false) //work to be done in second pop-up
				{
				System.out.println("first pop up passed....got into second");
				WebElement firstname=driver.findElement(By.id("firstname"));
				if(firstname.isDisplayed())
					firstname.sendKeys(first);
				WebElement lastname=driver.findElement(By.id("lastname"));
				if(lastname.isDisplayed())
					lastname.sendKeys(last);
				WebElement phonenum=driver.findElement(By.id("phonenumber"));
				System.out.println(phone);
				if(phonenum.isDisplayed())
					phonenum.sendKeys(String.valueOf(phone));
				WebElement gen=driver.findElement(By.name("gender"));
				String g="male";
				if(gender.equalsIgnoreCase("F"))
					g="female";
				if(gen.isDisplayed())
					gen.sendKeys(g);
				WebElement dat=driver.findElement(By.name("dobdate"));
				if(dat.isDisplayed())
					dat.sendKeys(date);
				WebElement mon=driver.findElement(By.name("dobmonth"));
				if(mon.isDisplayed())
					mon.sendKeys(month);
				WebElement yea=driver.findElement(By.name("dobyear"));
				if(yea.isDisplayed())
					yea.sendKeys(year);
				WebElement agree=driver.findElement(By.name("agree"));
				if(agree.isDisplayed())
					agree.click();
				//WebElement registerButton=driver.findElement(By.cssSelector("button.btn:nth-child(5)"));
				WebElement registerButton=driver.findElement(By.xpath("//*[@id=\"myCarousel\"]/div[1]/div[2]/button"));
				if(registerButton.isDisplayed())
					registerButton.click();
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
					
					
				
				boolean accexists=false;
				try {
				   WebElement ee=driver.findElement(By.xpath("//*[@id=\"myCarousel\"]/div[1]/div[1]/div[1]/p[1]"));//acc already taken text
				   
				   if(ee.isDisplayed())
				   {System.out.println("exists has : "+ee.getText());
				   accexists = true;
				   }
				} catch (org.openqa.selenium.NoSuchElementException e) {
					//accexists = false;
					System.out.println("acc does not exist");
				}
				
				boolean present=false;
				try {
				   if(driver.findElement(By.className("modal-body")).isDisplayed())
					   present = true;
				} catch (org.openqa.selenium.NoSuchElementException e1) {
				   present = false;
				}
				
				if(present==true)
					observation="Credentials Entered are Wrong";
				if(accexists==true)
					observation="Account Already Exists";
				
				boolean signedin;
				try {
					driver.findElement(By.className("dropbtn2"));
					signedin = true;
				} catch (org.openqa.selenium.NoSuchElementException e1) {
					signedin = false;
				}
				if(signedin==true)
						observation="TM Account Created";
					
					
					
				System.out.println("Observation :"+observation);	
				
					
				 cell1=nextRow.createCell(colNum[9]);
					if(cell1!=null)
						cell1.setCellValue(observation);
					else
						System.out.println("Actual Report column not present!!");
					
				//------------------------------------------------------------------------------driver.close();
				
				}//script try
		catch(Exception e){
			e.printStackTrace();
			//-----------------------------------------------------------------------------------------driver.close();
		}
		 
		 
		 FileOutputStream outputStream = new FileOutputStream(new File(excelFilePath));
		 workbook.write(outputStream);
		 outputStream.close();

		
		 }//while
		 
		 
		 	
		 	
		 }//workbook try 
		 catch (IOException e1) {
				// TODO Auto-generated catch block
	        	//workbook.close();
				e1.printStackTrace();
			}
		 
 
		 
		 
		 workbook.close();
		 }//inputstream try 
	        catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
	        	
				e2.printStackTrace();
			}
		
		
	}

}
