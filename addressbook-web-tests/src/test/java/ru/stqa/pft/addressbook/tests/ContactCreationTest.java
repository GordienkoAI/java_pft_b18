package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;


import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class ContactCreationTest extends TestBase {

  @Test
  public void testCreateNewContact() throws Exception {
      Contacts before = app.contact().all();
      app.contact().init();
      ContactData cont  = new ContactData()
      .withFirstName("Name1").withLastName("lastName32").withEmail("mail32@mail.ru").withGroup("1test");
      app.contact().create(cont, true);
      Contacts after = app.contact().all();
      assertEquals(after.size(), before.size() + 1);

      assertThat(after, equalTo(before.withAdded(
              cont.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

}
