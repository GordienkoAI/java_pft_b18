package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.contact().list().size() == 0){
            app.contact().create(new ContactData()
                    .withFirstName("NameDel").withLastName("MiddleDel").withGroup("test1"), true);
        }
    }

    @Test
    public void testContactModification(){
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData cont = new ContactData()
        .withId(modifiedContact.getId()).withLastName("First").withFirstName("MiddleName").withEmail("email");
        app.contact().modify(cont);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(cont);
        Assert.assertEquals(before, after);
    }

}
