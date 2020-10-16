package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddInGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().contacts().size() == 0){
            app.contact().create(new ContactData()
                    .withFirstName("NameDel").withLastName("MiddleDel").withAddress("Germany"), true);
        }

        if(app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("NewGroupName").withHeader("NewGroupHeader").withFooter("NewGroupFooter"));
        }
    }

    @Test
    public void contactAddInGroupTest(){
        Contacts before = app.db().contacts();
        Groups groups = app.db().groups();

        GroupData group = groups.iterator().next();   //получаем группу в которую будем добавлять контакт
        ContactData contact = before.iterator().next(); //получаем контакт для добавления

        int count = 0;

        while (contact.getGroups().contains(group)){    //проверяем что в группе нет указанного контакта
            group = groups.iterator().next();
            count++;
            if(count == groups.size()) {
                app.goTo().groupPage();
                app.group().create(new GroupData().withName("ExceptGroup " + count).withHeader("NewGroupHeader").withFooter("NewGroupFooter"));
                app.goTo().gotoStartPage();
                break;
            }
        }

        app.contact().addContactInGroup(contact, group);  // добавление контакта в группу
        Contacts after = app.db().contacts();
        ContactData contAfter = after.stream().filter(c ->    //получаем обновленный контакт из базы
                Integer.toString(contact.getId()).equals(Integer.toString(c.getId()))).findAny().orElse(null);

        Assert.assertTrue(contAfter.getGroups().contains(group));  //проверяем что контакт содержит группу в которую он добавлен

/*      Groups afterGroup = app.db().groups();
        GroupData toGroup = afterGroup.stream().filter(g ->  //получаем группу из БД с обновленным списком контактов
                Integer.toString(group.getId()).equals(Integer.toString(g.getId()))).findAny().orElse(null);
*/

 //     assertThat(contact.inGroup(group).getGroups(), equalTo(contAfter.getGroups())); //проверяем обновленный кнотакт со значением из БД


  //      Assert.assertTrue(contAfter.getGroups().contains(group)); // проверяем что контакт содержит добавленную группу
  //      Assert.assertTrue(toGroup.getContacts().contains(contact)); //проверяем что группа содержит добавляемый контакт
  //      assertThat(contAfter, equalTo(toGroup.getContacts().contains(contact)));

    /*    assertThat(before, equalTo(after.stream()
                .map((g) -> new ContactData().withId(g.getId()).withLastName(g.getLastName()).withFirstName(g.getFirstName()).withEmail(g.getEmail()))
                .collect(Collectors.toSet())));
     */


    }
}
