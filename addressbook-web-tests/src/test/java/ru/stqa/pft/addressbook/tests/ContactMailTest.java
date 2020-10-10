package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactMailTest extends TestBase {

    @Test
    public void contactMailTest(){
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail1(), contact.getEmail2())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactMailTest::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String mail){
        return mail.replaceAll("\\s","");
    }
}
