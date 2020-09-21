package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;


public class ContactHelper  extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }


    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"),contactData.getFirstName());
        type(By.name("middlename"),contactData.getMiddleName() );
        type(By.name("email"),contactData.getEmail() );

        if (creation){
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(ContactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }


    public void returnToContactPage() {
        click(By.linkText("home page"));
    }

    public void submitCreateContact() {
        click(By.name("submit"));
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void editContact(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }

    public void updateContact() {
        click(By.name("update"));
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void createContact(ContactData contactData,boolean creation) {
        initContactCreation();
        fillContactForm(contactData, creation);
        submitCreateContact();
        returnToContactPage();
    }

    public boolean isContactAPresent() {
        return isElementPresent(By.xpath(("//table[@id='maintable']/tbody/tr/td/input[@type='checkbox']")));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }



    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']"));
        for(int i=1; i <= elements.size(); i++  ){
            String lastName = wd.findElement(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']["+i+"]/td[2]")).getText();
            String name = wd.findElement(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']["+i+"]/td[3]")).getText();
            ContactData c = new ContactData(null, lastName, name, null);
            contacts.add(c);
        }
        return contacts;
    }
}
