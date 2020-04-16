package answers;

import answers.helpers.AnswersSeleniumHelpers;
import answers.pages.*;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AnswersIteration7 {

    private WebDriver driver;
    private AnswersSeleniumHelpers selenium = new AnswersSeleniumHelpers();

    @Before
    public void createBrowser() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void requestLoan_outsideLimits_shouldBeDenied() {

        new LoginPage(driver).
            loadAndLoginAs("john","demo");

        new AccountsOverviewPage(driver).
            selectMenuItem("Request Loan");

        new RequestLoanPage(driver).
            submitLoanRequest("10000","10","54321");

        Assert.assertEquals(
            "Denied",
            new RequestLoanResultPage(driver).getLoanApplicationResult()
        );
    }

    @After
    public void closeBrowser() {

        driver.quit();
    }
}
