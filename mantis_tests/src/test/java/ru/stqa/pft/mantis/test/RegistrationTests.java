package ru.stqa.pft.mantis.test;


import biz.futureware.mantis.rpc.soap.client.IssueData;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.Project;

import javax.mail.MessagingException;
import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.List;


public class RegistrationTests extends TestBase {


    //   @BeforeMethod
    public void startRegistrationTests() throws RemoteException, ServiceException, MalformedURLException {
          app.mail().start();
    }


    @Test
    public void testRegistration() throws IOException, MessagingException, ServiceException {


        long now = System.currentTimeMillis();
        String email = String.format("user%slocalhost.localdomain", now);
        String user = String.format("user%s",now);
        String password = "password";
        app.james().createUser(user, password);
        //    app.registration().start(user, email);
        //    List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
       List<MailMessage> mailMessages = app.james().waitForMail(user, email, 60000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);
        Assert.assertTrue(app.newSession().login(user, password));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findAny().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }


//     @AfterMethod(alwaysRun = true)
    public void stopRegistrationTests() throws RemoteException, ServiceException, MalformedURLException {
        app.mail().stop();

    }
}
