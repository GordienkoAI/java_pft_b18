package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTest extends TestBase {

  @Test
  public void testCreateNewContact() throws Exception {
      Set<ContactData> before = app.contact().all();
      app.contact().init();
      ContactData cont  = new ContactData()
      .withFirstName("Name1").withLastName("lastName32").withEmail("mail32@mail.ru").withGroup("1test");
      app.contact().create(cont, true);
      Set<ContactData> after = app.contact().all();
      Assert.assertEquals(after.size(), before.size() + 1);

      cont.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
      before.add(cont);
      Assert.assertEquals(before, after);
  }

}
