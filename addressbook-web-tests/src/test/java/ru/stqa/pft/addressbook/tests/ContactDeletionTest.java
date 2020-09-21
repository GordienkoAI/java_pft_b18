package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;


public class ContactDeletionTest extends TestBase {

    @Test
    public void testContactDeletion(){
        if (! app.getContactHelper().isContactAPresent()){
            app.getContactHelper().createContact(new ContactData("Andrey", "Gordienko", null, "test1"), true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(1);
        app.getContactHelper().deleteContact();
        app.confirmDeleteContact();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);
    }
}
