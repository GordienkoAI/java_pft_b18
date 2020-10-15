package ru.stqa.pft.addressbook.tests;

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
            app.group().create(new GroupData().withName("NewGroupName").withHeader("NewGroupHeader").withFooter("NewGroupFooter"));
        }
    }

    @Test
    public void contactAddInGroupTest(){
        Contacts before = app.db().contacts();
        Groups groups = app.db().groups();

        GroupData group = groups.iterator().next();
        ContactData contact = before.iterator().next();

        app.contact().addContactInGroup(contact, group);

        Contacts after = app.db().contacts();
        ContactData contAfter = after.stream().filter(c ->  //получаем обновленный контакт из базы
                Integer.toString(contact.getId()).equals(Integer.toString(c.getId()))).findAny().orElse(null);

/*      Groups afterGroup = app.db().groups();
        GroupData toGroup = afterGroup.stream().filter(g ->  //получаем группу из БД с обновленным списком контактов
                Integer.toString(group.getId()).equals(Integer.toString(g.getId()))).findAny().orElse(null);
*/

        assertThat(contact.inGroup(group).getGroups(), equalTo(contAfter.getGroups())); //проверяем обновленный кнотакт со значением из БД


  //      Assert.assertTrue(contAfter.getGroups().contains(group)); // проверяем что контакт содержит добавленную группу
  //      Assert.assertTrue(toGroup.getContacts().contains(contact)); //проверяем что группа содержит добавляемый контакт
  //      assertThat(contAfter, equalTo(toGroup.getContacts().contains(contact)));

    /*    assertThat(before, equalTo(after.stream()
                .map((g) -> new ContactData().withId(g.getId()).withLastName(g.getLastName()).withFirstName(g.getFirstName()).withEmail(g.getEmail()))
                .collect(Collectors.toSet())));
     */


    }
}
