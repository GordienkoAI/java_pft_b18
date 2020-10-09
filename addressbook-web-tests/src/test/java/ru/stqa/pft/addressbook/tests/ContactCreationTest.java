package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class ContactCreationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        File photo = new File("src/test/resources/imagJava.jpeg");
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
        String xml = "";
        String line = reader.readLine();
        while(line != null){
            xml += line;
            line = reader.readLine();
        }
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
        return  contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
    }

  @Test(dataProvider = "validContacts")
  public void testCreateNewContact(ContactData cont) throws Exception {
     Contacts before = app.contact().all();
      app.contact().init();
      app.contact().create(cont, true);
      Contacts after = app.contact().all();
      assertEquals(after.size(), before.size() + 1);

      assertThat(after, equalTo(before.withAdded(
              cont.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test(enabled =  false)
    public void testCurrentDir(){
        File currentDir = new File(".");
      System.out.println(currentDir.getAbsolutePath());
      File photo = new File("src/test/resources/imagJava.jpeg");
      System.out.println(photo.getAbsolutePath());
      System.out.println(photo.exists());

  }
}
