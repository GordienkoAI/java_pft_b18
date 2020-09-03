package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification(){
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactForm(new ContactData("FirstName1","MiddleName", "email"));
        app.getContactHelper().updateContact();
        app.getContactHelper().returnToContactPage("home page");

    }
}
