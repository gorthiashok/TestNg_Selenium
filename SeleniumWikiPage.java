package giveIndia.giveIndia.com;

 

import static org.testng.Assert.assertEquals;

 

import java.awt.Rectangle;

import java.awt.image.BufferedImage;

import java.io.File;

import java.io.IOException;

import java.nio.file.Files;

import java.util.Arrays;

import java.util.List;

import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;

 

import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.OutputType;

import org.openqa.selenium.Point;

import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeTest;

import org.testng.annotations.Test;

 

/**

*

 * @author ashok.kumar

*

*/

public class SeleniumWikiPage {

 

                public WebDriver driver;

 

                /**

                * @throws InterruptedException

                *

                 */

                @Test(priority = 1)

                public void verifySeleniumWikiPage() throws InterruptedException {

                                driver.findElement(By.xpath("(//*[contains(text(),'External links')])[1]")).click();

                                List<WebElement> ele = driver.findElements(By.xpath("//*[@id='mw-content-text']/div/ul[2]/li/a"));

 

                                System.out.println("--------------------------");

                                System.out.println("Total links Under External links Section " + ele.size());

                                System.out.println("--------------------------");

                                List<String> list = Arrays

                                                                .asList(new String[] { "Selenium Video - The Periodic Table of Videos - University of Nottingham",

                                                                                                "Supra-Regional Assay Service › Assays › Trace Elements › Selenium", "Web Page Blocked",

                                                                                                "ATSDR - Page Not Found | ATSDR", "CDC - NIOSH Pocket Guide to Chemical Hazards - Selenium",

                                                                                                "Web Page Blocked", "Selenium — Health Professional Fact Sheet","elements.vanderkrogt.net"});

                                for (int i = 0; i < ele.size(); i++) {

                                                ele = driver.findElements(By.xpath("//*[@id='mw-content-text']/div/ul[2]/li/a"));

                                                System.out.println("Clicking On ---->" + ele.get(i).getText());

                                                Thread.sleep(3000);

                                                ele.get(i).click();

                                                Thread.sleep(3000);

                                                String title = driver.getTitle();

                                                System.out.println(driver.getTitle());

                                                assertEquals(true, list.contains(title));

                                                // System.out.println(list.contains(driver.getTitle()));

                                                // assertEquals(driver.getTitle(), "Selenium Video - The Periodic Table of

                                                // Videos - University of Nottingham");

                                                // ele.get(i).getText();

 

                                                driver.navigate().back();

                                                driver.navigate().refresh();

 

                                }

 

                                // (//*[@title='Periodic table'])[3]

 

                }

 

                /**

                * 3. Click on the “Oxygen” link on the Periodic table at the bottom of the page

                */

                @Test(priority = 2)

                public void verifyOxygenLink() {

                                driver.findElement(By.xpath("(//*[@title='Periodic table'])[3]")).click();

                                driver.findElement(By.xpath("//*[@title='O, Oxygen']")).click();

 

                                System.out.println(driver.getTitle());

                                assertEquals(driver.getTitle(), "Oxygen - Wikipedia");

 

                }

 

                @BeforeTest

                public void beforeTest() {

                                // Browser launching

                                /**

                                *

                                 * 1.Open the page https://en.wikipedia.org/wiki/..

                                */

 

                                System.setProperty("webdriver.chrome.driver", "E:\\ccjar\\chromedriver_win32\\chromedriver.exe");

                                driver = new ChromeDriver();

                                driver.manage().window().maximize();

                                driver.get("https://en.wikipedia.org/wiki/Selenium");

                                System.out.println(driver.getTitle());

                                assertEquals(driver.getTitle(), "Selenium - Wikipedia");

 

                }

 

                @Test(priority = 3)

                public void isFeaturedArticle() {

                                driver.get("https://en.wikipedia.org/wiki/Selenium");

                                String expectedTooltip = "This is a featured article. Click here for more information";

                                WebElement element = driver.findElement(By.xpath("//*[@id='mw-indicator-good-star']/a"));

                                String act = element.getAttribute("title");

                                if (!expectedTooltip.equals(act)) {

 

                                                System.out.println("it is not a featured article ");

 

                                } else {

                                                System.out.println("it is  a featured article ");

                                }

                }

 

                @Test(priority = 4)

                public void takeElementProperties() throws IOException {

                                WebElement ele = driver.findElement(By.xpath("//*[@id='mw-content-text']/div/table[2]"));

                                /*

                                * int x=ele.getLocation().getX(); int y=ele.getLocation().getY(); int

                                * w=ele.getSize().getWidth(); int h=ele.getSize().getHeight();

                                */

 

                                // WebElement ele = driver.findElement(By.id("hplogo"));

 

                                // Get entire page screenshot

                                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", ele);

 

                                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

 

                                BufferedImage fullImg = ImageIO.read(screenshot);

 

                                // Get the location of element on the page

                                Point point = ele.getLocation();

 

                                // Get width and height of the element

 

                                int eleWidth = ele.getSize().getWidth();

                                int eleHeight = ele.getSize().getHeight();

                                Rectangle rect = new Rectangle(eleWidth, eleHeight);

 

                                System.out.println(eleHeight);

                                System.out.println(eleWidth);

                                BufferedImage img = null;

                                img = ImageIO.read(screenshot);

 

                                // BufferedImage dest = img.getSubimage(point.getX(), p.getY(), rect.width,

                                // rect.height);

                                // Crop the entire page screenshot to get only element screenshot

                                BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), 0, eleWidth, eleHeight / 3);

 

                                ImageIO.write(eleScreenshot, "png", screenshot);

 

                                /*

                                * // Copy the element screenshot to disk File screenshotLocation = new

                                * File("C:\\takeElementProperties.png");

                                * com.google.common.io.Files.copy(screenshot, screenshotLocation);

                                */

 

                                FileUtils.copyFile(screenshot, new File("D:/takeElementProperties.png"));

 

                }

 

                @Test(priority = 5)

                public void pdfLinks() {

 

                                driver.findElement(By.xpath("(//*[contains(text(),'References')])[1]")).click();

                                List<WebElement> pdflinks = driver.findElements(By.xpath("//*[@class='cs1-format']"));

                                System.out.println("No of pdf link in References -------->" + pdflinks.size());

                }

 

                @Test(priority = 6)

                public void verifyPlutonium() throws InterruptedException {

                                driver.get("https://en.wikipedia.org/wiki/Selenium");

                                driver.findElement(By.name("search")).sendKeys("pluto");

                                Thread.sleep(3000);

                                List<WebElement> plu = driver.findElements(By.xpath("//*[@class='mw-searchSuggest-link']/div"));

 

                                System.out.println(plu.size());

                                System.out.println(plu.get(1).getText());

                                assertEquals(plu.get(1).getText(), "Plutonium");

 

                }

 

                @AfterTest

                public void afterTest() {

                                driver.close();

                }

 

}

 
