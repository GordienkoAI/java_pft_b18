package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactDeletionTest extends TestBase {

    @Test
    public void testContactDeletion(){
        if (! app.getContactHelper().isContactAPresent()){
            app.getContactHelper().createContact(new ContactData("Andrey", "Gordienko", null, "test1"), true);
        }
        app.getNavigationHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.confirmDeleteContact();
    }
}
