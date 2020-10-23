package ru.stqa.pft.mantis.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import java.util.List;


public class ChangePasswordMantisTest extends TestBase {
    private String username;
    private String password;


    @Test
    public void testUntitledTestCase() throws Exception {
        username = app.getProperty("web.adminLogin");
        password = app.getProperty("web.adminPassword");
        String userPassword = "userPassword";

        app.navigateHelper().login(username, password);

        Users user = app.db().users();
        UserData modifyUser = user.iterator().next();

        while (modifyUser.getUserName() == username) {
            modifyUser = user.iterator().next();
        }

        if(!app.james().doesUserExist(modifyUser.getUserName())){
            app.james().createUser(modifyUser.getUserName(), userPassword);
        }

        app.navigateHelper().goToManageUsers();
        app.navigateHelper().resetPassword(modifyUser.getId());

        List<MailMessage> mailMessages = app.james().waitForMail(modifyUser.getUserName(), modifyUser.getEmail(), 60000);
        String confirmationLink = findConfirmationLink(mailMessages, modifyUser.getEmail());
        app.registration().finish(confirmationLink, userPassword);
        Assert.assertTrue(app.newSession().login(modifyUser.getUserName(), userPassword));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findAny().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }
}

