package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactDeleteFromGroupTest extends TestBase{


    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstName("NameDel").withLastName("MiddleDel").withAddress("Germany"), true);
            app.goTo().gotoHomePage();
        }

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("NewGroupName").withHeader("NewGroupHeader").withFooter("NewGroupFooter"));

        ContactData c = app.db().contacts().iterator().next();
        GroupData g  = app.db().groups().iterator().next();
        app.goTo().gotoStartPage();
        app.contact().addContactInGroup(c,g);
    }
    }

    @Test
    public void contactDeleteFromGroupTest(){
        ContactData cont = app.db().contacts().iterator().next();
        System.out.println(cont);
        System.out.println(cont.getGroups());

    }
}
