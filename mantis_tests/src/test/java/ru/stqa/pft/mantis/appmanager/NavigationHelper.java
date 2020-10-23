package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;



public class NavigationHelper extends HelperBase {
    private ApplicationManager app;
    private String baseUrl;

    public NavigationHelper(ApplicationManager app) {
        super(app);
        baseUrl = app.getProperty("web.baseUrl");
    }



    public void login(String username, String password){
        wd.get(baseUrl +"/login_page.php");
        wd.findElement(By.id("username")).clear();
        wd.findElement(By.id("username")).sendKeys(username);
        wd.findElement(By.xpath("//input[@value='Войти']")).click();
        wd.findElement(By.id("password")).clear();
        wd.findElement(By.id("password")).sendKeys(password);
        wd.findElement(By.xpath("//input[@value='Войти']")).click();
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }


    public void resetPassword(int id) {
        wd.findElement(By.linkText(Integer.toString(id))).click();
        wd.findElement(By.xpath("//input[@value='Сбросить пароль']")).click();
    }

    public void goToManageUsers() {
        wd.get(baseUrl +"/manage_user_page.php");
    }
}
