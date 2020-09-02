package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

  @Test
  public void testCreateNewContact() throws Exception {
    app.initContactCreation("add new");
    app.fillContactForm(new ContactData("Andrey", "Gordienko", "andruxagi@mail.ru"));
    app.submitCreateContact("submit");
    app.returnToContactPage("home page");
  }

}
