package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.*;

public class ContactModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().contacts().size() == 0){
            Groups groups = app.db().groups();
            app.contact().create(new ContactData()
                    .withFirstName("NameDel").withLastName("MiddleDel").inGroup(groups.iterator().next()), true);
        }
    }

    @Test
    public void testContactModification(){
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData cont = new ContactData()
        .withId(modifiedContact.getId()).withLastName("First").withFirstName("MiddleName").withEmail("email@mail.com").withAddress("New-York");
        app.contact().modify(cont);
        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size());
        verifyContactListInUI();
       // assertThat(after, equalTo(before.without(modifiedContact).withAdded(cont)));
    }

}
