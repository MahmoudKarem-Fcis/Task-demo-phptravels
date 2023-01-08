import org.openqa.selenium.WebDriver;

public class CodeDesign {

    WebDriver driver ;

    public CodeDesign(WebDriver driver)
    {
        this.driver=driver;

    }

    public void nav()
    {
        driver.navigate().to("https://phptravels.net/signup");

    }


}

