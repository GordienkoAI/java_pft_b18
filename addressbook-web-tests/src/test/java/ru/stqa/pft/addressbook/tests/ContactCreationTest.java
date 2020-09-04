package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

  @Test
  public void testCreateNewContact() throws Exception {
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("Andrey", "Gordienko", "andruxagi@mail.ru"));
    app.getContactHelper().submitCreateContact();
    app.getContactHelper().returnToContactPage();
  }

}
