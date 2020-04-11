package com.chinngyn.test;

import java.io.FileInputStream;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Chinngyn {

	public static void main(String args[]) throws InterruptedException, IOException {

		File excelfile = new File("C:\\Users\\HAP\\Desktop\\ChinnUserSays.xlsx");

		FileInputStream fis = new FileInputStream(excelfile);

		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowit = sheet.iterator();

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\HAP\\Desktop\\Tools\\Automation\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();

		driver.get(
				"https://toolassets.haptikapi.com/js-sdk/html/demoqp.html?business-id=1603&client-id=c80276cedc22c475c0168b4a9cec87be4ae125c4&api-key=chinngyn:7b25c148d2ffb0d146fbd2e065128b765dff5b20&base-url=https://staging.hellohaptik.com&debug=true&utm_source=googleAds");
		Thread.sleep(7000);

		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

		driver.switchTo().frame(0);
		System.out.println("****************moved to iframe");
		driver.findElement(By.xpath("//*[@id=\"my-app\"]/div[1]/span[3]")).click();
		driver.findElement(By.xpath("//*[@id=\"my-app\"]/div")).click();

		while (rowit.hasNext()) {

			Row row = rowit.next();
			Iterator<Cell> celliterator = row.cellIterator();

			while (celliterator.hasNext()) {

				Cell cell = celliterator.next();
				System.out.println(cell.toString());
				driver.findElement(By.xpath("//*[@id=\"composer-text-area\"]")).sendKeys(cell.toString());
				driver.findElement(By.xpath("//*[@id=\"composer-text-area\"]")).sendKeys(Keys.ENTER);
				Thread.sleep(5000);
			}

		}

		workbook.close();
		fis.close();
		// TODO Auto-generated method stub

	}

}
