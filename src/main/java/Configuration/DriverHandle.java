package Configuration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverHandle {
    private static Logger log = LoggerFactory.getLogger("BrowserEnvironment.class");
    private WebDriver driver;
    private String browserName;

    public WebDriver getDriver() {
        this.browserName = System.getProperty("browserName");
        log.info("Choosed browsername: " + browserName);
        switch (this.browserName) {
            case "chrome":
                driver = getChromeDriver();
                break;
            case "firefox":
                driver = getFireFoxDriver();
                break;
            case "ie":
                driver = getIeDriver();
                break;
            case "edge":
                driver = getEdgeDriver();
                break;
            default:
                driver = getChromeDriver();
        }

        return this.driver;
    }


    private WebDriver getChromeDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        chromeOptions.addArguments("start-maximized");
        return new ChromeDriver(chromeOptions);
    }

    private WebDriver getFireFoxDriver() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        WebDriverManager.firefoxdriver().setup();
        firefoxOptions.addArguments("start-maximized");
        return new FirefoxDriver(firefoxOptions);
    }

    private WebDriver getIeDriver() {
        InternetExplorerOptions ieOptions = new InternetExplorerOptions().ignoreZoomSettings();
        WebDriverManager.iedriver().setup();
        return new InternetExplorerDriver(ieOptions);
    }

    private WebDriver getEdgeDriver() {
        EdgeOptions edgeOptions = new EdgeOptions();
        WebDriverManager.edgedriver().setup();
        edgeOptions.addArguments("start-maximized");
        return new EdgeDriver(edgeOptions);
    }



}


