package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ContactHelper  extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }


    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"),contactData.getFirstName());
        type(By.name("lastname"),contactData.getLastName() );
        type(By.name("email"),contactData.getEmail());

        if (creation){
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
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

    public void init() {
        click(By.linkText("add new"));
    }

    public void editContact(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }

    public void editContactById(int index) {
        wd.findElement(By.cssSelector("a[href='edit.php?id="+ index +"']")).click();
    }


    public void updateContact() {
        click(By.name("update"));
    }

    public void delete() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void create(ContactData contactData, boolean creation) {
        init();
        fillContactForm(contactData, creation);
        submitCreateContact();
        returnToContactPage();
    }

    public void modify(ContactData cont) {
        editContactById(cont.getId());
        fillContactForm(cont, false);
        updateContact();
        returnToContactPage();
    }

    public void delete(ContactData contact) {
        selectById(contact.getId());
        delete();
    }


    public void select(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectById(int index) {
        wd.findElement(By.cssSelector("input[value='"+index+"']")).click();
    }


    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']"));
        for(int i=1; i <= elements.size(); i++  ){
            String lastName = wd.findElement(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']["+i+"]/td[2]")).getText();
            String name = wd.findElement(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']["+i+"]/td[3]")).getText();
            int id = Integer.parseInt(wd.findElement(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']["+i+"]/td/input")).getAttribute("value"));
            ContactData c = new ContactData().withId(id).withFirstName(name).withLastName(lastName);
            contacts.add(c);
        }
        return contacts;
    }


    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<>();
        List<WebElement> elements = wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']"));
        for(int i=1; i <= elements.size(); i++  ){
            String lastName = wd.findElement(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']["+i+"]/td[2]")).getText();
            String name = wd.findElement(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']["+i+"]/td[3]")).getText();
            int id = Integer.parseInt(wd.findElement(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']["+i+"]/td/input")).getAttribute("value"));
            ContactData c = new ContactData().withId(id).withFirstName(name).withLastName(lastName);
            contacts.add(c);
        }
        return contacts;
    }

}
