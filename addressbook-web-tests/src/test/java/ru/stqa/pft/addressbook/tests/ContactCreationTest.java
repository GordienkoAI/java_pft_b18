package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTest extends TestBase {

  @Test
  public void testCreateNewContact() throws Exception {
   //int befor = app.getContactHelper().getContactCount();
   List<ContactData> befor = app.getContactHelper().getContactList();
    app.getContactHelper().initContactCreation();
    app.getContactHelper().createContact(new ContactData("Andrey", "Gordienko", "andruxagi@mail.ru", "1test"), true);
    //int after = app.getContactHelper().getContactCount();
   List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), befor.size() + 1);
  }

}
