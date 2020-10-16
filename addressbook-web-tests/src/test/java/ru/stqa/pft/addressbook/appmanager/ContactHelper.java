package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper  extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }


    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"),contactData.getFirstName());
        type(By.name("lastname"),contactData.getLastName() );
        type(By.name("email"),contactData.getEmail());
        type(By.name("address"),contactData.getAddress());
//        attach(By.name("photo"), contactData.getPhoto());

        if (creation){
            if(contactData.getGroups().size() > 0){
                Assert.assertTrue(contactData.getGroups().size() == 1);
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
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

    public void removeFromGroup() {
        click(By.name("remove"));
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

    public void addContactInGroup(ContactData contact, GroupData group) {
        selectById(contact.getId());
        selectGroup(group);
        addToGroup();
        returnHomePage();
    }

    private void returnHomePage() {
        click(By.linkText("home"));
    }

    private void addToGroup() {
        wd.findElement(By.name("add")).click();
    }

    private void selectGroup(GroupData group) {
    }

    public void deleteFromGroup(ContactData contact) {
        selectById(contact.getId());
        removeFromGroup();
        returnHomePage();
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


    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']"));
        for(int i=1; i <= elements.size(); i++  ){
            int id = Integer.parseInt(wd.findElement(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']["+i+"]/td/input")).getAttribute("value"));
            String lastName = wd.findElement(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']["+i+"]/td[2]")).getText();
            String name = wd.findElement(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']["+i+"]/td[3]")).getText();
            String address = wd.findElement(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']["+i+"]/td[4]")).getText();
            String allEmails = wd.findElement(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']["+i+"]/td[5]")).getText();
            String allPhones = wd.findElement(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']["+i+"]/td[6]")).getText();
            ContactData c = new ContactData().withId(id).withFirstName(name).withLastName(lastName).withAddress(address)
                            .withAllPhones(allPhones).withEmails(allEmails);
            contacts.add(c);
        }
        return contacts;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");


        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(contact.getFirstName())
                .withLastName(contact.getLastName()).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
                .withEmail(email).withEmail1(email2).withEmail2(email3).withAddress(address);
    }

    private void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();

//     wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

}
