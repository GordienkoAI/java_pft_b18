package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification(){
        List<ContactData> befor = app.getContactHelper().getContactList();
        if (! app.getContactHelper().isContactAPresent()){
            app.getContactHelper().createContact(new ContactData("Andrey", "Gordienko", "andruxagi@mail.ru", "test1"), true);
        }
        app.getContactHelper().editContact(befor.size() - 1);
        app.getContactHelper().fillContactForm(new ContactData("Last","MiddleName", "email", null), false);
        app.getContactHelper().updateContact();
        app.getContactHelper().returnToContactPage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), befor.size());

    }
}
