package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification(){
        List<ContactData> before = app.getContactHelper().getContactList();
        if (! app.getContactHelper().isContactAPresent()){
            app.getContactHelper().createContact(new ContactData("First", "MiddleName", "mail@mail.ru", "test1"), true);
        }
        app.getContactHelper().editContact(before.size() - 1);
        ContactData cont = new ContactData(before.get(before.size() -1).getId(),"First","MiddleName", "email", null);
        app.getContactHelper().fillContactForm(cont, false);
        app.getContactHelper().updateContact();
        app.getContactHelper().returnToContactPage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());


        before.remove(before.size() - 1);
        before.add(cont);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
