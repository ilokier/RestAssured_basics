package Configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class WebListener extends AbstractWebDriverEventListener {

    @Override
    public void beforeAlertAccept(WebDriver driver) {
        System.out.println("<<<<<<<<<<<<beforeAlert>>>>>>>>>>>>>>>>");
    }

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        System.out.println("<<<<<<<<<<<<beforeNavigateTo>>>>>>>>>>>>>>>>" + url);
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        System.out.println("<<<<<<<<<<<<afterNavigateTo>>>>>>>>>>>>>>>>" + url);
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        System.out.println("<<<<<<<<<<<<beforeClick>>>>>>>>>>>>>>>>" + element.getText());
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        System.out.println("<<<<<<<<<<<<afterClick>>>>>>>>>>>>>>>>" + element.getText());
    }

}
