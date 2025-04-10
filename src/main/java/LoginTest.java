import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginTest {
    private static final String BASE_URL = "https://www.intervue.io";
    private static final Duration TIMEOUT = Duration.ofSeconds(20);

    public static void main(String[] args) {
        WebDriver driver = null;
        try {
            driver = new SafariDriver();
            driver.manage().window().maximize();

            WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
            Actions actions = new Actions(driver);

            // 1. Open site
            driver.get(BASE_URL);
            System.out.println("Opened homepage: " + driver.getTitle());

            // 2. Hover for UX
            hoverElement(wait, actions, By.xpath("//span[@id='products-arrow']/parent::*"));
            Thread.sleep(1000);
            hoverElement(wait, actions, By.xpath("//span[@id='solutions-arrow']/parent::*"));
            Thread.sleep(1000);
            hoverElement(wait, actions, By.xpath("//span[@id='resources-arrow']/parent::*"));
            Thread.sleep(1000);

            String originalTab = driver.getWindowHandle();

            // 3. Click first login
            WebElement firstLogin = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[contains(text(), 'Login') or contains(@href, 'access-account')]")));
            firstLogin.click();

            // 4. Switch tab
            wait.until(driver1 -> driver1.getWindowHandles().size() > 1);
            for (String tab : driver.getWindowHandles()) {
                if (!tab.equals(originalTab)) {
                    driver.switchTo().window(tab);
                    break;
                }
            }
            // 5. Wait for /access-account
            wait.until(ExpectedConditions.urlContains("access-account"));

            // 6. Click second login
            WebElement secondLogin = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[contains(@href, '/login')]/div")));
            clickElement(actions, secondLogin);

            // 7. Wait for /login
            wait.until(ExpectedConditions.urlContains("/login"));

            // 8. Enter email
            WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("/html/body/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div/span/input")));
            emailInput.sendKeys("neha@intervue.io");

            // 9. Enter password
            WebElement passInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("/html/body/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[2]/div/div/span/input")));
            passInput.sendKeys("Ps@neha@123");

            // 10. Click login
            WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("/html/body/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[4]/div/div/span/button")));
            loginBtn.click();

            // 11. Wait for homepage/dashboard after login
            Thread.sleep(5000);

            // 12. Click search icon
            WebElement searchIcon = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("/html/body/div[2]/div/div[1]/div/div/div[1]/div/div[2]/div[1]/span/div[1]/span")));
            searchIcon.click();
            Thread.sleep(5000);
            // 13. Enter search keyword
            WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("/html/body/div[9]/div/div[2]/div/div[2]/div/div[1]/input")));
            searchBox.sendKeys("hello");
            Thread.sleep(1000);
            // 14. Click on search suggestion
            WebElement suggestion = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("/html/body/div[9]/div/div[2]/div/div[2]/div/div[2]/div")));
            suggestion.click();

            Thread.sleep(10000);

            // 15. Click profile icon
            WebElement profileIcon = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("/html/body/div[2]/div/div[1]/div/div/div[1]/div/div[2]/div[2]/div[1]/div/i")));
            profileIcon.click();
            Thread.sleep(1000);
            // 16. Click logout button
            WebElement logoutBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("/html/body/div[2]/div/div[1]/div/div/div[1]/div/div[2]/div[2]/div[2]/div/a[5]")));
            logoutBtn.click();

            wait.until(driver1 -> !driver1.getCurrentUrl().contains("/login"));
            System.out.println("Redirected to: " + driver.getCurrentUrl());

            Thread.sleep(10000);

            System.out.println("Successfully logged out!");

        } catch (Exception e) {
            System.err.println("Test failed: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    private static void hoverElement(WebDriverWait wait, Actions actions, By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        actions.moveToElement(element).perform();
    }

    private static void clickElement(Actions actions, WebElement element) {
        actions.moveToElement(element).click().perform();
    }
}

