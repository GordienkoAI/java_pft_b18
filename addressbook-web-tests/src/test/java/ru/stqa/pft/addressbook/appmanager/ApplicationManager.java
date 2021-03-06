package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.GroupData;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private final Properties properties;
    WebDriver wd;

    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private SessionHelper sessionHelper;
    private ContactHelper contactHelper;
    private String browser;
    private DbHelper dbHelper;


    public ApplicationManager(String browser){
        this.browser = browser;
        properties = new Properties();
    }


    public void init() throws IOException{
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        dbHelper = new DbHelper();

        if("".equals(properties.getProperty("selenium.server"))){
            if (browser.equals(BrowserType.CHROME)) {
                wd = new ChromeDriver();
            } else if (browser.equals(BrowserType.FIREFOX)) {
                wd = new FirefoxDriver();
            } else if (browser.equals(BrowserType.IE)) {
                wd = new InternetExplorerDriver();
            }
        } else {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(browser);
            capabilities.setPlatform(Platform.fromString(System.getProperty("target", "win10")));
            wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
        }


        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.baseUrl"));
        groupHelper= new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        contactHelper = new ContactHelper(wd);
        sessionHelper.login(properties.getProperty("web.adminLogin"),properties.getProperty("web.adminPassword"));
    }



    public void logout() {
       wd.findElement(By.linkText("Logout")).click();
    }

    public void stop() {
        wd.quit();
    }

    private boolean isElementPresent(By by) {
      try {
        wd.findElement(by);
        return true;
      } catch (NoSuchElementException e) {
        return false;
      }
    }


    public GroupHelper group() {
        return groupHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public ContactHelper contact() {
        return contactHelper;
    }

    public void confirmDeleteContact() {
        wd.switchTo().alert().accept();
        wd.findElement(By.linkText("home")).click();
    }

    public DbHelper db(){ return dbHelper; }


    public void selectGroup(GroupData group) {
    //    GroupData group = dbHelper.groups().iterator().next();
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(group.getName());
    }

    public byte[] takeScreenshot(){
       return ((TakesScreenshot) wd).getScreenshotAs(OutputType.BYTES);
    }
}
