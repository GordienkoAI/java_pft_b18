package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

  @Test
  public void testCreateNewContact() throws Exception {
      List<ContactData> before = app.getContactHelper().getContactList();
      app.getContactHelper().initContactCreation();
      ContactData cont  = new ContactData("Name1", "lastName32", "mail32@mail.ru", "1test");
      app.getContactHelper().createContact(cont, true);
      List<ContactData> after = app.getContactHelper().getContactList();
      Assert.assertEquals(after.size(), before.size() + 1);

      before.add(cont);
      Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
      before.sort(byId);
      after.sort(byId);
      Assert.assertEquals(before, after);
  }

}
