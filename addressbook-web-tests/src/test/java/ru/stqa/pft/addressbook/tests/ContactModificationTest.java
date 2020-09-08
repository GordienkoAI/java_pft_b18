package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification(){
        if (! app.getContactHelper().isContactAPresent()){
            app.getContactHelper().createContact(new ContactData("Andrey", "Gordienko", "andruxagi@mail.ru", "test1"), true);
        }
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactForm(new ContactData("FirstName1","MiddleName", "email", null), false);
        app.getContactHelper().updateContact();
        app.getContactHelper().returnToContactPage();

    }
}
