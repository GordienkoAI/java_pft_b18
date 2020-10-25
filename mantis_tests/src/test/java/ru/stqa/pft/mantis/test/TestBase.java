package ru.stqa.pft.mantis.test;


import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;


public class TestBase {

    public static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    public  String testName;
    public  String testDescription;

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();

    //    app.ftp().upload(new File("src/test/resources/config_inc.php"),"config_inc.php", "config_inc.php.back");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
  //      app.ftp().restore("config_inc.php.back","config_inc.php" );
    }

   public boolean isIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException {
       IssueData issue = null;
       issue = app.soap().getIssue(issueId);
       String status = issue.getStatus().getName();
       return !status.equals("resolved");
   }


    public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

}