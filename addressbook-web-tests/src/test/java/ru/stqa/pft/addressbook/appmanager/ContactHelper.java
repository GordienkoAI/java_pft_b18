package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper  extends HelperBase {

    public ContactHelper(ChromeDriver wd) {
        super(wd);
    }


    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"),contactData.getFirstName());
        type(By.name("middlename"),contactData.getMiddleName() );
        type(By.name("email"),contactData.getEmail() );
    }


    public void returnToContactPage(String s) {
        click(By.linkText(s));
    }

    public void submitCreateContact(String submit) {
        click(By.name(submit));
    }

    public void initContactCreation(String s) {
        click(By.linkText(s));
    }
}
