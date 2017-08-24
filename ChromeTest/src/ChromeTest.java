/**
 * @author Astha Verma
 * 		  Ticketmelon.com
 */


import org.openqa.selenium.chrome.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.*;


public class ChromeTest {


	/**
	 * 
	 * @param args String[] default
	 * @throws NullPointerException Parent Class has to throw exceptions
	 * @throws IOException Parent Class has to throw exceptions
	 * 
	 * Give the file name here in variable excelFilePath
	 */
	public static void main(String[] args) throws NullPointerException, IOException {
		// TODO Auto-generated method stub
		
		
		String fileName = new SimpleDateFormat("yyyy--MM--dd-HH-mm'.log'").format(new Date());
		System.setOut(new PrintStream(new FileOutputStream("C:\\Users\\DELL\\workspace-neon\\ChromeTest\\Logs\\"+fileName)));
		System.out.println("\n/////////////////////////////////// - This is test output - ////////////////////////////////////////////\n");
		
		String excelFilePath = "C:\\Users\\DELL\\workspace-neon\\ChromeTest\\Input_Data_Sheet.xlsx";
		
		
		SignUpTest obj=new SignUpTest();
		LoginTest obj1=new LoginTest();
		FirstLook_TB obj2=new FirstLook_TB();
		
		
		
		//obj1.loginTest(excelFilePath,0);
		//obj.signUpTest("https://uat3.ticketmelon.com/",excelFilePath, 1);
		System.out.println("\n\n----------CHROME------------\n\n");
		obj2.firstLook_TB_Chrome(excelFilePath,2);
		System.out.println("\n\n----------FIREFOX------------\n\n");
		obj2.firstLook_TB_FF(excelFilePath,2);
//		System.out.println("\n\n----------SAFARI------------\n\n");
//		obj2.firstLook_TB_Safari(excelFilePath,2);


	}

			
		

}//class
