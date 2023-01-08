import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SignUp extends Open_browser {

    CodeDesign design;
    //String log_in_email;
    //String sendpass ;



    @Test(priority = 0)
    public void Filldata() {
        design = new CodeDesign(driver);
        design.nav();

        Actions a = new Actions(driver);
        //first name
        //a.moveToElement(driver.findElement(By.name("first_name"))).click().keyDown(Keys.SHIFT).sendKeys("test").build().perform();

        WebElement first_name_wb = driver.findElement(By.name("first_name"));
        //a.moveToElement(first_name_wb).click().keyDown(Keys.SHIFT).build().perform();
        String fname = getSaltString();
        String fs1 = fname.substring(0, 1).toUpperCase();
        String fs2 = fname.substring(1);
        first_name_wb.sendKeys(fs1 + fs2);


        //last name
        WebElement last_name_wb = driver.findElement(By.name("last_name"));
        //a.moveToElement(last_name_wb).click().keyDown(Keys.SHIFT).build().perform();
        String lname = getSaltString();
        String ls1 = lname.substring(0, 1).toUpperCase();
        String ls2 = lname.substring(1);
        last_name_wb.sendKeys(ls1 + ls2);
        //last_name_wb.sendKeys("medo");

        //phone number
        WebElement phone_num_wb = driver.findElement(By.name("phone"));
        String phone_num_rand = getNumbString();
        phone_num_wb.sendKeys("0114" + phone_num_rand);

        //email
        WebElement email_wb = driver.findElement(By.name("email"));
        //use this email to log in
        String log_in_email = randomEmail();
        email_wb.sendKeys(log_in_email);
        System.out.println(log_in_email);


        //password
        WebElement password_wb = driver.findElement(By.name("password"));
        String pass = getSaltString();
        String ps1 = pass.substring(0, 1).toUpperCase();
        String ps2 = pass.substring(1, 2).toLowerCase();
        String ps3 = pass.substring(2);
        String sendpass = ps1 + ps2 + ps3;

        System.out.println(sendpass);

        password_wb.sendKeys(sendpass);


//        By dropdown_by= By.className("selection");
//
//        Select drop_down= new Select(driver.findElement(dropdown_by));
//        drop_down.selectByVisibleText("Agent");


//        driver.findElement(By.className("selection")).click();

        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofMinutes(3));


        //recaptcha can't be automated
//        wait1.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='reCAPTCHA']")));
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//        driver.findElement(By.xpath("//div[@class=\"rc-anchor-center-item rc-anchor-checkbox-holder\"]")).click();

        //driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

        wait1.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@id=\"button\"]"))));
        driver.findElement(By.xpath("//button[@id=\"button\"]")).click();


        wait1.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("email"))));
        driver.findElement(By.name("email")).sendKeys(log_in_email);


        wait1.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("password"))));
        driver.findElement(By.name("password")).sendKeys(sendpass);
    }
        @Test(priority = 1)
        public  void loginpage(){

            WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofMinutes(3));

            wait2.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[4]/div/div[2]/div[2]/div/form/div[2]/div[2]/div[1]"))));
            driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[4]/div/div[2]/div[2]/div/form/div[2]/div[2]/div[1]")).click();

        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//button[@class=\"btn btn-default btn-lg btn-block effect ladda-button waves-effect\"]")).click();

        String actual_pagetitle =driver.getTitle();
        System.out.println(actual_pagetitle);

        String expected_pagetitle = "Dashboard - PHPTRAVELS";

        SoftAssert soft ;
        soft = new SoftAssert();
        soft.assertEquals(actual_pagetitle,expected_pagetitle,"login successfully");
        System.out.println("login successfully");

        Assert.assertEquals(actual_pagetitle,expected_pagetitle,"login successfully");


    }
    @AfterMethod
    public void recordFailure(ITestResult result) throws IOException {

        if(ITestResult.FAILURE == result.getStatus())
        {
            var camera = (TakesScreenshot)driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            Files.move(screenshot, new File("resources/screenshots/" + result.getName() + ".png"));

//            try{
//                Files.move(screenshot, new File("resources/screenshots/" + result.getName() + ".png"));
//            }catch(IOException e){
//                e.printStackTrace();
//            }
        }
    }

    public String getSaltString()
    {
        String SALTCHARS = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 8) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    public String getNumbString()
    {
        String SALTCHARS = "0123456789";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 7)
        { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    public static String randomEmail() {
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }





}
