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
public class LoginTest {
	/**
	 * 
	 * entity login
	 * For testing the login with different set of input combinations
	 * 
	 * @param excelFilePath String File name which has data for input
	 * @throws IOException Child class has to throw exceptions
	 * @throws NullPointerException Child class has to throw exceptions 
	 * 
	 */
	
	public void loginTest(String excelFilePath, int workbookSheet)  throws IOException, NullPointerException {
		WebDriver driver=null;
		FileInputStream inputStream=null;
		Workbook workbook=null;
		String cellValue1=null,observation=null;
		 Cell cell1=null;
		
		
		 
		/**
		 * Reading the Input Sheet
		 */
		 try {
				inputStream = new FileInputStream(new File(excelFilePath));
				
		 
		 try {
				workbook = new XSSFWorkbook(inputStream);
			
		        Sheet firstSheet = workbook.getSheetAt(workbookSheet);
		        
		        
		        
		        /** 
		         * Getting the column index  by headings
		         */
		        Row firstRow = firstSheet.getRow(0);  
		        int colNum[]=new int[3];
		        for (int i = 0 ;i<=firstRow.getPhysicalNumberOfCells();i++){
		            cell1 = firstRow.getCell(i);
		            if(cell1!=null)
		            	cellValue1 = cell1.getStringCellValue();
		            else
		            	cellValue1="<cell is empty>";
		            if ("Username".equals(cellValue1)){
		            	 colNum[0]=cell1.getColumnIndex();
		            }
		            if ("Password".equals(cellValue1)){
		            	 colNum[1]=cell1.getColumnIndex();
		            }
		            
		            if ("Actual Report".equals(cellValue1)){
		            	 colNum[2]=cell1.getColumnIndex();
		            	 
		            }
		           }
		        
		       
		        
		        
		        
		        Iterator<Row> iterator = firstSheet.iterator();
		        if(iterator.hasNext())
		        	iterator.next();
		        else
		        	System.out.println("The excel is empty..!!");
		 
		 while (iterator.hasNext()){
	         
	        	Row nextRow = iterator.next();
	        	String user=nextRow.getCell(colNum[0]).getStringCellValue();
	        	String pass=nextRow.getCell(colNum[1]).getStringCellValue();
	        	
	            Iterator<Cell> cellIterator = nextRow.cellIterator();
	            

	            
	            
	            
		 
		 try {
				System.setProperty("webdriver.chrome.driver", "C:\\Softwares\\jars\\chromedriver_win32\\chromedriver.exe");
				driver = new ChromeDriver();
				//driver.navigate().to("http://www.yahoo.com");
				driver.get(("https://www.ticketmelon.com/"));
				WebElement ele=driver.findElement(By.linkText("SIGN IN"));
				ele.click();
				//System.out.println("after clicking : "+driver.getCurrentUrl());
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
				emailid.sendKeys(user);
				WebElement password=driver.findElement(By.id("exampleInputPassword1"));
				password.sendKeys(pass);
				WebElement submitButton=driver.findElement(By.id("submitButton1"));
				submitButton.click();
				try {
					Thread.sleep(6000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(driver.getPageSource().contains("Password Incorrect"))
					observation="EmailId or Password is incorrect";
				else
					{
					if(driver.findElement(By.className("dropbtn2")).isDisplayed())
						observation="Login Successful";
					else
						observation="Unknown Problem Occurred";
					}
					
					cell1=nextRow.createCell(colNum[2]);
					if(cell1!=null)
						cell1.setCellValue(observation);
					else
						System.out.println("Actual Report column not present!!");
					
				 
					
					
					
				driver.close();
				
				}//script try
		catch(Exception e){
			e.printStackTrace();
			driver.close();
		}
		
		 

		
		 }//while
		 
		 
		 	
		 	
		 }//workbook try 
		 catch (IOException e1) {
				// TODO Auto-generated catch block
	        	//workbook.close();
				e1.printStackTrace();
			}
		 
		 
		 inputStream.close();
		 
		 
		 //check if the file is open...then close it
		 Runtime.getRuntime().exec("TASKKILL /FI \"WINDOWTITLE eq Input_Data_Sheet - Excel\"");
		 
		 try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		 
		 
		 FileOutputStream outputStream = new FileOutputStream(new File(excelFilePath));
		 workbook.write(outputStream);
		 outputStream.close();
		 
		 workbook.close();
		 }//inputstream try 
	        catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
	        	
				e2.printStackTrace();
			}
	}
	

}
