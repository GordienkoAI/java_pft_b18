package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification(){
        int befor = app.getContactHelper().getContactCount();
        if (! app.getContactHelper().isContactAPresent()){
            app.getContactHelper().createContact(new ContactData("Andrey", "Gordienko", "andruxagi@mail.ru", "test1"), true);
        }
        app.getContactHelper().editContact(befor - 1);
        app.getContactHelper().fillContactForm(new ContactData("Last","MiddleName", "email", null), false);
        app.getContactHelper().updateContact();
        app.getContactHelper().returnToContactPage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, befor);

    }
}
