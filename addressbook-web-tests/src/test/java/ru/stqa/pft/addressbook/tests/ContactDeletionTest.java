package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactDeletionTest extends TestBase {

    @Test
    public void testContactDeletion(){
        int befor = app.getContactHelper().getContactCount();
        if (! app.getContactHelper().isContactAPresent()){
            app.getContactHelper().createContact(new ContactData("Andrey", "Gordienko", null, "test1"), true);
        }
        app.getContactHelper().selectContact(1);
        app.getContactHelper().deleteContact();
        app.confirmDeleteContact();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, befor - 1);
    }
}
