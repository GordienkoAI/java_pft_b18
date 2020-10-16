package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
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
            app.goTo().gotoStartPage();
        }

        ContactData c = app.db().contacts().iterator().next();

        if(c.getGroups().size() == 0){
            GroupData g  = app.db().groups().iterator().next();
            app.contact().addContactInGroup(c,g);
        }
    }

    @Test
    public void contactDeleteFromGroupTest(){
        ContactData cont = app.db().contacts().iterator().next();
        GroupData g  = app.db().groups().iterator().next();

        if(cont.getGroups().size() == 0){                           // проверяем, что выбранный контакт входит в группу
            app.contact().addContactInGroup(cont,g);
            app.goTo().gotoStartPage();
        }
        app.selectGroup(cont.getGroups().iterator().next());       //выбираем группу на главном экране
        app.contact().deleteFromGroup(cont);                       //исключаем контакт из группы

        Contacts after = app.db().contacts();                       //загружаем новый список контактов из БД
        ContactData contAfter = after.stream().filter(c ->         //извлекаем из списка контакт который исключали
                Integer.toString(cont.getId()).equals(Integer.toString(c.getId()))).findAny().orElse(null);

        Assert.assertFalse(contAfter.getGroups().contains(g));   //проверяем наличие группы в группах контакта

    }
}
