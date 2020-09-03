package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;


public class ContactDeletionTest extends TestBase {

    @Test
    public void testContactDeletion(){
        app.getNavigationHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.confirmDeleteContact();
    }
}
